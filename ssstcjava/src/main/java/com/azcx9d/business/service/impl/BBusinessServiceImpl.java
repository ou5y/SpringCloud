package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BBusinessDAO;
import com.azcx9d.business.dto.BusinessManageDto;
import com.azcx9d.business.service.BBusinessService;
import com.azcx9d.business.entity.ParaMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HuangQing on 2017/4/3 0003 09:48.
 */
@Service("businessBusinessService")
public class BBusinessServiceImpl implements BBusinessService {

    @Autowired
    private BBusinessDAO dao;


//    @Override
//    public List selectAll() throws Exception {
//        return dao.selectAll();
//    }

    @Override
    public BusinessManageDto selectByBusinessId(int businessId) throws Exception {
        return dao.selectByBusinessId(businessId);
    }

    @Override
    public int updateByBusinessId(ParaMap paraMap) {
        return dao.updateByBusinessId(paraMap);
    }
}
