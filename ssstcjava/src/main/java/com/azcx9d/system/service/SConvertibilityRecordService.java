package com.azcx9d.system.service;

import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.common.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/8 0008 09:18.
 */
public interface SConvertibilityRecordService {
    void recordList(Page page, ParaMap paraMap) throws Exception;

    /**
     * 查询某一天的所有交易记录，主要用于导出excel
     * @param paraMap
     * @return
     */
    List<Map> selectAllByQueryDay(ParaMap paraMap) throws Exception;
}
