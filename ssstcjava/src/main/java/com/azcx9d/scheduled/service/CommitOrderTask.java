package com.azcx9d.scheduled.service;

import com.azcx9d.common.jpush.JpushClientUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by Administrator on 2017/7/21 0021.
 */

@Component("commitOrderTask")
public class CommitOrderTask {

    @Scheduled(cron = "0 30 21 * * ?")
    @Transactional(rollbackFor = Exception.class)
    public void pushMessage(){
//        System.out.println("定时任务");
//        String alias = "53bf4de8a65c4108827775e0f46a938b";
        String appType = "1";
        String body = "尊敬的“全团了”商户，为了创造更好的收益，实现真正的互创互赢，全团了“小助手”，提醒您尽快盘点并提交今天订单。";
        JpushClientUtil.init(appType);
        int code = JpushClientUtil.sendToAll(null,null,body,null);
//        int code = JpushClientUtil.sendToAlias(alias,null,null,body,null);

        if(code==1){
            // 推送成功
            System.out.println("推送成功");
        }else{
            // 推送失败
            System.out.println("推送失败");
        }
    }

}
