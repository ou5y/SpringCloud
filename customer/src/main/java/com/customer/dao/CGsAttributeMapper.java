package com.customer.dao;

import com.customer.entity.CGsAttribute;
import java.util.List;

public interface CGsAttributeMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CGsAttribute record);

    CGsAttribute selectByPrimaryKey(Integer id);

    List<CGsAttribute> selectAll();

    int updateByPrimaryKey(CGsAttribute record);
}