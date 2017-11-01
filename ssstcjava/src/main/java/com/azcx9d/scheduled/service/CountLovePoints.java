package com.azcx9d.scheduled.service;

import com.azcx9d.user.dao.ProfitCenterDao;
import com.azcx9d.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

/**
 * Created by fangbaoyan on 2017/4/5.
 */
public class CountLovePoints implements Runnable{

    private Logger logger = LoggerFactory.getLogger(CountLovePoints.class);


    private ProfitCenterDao dao;

    private User user;

    private float lovePercentage;


    public CountLovePoints(ProfitCenterDao dao, User user, float lovePercentage) {
        this.dao = dao;
        this.user = user;
        this.lovePercentage = lovePercentage;
    }

    @Override
    public void run() {

        double love= user.getShanxin();

        double lovePoints = lovePercentage*love;

        user.setShandian(lovePoints);

        try {
            dao.updateUserLovePoints(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        logger.info("用户："+user.getId()+"善点新增："+lovePoints);

        //dao.insertLovePointRecord(user);

    }
}
