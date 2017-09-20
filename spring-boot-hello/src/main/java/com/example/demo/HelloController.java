package com.example.demo;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by baipan
 * Date: 2017/9/19
 */
@RestController
public class HelloController {


    private final Logger logger = Logger.getLogger(getClass());

    @Autowired
    private DiscoveryClient client;

    @RequestMapping("hello/{n}")
    public String hello(@PathVariable(value = "n", required = false) String n){

        ServiceInstance instance = client.getLocalServiceInstance();
        logger.info("hello，host：" + instance.getHost() + "，port：" + instance.getPort() + "，serviceName：" + instance.getServiceId());

        return "hello" + (n==null?"...":n);
    }
}
