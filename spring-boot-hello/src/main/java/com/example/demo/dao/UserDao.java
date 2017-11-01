package com.example.demo.dao;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {

    List<User> query();

    User get(@Param("id")Integer id);
}
