package com.customer.dao;

import com.customer.entity.CPSpecification;
import java.util.List;

public interface CPSpecificationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CPSpecification record);

    CPSpecification selectByPrimaryKey(Integer id);

    List<CPSpecification> selectAll();

    int updateByPrimaryKey(CPSpecification record);
}