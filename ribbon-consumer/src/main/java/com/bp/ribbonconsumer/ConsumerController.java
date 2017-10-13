package com.bp.ribbonconsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by baipan
 * Date: 2017/9/24
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;


    @RequestMapping(value = "/ribbon-consumer/{n}")
    public String helloConsumer(@PathVariable(value = "n", required = false) String n){
        ResponseEntity<String> body = restTemplate.getForEntity("http://hello-service/hello/"+n, String.class);
        String str = body.getBody();
        return str==null?"...":str;
    }
}
