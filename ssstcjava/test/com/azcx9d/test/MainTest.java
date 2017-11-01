package com.azcx9d.test;

import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.service.BUserService;
import com.azcx9d.user.dao.BaseJunit4Test;
import org.junit.Test;

import javax.annotation.Resource;

public class MainTest extends BaseJunit4Test{

    @Test
    public void operateCode(){
        System.out.println("12312321");
    }

    @Resource
    private BUserService bUserService;

    @Test
    public void selectByPhone() throws Exception{
        BUser bUser = new BUser();
        System.out.println(bUser);
        bUser.setPhone("15600046101");
        bUser = bUserService.selectByPhone(bUser);
        System.out.println(bUser);
    }

}
