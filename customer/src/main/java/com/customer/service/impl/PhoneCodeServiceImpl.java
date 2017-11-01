package com.customer.service.impl;

import com.customer.config.RedisManager;
import com.customer.service.PhoneCodeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HuangQing on 2017/6/26 0026 14:04.
 */
@Service("phoneCodeService")
public class PhoneCodeServiceImpl implements PhoneCodeService {

    @Autowired
    private RedisManager redisManager;

    /**
     * set
     *
     * @param phone 手机号
     * @param code 验证码
     * @return code 验证码
     */
    @Override
    public String setPhoneAndCode(String phone,String code){
        return redisManager.setPhoneAndCode(phone,code);
    }

    /**
     * 验证通过返回true,否则false
     * @param phone
     * @param code
     * @return
     * @throws Exception
     */
    @Override
    public boolean checkPhoneCode(String phone, String code){
        if (StringUtils.isNotBlank(code) && code.equals(redisManager.getPhontCode(phone))){
            return true;
        }
        return false;
    }

}
