package com.customer.dao;

import com.customer.entity.CRole;
import java.util.List;

public interface CRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CRole record);

    CRole selectByPrimaryKey(Integer id);

    List<CRole> selectAll();

    int updateByPrimaryKey(CRole record);
}