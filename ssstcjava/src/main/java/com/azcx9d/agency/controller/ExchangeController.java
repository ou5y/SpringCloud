package com.azcx9d.agency.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azcx9d.agency.base.HQBaseController;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.agency.entity.CustomBill;
import com.azcx9d.agency.entity.Exchange;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.agency.service.ExchangeService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Arith;
import com.azcx9d.common.util.CalendarUtil;
import com.azcx9d.common.util.Page;
import com.azcx9d.user.entity.User;
import com.azcx9d.user.service.UserApiService;
import com.mysql.jdbc.StringUtils;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "兑换中心",description="代理商兑换中心")
@RestController
@RequestMapping("/agApi/exchange")
public class ExchangeController extends HQBaseController{
	
	@Autowired
	private ExchangeService exchangeService;
	
	@Autowired
	private UserApiService userApiService;
	
	@ApiOperation(value="查询我的善点",notes = "查询我的善点<br/>enable:可兑换<br/>unable:不可兑换")
	@RequestMapping(value="/queryMyShandian/v1",method = RequestMethod.GET)
	public JsonResult myShandian(@RequestHeader("token") String token){
		try {
			TokenModel model= getTokenModel();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			Map<String,Object> myShandian = exchangeService.queryMyShandian(params);
//			Map<String,Object> shanxin = exchangeService.queryLovePercent();
//			myShandian.put("unable", Arith.mul((double)myShandian.get("shandian"),0.2,2));
//			if(shanxin!=null &&shanxin.size()>0){
//				myShandian.put("shanxin", shanxin.get("percent"));
//			}else{
//				myShandian.put("shanxin", 0.0);
//			}
			return new JsonResult(0, "查询我的善点成功",myShandian);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "计算兑换金额",notes = "算出实际可到账金额<br/>arrivalMode可取值1:T+1、3:T+3、7:T+7")
	@RequestMapping(value = "/getRealMoney/v1",method = RequestMethod.GET)
	public JsonResult calcShandian(@RequestHeader("token") String token,
			@ApiParam(required=true,value="兑换善点数") @RequestParam double shandian,
			@ApiParam(required=true,value="到账方式,可取值1:T+1、3:T+3、7:T+7") @RequestParam int arrivalMode,
			@ApiParam(required=true,value="类型,0:主动善点   1:被动善点，默认0") @RequestParam int type){
		try {
			TokenModel model= getTokenModel();
			if(shandian>=100 && shandian%100==0){
				Map<String,Object> params = new HashMap<String, Object>();
				params.put("exchangeShandian", shandian);
				params.put("userId", model.getUserId());
				params.put("arrivalMode", arrivalMode);
				Map<String,Object> result = exchangeService.calcShandian(params,type);
				if(result!=null && result.size()>0){
					return new JsonResult(0, "计算可兑换金额成功",result);
				}else{
					return new JsonResult(1, "善点或积分不足");
				}
			}else{
				return new JsonResult(1, "兑换善点只能是100的整数倍");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "添加兑换记录",notes = "保存兑换数据到数据库",response=Exchange.class)
	@RequestMapping(value = "/exchange/v1",method = RequestMethod.POST)
	public synchronized JsonResult addExchangeRecord(@RequestHeader("token") String token,
			@ApiParam(required=true,value="银行卡号Id") @RequestParam String bankId,
			@ApiParam(required=true,value="善点") @RequestParam double shandian,
			@ApiParam(required=true,value="手续费") @RequestParam double poundage,
			@ApiParam(required=true,value="可到账金额") @RequestParam double realMoney,
			@ApiParam(required=true,value="到账方式") @RequestParam int arrivalMode,
			@ApiParam(required=true,value="类型,0:主动善点   1:被动善点，默认0") @RequestParam int type){
		
		try {
			TokenModel model= getTokenModel();
			if(StringUtils.isNullOrEmpty(bankId))	return new JsonResult(1, "请先绑定银行卡");
			if(shandian>50000)			return new JsonResult(1, "单笔兑换不可超过50000善点");
			if(shandian<100 || shandian%100!=0) return new JsonResult(1, "兑换善点只能是100的整数倍");
			if(CalendarUtil.getDay()>25)	return new JsonResult(1, "善点仅每月1-25可兑换");
			
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("bankId", bankId);
			params.put("exchangeShandian", shandian);
			params.put("userId", model.getUserId());
			params.put("poundage", poundage);
			params.put("tax", 0.0);
			params.put("realMoney", realMoney);
			params.put("arrivalMode", arrivalMode);
			params.put("type", type);
			
			
			BankCard  bankCard = userApiService.findById(Long.valueOf(bankId));
			if(bankCard==null)	return new JsonResult(1, "银行卡不存在");
			params.put("bankCardNo", bankCard.getBankCardNo());
			params.put("bankName", bankCard.getBankName());
			params.put("cardHolder", bankCard.getCardHolder());
			Map<String,Object> myShandian = exchangeService.queryMyShandian(params);
			double oldShandian = 0.0;
			if(type==0){
				params.put("leixing", 0);  		//主动善点
				oldShandian = (double)myShandian.get("shandian");
			}else{
				params.put("leixing", 3);  		//被动善点
				oldShandian = (double)myShandian.get("shandian2");
			}
			if(shandian>oldShandian) return new JsonResult(1, "善点余额不足");
			int count = exchangeService.saveExchange(params);
			if(count>0){
				double current = oldShandian-shandian;
				if(type==0){
					myShandian.put("shandian", current);
					myShandian.put("shandian2", myShandian.get("shandian2"));
					myShandian.put("jifen", Arith.sub((double)myShandian.get("jifen"),shandian));
				}else{
					myShandian.put("shandian", myShandian.get("shandian"));
					myShandian.put("shandian2", current);
				}
				return new JsonResult(0, "兑换成功",myShandian);
			}else{
				return new JsonResult(1, "兑换失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "累计兑换金额",notes = "查询累计兑换金额",response=Exchange.class)
	@RequestMapping(value = "/totalExchange/v1",method = RequestMethod.GET)
	public JsonResult queryExchangeList(@RequestHeader("token") String token){
		try {
			TokenModel model= getTokenModel();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			
			double total =  exchangeService.countTotalConversion(params);
			Map<String,Object> result = new HashMap<String, Object>();
			result.put("total", total);
			return new JsonResult(0, "查询累计兑换金额成功", result);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "查询兑换记录列表",notes = "查询兑换记录列表",response=Exchange.class)
	@RequestMapping(value = "/exchangeList/v1",method = RequestMethod.GET)
	public JsonResult queryExchangeList(@RequestHeader("token") String token,
			@ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
			@ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize){
		
		try {
			TokenModel model= getTokenModel();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			params.put("pageSize", pageSize);
			
			Page page = new Page(currentPage,pageSize);
			page = exchangeService.queryExchangeList(page,params);
			page.init();
			return new JsonResult(0, "查询兑换记录列表成功", page);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value="查询兑现记录详情",notes="兑换记录详情",response=Exchange.class)
	@RequestMapping(value="/queryExcDetail/v1",method = RequestMethod.GET)
	public JsonResult queryDetail(@RequestHeader("token") String token,
			@ApiParam(required=true,value="id") @RequestParam long id){
		try {
			TokenModel model= getTokenModel();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			params.put("id", id);
			
			Exchange exchange = exchangeService.queryDetail(params);
			if(exchange!=null){
				return new JsonResult(0, "查询兑换记录明细成功", exchange);
			}else{
				return new JsonResult(1, "查询兑换记录明细失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value="查询善点变动明细",notes = "查询善点变动明细",response = CustomBill.class)
	@RequestMapping(value="/queryShandianList/v1",method = RequestMethod.GET)
	public JsonResult queryBalance(@RequestHeader("token") String token,
			@ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
			@ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize,
			@ApiParam(required=true,value="类型,0=善点  1=积分  2=善心  3=被动善点") @RequestParam int leixing){
		
		try {
			TokenModel model= getTokenModel();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			params.put("pageSize", pageSize);
			params.put("leixing", leixing);
			
			Page page = new Page(currentPage,pageSize);
			
			page = exchangeService.queryBalanceDetail(page,params);
			page.init();
			return new JsonResult(0, "查询善点明细列表成功", page);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value="我要兑换",notes="跳转到我要兑换页面<br/>shandian:剩余善点<br/>enable:可兑换<br/>unable:不可兑换")
	@RequestMapping(value="/toExchange/v1",method = RequestMethod.GET)
	public JsonResult toExchange(@RequestHeader("token") String token){
		
		try {
			TokenModel model= getTokenModel();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			
			User user = userApiService.queryUserInfo(model.getUserId());
			Map<String,Object> myShandian = exchangeService.queryMyShandian(params);
			List<BankCard> lists = userApiService.queryMyBankCard(model.getUserId());
			if(myShandian!=null && myShandian.size()>0){
				myShandian.put("lists", lists);
			}
			return new JsonResult(0, "查询我的善点成功",myShandian);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value="历史收益上部统计",notes="历史收益,显示当前善点、累计善点及详细信息")
	@RequestMapping(value="/getIncomeCount/v1",method=RequestMethod.POST)
	public JsonResult allIncomeCount(@RequestHeader("token") String token){
		try {
			TokenModel model= getTokenModel();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			Double total = exchangeService.countTotalConversion(params);
			User user = userApiService.queryUserInfo(model.getUserId());
			double currentShandian = user.getShandian();
			total += currentShandian;
			
			Map<String,Object> datas = new HashMap<String, Object>();
			datas.put("totalShandian", total);
			datas.put("currentShandian", currentShandian);
			return new JsonResult(0, "查询数据成功", datas);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	
	@ApiOperation(value="历史收益数据列表",notes="历史收益,显示当前善点、累计善点及详细信息<br/>",response=Exchange.class)
	@RequestMapping(value="/getAllIncome/v1",method=RequestMethod.GET)
	public JsonResult incomeList(@RequestHeader("token") String token,
			@ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
			@ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize,
			@ApiParam(required=false,value="开始日期,时间戳10位") @RequestParam(required = false) Long startDate,
			@ApiParam(required=false,value="结束日期,时间戳10位") @RequestParam(required = false) Long endDate){
		try {
			TokenModel model= getTokenModel();
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			params.put("state", 1);
			params.put("pageSize", pageSize);
			
			if(startDate!=null && endDate!=null){
				params.put("startDate", CalendarUtil.formatDateByString(new Date(startDate*1000)));
				params.put("endDate", CalendarUtil.formatDateByString(new Date(endDate*1000+(24*60*60*1000))));
			}else if((startDate==null && endDate!=null)||(startDate!=null && endDate==null)){
				return new JsonResult(1,"开始日期或结束日期未选择");
			}
			
			Page page = new Page(currentPage,pageSize);
			page = exchangeService.queryExchangeList(page,params);
			page.init();
			return new JsonResult(0,"查询历史收益成功",page);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}

}
