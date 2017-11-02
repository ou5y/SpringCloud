package com.bp.ribbonconsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
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
        // 跟getForEntity类似，postForEntity也存在多个重载方法
        // 同时和getForObject一样，postForObject也有多个重载方法
        // 注意点，第二个参数可以是一个普通对象，也可以是HTTPEntity对象
        // 当第二参数非HTTPEntity对象时，会把请求对象转换成一个HTTPEntity对象
        // HTTPEntity对象不仅包含了body内容，也包含了header内容
        // userBody = restTemplate.postForEntity("http://hello-service/getUser", new User(), User.class);
        // 上面这个很坑爹***，试验了不行还是要这样
        // 第二个参数一般是请求头等信息，也可以添加Body数据（报文的方式），UserService中有例子
        // userBody = restTemplate.postForEntity("http://hello-service/getUser?id={1}", HttpEntity, User.class, id);
        // user = userBody.getBody();
        // * * * * * * * * POST请求方式 End * * * * * * * * *

        // * * * * * * * * 其他还有PUT, DELETE请求等

        //System.out.println(user);

        //return str==null?"...":str;
    }

    //这个参数得和helloService一致
    public String helloFallback(String name){
        return String.format("request error：\t%s", name);
    }




}
