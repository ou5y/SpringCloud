package com.azcx9d.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.azcx9d.user.entity.Notice;
import com.azcx9d.user.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.SendMessage.MobileServerUtils;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.common.util.MD5;
import com.azcx9d.user.entity.SystemMessage;
import com.azcx9d.user.entity.UserSuggest;
import com.azcx9d.user.service.UserApiService;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import springfox.documentation.annotations.ApiIgnore;

@Api(value = "用户安全",description = "用户安全管理")
@RestController
@RequestMapping(value  = "/userSecurity")
public class UserSecurityController extends BaseController{
	
	@Autowired
	private UserApiService userApiService;

	@Autowired
	private AppService appService;
	
	@ApiOperation(value = "设置交易密码",notes = "设置交易密码 <br/>transactionPwd：交易密码")
	@RequestMapping(value = "/{transactionPwd}/addTransPwd/v1",method = RequestMethod.POST)
	public JsonResult addTransPwd(@PathVariable String transactionPwd){
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("id", model.getUserId() + "");
			params.put("transactionPwd", MD5.MD5Encode(transactionPwd, "UTF-8"));
			int count = userApiService.updateTransPwd(params);
			if (count > 0) {
				return new JsonResult(0, "设置交易密码成功");
			} else {
				return new JsonResult(1, "设置交易密码失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "修改交易密码",notes = "修改交易密码,对比原始密码是否正确<br/>oldTransPwd:旧密码<br/>"
			+ "transactionPwd：新密码 <br/>")
	@RequestMapping(value = "/{transactionPwd}/modifyTransPwd/v1",method = RequestMethod.POST)
	public JsonResult modifyTransPwd(@PathVariable String transactionPwd){
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		try {
//			Agency agency = userApiService.queryTransPwd(model.getUserId());
//			// 判断就密码是否相同
//			if (agency!=null && agency.getTransactionPsw()!=null&&
//					agency.getTransactionPsw().equals(MD5.MD5Encode(oldTransPwd, "UTF-8"))) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("id", model.getUserId() + "");
				params.put("transactionPwd", MD5.MD5Encode(transactionPwd, "UTF-8"));
				int count = userApiService.updateTransPwd(params);
				if (count > 0) {
					return new JsonResult(0, "修改交易密码成功");
				} else {
					return new JsonResult(1, "修改交易密码失败");
				}
//			} else {
//				return new JsonResult(1, "旧密码不正确,请重新输入");
//			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "找回交易密码",notes = "忘记密码时，可重置交易密码<br/>"
			+ "transactionPwd：新密码 <br/> ")
	@RequestMapping(value = "/{transactionPwd}/{phone}/{code}/findTransPwd/v1",method = RequestMethod.POST)
	public JsonResult findTransPwd(@ApiParam(required=true,value="交易密码") @PathVariable String transactionPwd,
			@ApiParam(required=true,value="手机号码") @PathVariable String phone,
			@ApiParam(required=true,value="验证码") @PathVariable int code){
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		try {
			if(MobileServerUtils.checkCode(phone, code)){
				Map<String, String> params = new HashMap<String, String>();
				params.put("id", model.getUserId() + "");
				params.put("transactionPwd", MD5.MD5Encode(transactionPwd, "UTF-8"));
				int count = userApiService.updateTransPwd(params);
				if (count > 0) {
					return new JsonResult(0, "设置交易密码成功");
				} else {
					return new JsonResult(1, "修改交易密码失败");
				}
			}else{
				return new JsonResult(1, "验证码错误或失效");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "修改登录密码",notes = "修改登录密码,对比原始密码是否正确,password")
	@RequestMapping(value = "/{password}/modifyLoginPwd/v1",method = RequestMethod.POST)
	public JsonResult modifyLoginPwd(@PathVariable String password){
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		
		try {
			Map<String, String> params = new HashMap<String, String>();
			params.put("id", model.getUserId() + "");
//			params.put("password", MD5.MD5Encode(password,"UTF-8"));
			params.put("userType", "2");		//代理商
			params.put("password", password);
			int count = userApiService.updateLoginPwd(params);
			if (count > 0) {
				return new JsonResult(0, "修改登录密码成功");
			} else {
				return new JsonResult(1, "修改登录密码失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "找回登录密码",notes = "忘记密码时重置登录密码")
	@RequestMapping(value = "/{phone}/{password}/findLoginPwd/v1",method=RequestMethod.POST)
	public JsonResult findLoginPwd(@ApiParam(required=true,value="手机号码") @PathVariable String phone,
			@ApiParam(required=true,value="登录密码") @PathVariable String password){
		
		Map<String,String> params = new HashMap<String, String>();
		params.put("phone", phone);
		params.put("userType", "2");		//代理商
		params.put("password", password);
		try {
			int count = userApiService.updateLoginPwd(params);
			if (count > 0) {
				return new JsonResult(0, "重置登录密码成功");
			} else {
				return new JsonResult(1, "重置登录密码失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "系统消息",notes = "获取当前用户的系统消息",response = SystemMessage.class)
	@RequestMapping(value = "/systemMessage/v1",method = RequestMethod.POST)
	public JsonResult systemMessage(){
		String token = request.getHeader("token");
		TokenModel model=tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		try {
			Map<String,Object> params = new HashMap<String, Object>();
			params.put("userId", model.getUserId());
			List<SystemMessage> lists = userApiService.queryMessage(params);
			return new JsonResult(0, "查询系统消息成功",lists);
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value = "添加用户建议反馈",notes = "添加用户建议反馈,userId、content必填",response=UserSuggest.class)
	@RequestMapping(value = "/addUserSuggest/v1",method = RequestMethod.POST)
	public JsonResult addUserSuggest(@RequestHeader("token") String token,
			@ApiParam(value="内容") @RequestParam String content,
			@ApiParam(value="用户id") @RequestParam long userId){
		TokenModel model=tokenDao.getToken(token);
		
		if(!tokenDao.checkToken(model))
			return new JsonResult<Object>(1,"账号身份已过期，请重新登录");
		
		try {
			UserSuggest suggest = new UserSuggest();
			suggest.setContent(content);
			suggest.setUserId(userId);
			int count = userApiService.addUserSuggest(suggest);
			if(count>0){
				return new JsonResult(0, "添加建议反馈成功");
			}else{
				return new JsonResult(1, "添加建议反馈失败");
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}

	@ApiOperation(value="查询版本更新信息", notes="查询版本更新信息appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
			+ "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>")
	@RequestMapping(value="/versionInfo", method=RequestMethod.GET)
	public JsonResult updateInfo(@ApiParam(required=true,value="app类型") @RequestParam(defaultValue="2") int appType){

		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("appType", appType);			//代理端更新
			Map<String,Object> results = userApiService.queryVersionInfo(params);
			return new JsonResult(0, "查询更新信息成功",(results!=null?results:new HashMap<String, Object>(0)));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
	@ApiOperation(value="查询版本更新信息", notes="查询版本更新信息appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
			+ "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>")
	@RequestMapping(value="/versionInfo/v1", method=RequestMethod.GET)
	public JsonResult updateInfoVsersion(@ApiParam(required=true,value="app类型") @RequestParam(defaultValue="2") int appType){
		
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("appType", appType);			//代理端更新
			Map<String,Object> results = userApiService.queryVersionInfo(params);
			return new JsonResult(0, "查询更新信息成功",(results!=null?results:new HashMap<String, Object>(0)));
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}

	@ApiOperation(value="查询公告信息", notes = "查询系统公告",response = Notice.class)
	@RequestMapping(value = "/queryNotice/v1", method = RequestMethod.GET)
	public JsonResult queryNotice(@ApiParam(value = "APP类型",required = true) @RequestParam int appType){
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("appType", appType);			//代理端更新
			Notice notice = appService.findByParams(params);
			return new JsonResult(0, "查询更新信息成功",(notice!=null?notice:new HashMap<String, Object>(0)) );
		} catch (Exception e) {
			logger.error(e.getMessage());
			return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
		}
	}
	
}
