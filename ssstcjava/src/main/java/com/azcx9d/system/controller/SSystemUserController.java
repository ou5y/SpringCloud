package com.azcx9d.system.controller;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.system.entity.SSystemUser;
import com.azcx9d.system.redis.SysUserTokenManager;
import com.azcx9d.system.service.SSystemUserService;
import com.azcx9d.system.util.MD5;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by HuangQing on 2017/4/6 0006 16:31.
 */
@RestController
@RequestMapping("/sApi/sysuser")
@Api(value = "系统用户登录",description="登录")
public class SSystemUserController extends HQBaseController {

    @Autowired
    private SSystemUserService systemUserService;

    @Autowired
    private SysUserTokenManager sysUserTokenDAO;

    @RequestMapping(value = "/login")
    @ApiOperation(value = "管理员登录", notes = "管理员登录",response=SSystemUser.class)
    public Object login(@ApiParam(required = true,value = "管理员账号")@RequestParam("username") String username,
                        @ApiParam(required = true,value = "管理员密码密码")@RequestParam("password") String password){

        ParaMap paraMap = super.getParaMap();
        if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
            return super.errorMsg(1,"账号密码不能为空");
        }
        paraMap.put("password", MD5.md5(password));
        try {
            SSystemUser dbUser = systemUserService.selectByUsername(paraMap);
            if(dbUser==null){   //用户名不存在
                return super.errorMsg(1,"账号或密码错误");
            }
            if(!dbUser.getPassword().equals(paraMap.getString("password"))){ //用户密码输入错误
                return super.errorMsg(1,"账号或密码错误");
            }
            dbUser.setPassword(null);
            TokenModel tokenModel = sysUserTokenDAO.createToken(dbUser.getId());
            String token = tokenModel.getUserId() + "_" + tokenModel.getToken();
            dbUser.setToken(token);
            return super.successMsg(dbUser);
        } catch (Exception e) {
            e.printStackTrace();
            return super.errorMsg(500,"服务器异常");
        }
    }

}
