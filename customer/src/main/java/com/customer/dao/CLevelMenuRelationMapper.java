package com.customer.dao;

import com.customer.entity.CLevelMenuRelation;
import java.util.List;

public interface CLevelMenuRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CLevelMenuRelation record);

    CLevelMenuRelation selectByPrimaryKey(Integer id);

    List<CLevelMenuRelation> selectAll();

    int updateByPrimaryKey(CLevelMenuRelation record);
}