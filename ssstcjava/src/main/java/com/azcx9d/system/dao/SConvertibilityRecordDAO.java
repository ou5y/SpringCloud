package com.azcx9d.system.dao;

import com.azcx9d.business.entity.ParaMap;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/8 0008 09:13.
 */
public interface SConvertibilityRecordDAO {
    int countTotalByQueryDate(ParaMap paraMap);

    List<Map> selectRecordByQueryDate(ParaMap paraMap);

    List<Map> selectAllByQueryDay(ParaMap paraMap);
}
