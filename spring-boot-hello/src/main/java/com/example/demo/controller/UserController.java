package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Random;


@RestController
@Api(value = "用户",description = "用户接口")
public class UserController {


    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取用户信息", notes = "根据url的id来获取用户对象")
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public Object user(@ApiParam(value = "用户ID") @RequestParam(required = false) Integer id) {
        System.out.println("收到参数："+id);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(id)) {
            jsonObject.put("error", "id参数为空");
        } else {
            try {
                User user = userService.get(id);
                if (user == null) {
                    jsonObject.put("error", "未找到ID=" + id + "的User信息");
                } else {
                    System.out.println("返回信息：");
                    System.out.println(JSON.toJSONString(user));
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error", e.getMessage());
            }
        }
        return jsonObject;
    }


    @RequestMapping(value = "/getUsers/{n}")
    public Object users(@PathVariable(value = "n", required = false) String id) {
        System.out.println("收到参数s："+id);
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(id)) {
            jsonObject.put("error", "id参数为空");
        } else {
            try {
                User user = userService.get(Integer.valueOf(id));
                if (user == null) {
                    jsonObject.put("error", "未找到IDs=" + id + "的User信息");
                } else {
                    System.out.println("返回信息s：");
                    System.out.println(JSON.toJSONString(user));
                    return user;
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error", e.getMessage());
            }
        }
        return jsonObject;
    }
}
