package com.azcx9d.scheduled.service;

import com.azcx9d.agency.entity.Business;
import com.azcx9d.scheduled.dto.OderFormDto;
import com.azcx9d.user.dao.ProfitCenterDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by fangbaoyan on 2017/4/10.
 */
@Component
public class BusinessMaxAmount {


    private Logger logger = LoggerFactory.getLogger(BusinessMaxAmount.class);



    public static final int flag=7;


    @Autowired
    private ProfitCenterDao dao;

    /**
     * 每周一零点30统计上周商家的店铺消费金额
     * 如果上周每天的消费的总额都达到封顶限额，则将封顶限额*2
     */
//    @Scheduled(cron = "0 30 0 ? * MON")
    public void doAdjust()
    {
        List<OderFormDto> list = null;
        list=dao.queryStatisticsList();
        if(null!=list && list.size()>0)
        {
            for (OderFormDto oderFormDto:list)
            {
                long maxAmount=oderFormDto.getMaxAmount();
                if (maxAmount<=100000)//每个商家每天最高额度10W
                {
                    if (oderFormDto.getMoney()==maxAmount*flag)
                    {
                        Business b = new Business();
                        b.setId(oderFormDto.getBusinessId());
                        BigDecimal adjustNum = new BigDecimal(maxAmount*2);
                        b.setMaxAmount(adjustNum);
                        int result=dao.doAdjust(b);
                        logger.info("商家："+oderFormDto.getBusinessId()+"调整限额为："+adjustNum+"调整条数："+result);
                    }
                }


            }
        }
    }
}
