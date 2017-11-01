package com.customer.service.impl;

import com.customer.dto.LoginExecution;
import com.customer.dto.UserDto;
import com.customer.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by fangbaoyan on 2017/5/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService userService;
    @Test
    public void login() throws Exception {
        LoginExecution<UserDto> execution=userService.login("15201686857");
        Assert.assertEquals(147767,execution.getData().getId());

    }

}