package com.azcx9d.consumer.service.impl;

import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.SendMessage.MobileServerUtils;
import com.azcx9d.common.token.TokenManagerDao;
import com.azcx9d.consumer.dao.ConsumerDao;
import com.azcx9d.consumer.entity.Consumer;
import com.azcx9d.consumer.service.ConsumerApiService;
import com.azcx9d.user.entity.YunpianReaultEntity;
import org.apache.http.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/29 0029.
 */
@Service("consumerApiService")
public class ConsumerApiServiceImpl implements ConsumerApiService {

    @Autowired
    private TokenManagerDao tokenDao;

    @Autowired
    private ConsumerDao dao;

    @Override
    public LoginExecution login(Consumer consumer) throws Exception {
        Consumer outConsumer = dao.selectByIdentify(consumer.getPhone());
        if (outConsumer !=null)
        {
            LoginExecution<Consumer> loginExecution = new LoginExecution<Consumer>(2, "用户不存在");
            return loginExecution;
        }
        else
        {
             consumer.setId(outConsumer.getId());
             outConsumer = dao.selectUser(consumer);
            if (outConsumer != null) {
                TokenModel tokenModel = tokenDao.createToken(outConsumer.getId());
                String token = tokenModel.getUserId() + "_" + tokenModel.getToken();
                outConsumer.setToken(token);

            /*Map<String,Object> lovePercent = dao.queryLovePercent();
            if(lovePercent!=null && lovePercent.size()>0){
                outConsumer.setShanxin(Double.parseDouble((lovePercent.get("percent").toString()))); //设置大盘善心比例
            }else{
                outConsumer.setShanxin(0.0); //设置大盘善心比例
            }*/

                LoginExecution<Consumer> loginExecution = new LoginExecution<Consumer>(0, "登录成功", outConsumer);
                return loginExecution;
            } else {
                LoginExecution<Consumer> loginExecution = new LoginExecution<Consumer>(1, "用户名或密码错误");
                return loginExecution;
            }
        }

    }

    @Override
    public Consumer queryUserInfo(long id) throws Exception {
        return dao.selectUserById(id);
    }

    @Override
    public int doRegister(Consumer consumer) throws Exception {
        return dao.insertUser(consumer);
    }

    @Override
    public YunpianReaultEntity identifyingCode(String phone) throws ParseException, IOException {
        int randNum = (int)((Math.random()*9+1)*100000);
        String text = "【全团了】您的验证码是" + randNum + "。如非本人操作，请忽略本短信";
        YunpianReaultEntity yre = new YunpianReaultEntity();
        yre = MobileServerUtils.singleSend(text, phone, randNum);
        return yre;
    }

    @Override
    public Consumer checkPhone(String phone) throws Exception {
        return dao.checkPhone(phone);
    }

    @Override
    public Consumer checkRecommendPhone(String phone) throws Exception {
        return dao.checkRecommendPhone(phone);
    }

    @Override
    public int resetPassword(Consumer consumer) throws Exception {
        return dao.resetPassword(consumer);
    }
}
