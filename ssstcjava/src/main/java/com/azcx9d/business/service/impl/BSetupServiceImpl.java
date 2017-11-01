package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BSetupDao;
import com.azcx9d.business.dto.CommonProblemDto;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BSetupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenxl on 2017/5/23 0023.
 */
@Service
public class BSetupServiceImpl implements BSetupService {

    @Autowired
    private BSetupDao bSetupDao;

    @Override
    public int updateAvatar(ParaMap paraMap) throws Exception {
        return bSetupDao.updateAvatar(paraMap);
    }

    @Override
    public int updateUserName(ParaMap paraMap) throws Exception {
        return bSetupDao.updateUserName(paraMap);
    }

    @Override
    public int saveUserSuggest(ParaMap paraMap) throws Exception {
        return bSetupDao.saveUserSuggest(paraMap);
    }

    @Override
    public List<CommonProblemDto> getCommonProblem(ParaMap paraMap) throws Exception {
        return bSetupDao.getCommonProblem(paraMap);
    }
}
