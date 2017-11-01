package com.azcx9d.user.controller;


import java.io.IOException;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.SendMessage.MobileServerUtils;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.token.TokenManagerDao;
import com.azcx9d.common.util.AliyunBankUtils;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.user.entity.User;
import com.azcx9d.user.entity.YunpianReaultEntity;
import com.azcx9d.user.service.RegionService;
import com.azcx9d.user.service.UserApiService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
@Api(value = "用户", description = "用户信息管理")
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {


	@Autowired
	private UserApiService userApiService;
	@Autowired
	private TokenManagerDao tokenDao;
	
	@ApiOperation(notes = "登录", value = "登录",response = Agency.class)
	@RequestMapping(value = "/login/v1",method=RequestMethod.POST)
	public LoginExecution<Agency> login(@ApiParam(required =true, value = "手机号码") @RequestParam String phone,
			@ApiParam(required=true,value="密码") @RequestParam String password) {
		
			LoginExecution<Agency> execution;
			try {  
				Agency agency = new Agency();
				agency.setPhone(phone);
				agency.setPassword(password);
				execution = userApiService.login(agency);
			} catch (Exception e) {
				logger.error(e.getMessage());
				return execution = new LoginExecution(500,"请求超时或者服务器出错啦，稍后再试试~~！");
			}
			return execution;
		
		
	}

//	@ApiOperation(notes = "退出",value = "注销")
//	@RequestMapping(value = "/logout", method = RequestMethod.DELETE)
//	@ApiIgnore
//	public JsonResult logout() {
//		String token = request.getHeader("token");
//		TokenModel model=tokenDao.getToken(token);
//
//		if(!tokenDao.checkToken(model))
//			return new JsonResult(1,"账号身份已过期，请重新登录");
//
//		tokenDao.deleteToken(model.getToken());
//		return new JsonResult(0,"ok");
//	}
	
	@ApiOperation(notes = "用户详情",value = "用户详细信息")
	@RequestMapping(value = "/detail/v1", method = RequestMethod.GET)
	public JsonResult<Agency> getUserInfo(@RequestHeader("token") String token) {
		TokenModel model=tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult(1,"账号身份已过期，请重新登录");
		Agency agency;
		try {
			agency = userApiService.queryUserInfo(model.getUserId());
			agency.setShandian((int)agency.getShandian());
			agency.setShandian2((int)agency.getShandian2());
			if(agency==null){
				return new JsonResult(1,"没有找到该用户");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
		JsonResult<Agency> result = new JsonResult<Agency>(agency);
		return result;
	}
	
	@ApiOperation(notes = "获取验证码",value = "获取验证码")
	@RequestMapping(value = "/{phone}/getCode/v1", method = RequestMethod.GET)
	public JsonResult identifyingCode(@PathVariable("phone") String phone){
		
			YunpianReaultEntity yre;
			try {
				yre = userApiService.identifyingCode(phone);
				if(yre.getCode()!=0)
				{
					return new JsonResult(yre.getCode(),yre.getMsg());
				}else
				{
					return new JsonResult(0,"OK");
				}
			} catch (ParseException e) {
				logger.error(e.getMessage());
				return new JsonResult(500,"服务器出错啦");
			} catch (IOException e) {
				logger.error(e.getMessage());
				return new JsonResult(500,"网络不太好，稍后在试试");
			}catch (Exception e) {
				logger.error(e.getMessage());
				return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
			}
			
			
	}
	
	@ApiOperation(notes = "校验验证码",value = "校验验证码")
	@RequestMapping(value = "/{phone}/{code}/checkCode/v1", method = RequestMethod.GET)
	public JsonResult checkCode(@PathVariable("phone")String phone,@PathVariable("code")int code) 
	{
		
		if (MobileServerUtils.checkCode(phone, code)) {
			return new 	JsonResult(0,"ok");
		}
		return new 	JsonResult(1,"failed");
			
			
		
	}
	
	
//	@ApiOperation(notes="注册",value="注册")
//	@RequestMapping(value = "/{user}/register", method = RequestMethod.POST)
//	
//	public JsonResult register(@PathVariable("password") String password,
//								@PathVariable("phone") String phone,
//								@PathVariable("code") String code)
//	{
////		loginApiService.doRegister(user);
//		return null;
//	}
	
	@ApiOperation(value = "我的银行卡", notes = "查询当前登录用户的全部银行卡")
	@RequestMapping(value = "/myBankCard/v1", method = RequestMethod.GET)
	public JsonResult<Object> queryBankCard(){
		JsonResult<Object> result = null;
		String token = request.getHeader("token");
		TokenModel model = tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		List<BankCard> lists = null;
		try {
			lists = userApiService.queryMyBankCard(model.getUserId());
		
			result =  new JsonResult<Object>(0,"查询我的银行卡成功",lists);
		} catch (Exception e) {
			logger.error(e.getMessage());
			result = new JsonResult<Object>(500,"服务器出错啦");
		}
		return result;
	}
	
	@ApiOperation(value = "添加银行卡", notes = "新增银行卡")
	@RequestMapping(value = "/addBankCard/v1",method = RequestMethod.POST)
	public JsonResult<Object> addBankCard(
			@RequestHeader("token") String token,
			@ApiParam(required=true,value="银行卡号") @RequestParam String bankCardNo,
			@ApiParam(required=true,value="姓名") @RequestParam String name,
			@ApiParam(required=true,value="身份证号码") @RequestParam String identifyCard,
			@ApiParam(required=true,value="银行编号") @RequestParam String bankId,
			@ApiParam(required=true,value="开户行名称") @RequestParam String bankName,
			@ApiParam(required=true,value="卡名称") @RequestParam String cardName,
			@ApiParam(required=true,value="卡类型") @RequestParam String cardType){
		JsonResult<Object> result = null;
		
		try {
			TokenModel model = tokenDao.getToken(token);
			
			if(!tokenDao.checkToken(model))
				return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
			
			HashMap<String, String> params = new HashMap<String, String>();
			params.put("bankCardNo", bankCardNo);
			params.put("bankName", "");
			params.put("userId", model.getUserId()+"");
			params.put("name", name);
			params.put("identifyCard", identifyCard);
			params.put("userType","2");
			params.put("bankId", bankId);
			params.put("bankName", bankName);
			params.put("cardName", cardName);
			params.put("cardType", cardType);
			
			BankCard card = userApiService.findBankCard(params);
			if(card==null){
				JSONObject verifyObj = AliyunBankUtils.realNameAuthentication(params);
				if(verifyObj!=null&&verifyObj.getJSONObject("resp")!=null&&
						verifyObj.getJSONObject("resp").getIntValue("code")==0){
					int count = userApiService.addBankCard(params);
					if(count>0){
						result = new JsonResult<Object>(0,"添加银行卡成功");
					}else{
						result = new JsonResult<Object>(1,"添加银行卡失败");
					}
				}else{
					result = new JsonResult<Object>(1,verifyObj.getJSONObject("resp").getString("desc"));
				}
			}else{
				result = new JsonResult<Object>(1,"该银行卡已绑定");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new JsonResult<Object>(500,"添加银行卡失败");
		}
		return result;
	}
	
	@ApiOperation(value = "解绑银行卡", notes = "接触银行卡绑定关系")
	@RequestMapping(value = "/{bankCardNo}/deleteBankCard/v1", method = RequestMethod.DELETE)
	public JsonResult deleteBankCard(@PathVariable String bankCardNo){
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		if(!tokenDao.checkToken(model))
			return new JsonResult(1,"账号身份已过期，请重新登录");
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("bankCardNo", bankCardNo);
		params.put("userType","2");
		int count = userApiService.deleteBankCard(params);
		try {
			if(count>0){
				return new JsonResult(0,"解绑成功");
			}else{
				return new JsonResult(500,"解绑失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(notes="我的二维码",value="我的二维码")
	@RequestMapping(value = "/getMyQRCode/v1", method = RequestMethod.GET)
	public JsonResult MyQRCode()
	{
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		if(!tokenDao.checkToken(model))
			return new JsonResult(1,"账号身份已过期，请重新登录");
				
		User user = new User();
		user.setId(model.getUserId());
		try {
			 Map<String,String> map = new HashMap<String,String>();
			String code =   userApiService.getMyQRCode(user);
			map.put("code", code);
			if (code.equals(1)) 
				return new JsonResult(1,"没有找到二维码");
			return new JsonResult<>(map);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "绑定身份证", notes = "添加身份证正反面照片绑定身份证信息")
	@RequestMapping(value = "/addIdentityBind/v1",method = RequestMethod.POST)
	public JsonResult<Object> addIdentityBind(@RequestParam("files") CommonsMultipartFile files[]){
		JsonResult<Object> result = null;
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		
        
        try {
			int count  = userApiService.uploadIdentityCard(files, model.getUserId());
			if(count > 0){
				result = new JsonResult<Object>(0,"身份证照片上传成功");
			}else{
				result = new JsonResult<Object>(1,"身份证照片上传失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result = new JsonResult<Object>(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
		return result;
	}
	
	@ApiOperation(value = "我的推荐记录", notes = "查询当前登录用户的推荐记录",response = User.class)
	@RequestMapping(value = "/queryRecommend/v1", method = RequestMethod.GET)
	public JsonResult<Object> myRecommend(){
		JsonResult<Object> result = null;
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		Map<String,String> params = new HashMap<String, String>();
		params.put("parentId", model.getUserId()+"");
		try {
			List<Map<String,Object>> lists = userApiService.queryRecommend(params);
			result = new JsonResult<Object>(0,"",lists);
		} catch (Exception e) {
			e.printStackTrace();
			result = new JsonResult<Object>(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
		return result;
	}
	
	@ApiOperation(value="查询开户行名称",notes="根据输入的银行卡号查询开户行名称")
	@RequestMapping(value="/queryBankName/v1",method=RequestMethod.GET)
	public JsonResult queryBankName(@RequestHeader("token") String token,
			@ApiParam(required=true,value="银行卡") @RequestParam String bankCardNo){
		TokenModel model=tokenDao.getToken(token);
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		try {
			JSONObject bankObj = AliyunBankUtils.queryBankcard(bankCardNo);				//查询银行信息
			if(bankObj!=null&&bankObj.getJSONObject("resp")!=null&&
					bankObj.getJSONObject("resp").getIntValue("code")==0){
				JSONObject dataObj = bankObj.getJSONObject("data");
				if(dataObj.getString("bank_name").contains("邮")){
					return new JsonResult(0,"查询成功",dataObj);
				}else{
					return new JsonResult(1,"只能绑定邮政银行卡，请更换卡号");
				}
			}else{
				return new JsonResult(1,bankObj.getJSONObject("resp").getString("desc"));
			}
		} catch (Exception e) {
			logger.debug(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
}
