package com.azcx9d.business.controller;

import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.UserDto;
import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BUserService;
import com.azcx9d.business.service.DeviceUserService;
import com.azcx9d.business.service.PhoneCodeService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.user.entity.Notice;
import com.azcx9d.user.entity.YunpianReaultEntity;
import com.azcx9d.user.service.UserApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by HuangQing on 2017/3/29 0029.
 */
@Api(value = "商户登录、找回密码", description = "登录、找密")
@RestController
@RequestMapping("/v1/bApi/user")
public class BUserApiController extends HQBaseController {

    @Autowired
    private BUserService bUserService;

    @Autowired
    UserApiService userApiService;  //仅用来发送验证码，而非操作数据库

    @Autowired
    private PhoneCodeService phoneCodeService;

    @Autowired
    private DeviceUserService deviceUserService;

    /**
     *
     * @param phone 商户账号
     * @param password 商户密码
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value = "商家登录", notes = "商家登录",response=UserDto.class)
    public LoginExecution<UserDto> login(@ApiParam(required = true,value = "商户账号")@RequestParam("phone") String phone,
                                         @ApiParam(required = true,value = "密码")@RequestParam("password") String password,
                                         @ApiParam(required=false,value="设备编号,MD5加密后的32位字符串") @RequestParam(required = false) String deviceNo,
                                         @ApiParam(required=false,value="来源,0：未知   1：IOS   2:Android") @RequestParam(required = false) String source){
        BUser buser = new BUser(phone, password);
        LoginExecution<UserDto> execution;
        try {
            execution = bUserService.login(buser,deviceNo,source);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return execution = new LoginExecution(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
        return execution;
    }



    /**
     * 发送验证码
     * @param phone : 用于查询商家是否存在再决定发送验证码
     * @return
     */
    @ApiOperation(value = "获取验证码", notes = "获取验证码")
    @RequestMapping(value = "/getCode" ,method = RequestMethod.GET)
    public JsonResult getCode(@ApiParam(required = true,value = "商户账号")@RequestParam("phone") String phone){
//        BUser user = new BUser();
//        user.setPhone(phone);
//        BUser dbUser = null;
//        try {
//            dbUser = bUserService.selectByPhone(user);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return super.errorMsg(2,"服务器异常");
//        }
//        if (dbUser==null){
//            //该商户不存在
//            return super.errorMsg(1,"该商户不存在");
//        }

        YunpianReaultEntity yre;
        try {
            yre = userApiService.identifyingCode(phone);
            if(yre.getCode()!=0)
            {
                if(yre.getCode()==33){yre.setMsg("您操作太频繁了，请稍后再重新获取验证码！");}
                if(yre.getCode()==3){yre.setMsg("短信发送失败,请联系客服，400-9612606");}
                logger.debug("code:"+yre.getCode()+",message"+yre.getMsg());
                return new JsonResult(yre.getCode(),yre.getMsg());
            }else
            {
                return new JsonResult(0,"OK");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"服务器异常");
        }
    }

    /**
     * 校验验证码
     * @param phone 接收验证码的手机号
     * @param code 手机收到的验证码
     * @return 校验结果
     */
    @ApiOperation(value = "校验验证码", notes = "校验验证码")
    @RequestMapping(value = "/checkCode",method = RequestMethod.POST)
    public JsonResult checkCode(@ApiParam(required =true, value = "商户手机号")@RequestParam("phone")String phone,
                                @ApiParam(required =true, value = "验证码")@RequestParam("code")int code){
        if (phoneCodeService.checkPhoneCode(phone, code+"")) {
            return new 	JsonResult(0,"ok");
        }
        return new 	JsonResult(2,"验证码错误或已失效!");
    }

    @ApiOperation(value = "根据手机号码获取短信验证码(网页端专用,APP勿用)",notes = "根据手机号码获取短信验证码(网页端专用,APP勿用)")
    @RequestMapping(value = "/getPhoneCode",method = RequestMethod.GET)
    public JsonResult getPhoneCode(@ApiParam(value = "手机号码") @RequestParam String phone){
        if(phone.length()!=11){
            return new JsonResult(2,"手机号码不正确，请重新输入");
        }
        //YunpianReaultEntity yre;
        try {
            int randNum = (int)((Math.random()*9+1)*100000);
            //yre = userApiService.identifyingCodeByphone(phone, randNum);
            phoneCodeService.setPhoneAndCode(phone, randNum+"");
            return new JsonResult(0,"OK", randNum);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"服务器异常");
        }

    }

    /**
     * 重置密码
     * @param code 验证码
     * @return
     */
    @ApiOperation(value = "找回登录密码", notes = "重置商户登录密码")
    @RequestMapping(value = "/resetPass",method = RequestMethod.POST)
    public JsonResult resetPass(@ApiParam(required = true,value = "商户账号")@RequestParam("phone") String phone,
                                @ApiParam(required = true,value = "密码")@RequestParam("password") String password,
                                @ApiParam(required = true,value = "验证码")@RequestParam("code") int code){
        BUser buser = new BUser(phone,password);
        if (StringUtils.isBlank(buser.getPhone())||StringUtils.isBlank(buser.getPass())){
            return super.errorMsg(2,"手机号码或密码不能为空");
        }
        if (!phoneCodeService.checkPhoneCode(buser.getPhone(), code+"")){//验证码错误或过期
            return super.errorMsg(2,"验证码错误或过期");
        }
        try {
            bUserService.updatePassByPhone(buser);
            return super.successMsg();
        } catch (Exception e) {
            e.printStackTrace();
            return super.errorMsg(2,"服务器异常");
        }
    }

    /**
     * 退出登录
     * @return
     */
    @ApiOperation(value = "退出登录", notes = "商户退出登录")
    @RequestMapping(value = "/logout",method = RequestMethod.DELETE)
    @ApiIgnore
    public JsonResult logout(@RequestHeader("token") String token) {
        TokenModel model=tokenDao.getToken(token);
        if (token==null||model==null){
            return super.errorMsg(2,"操作失败");
        }
        tokenDao.deleteToken(model.getToken());
        return new JsonResult(0,"ok");
    }

    @ApiOperation(value="查询版本更新信息", notes="查询版本更新信息appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
            + "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>")
    @RequestMapping(value="/versionInfo", method=RequestMethod.GET)
    public JsonResult queryVersionInfo(@ApiParam(required=true,value="app类型") @RequestParam(defaultValue="1") int appType){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("appType", appType);
            Map<String,Object> results = bUserService.queryVersionInfo(params);
            return new JsonResult(0, "查询更新信息成功",(results!=null?results:new HashMap<String, Object>(0)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value="查询版本更新信息(兼容第一个版本不加版本号)", notes="查询版本更新信息appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
            + "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>")
    @RequestMapping(value="/versionCompatible", method=RequestMethod.GET)
    public JsonResult queryVersionInfoFirst(@ApiParam(required=true,value="app类型") @RequestParam(defaultValue="1") int appType){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("appType", appType);
            Map<String,Object> results = bUserService.queryVersionInfo(params);
            return new JsonResult(0, "查询更新信息成功",(results!=null?results:new HashMap<String, Object>(0)));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value="查询公告信息", notes = "查询系统公告",response = Notice.class)
    @RequestMapping(value = "/queryNotice", method = RequestMethod.GET)
    public JsonResult queryNotice(@ApiParam(value = "APP类型：1：Android商户端 2 :Android代理端3：Android用户端 4：IOS商户端 5：iOS代理端 6：IOS用户端",required = true) @RequestParam int appType){
        try {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("appType", appType);
            Notice notice = bUserService.queryNotice(params);
            return new JsonResult(0, "查询更新信息成功",notice);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"查询失败，稍后再试试~~");
        }
    }

    @RequestMapping(value = "/register",method = RequestMethod.POST )
    @ApiOperation(value="用户注册", notes="创建用户")
    public JsonResult register(
            @ApiParam(value = "电话",required = true) @RequestParam(value = "phone",required = true) String phone,
            @ApiParam(value = "验证码",required = true) @RequestParam(value = "checkCode",required = true) int checkCode,
            @ApiParam(value = "登陆密码",required = true) @RequestParam(value = "password",required = true) String password,
            @ApiParam(value = "推荐人手机号码",required = false) @RequestParam(value = "recommendPhone",required = false) String recommendPhone,
            @ApiParam(value = "姓名",required = false) @RequestParam(value = "userName",required = false) String userName) {

        ParaMap paraMap = super.getParaMap();

        try {
            if (phoneCodeService.checkPhoneCode(phone, checkCode+"")) {
                return bUserService.addUser(paraMap);
            }else{
                return new JsonResult(2,"短信验证失败或验证码已经失效！请重新获取验证码" );
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"注册失败，请稍后重试");
        }
    }

    @ApiOperation(value = "是否是可用设备",notes = "是否是可用设备")
    @RequestMapping(value = "/isEnableDevice",method = RequestMethod.GET)
    public JsonResult isEnableDevice(@ApiParam(value = "设备号,MD5加密后的32位字符串") @RequestParam String deviceNo,
                                     @ApiParam(value="来源,0：未知   1：IOS   2:Android") @RequestParam String source){
        ParaMap paraMap = super.getParaMap();
        Integer isEnable = deviceUserService.queryIsEnable(paraMap);
        if(isEnable!=null && isEnable == 1){
            return new JsonResult(0,"设备可用");
        }else{
            return new JsonResult(2,"该设备已被拉入黑名单",isEnable);
        }
    }

}
