package com.customer.dao;

import com.customer.entity.CCollect;
import java.util.List;

public interface CCollectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CCollect record);

    CCollect selectByPrimaryKey(Integer id);

    List<CCollect> selectAll();

    int updateByPrimaryKey(CCollect record);
}