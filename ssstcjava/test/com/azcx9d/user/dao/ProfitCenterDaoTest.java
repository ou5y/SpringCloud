package com.azcx9d.user.dao;

import com.azcx9d.user.entity.User;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/4/3.
 */
public class ProfitCenterDaoTest extends BaseJunit4Test {



    @Autowired
    private ProfitCenterDao dao;

    @Test
    public void updateUserLovePoints1() throws Exception {

    }

    @Test
    public void queryMarket() throws Exception {

    }

    @Test
    public void getVeryDayMarketTotalMoney() throws Exception {

    }

    @Test
    public void queryUserThreeItem() throws Exception {

    }



    @Test
    public void insertLovePointRecord() throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("totalLovePoint",123);
        map.put("totalLove",123);
        map.put("lovePercentage",11);
        map.put("surplusIntegal",123);
        map.put("expenditure",123+123+123);
        map.put("totalIntegal",123);
        map.put("surplusIntegal",123);
        map.put("oldLove",123);
        map.put("newLove",123);
        dao.insertLovePointRecord(map);
    }

    @Test
    public void updateUserLovePoints() throws Exception {
            User u= new User();
            u.setId(28);
            u.setShandian(100);
            dao.updateUserLovePoints(u);
    }

    @Test
    public void insertMarketCustomer() throws Exception {
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("totalLovePoint",123);
        map.put("totalLove",123);
        map.put("lovePercentage",11);
        map.put("surplusIntegal",123);
        map.put("expenditure",123+123+123);
        map.put("totalIntegal",123);
        map.put("surplusIntegal",123);
        map.put("oldLove",123);
        map.put("newLove",123);
        dao.insertMarketCustomer(map);
    }

    @Test
    public void insertMarketBusiness() throws Exception {
    }

}