package com.customer.dao;

import com.customer.entity.CGsAttValue;
import java.util.List;

public interface CGsAttValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CGsAttValue record);

    CGsAttValue selectByPrimaryKey(Integer id);

    List<CGsAttValue> selectAll();

    int updateByPrimaryKey(CGsAttValue record);
}