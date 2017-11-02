package com.bp.ribbonconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.bp.ribbonconsumer.entity.User;
import com.bp.ribbonconsumer.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.Future;

@RestController
@Api(value = "用户",description = "用户接口")
public class UserController {

    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取用户信息POST", notes = "通过@HystrixCommand注解同步请求服务")
    @RequestMapping(value = "/user-sync-post", method = RequestMethod.POST)
    public String getUserBySyncPOST(@ApiParam(value = "用户ID", required = true) @RequestParam Integer id){
        User user = userService.getUserBySyncPOST(id);
        return user==null||user.getId()==null ? "..." : JSON.toJSONString(user);
    }


    @ApiOperation(value = "获取用户信息GET", notes = "通过@HystrixCommand注解同步请求服务s")
    @RequestMapping(value = "/user-sync-get", method = RequestMethod.POST)
    public String getUserBySyncGET(@ApiParam(value = "用户ID", required = true) @RequestParam Integer id){
        User user = userService.getUserBySyncGET(id);
        return user==null||user.getId()==null ? "..." : JSON.toJSONString(user);
    }


    @ApiOperation(value = "获取用户信息POST", notes = "通过@HystrixCommand注解异步请求服务")
    @RequestMapping(value = "/user-async-post", method = {RequestMethod.POST})
    public String getUserByAsyncPOST(@ApiParam(value = "用户ID", required = true) @RequestParam Integer id){
        try {
            Future<User> future = userService.getUserByAsyncPOST(id);
            User user = future.get();
            return user ==null ? "..." : JSON.toJSONString(user);
        }catch (Exception e){
            e.printStackTrace();
            return e.getMessage();
        }
    }

//    //通过@HystrixCommand注解异步请求服务
//    @RequestMapping(value = "/user-observer-post", method = RequestMethod.POST)
//    public String getUserByObservablePOST(HttpServletRequest request){
//        Integer id = Integer.parseInt(request.getParameter("id"));
//        Observable<User> observable = userService.getUserByObservable(id);
//        return user ==null ? "..." : JSON.toJSONString(user);
//    }

}
