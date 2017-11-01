package com.customer.dao.index;

import com.customer.entity.index.CBanner;
import com.customer.entity.index.CBannerCriteria;
import com.customer.entity.index.CBannerKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CBannerMapper {
    long countByExample(CBannerCriteria example);

    int deleteByExample(CBannerCriteria example);

    int deleteByPrimaryKey(CBannerKey key);

    int insert(CBanner record);

    int insertSelective(CBanner record);

    List<CBanner> selectByExample(CBannerCriteria example);

    CBanner selectByPrimaryKey(CBannerKey key);

    int updateByExampleSelective(@Param("record") CBanner record, @Param("example") CBannerCriteria example);

    int updateByExample(@Param("record") CBanner record, @Param("example") CBannerCriteria example);

    int updateByPrimaryKeySelective(CBanner record);

    int updateByPrimaryKey(CBanner record);
}