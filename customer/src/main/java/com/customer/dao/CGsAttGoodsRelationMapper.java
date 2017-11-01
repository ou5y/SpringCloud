package com.customer.dao;

import com.customer.entity.CGsAttGoodsRelation;
import java.util.List;

public interface CGsAttGoodsRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CGsAttGoodsRelation record);

    CGsAttGoodsRelation selectByPrimaryKey(Integer id);

    List<CGsAttGoodsRelation> selectAll();

    int updateByPrimaryKey(CGsAttGoodsRelation record);
}