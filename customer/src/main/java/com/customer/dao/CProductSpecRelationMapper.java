package com.customer.dao;

import com.customer.entity.CProductSpecRelation;
import java.util.List;

public interface CProductSpecRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CProductSpecRelation record);

    CProductSpecRelation selectByPrimaryKey(Integer id);

    List<CProductSpecRelation> selectAll();

    int updateByPrimaryKey(CProductSpecRelation record);
}