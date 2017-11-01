package com.customer.web.my;

import com.alibaba.fastjson.JSONObject;
import com.customer.dto.BankCardListDto;
import com.customer.dto.BankInfoDto;
import com.customer.entity.BankCard;
import com.customer.entity.ParaMap;
import com.customer.service.AccountSecurityService;
import com.customer.service.PhoneCodeService;
import com.customer.util.*;
import com.customer.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@Api(value = "账户安全",description = "账户安全")
@RestController
@RequestMapping(value = "/v1/cApi/security")
public class AccountSecurityController extends BaseController{

    @Autowired
    private AccountSecurityService accountSecurityService;

    @Autowired
    private PhoneCodeService phoneCodeService;

    @ApiOperation(value = "我的银行卡", notes = "查询当前登录用户的全部银行卡",response = BankCardListDto.class)
    @RequestMapping(value = "/myBankCard", method = RequestMethod.GET)
    public JsonResult queryBankCard(@RequestHeader(value = "token") String token){

        try {
            TokenModel model = getTokenModel();
            Map<String,Object> params = new HashMap<String,Object>(0);
            params.put("userId",model.getId());
            List<Map<String,Object>> bankCards = accountSecurityService.queryMyBankCard(params);

            if(bankCards!=null && bankCards.size()>0){
                return  new JsonResult(0,"查询我的银行卡成功",bankCards);
            }else{
                return  new JsonResult(4,"暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"查询失败，请稍后再试");
        }
    }

    @ApiOperation(value = "添加银行卡", notes = "新增银行卡")
    @RequestMapping(value = "/addBankCard",method = RequestMethod.POST)
    public JsonResult addBankCard(
            @RequestHeader("token") String token,
            @ApiParam(required=true,value="银行卡号") @RequestParam String bankCardNo,
            @ApiParam(required=true,value="姓名") @RequestParam String name,
            @ApiParam(required=true,value="银行编号") @RequestParam String bankId,
            @ApiParam(required=true,value="开户行名称") @RequestParam String bankName,
            @ApiParam(required=true,value="卡名称") @RequestParam String cardName,
            @ApiParam(required=true,value="卡类型") @RequestParam String cardType,
            @ApiParam(required=true,value="手机号码") @RequestParam String phone,
            @ApiParam(required=true,value="验证码") @RequestParam String code,
            @ApiParam(required=false,value="身份证号码") @RequestParam(required=false) String identifyCard){

        try {
            if(!phoneCodeService.checkPhoneCode(phone, code)){
                return new JsonResult(2, "短信验证失败或验证码已经失效！请重新获取验证码");
            }
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", model.getId()+"");        //c_user_role_attribute  id
            paraMap.put("uid",model.getUserId());            //user id
            paraMap.put("userType","2");
            if(!bankName.contains("邮")){return new JsonResult<Object>(2,"只能绑定邮政银行卡，请更换卡号");}

            BankCard card = accountSecurityService.findBankCard(paraMap);
            if(card==null){
                JSONObject verifyObj = AliyunBankUtils.realNameAuthentication(paraMap);
                if(verifyObj!=null&&verifyObj.getJSONObject("resp")!=null&&
                        verifyObj.getJSONObject("resp").getIntValue("code")==0){
                    int count = accountSecurityService.addBankCard(paraMap);
                    if(count>0){
                        return new JsonResult<Object>(0,"添加银行卡成功");
                    }else{
                        return new JsonResult<Object>(2,"添加银行卡失败");
                    }
                }else if(verifyObj!=null&&verifyObj.getJSONObject("resp")!=null&&
                        verifyObj.getJSONObject("resp").getString("desc")!=null){
                    return new JsonResult<Object>(2,verifyObj.getJSONObject("resp").getString("desc"));
                }else{
                    return new JsonResult<Object>(2,"银行卡认证失败，请联系客服");
                }
            }else{
                return new JsonResult<Object>(2,"该银行卡已绑定");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult<Object>(2,"添加银行卡失败");
        }
    }

    @ApiOperation(value = "解绑银行卡", notes = "接触银行卡绑定关系")
    @RequestMapping(value = "/deleteBankCard", method = RequestMethod.DELETE)
    public JsonResult deleteBankCard(@RequestHeader("token") String token,
            @ApiParam(value = "银行卡") @RequestParam String bankCardNo){

        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", model.getId()+"");
            paraMap.put("userType","2");
            int count = accountSecurityService.deleteBankCard(paraMap);
            if(count>0){
                return new JsonResult(0,"解绑成功");
            }else{
                return new JsonResult(2,"解绑失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value="查询开户行名称",notes="根据输入的银行卡号查询开户行名称",response = BankCard.class)
    @RequestMapping(value="/queryBankName",method=RequestMethod.GET)
    public JsonResult queryBankName(@RequestHeader("token") String token,
                                    @ApiParam(required=true,value="银行卡") @RequestParam String bankCardNo){
        try {
            JSONObject bankObj = AliyunBankUtils.queryBankcard(bankCardNo);				//查询银行信息
            if(bankObj!=null&&bankObj.getJSONObject("resp")!=null&&
                    bankObj.getJSONObject("resp").getIntValue("code")==0){
                JSONObject dataObj = bankObj.getJSONObject("data");
                return new JsonResult(0,"查询成功", JSONObject.parseObject(dataObj.toJSONString(), BankInfoDto.class));
            }else if(bankObj!=null&&bankObj.getJSONObject("resp")!=null&&
                    bankObj.getJSONObject("resp").getString("desc")!=null){
                return new JsonResult<Object>(2,bankObj.getJSONObject("resp").getString("desc"));
            }else{
                return new JsonResult<Object>(2,"银行卡认证失败，请联系客服");
            }
        } catch (Exception e) {
            logger.debug(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "设置交易密码",notes = "设置交易密码 <br/>transactionPwd：交易密码")
    @RequestMapping(value = "/addTransPwd",method = RequestMethod.POST)
    public JsonResult addTransPwd(@RequestHeader("token") String token,
                                  @ApiParam(value = "交易密码") @RequestParam String transactionPwd,
                                  @ApiParam(required=true,value="手机号码") @RequestParam String phone,
                                  @ApiParam(required=true,value="验证码") @RequestParam String code){
        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("id", model.getId()+"");
            paraMap.put("transactionPwd", transactionPwd);
            if(!phoneCodeService.checkPhoneCode(phone, code)){
                return new JsonResult(2, "短信验证失败或验证码已经失效！请重新获取验证码");
            }
            int count = accountSecurityService.updateTransPwd(paraMap);
            if (count > 0) {
                return new JsonResult(0, "设置交易密码成功");
            } else {
                return new JsonResult(2, "设置交易密码失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "修改交易密码",notes = "修改交易密码,对比原始密码是否正确<br/>oldTransPwd:旧密码<br/>"
            + "transactionPwd：新密码 <br/>")
    @RequestMapping(value = "/modifyTransPwd",method = RequestMethod.POST)
    public JsonResult modifyTransPwd(@RequestHeader("token") String token,
                                     @ApiParam(value = "旧交易密码") @RequestParam String oldTransactionPwd,
                                     @ApiParam(value = "交易密码") @RequestParam String transactionPwd){
//                                     @ApiParam(required=true,value="手机号码") @RequestParam String phone,
//                                     @ApiParam(required=true,value="验证码") @RequestParam String code){
        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("id", model.getId()+"");
            Map<String,Object> pwd = accountSecurityService.queryPwd(paraMap);
//            if(!phoneCodeService.checkPhoneCode(phone, code)){
//                return new JsonResult(2, "短信验证失败或验证码已经失效！请重新获取验证码");
//            }
            if(pwd==null || pwd.get("transPwd")==null){
                return new JsonResult(2,"还未设置交易密码");
            }
            if(oldTransactionPwd.equals(pwd.get("transPwd"))){
                int count = accountSecurityService.updateTransPwd(paraMap);
                if (count > 0) {
                    return new JsonResult(0, "修改交易密码成功");
                } else {
                    return new JsonResult(1, "修改交易密码失败");
                }
            }else{
                return new JsonResult(2,"旧密码不正确,请重新输入");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "找回交易密码",notes = "忘记密码时，可重置交易密码<br/>"
            + "transactionPwd：新密码 <br/> ")
    @RequestMapping(value = "/findTransPwd",method = RequestMethod.POST)
    public JsonResult findTransPwd(@RequestHeader("token") String token,
                                   @ApiParam(required=true,value="交易密码") @RequestParam String transactionPwd,
                                   @ApiParam(required=true,value="手机号码") @RequestParam String phone,
                                   @ApiParam(required=true,value="验证码") @RequestParam String code){
        try {
            if(phoneCodeService.checkPhoneCode(phone, code)){
                TokenModel model = getTokenModel();
                ParaMap paraMap = super.getParaMap();
                paraMap.put("id", model.getId()+"");
                paraMap.put("transactionPwd", transactionPwd);
                int count = accountSecurityService.updateTransPwd(paraMap);
                if (count > 0) {
                    return new JsonResult(0, "设置交易密码成功");
                } else {
                    return new JsonResult(2, "修改交易密码失败");
                }
            }else{
                return new JsonResult(2, "验证码错误或失效");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "修改登录密码",notes = "修改登录密码,对比原始密码是否正确,password")
    @RequestMapping(value = "/modifyLoginPwd",method = RequestMethod.POST)
    public JsonResult modifyLoginPwd(@RequestHeader("token") String token,
                                     @ApiParam(value = "旧密码") @RequestParam String oldPassword,
                                     @ApiParam(value = "密码") @RequestParam String password){

        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("id", model.getId()+"");
            Map<String,Object> pwd = accountSecurityService.queryPwd(paraMap);
            if(oldPassword.equals(pwd.get("pass"))){
                int count = accountSecurityService.updateLoginPwd(paraMap);
                if (count > 0) {
                    return new JsonResult(0, "修改登录密码成功");
                } else {
                    return new JsonResult(2, "修改登录密码失败");
                }
            }else{
                return new JsonResult(2, "旧密码不正确,请重新输入");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "验证交易密码",notes = "验证交易密码是否正确<br/>transactionPwd：交易密码")
    @RequestMapping(value = "/checkTransPwd",method = RequestMethod.POST)
    public JsonResult checkTransPwd(@RequestHeader("token") String token,
                                    @ApiParam(value = "交易密码") @RequestParam("transactionPwd") String transactionPwd){
        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("id", model.getId()+"");
            Map<String,Object> pwd = accountSecurityService.queryPwd(paraMap);
            if(pwd==null || pwd.get("transPwd")==null){
                return new JsonResult(2,"还未设置交易密码");
            }
            if(transactionPwd.equals(pwd.get("transPwd"))){
                return new JsonResult(0,"交易密码正确");
            }else{
                return new JsonResult(2,"交易密码不正确,请重新输入");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

}
