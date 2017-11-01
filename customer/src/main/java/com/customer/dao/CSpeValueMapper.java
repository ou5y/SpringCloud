package com.customer.dao;

import com.customer.entity.CSpeValue;
import java.util.List;

public interface CSpeValueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CSpeValue record);

    CSpeValue selectByPrimaryKey(Integer id);

    List<CSpeValue> selectAll();

    int updateByPrimaryKey(CSpeValue record);
}