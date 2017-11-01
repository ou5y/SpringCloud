package com.azcx9d.business.controller;

import cn.jpush.api.JPushClient;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.cache.RedisManager;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.jpush.JpushClientUtil;
import com.azcx9d.common.token.TokenManager;
import com.azcx9d.common.util.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
@Api(value="Jpush消息推送",description="Jpush消息推送")
@RestController
@RequestMapping(value="/jpush")
public class JpushController extends BaseController {

    @Autowired
    private TokenManager tokenManager;

    @ApiOperation(value="Android",notes = "推送至Android平台所有注册用户,appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
            + "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>")
    @RequestMapping(value="/sendToAndroid",method = RequestMethod.POST)
    public JsonResult pushToAndroid(@ApiParam(required = true,value = "App类型") @RequestParam String appType,
//                                    @ApiParam(required = true,value ="消息标题") @RequestParam String title,
                                    @ApiParam(required = true,value ="消息内容") @RequestParam String body,
                                    @ApiParam(required = false,value = "扩展参数") @RequestParam(required = false) String extrasParam){
        try {
            if(!StringUtils.isBlank(appType)&&!StringUtils.isBlank(body)){
                JpushClientUtil.init(appType);
                int code = JpushClientUtil.sendToAllAndroid(null,null,body,extrasParam);
                if(code==1){
                    return  new JsonResult(0,"消息推送成功",null);
                }else {
                    return  new JsonResult(1,"消息推送失败",null);
                }
            }else{
                return  new JsonResult(1,"参数错误",null);
            }
        } catch (Exception e) {
            return  new JsonResult(500,"服务器错误请稍后重试",null);
        }
    }

    @ApiOperation(value="IOS",notes = "推送至IOS平台所有注册用户,appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
            + "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>")
    @RequestMapping(value="/sendToIos",method = RequestMethod.POST)
    public JsonResult pushToIos(@ApiParam(required = true,value = "App类型") @RequestParam String appType,
//                                @ApiParam(required = true,value ="消息标题") @RequestParam String title,
                                @ApiParam(required = true,value ="消息内容") @RequestParam String body,
                                @ApiParam(required = false,value = "扩展参数") @RequestParam(required = false) String extrasParam){
        try {
            if(!StringUtils.isBlank(appType)&&!StringUtils.isBlank(body)){
                JpushClientUtil.init(appType);
                int code = JpushClientUtil.sendToAllIos(null,null,body,extrasParam);
                if(code==1){
                    return  new JsonResult(0,"消息推送成功",null);
                }else {
                    return  new JsonResult(1,"消息推送失败",null);
                }
            }else{
                return  new JsonResult(1,"参数错误",null);
            }
        } catch (Exception e) {
            return  new JsonResult(500,"服务器错误请稍后重试",null);
        }
    }

    @ApiOperation(value="All",notes = "推送至Android、IOS平台所有注册用户,appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
            + "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>")
    @RequestMapping(value="/sendToAll",method = RequestMethod.POST)
    public JsonResult pushToAll(@ApiParam(required = true,value = "App类型") @RequestParam String appType,
//                                @ApiParam(required = true,value ="消息标题") @RequestParam String title,
                                @ApiParam(required = true,value ="消息内容") @RequestParam String body,
                                @ApiParam(required = false,value = "扩展参数") @RequestParam(required = false) String extrasParam){
        try {
            if(!StringUtils.isBlank(appType)&&!StringUtils.isBlank(body)){
                JpushClientUtil.init(appType);
                int code = JpushClientUtil.sendToAll(null,null,body,extrasParam);
                if(code==1){
                    return  new JsonResult(0,"消息推送成功",null);
                }else {
                    return  new JsonResult(1,"消息推送失败",null);
                }
            }else{
                return  new JsonResult(1,"参数错误",null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  new JsonResult(500,"服务器错误请稍后重试",null);
        }
    }

    @ApiOperation(value="单个用户",notes = "推送至已注册别名的单个用户,appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
            + "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>")
    @RequestMapping(value="/sendToAlias",method = RequestMethod.POST)
    public JsonResult pushToAlias(@ApiParam(required=true,value = "用户id") @RequestParam String userId,
                                  @ApiParam(required = true,value = "App类型") @RequestParam String appType,
//                                  @ApiParam(required = true,value ="消息标题") @RequestParam String title,
                                  @ApiParam(required = true,value ="消息内容") @RequestParam String body,
                                  @ApiParam(required = false,value = "扩展参数") @RequestParam(required = false) String extrasParam){
        try {
            if(!StringUtils.isBlank(userId)&&!StringUtils.isBlank(appType)&&!StringUtils.isBlank(body)){
                TokenModel model = tokenManager.getTokenByUserId(userId);
                if(model!=null){
                    String alias = model.getToken();
                    JpushClientUtil.init(appType);
                    int code = JpushClientUtil.sendToAlias(alias,null,null,body,extrasParam);
                    if(code==1){
                        return  new JsonResult(0,"消息推送成功",null);
                    }else {
                        return  new JsonResult(1,"消息推送失败",null);
                    }
                }else{
                    return  new JsonResult(1,"找不到用户",null);
                }

            }else{
                return  new JsonResult(1,"参数错误",null);
            }
        } catch (Exception e) {
            return  new JsonResult(500,"服务器错误请稍后重试",null);
        }
    }

}
