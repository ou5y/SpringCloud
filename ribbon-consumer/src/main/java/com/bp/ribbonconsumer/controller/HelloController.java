package com.bp.ribbonconsumer.controller;

import com.alibaba.fastjson.JSON;
import com.bp.ribbonconsumer.entity.User;
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



        // * * * * * * * * GET请求方式 * * * * * * * * *
        // A1 ↓
        //ResponseEntity<String> body = restTemplate.getForEntity("http://hello-service/hello/"+n, String.class);
        //ResponseEntity<String> body = restTemplate.getForEntity("http://hello-service/hello/{1}", String.class, n);
        //String str = body.getBody();

        // A2 ↓
        //ResponseEntity<User> userBody = restTemplate.getForEntity("http://hello-service/getUser?id={1}", User.class, 123L);
        //User user = userBody.getBody();

        // A3 ↓
        //Map<String, Object> map = new HashMap<>();
        //map.put("name", "name");
        //map.put("age", "age");
        //userBody = restTemplate.getForEntity("http://hello-service/getUser?name={name}&age={age}", User.class, map);
        //user = userBody.getBody();

        // A4 ↓
        //UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://hello-service/getUser?name={name}")
        //        .build()
        //        .expand("王麻子")
        //        .encode();
        //URI uri = uriComponents.toUri();
        //userBody = restTemplate.getForEntity(uri, User.class);
        //user = userBody.getBody();

        // B1 ↓
        // getForObject相当于对getForEntity的进一步封装，通过HTTPMessageConverterExtractor对HTTP的请求响应体body内容进行对象转换，实现请求直接返回包装好的对象内容，其他使用无大差异
        // getForObject还存在多个重载方法
        //user = restTemplate.getForObject("http://hello-service/getUser?id={1}", User.class, 123L);
        // * * * * * * * * GET请求方式 End * * * * * * * * *


        // * * * * * * * * POST请求方式 * * * * * * * * *
        // 跟getForObject类似，postForEntity也存在多个重载方法
        // 同时和getForObject一样，postForObject也有多个重载方法
        // 注意点，第二个参数可以是一个普通对象，也可以是HTTPEntity对象
        // 当第二参数非HTTPEntity对象时，会把请求对象转换成一个HTTPEntity对象
        // HTTPEntity对象不仅包含了body内容，也包含了header内容
        //userBody = restTemplate.postForEntity("http://hello-service/getUser", new User(), User.class);
        //user = userBody.getBody();
        // * * * * * * * * POST请求方式 End * * * * * * * * *

        // * * * * * * * * 其他还有PUT, DELETE请求等

        //System.out.println(user);

        //return str==null?"...":str;
    }


}
