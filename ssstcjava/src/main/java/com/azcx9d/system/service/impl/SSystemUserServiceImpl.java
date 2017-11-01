package com.azcx9d.system.service.impl;

import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.system.dao.SSystemUserDAO;
import com.azcx9d.system.entity.SSystemUser;
import com.azcx9d.system.service.SSystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by HuangQing on 2017/4/6 0006 16:35.
 */
@Service("sSystemUserService")
public class SSystemUserServiceImpl implements SSystemUserService {

    @Autowired
    private SSystemUserDAO userDAO;

    @Override
    public SSystemUser selectByUsername(ParaMap paraMap) throws Exception {
        return userDAO.selectByUsername(paraMap);
    }

    @Override
    public SSystemUser selectById(ParaMap paraMap) throws Exception {
        return userDAO.selectById(paraMap);
    }
}
