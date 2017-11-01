package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.*;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.exception.BusinessException;
import com.azcx9d.business.service.BExchangeService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.CalendarUtil;
import com.azcx9d.common.util.Page;
import com.mysql.jdbc.StringUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenxl on 2017/4/7 0007.
 */
@Api(value = "商户兑换中心",description="商户兑换中心")
@RestController
@RequestMapping("/v1/bApi/exchange")
public class BExchangeApiController extends HQBaseController {
    @Autowired
    private BExchangeService bExchangeService;

    @ApiOperation(value = "是否可兑换",notes = "查询累计兑换金额", response=IsExchangeDto.class)
    @RequestMapping(value = "/getIsExchange",method = RequestMethod.GET)
    public JsonResult getIsExchange(@RequestHeader("token") String token){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            IsExchangeDto isExchangeDto = bExchangeService.getIsExchange(paraMap);
            if(null != isExchangeDto){
                return new JsonResult(0,"查询成功！", isExchangeDto);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "累计兑换金额",notes = "查询累计兑换金额", response=TotalExchangeDto.class)
    @RequestMapping(value = "/totalExchange",method = RequestMethod.GET)
    public JsonResult getTotalExchange(@RequestHeader("token") String token){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            TotalExchangeDto totalExchangeDto = bExchangeService.getTotalExchange(paraMap);
            if(null != totalExchangeDto){
                return new JsonResult(0,"查询成功！", totalExchangeDto);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "兑换记录列表",notes = "兑换记录列表",response=ExchangeRecordDto.class)
    @RequestMapping(value = "/getExchangeList",method = RequestMethod.GET)
    public JsonResult getExchangeList(@RequestHeader("token") String token,
                                      @ApiParam(required=true,value="兑换类型 0:主动善点(默认), 1:推荐奖励善点",defaultValue="0") @RequestParam("type") int type,
                                      @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                      @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("pageSize", pageSize);
            Page page = new Page(currentPage,pageSize);

            page = bExchangeService.getExchangeList(page, paraMap);;
            page.init();
            if(page.getPageList().size() > 0){
                return new JsonResult(0,"查询成功！", page);
            }else {
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "兑换详情",notes = "兑换详情",response=ExchangeDetailDto.class)
    @RequestMapping(value = "/getExchangeDetail",method = RequestMethod.GET)
    public JsonResult getExchangeDetail(@RequestHeader("token") String token,
                                        @ApiParam(required=true,value="兑换记录id") @RequestParam("exchangeId") int exchangeId){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());

            ExchangeDetailDto exchangeDetailDto = bExchangeService.getExchangeDetail(paraMap);
            if(null != exchangeDetailDto){
                return new JsonResult(0,"查询成功！", exchangeDetailDto);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value="进入我要兑换",notes="跳转到我要兑换页面", response=ExchangeShandianDto.class)
    @RequestMapping(value="/toExchange",method = RequestMethod.GET)
    public JsonResult toExchange(@RequestHeader("token") String token){

        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());

            ExchangeShandianDto exchangeShandianDto = bExchangeService.toExchange(paraMap);
            if(null != exchangeShandianDto){
                return new JsonResult(0,"查询成功！", exchangeShandianDto);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "计算实际可到账金额",notes = "计算实际可到账金额", response=ExchangeMoneyDto.class)
    @RequestMapping(value = "/getRealMoney",method = RequestMethod.GET)
    public JsonResult getRealMoney(@RequestHeader("token") String token,
                                   @ApiParam(required=true,value="兑换善点") @RequestParam("shandian") String shandian,
                                   @ApiParam(required=true,value="到账方式,可取值1:T+1、3:T+3、7:T+7") @RequestParam("arrivalMode") int arrivalMode,
                                   @ApiParam(required=true,value="兑换类型 0:主动善点(默认), 1:推荐奖励善点",defaultValue="0") @RequestParam("type") int type){
        try {
            if(Double.parseDouble(shandian) >= 100 && Double.parseDouble(shandian)%100 == 0){
                ParaMap paraMap = super.getParaMap();
                paraMap.put("userId", super.getUserId());

                ExchangeMoneyDto exchangeMoneyDto = bExchangeService.getRealMoney(paraMap);
                if(null != exchangeMoneyDto){
                    return new JsonResult(0,"查询成功！", exchangeMoneyDto);
                }else{
                    if(type==0){
                        return new JsonResult(2, "鼓励点或积分余额不足!");
                    }else{
                        return new JsonResult(2, "奖励点余额不足!");
                    }
                }
            }else {
                return new JsonResult(2, "只能兑换100的整数倍!");
            }
        } catch (BusinessException be) {
            return new JsonResult(2, be.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "添加兑换记录",notes = "添加兑换记录",response=ExchangedShandianDto.class)
    @RequestMapping(value = "/addExchange",method = RequestMethod.POST)
    public JsonResult addExchange(@RequestHeader("token") String token,
                                  @ApiParam(required=true,value="银行卡号Id") @RequestParam("bankId") String bankId,
                                  @ApiParam(required=true,value="善点") @RequestParam("shandian") String shandian,
                                  @ApiParam(required=true,value="手续费") @RequestParam("poundage") String poundage,
                                  @ApiParam(required=true,value="可到账金额") @RequestParam("sjdk") String sjdk,
                                  @ApiParam(required=true,value="到账方式") @RequestParam("arrivalMode") int arrivalMode,
                                  @ApiParam(required=true,value="0:兑换主动善点(默认),1:兑换推荐奖励善点",defaultValue="0") @RequestParam("type") int type){

//        return new JsonResult(2,"抱歉！兑换系统调整中，暂时不可兑换");

        try {
            if (StringUtils.isNullOrEmpty(bankId)) {
                return new JsonResult(2, "请先绑定银行卡!");
            }
//            if (Double.parseDouble(shandian) > 50000) {
//                return new JsonResult(2, "单笔兑换不可超过50000!");
//            }
            if (!(Double.parseDouble(shandian) >= 100 && Double.parseDouble(shandian) % 100 == 0)) {
                return new JsonResult(2, "只能兑换100的整数倍!");
            }
//            if (!(CalendarUtil.getDay() <= 25)) {
//                return new JsonResult(2, "仅每月1-25日可兑换!");
//            }
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());

            ExchangeBankcardDto bankCard = bExchangeService.getBankDetail(paraMap);
            if (bankCard == null) {
                return new JsonResult(2, "银行卡不存在!");
            }
            paraMap.put("bankCardNo", StringUtils.isNullOrEmpty(bankCard.getBankCardNo()) ? "" : bankCard.getBankCardNo());
            paraMap.put("bankName", StringUtils.isNullOrEmpty(bankCard.getBankName()) ? "" : bankCard.getBankName());
            paraMap.put("cardHolder", StringUtils.isNullOrEmpty(bankCard.getCardHolder()) ? "" : bankCard.getCardHolder());

            ExchangedShandianDto exchangedShandianDto = bExchangeService.saveExchange(paraMap);
            if (exchangedShandianDto != null) {
                return new JsonResult(0, "兑换成功!", exchangedShandianDto);
            } else {
                return new JsonResult(0, "兑换失败!");
            }
        } catch (BusinessException be) {
            logger.error(be.getMessage());
            return new JsonResult(be.getCode(), be.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

}
