package com.bp.ribbonconsumer.command;

import com.bp.ribbonconsumer.entity.User;
import com.netflix.hystrix.HystrixCommand;
import org.springframework.web.client.RestTemplate;

/**
 * 这个类是用原生代码实现的熔断
 * UserService类是用HystrixCommand注解实现的熔断
 */
public class UserCommand extends HystrixCommand<User>{

    private RestTemplate restTemplate;
    private Integer id;

    protected UserCommand(Setter setter, RestTemplate restTemplate, Integer id) {
        super(setter);
        this.restTemplate = restTemplate;
        this.id = id;
    }

    @Override
    protected User run() throws Exception {
        return restTemplate.getForObject("http://hello-service/user/{1}", User.class, id);
    }
}
