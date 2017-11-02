package com.bp.ribbonconsumer.controller;

import com.bp.ribbonconsumer.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    //@Autowired
    //private RestTemplate restTemplate;

    @Autowired
    private HelloService helloService;


    /**
     * Ribbon默认采用轮询的方式进行调用
     * 从而实现客户端的负载均衡
     * Created by baipan
     * Date: 2017/10/27
     */
    @RequestMapping(value = "/hello-ribbon-consumer/{n}", method = RequestMethod.POST)
    public String helloConsumer(@PathVariable(value = "n", required = false) String n){
        return helloService.helloService(n);
    }


}
