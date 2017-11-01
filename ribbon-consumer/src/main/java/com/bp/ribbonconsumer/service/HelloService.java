package com.bp.ribbonconsumer.service;

import com.bp.ribbonconsumer.entity.User;
import com.netflix.hystrix.HystrixObservableCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

@Service
public class HelloService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "helloFallback",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
            })
    //Hystrix默认超时为2000ms，可以设置
    public String helloService(String name){
        long start = System.currentTimeMillis();
        System.out.println("start");

        //这是使用的同步的方式请求
        ResponseEntity<String> body = restTemplate.getForEntity("http://hello-service/hello/{1}", String.class, name);
        String str = body.getBody();

        long end = System.currentTimeMillis();
        System.out.println("Spend time：\t" + (end - start));
        System.out.println("end");

        System.out.println(str);
        return str==null?"...":str;
    }

    //这个参数得和helloService一致
    public String helloFallback(String name){
        return String.format("request error：\t%s", name);
    }


    /**
     * Hystrix默认超时为2000ms，可以设置
     */
    @HystrixCommand(fallbackMethod = "userBack22",
            groupKey = "user22",
            commandKey = "getUserBySync22",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "20000")
            })
    public User getUserBySync22(Integer id){
        System.out.println("888888888888");
        //同步方式请求数据
        System.out.println(id);
        User user = new User(id);
        //return restTemplate.postForObject("http://hello-service/user", user, User.class);

        ResponseEntity<String> body = restTemplate.getForEntity("http://hello-service/hello/{1}", String.class, id.toString());
        user.setName(body.getBody());
        return user;
    }


    public User userBack22(Integer id){
        return new User();
    }

}
