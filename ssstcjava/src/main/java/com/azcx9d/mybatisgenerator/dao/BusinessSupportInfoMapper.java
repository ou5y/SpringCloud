package com.azcx9d.mybatisgenerator.dao;

import com.azcx9d.mybatisgenerator.model.BusinessSupportInfo;
import com.azcx9d.mybatisgenerator.model.BusinessSupportInfoCriteria;
import com.azcx9d.mybatisgenerator.model.BusinessSupportInfoKey;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BusinessSupportInfoMapper {
    long countByExample(BusinessSupportInfoCriteria example);

    int deleteByExample(BusinessSupportInfoCriteria example);

    int deleteByPrimaryKey(BusinessSupportInfoKey key);

    int insert(BusinessSupportInfo record);

    int insertSelective(BusinessSupportInfo record);

    List<BusinessSupportInfo> selectByExample(BusinessSupportInfoCriteria example);

    BusinessSupportInfo selectByPrimaryKey(BusinessSupportInfoKey key);

    int updateByExampleSelective(@Param("record") BusinessSupportInfo record, @Param("example") BusinessSupportInfoCriteria example);

    int updateByExample(@Param("record") BusinessSupportInfo record, @Param("example") BusinessSupportInfoCriteria example);

    int updateByPrimaryKeySelective(BusinessSupportInfo record);

    int updateByPrimaryKey(BusinessSupportInfo record);

    BusinessSupportInfo selectByStoreId(int storeId);
}