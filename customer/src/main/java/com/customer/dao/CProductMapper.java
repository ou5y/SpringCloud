package com.customer.dao;

import com.customer.entity.CProduct;
import java.util.List;

public interface CProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CProduct record);

    CProduct selectByPrimaryKey(Integer id);

    List<CProduct> selectAll();

    int updateByPrimaryKey(CProduct record);
}