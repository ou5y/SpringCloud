package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
@Api(value = "用户",description = "用户接口")
public class UserController {


    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private UserService userService;


    @ApiOperation(value = "获取用户信息", notes = "根据url的id来获取用户对象")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "String"),
    })
    @RequestMapping(value = "user/{id}", method = RequestMethod.POST)
    public JSONObject user(@PathVariable(value = "id", required = false) String id) {
        JSONObject jsonObject = new JSONObject();
        if (StringUtils.isEmpty(id)) {
            jsonObject.put("error", "id参数为空");
        } else {
            try {
                User user = userService.get(Integer.valueOf(id));
                if (user == null) {
                    jsonObject.put("error", "未找到ID=" + id + "的User信息");
                } else {
                    jsonObject.put("user", user);
                }
            } catch (Exception e) {
                e.printStackTrace();
                jsonObject.put("error", e.getMessage());
            }
        }
        return jsonObject;
    }
}
