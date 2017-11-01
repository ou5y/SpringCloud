package com.customer.dao;

import com.customer.entity.CPlatformStatus;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface CPlatformStatusMapper {
    int deleteByPrimaryKey(@Param("identityId") Integer identityId, @Param("levelId") Integer levelId);

    int insert(CPlatformStatus record);

    CPlatformStatus selectByPrimaryKey(@Param("identityId") Integer identityId, @Param("levelId") Integer levelId);

    List<CPlatformStatus> selectAll();

    int updateByPrimaryKey(CPlatformStatus record);
}