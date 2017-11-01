package com.azcx9d.system.service.impl;

import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.common.util.Page;
import com.azcx9d.system.dao.SConvertibilityRecordDAO;
import com.azcx9d.system.service.SConvertibilityRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/8 0008 09:18.
 */
@Service("sConvertibilityRecordService")
public class SConvertibilityRecordServiceImpl implements SConvertibilityRecordService {

    @Autowired
    private SConvertibilityRecordDAO recordDAO;

    @Override
    public void recordList(Page page, ParaMap paraMap) throws Exception {
        int total = recordDAO.countTotalByQueryDate(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<Map> list = recordDAO.selectRecordByQueryDate(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public List<Map> selectAllByQueryDay(ParaMap paraMap) throws Exception {
        return recordDAO.selectAllByQueryDay(paraMap);
    }
}
