package com.azcx9d.consumer.controller;

import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.SendMessage.MobileServerUtils;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.token.TokenManagerDao;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.consumer.entity.Consumer;
import com.azcx9d.consumer.service.ConsumerApiService;
import com.azcx9d.user.entity.YunpianReaultEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by chenxl on 2017/3/29.
 */
@Api(value = "用户端用户信息", description = "用户端用户信息")
@RestController
@RequestMapping(value = "/cApi/user")
public class CUserApiController extends BaseController {
    @Autowired
    private ConsumerApiService consumerApiService;
    @Autowired
    private TokenManagerDao tokenDao;

    @ApiOperation(notes = "登录", value = "登录",response = Consumer.class)
    @RequestMapping(value = "/login/v1",method= RequestMethod.POST)
    public LoginExecution<Consumer> login(@ApiParam(required = true,value = "消费者账号")@RequestParam("phone") String phone,
                                          @ApiParam(required = true,value = "密码")@RequestParam("password") String password) {
        LoginExecution<Consumer> execution;
        Consumer consumer = new Consumer(phone, password);
        try {
            execution = consumerApiService.login(consumer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return execution = new LoginExecution(500,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
        return execution;
    }

    @ApiOperation(notes = "注销退出",value = "注销退出")
    @RequestMapping(value = "/logout/v1", method = RequestMethod.POST)
    public JsonResult logout(@RequestHeader("token") String token) {
        //String token = request.getHeader("token");
        TokenModel model=tokenDao.getToken(token);
        if(!tokenDao.checkToken(model)){
            return new JsonResult(1,"账号身份已过期，请重新登录");
        }
        tokenDao.deleteToken(model.getToken());
        return new JsonResult(0,"ok");
    }

    @ApiOperation(notes = "用户详情",value = "用户详细信息")
    @RequestMapping(value = "/detail/v1", method = RequestMethod.POST)
    public JsonResult<Consumer> getUserInfo(@RequestHeader("token") String token) {
        TokenModel model=tokenDao.getToken(token);
        if(!tokenDao.checkToken(model))
            return new JsonResult(1,"账号身份已过期，请重新登录");
        Consumer consumer;
        try {
            consumer = consumerApiService.queryUserInfo(model.getUserId());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
        if(consumer==null)
        {
            return new JsonResult(1,"没有找到该用户");
        }
        JsonResult<Consumer> result = new JsonResult<Consumer>(consumer);
        return result;
    }

    @ApiOperation(notes = "获取短信验证码",value = "获取短信验证码")
    @RequestMapping(value = "/getCode/v1", method = RequestMethod.POST)
    public JsonResult identifyingCode(@ApiParam(required = true,value = "手机号码")@RequestParam("phone") String phone){
        YunpianReaultEntity yre;
        try {
            yre = consumerApiService.identifyingCode(phone);
            if(yre.getCode()!=0)
            {
                logger.debug("code:"+yre.getCode()+",message"+yre.getMsg());
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

    @ApiOperation(notes = "校验短信验证码",value = "校验短信验证码")
    @RequestMapping(value = "/checkCode/v1", method = RequestMethod.POST)
    public JsonResult checkCode(@ApiParam(required = true,value = "手机号码")@RequestParam("phone") String phone,
                                @ApiParam(required = true,value = "验证码")@RequestParam("code") int code)
    {
        if (MobileServerUtils.checkCode(phone, code)) {
            return new 	JsonResult(0,"ok");
        }
        return new 	JsonResult(1,"短信验证失败或验证码已经失效！请重新获取验证码");
    }

    @ApiOperation(notes = "校验推荐人手机号码",value = "校验推荐人手机号码")
    @RequestMapping(value = "/checkRecommendPhone/v1", method = RequestMethod.POST)
    public JsonResult checkRecommendPhone(@ApiParam(required = true,value = "推荐人手机号码")@RequestParam("recommendPhone") String recommendPhone)
    {
        try{
            Consumer consumer = consumerApiService.checkRecommendPhone(recommendPhone);
            if(null != consumer){
                return new JsonResult(0,"ok");
            }else{
                return new JsonResult(1,"推荐人手机号码不正确！");
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(notes="校验手机是否已经注册",value="校验手机是否已经注册")
    @RequestMapping(value = "/checkPhone/v1", method = RequestMethod.POST)
    public JsonResult checkPhone(@ApiParam(required = true,value = "手机号码")@RequestParam("phone") String phone){
        try{
            Consumer consumer = consumerApiService.checkPhone(phone);
            if(null != consumer){
                return new JsonResult(1,"该手机号码已经注册！");
            }else{
                return new JsonResult(0,"ok!");
            }
        }catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(notes="注册",value="注册")
    @RequestMapping(value = "/register/v1", method = RequestMethod.POST)
    public JsonResult register(@ApiParam(required = true,value = "手机号码")@RequestParam("phone") String phone,
                               @ApiParam(required = true,value = "密码")@RequestParam("password") String password,
                               @ApiParam(required = true,value = "短信验证码")@RequestParam("code") int code,
                               @ApiParam(required = true,value = "推荐人手机号码")@RequestParam("recommendPhone") String recommendPhone)
    {
        try {
            Consumer consumerExist = consumerApiService.checkPhone(phone);
            if(null != consumerExist){
                return new JsonResult(1,"该手机号码已经注册！");
            }
            if (!MobileServerUtils.checkCode(phone, code)) {
                return new JsonResult(2,"短信验证失败或验证码已经失效！请重新获取验证码");
            }
            Consumer consumerParent = consumerApiService.checkPhone(recommendPhone);
            Consumer consumer = new Consumer();
            consumer.setPhone(phone);
            consumer.setPassword(password);
            if(null != consumerParent){
                consumer.setParentId(consumerParent.getId());
            }
            int count = consumerApiService.doRegister(consumer);
            if (count > 0){
                return new JsonResult(0,"注册成功!");
            }else{
                return new JsonResult(3,"注册失败!稍后再试试~~");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(notes="重置密码",value="重置密码")
    @RequestMapping(value = "/resetPassword/v1", method = RequestMethod.POST)
    public JsonResult resetPassword(@ApiParam(required = true,value = "手机号码")@RequestParam("phone") String phone,
                                     @ApiParam(required = true,value = "密码")@RequestParam("password") String password,
                                     @ApiParam(required = true,value = "短信验证码")@RequestParam("code") int code){
        try {
            Consumer consumer = consumerApiService.checkPhone(phone);
            if(null == consumer){
                return new JsonResult(1,"该手机号码还未注册！");
            }
            if (!MobileServerUtils.checkCode(phone, code)) {
                return new JsonResult(2,"短信验证失败或验证码已经失效！请重新获取验证码");
            }
            consumer.setPassword(password);
            int count = consumerApiService.resetPassword(consumer);
            if (count > 0){
                return new JsonResult(0,"密码修改成功!请重新登录");
            }else{
                return new JsonResult(3,"密码修改失败!稍后再试试~~");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

}
