package com.azcx9d.consumer.dao;

import com.azcx9d.consumer.entity.Consumer;
import com.azcx9d.user.dao.UserDao;

import java.sql.SQLException;
import java.util.Map;

/**
 * Created by chenxl on 2017/3/29 0029.
 */
public interface ConsumerDao {

    Consumer selectByIdentify(String phone) throws SQLException;

    Consumer selectUser(Consumer consumer) throws SQLException;

    Consumer selectUserById(long id) throws SQLException;

    int insertUser(Consumer consumer) throws SQLException;

    Consumer checkPhone(String phone) throws SQLException;

    Consumer checkRecommendPhone(String phone) throws SQLException;

    int resetPassword(Consumer consumer) throws SQLException;

    Map<String,Object> queryLovePercent() throws SQLException;
}
