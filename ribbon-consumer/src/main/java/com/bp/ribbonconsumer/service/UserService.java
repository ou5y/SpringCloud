package com.bp.ribbonconsumer.service;

import com.bp.ribbonconsumer.entity.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Hystrix默认超时为2000ms，可以设置
     */
    @HystrixCommand(fallbackMethod = "helloFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    public User getUserById(Integer id){
        //同步方式请求数据
        return restTemplate.getForObject("http://hello-service/user/{1}", User.class, id);
    }


    @HystrixCommand(fallbackMethod = "helloFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    public Future<User> getUserByIdAsync(Integer id){
        //异步的方式请求数据
        return new AsyncResult<User>() {
            @Override
            public User invoke() {
                return restTemplate.getForObject("http://hello-service/user/{1}", User.class, id);
            }
        };
    }

    public String helloFallback(Integer id){
        return String.format("request error：\t%s", id);
    }

}
