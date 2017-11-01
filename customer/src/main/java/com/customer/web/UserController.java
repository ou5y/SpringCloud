package com.customer.web;

import com.customer.dto.LoginExecution;
import com.customer.dto.UserDto;
import com.customer.service.AccountSecurityService;
import com.customer.service.PhoneCodeService;
import com.customer.service.UserService;
import com.customer.util.BaseController;
import com.customer.util.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/4/24.
 */
@RestController
@RequestMapping(value = "/v1/customer")
@Api(value = "注册登陆",description = "用户注册 登陆")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    @Autowired
    private AccountSecurityService accountSecurityService;

    @Autowired
    private PhoneCodeService phoneCodeService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @ApiOperation(notes = "登录", value = "登录",response = UserDto.class)
    @PostMapping(value = "/login")
    public LoginExecution<UserDto> login(@ApiParam(required =true, value = "手机号码") @RequestParam String phone,
                                        @ApiParam(required=true,value="密码") @RequestParam String password,
                                         @ApiParam(required=false,value="设备编号,MD5加密后的32位字符串") @RequestParam(required = false) String deviceNo,
                                         @ApiParam(required=false,value="来源,0：未知   1：IOS   2:Android") @RequestParam(required = false) String source) {

        LoginExecution<UserDto> execution;
        Map<String, Object> map= CommonUtil.getParameterMap(request);
        try {
            execution = userService.login(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return execution = new LoginExecution(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
        return execution;


    }


    @RequestMapping(value = "/fastLgoin",method=RequestMethod.GET)
    @ApiOperation(notes = "快速登录", value = "快速登录",response = UserDto.class)
    public LoginExecution<UserDto> shortcutLgoin(@ApiParam(required =true, value = "手机号码") @RequestParam String phone,
                                                 @ApiParam(value = "验证码",required = true) @RequestParam(value = "checkCode",required = true) String checkCode
                                                )
    {
        if (phoneCodeService.checkPhoneCode(phone, checkCode)) {
            LoginExecution<UserDto> execution;
            Map<String, Object> map= CommonUtil.getParameterMap(request);
            try {
                execution = userService.login((String) map.get("phone"));
            } catch (Exception e) {
                logger.error(e.getMessage());
                return execution = new LoginExecution(2,"请求超时或者服务器出错啦，稍后再试试~~！");
            }
            return execution;
        }
        else
        {
            return new LoginExecution(2,"验证码错误");
        }

    }




    @RequestMapping(value = "/register",method = RequestMethod.POST )
    @ApiOperation(value="用户注册", notes="创建用户")
    public JsonResult register(
                               @ApiParam(value = "电话",required = true) @RequestParam(value = "phone",required = true) String phone,
                               @ApiParam(value = "验证码",required = true) @RequestParam(value = "checkCode",required = true) String checkCode,
                               @ApiParam(value = "登陆密码",required = true) @RequestParam(value = "password",required = true) String password,
                               @ApiParam(value = "推荐人手机号码",required = false) @RequestParam(value = "recommendPhone",required = false) String recommendPhone,
                               @ApiParam(value = "注册类型(1:高级推广员,2:推广员,3:消费者)",required = false)
                               @RequestParam(value = "type",required = false, defaultValue ="0") int type,
                               @ApiParam(value = "姓名",required = false) @RequestParam(value = "userName",required = false) String userName) {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        if (phoneCodeService.checkPhoneCode(phone, checkCode)) {
            return ResultUtil.success(userService.addUser(map));
        }
        else
        {
            return ResultUtil.error(2,"验证码错误");
        }
    }

    @ApiOperation(value = "找回登录密码",notes = "忘记密码时重置登录密码")
    @RequestMapping(value = "/findLoginPwd",method=RequestMethod.POST)
    public JsonResult findLoginPwd(
                                   @ApiParam(required=true,value="手机号码") @RequestParam String phone,
                                   @ApiParam(required=true,value="登录密码") @RequestParam String password,
                                   @ApiParam(required=true,value="验证码") @RequestParam String code){

        Map<String,Object> params = new HashMap<String, Object>();
        params.put("phone",phone);
        params.put("password",password);
        try {
            if(phoneCodeService.checkPhoneCode(phone, code)){
                int count = accountSecurityService.updateLoginPwd(params);
                if (count > 0) {
                    return new JsonResult(0, "重置登录密码成功");
                } else {
                    return new JsonResult(2, "重置登录密码失败");
                }
            }else{
                return new JsonResult(2, "验证码错误或失效");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @RequestMapping(value = "/weixinLgoin", method = RequestMethod.POST)
    @ApiOperation(notes = "微信登录", value = "微信登录", response = UserDto.class)
    public LoginExecution<UserDto> weixinLgoin(@ApiParam(required = true, value = "微信用户标识") @RequestParam String openid,
                                               @ApiParam(required=false,value="设备编号,MD5加密后的32位字符串") @RequestParam(required = false) String deviceNo,
                                               @ApiParam(required=false,value="来源,0：未知   1：IOS   2:Android") @RequestParam(required = false) String source) {

        LoginExecution<UserDto> execution;
        Map<String, Object> map = CommonUtil.getParameterMap(request);

        try {
            execution = userService.weixinLgoin(map);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return execution = new LoginExecution(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }

        return execution;

    }

    @RequestMapping(value = "/weixinBind", method = RequestMethod.POST)
    @ApiOperation(value = "微信用户绑定手机号", notes = "微信用户绑定手机号")
    public LoginExecution<UserDto> weixinBind(@ApiParam(required = true, value = "手机号码") @RequestParam String phone,
                                              @ApiParam(required = true, value = "登录密码") @RequestParam String password,
                                              @ApiParam(required = true, value = "验证码") @RequestParam String checkCode,
                                              @ApiParam(required = true, value = "微信用户标识") @RequestParam String openid,
                                              @ApiParam(required = false, value = "昵称") @RequestParam(required = false) String userName,
                                              @ApiParam(required = false, value = "头像") @RequestParam(required = false) String avatar) {

        if (phoneCodeService.checkPhoneCode(phone, checkCode)) {
            LoginExecution<UserDto> execution;
            Map<String, Object> map = CommonUtil.getParameterMap(request);
            if (StringUtils.isBlank(userName)) {
                map.put("userName", "");
            }
            if (StringUtils.isBlank(avatar)) {
                map.put("avatar", "");
            }
            try {
                execution = userService.weixinBind(map);
            } catch (Exception e) {
                logger.error(e.getMessage());
                return execution = new LoginExecution(2, "请求超时或者服务器出错啦，稍后再试试~~！");
            }
            return execution;
        } else {
            return new LoginExecution(2, "验证码错误");
        }
    }

}
