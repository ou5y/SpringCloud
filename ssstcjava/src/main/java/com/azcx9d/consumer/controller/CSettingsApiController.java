package com.azcx9d.consumer.controller;

import com.azcx9d.common.SendMessage.MobileServerUtils;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.common.util.MD5;
import com.azcx9d.consumer.entity.CAddress;
import com.azcx9d.consumer.entity.Consumer;
import com.azcx9d.consumer.service.CSettingsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/4/2 0002.
 */
@Api(value = "用户端个人设置", description = "用户端个人设置")
@RestController
@RequestMapping(value = "/cApi/settings")
public class CSettingsApiController extends BaseController {

    @Autowired
    private CSettingsService cSettingsService;

    @ApiOperation(value = "过滤区域列表", notes = "过滤区域列表,parentCode默认=100000表示获取省份")
    @RequestMapping(value = "/areaListByparentId/v1", method = RequestMethod.GET)
    public JsonResult areaListByparentId(@ApiParam(required =true, value = "地区父ID(默认=100000获取省份)") @RequestParam("parentCode") String parentCode,
                                        @RequestHeader("token") String token){
        List<Map> list = null;
        try {
            Map<String,String> params = new HashMap<String,String>();
            params.put("parentCode", parentCode);
            list = cSettingsService.areaListByparentId(params);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return  new JsonResult(500,"服务器出错！");
        }
        return new JsonResult(list);
    }

    @ApiOperation(value = "新增收货地址", notes = "新增收货地址")
    @RequestMapping(value = "/addAddress/v1", method = RequestMethod.POST)
    public JsonResult addAddress(@ApiParam(required =true, value = "收货人") @RequestParam("name") String name,
                                 @ApiParam(required =true, value = "手机号码") @RequestParam("phone") String phone,
                                 @ApiParam(required =true, value = "所在地区(省)") @RequestParam("province") int province,
                                 @ApiParam(required =true, value = "省(汉字)") @RequestParam("provinceDesc") String provinceDesc,
                                 @ApiParam(required =true, value = "所在地区(市)") @RequestParam("city") int city,
                                 @ApiParam(required =true, value = "市(汉字)") @RequestParam("cityDesc") String cityDesc,
                                 @ApiParam(required =true, value = "所在地区(区)") @RequestParam("area") int area,
                                 @ApiParam(required =true, value = "区(汉字)") @RequestParam("areaDesc") String areaDesc,
                                 @ApiParam(required =true, value = "详细地址") @RequestParam("fullAddress") String fullAddress,
                                 @RequestHeader("token") String token){
        JsonResult result;
        CAddress cAddress = new CAddress();
        cAddress.setUserId((Long) request.getAttribute("userId"));
        cAddress.setName(name);
        cAddress.setPhone(phone);
        cAddress.setProvince(province);
        cAddress.setProvinceDesc(provinceDesc);
        cAddress.setCity(city);
        cAddress.setCityDesc(cityDesc);
        cAddress.setArea(area);
        cAddress.setAreaDesc(areaDesc);
        cAddress.setFullAddress(fullAddress);
        try {
            result = cSettingsService.addAddress(cAddress);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new JsonResult(500,"服务器出错！");
        }
        return result;
    }

    @ApiOperation(value = "省市区下拉列表", notes = "省市区下拉列表")
    @RequestMapping(value = "/allAreaList/v1", method = RequestMethod.GET)
    public JsonResult allAreaList(@RequestHeader("token") String token){
        try {
            return cSettingsService.allAreaList();
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"服务器出错！");
        }
    }

    @ApiOperation(value = "收货地址列表", notes = "收货地址列表")
    @RequestMapping(value = "/addressList/v1", method = RequestMethod.GET)
    public JsonResult addressList(@RequestHeader("token") String token){
        try {
            return cSettingsService.addressList((Long) request.getAttribute("userId"));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"服务器出错！");
        }
    }

    @ApiOperation(value = "地址详细信息", notes = "地址详细信息")
    @RequestMapping(value = "/addressDetail/v1", method = RequestMethod.GET)
    public JsonResult addressDetail(@ApiParam(required =true, value = "地址id") @RequestParam("addressId") long addressId,
                                    @RequestHeader("token") String token){
        try {
            return cSettingsService.addressDetail(addressId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"服务器出错！");
        }
    }

    @ApiOperation(value = "修改收货地址", notes = "修改收货地址")
    @RequestMapping(value = "/updateAddress/v1", method = RequestMethod.POST)
    public JsonResult modifyAddress(@ApiParam(required =true, value = "地址id") @RequestParam("id") long id,
                                    @ApiParam(required =true, value = "收货人") @RequestParam("name") String name,
                                    @ApiParam(required =true, value = "手机号码") @RequestParam("phone") String phone,
                                    @ApiParam(required =true, value = "所在地区(省)") @RequestParam("province") int province,
                                    @ApiParam(required =true, value = "省(汉字)") @RequestParam("provinceDesc") String provinceDesc,
                                    @ApiParam(required =true, value = "所在地区(市)") @RequestParam("city") int city,
                                    @ApiParam(required =true, value = "市(汉字)") @RequestParam("cityDesc") String cityDesc,
                                    @ApiParam(required =true, value = "所在地区(区)") @RequestParam("area") int area,
                                    @ApiParam(required =true, value = "区(汉字)") @RequestParam("areaDesc") String areaDesc,
                                    @ApiParam(required =true, value = "详细地址") @RequestParam("fullAddress") String fullAddress,
                                    @RequestHeader("token") String token){
        JsonResult result;
        CAddress cAddress = new CAddress();
        cAddress.setId(id);
        cAddress.setUserId((Long) request.getAttribute("userId"));
        cAddress.setName(name);
        cAddress.setPhone(phone);
        cAddress.setProvince(province);
        cAddress.setProvinceDesc(provinceDesc);
        cAddress.setCity(city);
        cAddress.setCityDesc(cityDesc);
        cAddress.setArea(area);
        cAddress.setAreaDesc(areaDesc);
        cAddress.setFullAddress(fullAddress);
        try {
            result = cSettingsService.updateAddress(cAddress);
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new JsonResult(500,"服务器出错！");
        }
        return result;
    }

    @ApiOperation(value = "删除地址", notes = "删除地址")
    @RequestMapping(value = "/deleteAddress/v1", method = RequestMethod.DELETE)
    public JsonResult deleteAddress(@ApiParam(required =true, value = "地址id") @RequestParam("addressId") long addressId,
                                    @RequestHeader("token") String token){
        try {
            return cSettingsService.deleteAddress(addressId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"服务器出错！");
        }
    }

    @ApiOperation(value = "设置/取消默认地址", notes = "设置/取消默认地址")
    @RequestMapping(value = "/setDefaultAddress/v1", method = RequestMethod.POST)
    public JsonResult setDefaultAddress(@ApiParam(required =true, value = "地址id") @RequestParam("addressId") long addressId,
                                        @ApiParam(required =true, value = "是否默认地址") @RequestParam("defaultAddress") int defaultAddress,
                                        @RequestHeader("token") String token){
        long userId = (Long) request.getAttribute("userId");
        try {
            return cSettingsService.setDefaultAddress(userId, addressId, defaultAddress);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"服务器出错！");
        }
    }

    @ApiOperation(value = "设置交易密码",notes = "设置交易密码")
    @RequestMapping(value = "/addTransPwd/v1",method = RequestMethod.POST)
    public JsonResult addTransPwd(@ApiParam(required =true, value = "交易密码") @RequestParam("transactionPwd") String transactionPwd,
                                  @RequestHeader("token") String token){
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", request.getAttribute("userId")+"");
            params.put("transactionPsw", MD5.MD5Encode(transactionPwd, "UTF-8"));
            return cSettingsService.addTransPwd(params);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "修改交易密码",notes = "修改交易密码")
    @RequestMapping(value = "/modifyTransPwd/v1",method = RequestMethod.POST)
    public JsonResult modifyTransPwd(@ApiParam(required =true, value = "旧交易密码") @RequestParam("oldTransPwd") String oldTransPwd,
                                     @ApiParam(required =true, value = "新交易密码") @RequestParam("transactionPwd") String transactionPwd,
                                     @RequestHeader("token") String token){
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", request.getAttribute("userId")+"");
            params.put("oldTransPwd", MD5.MD5Encode(oldTransPwd, "UTF-8"));
            params.put("transactionPsw", MD5.MD5Encode(transactionPwd, "UTF-8"));
            return cSettingsService.modifyTransPwd(params);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "找回交易密码",notes = "找回交易密码")
    @RequestMapping(value = "/findTransPwd/v1",method = RequestMethod.POST)
    public JsonResult findTransPwd(@ApiParam(required =true, value = "电话号码") @RequestParam("phone") String phone,
                                   @ApiParam(required =true, value = "验证码") @RequestParam("code") int code,
                                   @ApiParam(required =true, value = "交易密码") @RequestParam("transactionPwd") String transactionPwd,
                                   @RequestHeader("token") String token){
        if (!MobileServerUtils.checkCode(phone, code)) {
            return new 	JsonResult(1,"短信验证失败或验证码已经失效！请重新获取验证码" );
        }
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", request.getAttribute("userId")+"");
            params.put("transactionPsw", MD5.MD5Encode(transactionPwd, "UTF-8"));
            return cSettingsService.findTransPwd(params);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "是否已经设置交易密码",notes = "是否已经设置交易密码")
    @RequestMapping(value = "/hasTransPwd/v1",method = RequestMethod.GET)
    public JsonResult hasTransPwd(@RequestHeader("token") String token){
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", request.getAttribute("userId")+"");
            return cSettingsService.hasTransPwd(params);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "修改登录密码",notes = "修改登录密码")
    @RequestMapping(value = "/modifyLoginPwd/v1",method = RequestMethod.POST)
    public JsonResult modifyLoginPwd(@ApiParam(required =true, value = "旧登录密码") @RequestParam("oldPassword") String oldPassword,
                                     @ApiParam(required =true, value = "新登录密码") @RequestParam("password") String password,
                                     @RequestHeader("token") String token){
        try {
            Map<String, String> params = new HashMap<String, String>();
            params.put("id", request.getAttribute("userId")+"");
            params.put("oldPassword", oldPassword);
            params.put("password", password);
            return cSettingsService.modifyLoginPwd(params);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "上传身份证正面",notes = "上传身份证正面<br/>identityCardUp:身份证正面路径")
    @RequestMapping(value = "/saveIdentityCardUp/v1",method = RequestMethod.POST)
    public JsonResult saveIdentityCardUp(@ApiParam(required =true, value = "身份证正面路径") @RequestParam("identityCardUp") String identityCardUp,
                                     @RequestHeader("token") String token){
        Consumer consumer = new Consumer();
        consumer.setId((int)request.getAttribute("userId"));
        consumer.setIdentityCardUp(identityCardUp);
        try {
            return cSettingsService.saveIdentityCardUp(consumer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "上传身份证反面",notes = "上传身份证反面<br/>identityCardDown:身份证反面路径")
    @RequestMapping(value = "/saveIdentityCardDown/v1",method = RequestMethod.POST)
    public JsonResult saveIdentityCardDown(@ApiParam(required =true, value = "身份证反面路径") @RequestParam("identityCardDown") String identityCardDown,
                                           @RequestHeader("token") String token){
        Consumer consumer = new Consumer();
        consumer.setId((int)request.getAttribute("userId"));
        consumer.setIdentityCardDown(identityCardDown);
        try {
            return cSettingsService.saveIdentityCardDown(consumer);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }
}
