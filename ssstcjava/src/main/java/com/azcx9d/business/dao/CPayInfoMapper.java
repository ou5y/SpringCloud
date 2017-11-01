package com.azcx9d.business.dao;

import com.azcx9d.business.entity.CPayInfo;
import com.azcx9d.business.entity.CPayInfoCriteria;
import com.azcx9d.business.entity.CPayInfoKey;
import java.util.List;
import java.util.Map;

public interface CPayInfoMapper {
    int deleteByPrimaryKey(CPayInfoKey key);

    int insert(CPayInfo record);

    int insertSelective(CPayInfo record);

    List<CPayInfo> selectByExample(CPayInfoCriteria example);

    CPayInfo selectByPrimaryKey(CPayInfoKey key);

    int updateByPrimaryKeySelective(CPayInfo record);

    int updateByPrimaryKey(CPayInfo record);

    /**
     * 保存第三方支付信息
     * @param params
     * @return int
     */
    int addPayInfo(Map params);

    /**
     * 更新支付信息
     * @param params
     * @return int
     */
    int updatePayInfo(Map params);
}