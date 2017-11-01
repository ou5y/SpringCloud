package com.azcx9d.business.controller;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.BankInfoDto;
import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BUserService;
import com.azcx9d.business.service.PhoneCodeService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.AliyunBankUtils;
import com.azcx9d.common.util.Page;
import com.azcx9d.user.service.UserApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/23 0023.
 */
@Api(value = "账户安全",description = "银行卡、密码")
@RestController
@RequestMapping(value = "/v1/bApi/account")
public class BAccountSecurityController extends HQBaseController{

    @Autowired
    private BUserService bUserService;

    @Autowired
    UserApiService userApiService;  //仅用来发送验证码，而非操作数据库

    @Autowired
    private PhoneCodeService phoneCodeService;

    /**
     * 商家查看自己的详细信息
     * @return
     */
    @ApiOperation(value = "用户详情", notes = "用户查看自己的详细信息",response=BUser.class)
    @RequestMapping(value = "/detail",method = RequestMethod.GET)
    public JsonResult getUserInfo(@RequestHeader("token") String token) {
        try {
            TokenModel model= getTokenModel();
            int userId = model.getUserId();
            Map<String,Object> params = new HashMap<String,Object>(0);
            params.put("uid",userId);
            Map<String,Object> detail = bUserService.queryUserDetail(params);

            return successMsg(detail);
        } catch (Exception e) {
            e.printStackTrace();
            return super.errorMsg(2,"查询失败，请稍后重试");
        }
    }

    @ApiOperation(value = "修改登录密码",notes = "修改登录密码")
    @RequestMapping(value = "/modifyPass",method = RequestMethod.POST)
    public JsonResult modifyPass(@RequestHeader("token") String token,
                                 @ApiParam(required =true, value = "旧登录密码") @RequestParam String oldPass,
                                 @ApiParam(required =true, value = "新密码") @RequestParam String pass){

        try {
            TokenModel model= getTokenModel();
            int userId = model.getUserId();
            BUser user = bUserService.selectById(userId);
            ParaMap paraMap = super.getParaMap();
            paraMap.put("id", userId);
            if(user==null){ return new JsonResult(2,"用户不存在"); }
            if(!user.getPass().equals(oldPass)){
                return new JsonResult(2,"旧密码不正确，请重新输入旧密码");
            }
            int count = bUserService.updatePassById(paraMap);
            if(count>0){
                return new JsonResult(0,"登录密码修改成功");
            }else{
                return new JsonResult(2,"修改登录密码失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return super.errorMsg(2,"查询失败，请稍后重试");
        }
    }

    @ApiOperation(value = "设置交易密码",notes = "设置交易密码<br/>transactionPwd:交易密码")
    @RequestMapping(value = "/addTransPwd",method = RequestMethod.POST)
    public JsonResult addTransPwd(@RequestHeader("token") String token,
                                  @ApiParam(required =true, value = "交易密码") @RequestParam("transactionPwd") String transactionPwd,
                                  @ApiParam(value = "手机号码") @RequestParam String phone,
                                  @ApiParam(value = "验证码") @RequestParam int code){
        try {
            TokenModel model= getTokenModel();
            int userId = model.getUserId();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("id", userId);
            paraMap.put("transactionPsw", transactionPwd);
            if (phoneCodeService.checkPhoneCode(phone, code+"")) {
                return bUserService.addTransPwd(paraMap);
            }else{
                return new JsonResult(2,"短信验证失败或验证码已经失效！请重新获取验证码");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "修改交易密码",notes = "修改交易密码<br/>oldTransPwd:旧交易密码<br/>transactionPwd:新交易密码")
    @RequestMapping(value = "/modifyTransPwd",method = RequestMethod.POST)
    public JsonResult modifyTransPwd(@ApiParam(required =true, value = "旧交易密码") @RequestParam("oldTransPwd") String oldTransPwd,
                                     @ApiParam(required =true, value = "新交易密码") @RequestParam("transactionPwd") String transactionPwd,
                                     @RequestHeader("token") String token
//                                     @ApiParam(value = "手机号码") @RequestParam String phone,
//                                     @ApiParam(value = "验证码") @RequestParam int code
    ){

        try {
            TokenModel model= getTokenModel();
            int userId = model.getUserId();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("id", userId);
            paraMap.put("transactionPsw", transactionPwd);
//            if (phoneCodeService.checkPhoneCode(phone, code+"")) {
                return bUserService.modifyTransPwd(paraMap);
//            }else{
//                return new JsonResult(2,"短信验证失败或验证码已经失效！请重新获取验证码");
//            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"修改交易密码失败，稍后再试试~~");
        }
    }

    @ApiOperation(value = "找回交易密码",notes = "找回交易密码<br/>phone:电话号码<br/>code:验证码<br/>transactionPwd:交易密码")
    @RequestMapping(value = "/findTransPwd",method = RequestMethod.POST)
    public JsonResult findTransPwd(@RequestHeader("token") String token,
                                   @ApiParam(required =true, value = "电话号码") @RequestParam("phone") String phone,
                                   @ApiParam(required =true, value = "验证码") @RequestParam("code") int code,
                                   @ApiParam(required =true, value = "交易密码") @RequestParam("transactionPwd") String transactionPwd){

        try {
            TokenModel model= getTokenModel();
            if (!phoneCodeService.checkPhoneCode(phone, code+"")) {
                return new JsonResult(2,"短信验证失败或验证码已经失效！请重新获取验证码" );
            }
            int userId = model.getUserId();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("id", userId);
            paraMap.put("transactionPsw", transactionPwd);
            return bUserService.findTransPwd(paraMap);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

//    @ApiOperation(value = "是否已经设置交易密码",notes = "是否已经设置交易密码")
//    @RequestMapping(value = "/hasTransPwd",method = RequestMethod.GET)
//    public JsonResult hasTransPwd(@RequestHeader("token") String token){
//
//        try {
//            TokenModel model= getTokenModel();
//            int userId = model.getUserId();
//            ParaMap paraMap = super.getParaMap();
//            paraMap.put("id", userId);
//            return bUserService.hasTransPwd(paraMap);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
//        }
//    }

    @ApiOperation(value = "我的兑换银行卡", notes = "查询当前登录用户的全部银行卡")
    @RequestMapping(value = "/myBankCard", method = RequestMethod.GET)
    public JsonResult<Object> queryBankCard(@RequestHeader("token") String token){
        JsonResult<Object> result = null;
        List<BankCard> lists = null;
        try {
            TokenModel model = getTokenModel();
            lists = bUserService.queryMyBankCard(model.getUserId());
            if(lists!=null && lists.size()>0){
                result =  new JsonResult<Object>(0,"查询我的银行卡成功",lists);
            }else{
                result =  new JsonResult<Object>(0,"查询我的银行卡成功",lists);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new JsonResult<Object>(2,"服务器出错啦");
        }
        return result;
    }

    @ApiOperation(value = "添加银行卡", notes = "新增银行卡,bankCardNo,phone,code参数必填")
    @RequestMapping(value = "/addBankCard",method = RequestMethod.POST)
    public JsonResult<Object> addBankCard(
            @RequestHeader("token") String token,
            @ApiParam(required=true,value="银行卡号") @RequestParam("bankCardNo") String bankCardNo,
            @ApiParam(required=true,value="姓名") @RequestParam("name") String name,
            @ApiParam(required=true,value="银行编号") @RequestParam String bankId,
            @ApiParam(required=true,value="开户行名称") @RequestParam String bankName,
            @ApiParam(required=true,value="卡名称") @RequestParam String cardName,
            @ApiParam(required=true,value="卡类型") @RequestParam String cardType,
            @ApiParam(value = "手机号码") @RequestParam String phone,
            @ApiParam(value = "验证码") @RequestParam int code,
            @ApiParam(required=false,value="身份证号码") @RequestParam(required=false) String identifyCard){
        JsonResult<Object> result = null;

        try {
            TokenModel model = getTokenModel();

            // 校验验证码是否有效
            if (!phoneCodeService.checkPhoneCode(phone, code+"")) {
                return new JsonResult(2,"短信验证失败或验证码已经失效！请重新获取验证码" );
            }
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("bankCardNo", bankCardNo);
            params.put("bankName", "");
            params.put("userId", model.getUserId()+"");
            params.put("name", name);
            params.put("bankId", bankId);
            params.put("bankName", bankName);
            params.put("cardName", cardName);
            params.put("cardType", cardType);

            if(!bankName.contains("邮")){ return new JsonResult<Object>(2,"只能绑定邮政银行卡，请更换银行卡");}
            BankCard card = bUserService.findBankCard(params);
            if(card==null){
                JSONObject verifyObj = AliyunBankUtils.realNameAuthentication(params);
                if(verifyObj!=null&&verifyObj.getJSONObject("resp")!=null&&
                        verifyObj.getJSONObject("resp").getIntValue("code")==0){
                    int count = bUserService.addBankCard(params);
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
            logger.error(e.getMessage());
            return new JsonResult<Object>(2,"添加银行卡失败");
        }
    }

    @ApiOperation(value = "解绑银行卡", notes = "解除银行卡绑定关系")
    @RequestMapping(value = "/deleteBankCard", method = RequestMethod.DELETE)
    public JsonResult deleteBankCard(@RequestHeader("token") String token,
                                     @ApiParam(required=true,value="银行卡ID") @RequestParam("bankCardId") String bankCardId){

        try {
            TokenModel model = getTokenModel();
            HashMap<String, String> params = new HashMap<String, String>();
            params.put("bankCardId", bankCardId);
            int count = bUserService.deleteBankCard(params);
            if(count>0){
                return new JsonResult(0,"解绑成功");
            }else{
                return new JsonResult(2,"解绑失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "我的分账银行卡", notes = "查询当前登录用户的全部银行卡")
    @RequestMapping(value = "/myClearenceAaccount", method = RequestMethod.GET)
    public JsonResult<Object> queryClearenceAaccount(@RequestHeader("token") String token,
                                                     @ApiParam("businessId") @RequestParam Integer businessId){
        JsonResult<Object> result = null;
        List<Map<String,Object>> lists = null;
        try {
            Map<String,Object> params = new HashMap<String,Object>(0);
            params.put("businessId",businessId);
            lists = bUserService.queryClearanceAccount(params);
            if(lists!=null && lists.size()>0){
                result =  new JsonResult<Object>(0,"查询我的银行卡成功",lists);
            }else{
                result =  new JsonResult<Object>(4,"暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            result = new JsonResult<Object>(2,"服务器出错啦");
        }
        return result;
    }

    @ApiOperation(value = "解绑分账银行卡",notes = "解绑分账银行卡")
    @RequestMapping(value = "/deleteClearenceAccount",method = RequestMethod.DELETE)
    public JsonResult deleteClearenceAccount(@RequestHeader("token") String token,
                                              @ApiParam("id") @RequestParam Integer id){
        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            int count = bUserService.deleteClearanceAccount(paraMap);
            if(count>0){
                return new JsonResult<Object>(0,"查询我的银行卡成功");
            }else{
                return new JsonResult<Object>(2,"解绑失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult<Object>(2,"解绑失败，请稍后重试");
        }
    }

    @ApiOperation(value = "添加分账银行卡",notes = "添加分账银行卡")
    @RequestMapping(value = "/addClearenceAaccount",method = RequestMethod.POST)
    public JsonResult addClearenceAaccount(@RequestHeader("token") String token,
                                           @ApiParam(required=true,value="银行卡号") @RequestParam("bankCardNo") String bankCardNo,
                                           @ApiParam(required=true,value="姓名") @RequestParam("name") String name,
                                           @ApiParam(required=true,value="银行编号") @RequestParam String bankId,
                                           @ApiParam(required=true,value="开户行名称") @RequestParam String bankName,
                                           @ApiParam(required=true,value="卡名称") @RequestParam String cardName,
                                           @ApiParam(required=true,value="卡类型") @RequestParam String cardType,
                                           @ApiParam(value = "店铺id") @RequestParam Integer businessId,
                                           @ApiParam(value = "支行名称") @RequestParam String branchName,
                                           @ApiParam(required =true, value = "电话号码") @RequestParam("phone") String phone,
                                           @ApiParam(required =true, value = "验证码") @RequestParam("code") int code
                                           ){
        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId",model.getUserId());
            paraMap.put("cardHolder",name);
            if (!phoneCodeService.checkPhoneCode(phone, code+"")) {
                return new JsonResult(2,"短信验证失败或验证码已经失效！请重新获取验证码" );
            }
            int total = bUserService.queryClearanceAccountCount(paraMap);
            if(total>0){ return new JsonResult<Object>(2,"只能绑定一张分账银行卡");}
            JSONObject verifyObj = AliyunBankUtils.realNameAuthentication(paraMap);
            if(verifyObj!=null&&verifyObj.getJSONObject("resp")!=null&&
                    verifyObj.getJSONObject("resp").getIntValue("code")==0){
                int count = bUserService.addClearanceAccount(paraMap);
                if(count>0){
                    return new JsonResult<Object>(0,"添加分账银行卡成功");
                }else{
                    return new JsonResult<Object>(2,"绑卡失败");
                }
            }else if(verifyObj!=null&&verifyObj.getJSONObject("resp")!=null&&
                    verifyObj.getJSONObject("resp").getString("desc")!=null){
                return new JsonResult<Object>(2,verifyObj.getJSONObject("resp").getString("desc"));
            }else{
                return new JsonResult<Object>(2,"银行卡认证失败，请联系客服");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult<Object>(2,"添加银行卡失败，请稍后重试");
        }
    }

    @ApiOperation(value="查询开户行名称",notes="根据输入的银行卡号查询开户行名称")
    @RequestMapping(value="/queryBankName",method=RequestMethod.GET)
    public JsonResult queryBankName(@RequestHeader("token") String token,
                                    @ApiParam(required=true,value="银行卡号") @RequestParam String bankCardNo){

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

    @ApiOperation(value = "选择店铺",notes = "选择店铺")
    @RequestMapping(value = "/getBussinessList",method = RequestMethod.GET)
    public JsonResult getBussinessList(@RequestHeader("token") String token,
                                       @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                       @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize){
        try {
            TokenModel model = getTokenModel();
            Map<String,Object> params = new HashMap<String, Object>();
            params.put("userId", model.getUserId());
            params.put("pageSize", pageSize);
            Page page = new Page(currentPage,pageSize);
            page = bUserService.getBussinessList(params,page);
            page.init();
            return new JsonResult(0, "查询店铺成功",page);
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
            paraMap.put("id", model.getUserId() + "");

            Map<String,Object> pwd = bUserService.queryPwd(paraMap);
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
