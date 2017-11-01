package com.example.demo.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
public class HelloController {


    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("/hello/{n}")
    public String hello(@PathVariable(value = "n", required = false) String n){

        ServiceInstance instance = client.getLocalServiceInstance();

        int sleepTime = new Random().nextInt(3000);
        System.out.println(String.format("sleepTime：\t%d", sleepTime));
        try {
            Thread.sleep(sleepTime);
        }catch (Exception e){
            e.printStackTrace();
        }

        if (n == null){
            System.out.println("name Is Null 是空的");
            return "...";
        }




        String info = "hello，" + n + "host：" + instance.getHost() + "，port：" + instance.getPort() + "，serviceName：" + instance.getServiceId();
        System.out.println(info);
        return info;
    }
}
