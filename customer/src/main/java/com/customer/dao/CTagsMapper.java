package com.customer.dao;

import com.customer.entity.CTags;
import java.util.List;

public interface CTagsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CTags record);

    CTags selectByPrimaryKey(Integer id);

    List<CTags> selectAll();

    int updateByPrimaryKey(CTags record);
}