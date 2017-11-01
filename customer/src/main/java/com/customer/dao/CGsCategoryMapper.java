package com.customer.dao;

import com.customer.entity.CGsCategory;

import java.util.List;

public interface CGsCategoryMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CGsCategory record);

    CGsCategory selectByPrimaryKey(Integer id);

    List<CGsCategory> selectAll();

    int updateByPrimaryKey(CGsCategory record);

    List<Integer> selectByParentId(Integer id);
}