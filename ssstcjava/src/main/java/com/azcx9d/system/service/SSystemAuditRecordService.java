package com.azcx9d.system.service;

import com.azcx9d.business.entity.ParaMap;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/15 0015 18:48.
 */
public interface SSystemAuditRecordService {

    List<Map> selectFaildList(ParaMap paraMap) throws Exception;
}
