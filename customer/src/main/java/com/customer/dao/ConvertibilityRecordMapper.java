package com.customer.dao;

import com.customer.entity.ConvertibilityRecord;
import java.util.List;

public interface ConvertibilityRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ConvertibilityRecord record);

    ConvertibilityRecord selectByPrimaryKey(Integer id);

    List<ConvertibilityRecord> selectAll();

    int updateByPrimaryKey(ConvertibilityRecord record);
}