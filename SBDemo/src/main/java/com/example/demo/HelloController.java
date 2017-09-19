package com.example.demo;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by baipan
 * Date: 2017/9/19
 */
@RestController
public class HelloController {

    @RequestMapping("hello/{n}")
    public String hello(@PathVariable(value = "n", required = false) String n){
        return "hello" + (n==null?"...":n);
    }
}
