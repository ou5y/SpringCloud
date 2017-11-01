package com.customer.dao;

import com.customer.entity.CMenu;
import java.util.List;

public interface CMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CMenu record);

    CMenu selectByPrimaryKey(Integer id);

    List<CMenu> selectAll();

    int updateByPrimaryKey(CMenu record);
}