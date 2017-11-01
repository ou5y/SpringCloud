package com.azcx9d.system.service.impl;

import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.system.dao.SSystemAuditRecordDAO;
import com.azcx9d.system.service.SSystemAuditRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/15 0015 18:49.
 */
@Service("ssystemAuditRecordService")
public class SSystemAuditRecordServiceImpl implements SSystemAuditRecordService {

    @Autowired
    private SSystemAuditRecordDAO systemAuditRecordDAO;

    @Override
    public List<Map> selectFaildList(ParaMap paraMap) throws Exception {
        return systemAuditRecordDAO.selectFaildList(paraMap);
    }
}
