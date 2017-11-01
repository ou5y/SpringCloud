package com.azcx9d.scheduled.service;

import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.Business;
import com.azcx9d.common.entity.OrderForm;
import com.azcx9d.user.dao.ProfitCenterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 代理收益结算
 * Created by fangbaoyan on 2017/4/6.
 */
@Component
public class AgentProfitCenterTask {
    /**
     * 收益结算
     */
    private Logger logger = LoggerFactory.getLogger(AgentProfitCenterTask.class);

    @Autowired
    private ProfitCenterDao dao;

    public void areaAndJobAgent() {

         long orderId = 0;

        OrderForm oForm = dao.orderFormDetail(orderId);

         double baseMoney = oForm.getMoney();//消费金额

        Business business = dao.queryBusinessDetail(oForm.getStoreId());


        Map<String,Object> uaMap = new HashMap<String ,Object>();

        if (!business.getAreaId().equals("0"))
        {
            uaMap.put("areaId",business.getAreaId());
        }

        if (!business.getOperateType().equals("0"))
        {
            uaMap.put("tradeId",business.getOperateType());
        }

        uaMap.put("userId",business.getUploadUser());


        Agency agency=dao.queryUploadUserInfo(uaMap);





    }

}
