//package com.azcx9d.system.validator;
//
//import com.azcx9d.common.entity.JsonResult;
//import com.azcx9d.system.annotation.Validator;
//import com.azcx9d.system.redis.SysUserTokenManager;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by HuangQing on 2017/4/8 0008 16:39.
// */
//public class PermissionValidator extends Validator {
//
//    @Autowired
//    private SysUserTokenManager sysUserTokenDAO;    // TODO 注入bean之后
//
//    JsonResult data;
//
//    @Override
//    protected boolean validate(HttpServletRequest request) {
////        System.err.println("====================================================");
////        System.out.println(sysUserTokenDAO);
////        data = new JsonResult(403,"没有权限");
//        return true;
//    }
//
//    @Override
//    protected JsonResult handleError() {
//        return data;
//    }
//}
