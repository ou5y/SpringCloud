package com.azcx9d.consumer.service;

import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.consumer.entity.Consumer;
import com.azcx9d.user.entity.YunpianReaultEntity;
import org.apache.http.ParseException;

import java.io.IOException;

/**
 * Created by chenxl on 2017/3/29 0029.
 */
public interface ConsumerApiService {
    /**
     * 用户端登陆
     * @param consumer
     * @return
     */
    LoginExecution login(Consumer consumer) throws Exception;

    /**
     * 用户信息
     * @param id
     * @return
     */
    Consumer queryUserInfo(long id) throws Exception;

    /**
     *用户注册
     * @param consumer
     */
    int doRegister(Consumer consumer) throws Exception;

    /**
     * 验证码
     * @param phone
     * @return
     */
    YunpianReaultEntity identifyingCode(String phone) throws ParseException, IOException;

    Consumer checkPhone(String phone) throws Exception;

    Consumer checkRecommendPhone(String phone) throws Exception;

    /**
     *忘记密码
     * @param consumer
     */
    int resetPassword(Consumer consumer) throws Exception;
}
