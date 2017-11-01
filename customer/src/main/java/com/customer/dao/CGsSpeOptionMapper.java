package com.customer.dao;

import com.customer.entity.CGsSpeOption;
import java.util.List;

public interface CGsSpeOptionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CGsSpeOption record);

    CGsSpeOption selectByPrimaryKey(Integer id);

    List<CGsSpeOption> selectAll();

    int updateByPrimaryKey(CGsSpeOption record);
}