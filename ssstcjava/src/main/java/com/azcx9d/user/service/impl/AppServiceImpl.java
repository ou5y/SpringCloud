package com.azcx9d.user.service.impl;

import com.azcx9d.user.dao.AppDao;
import com.azcx9d.user.entity.Notice;
import com.azcx9d.user.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/21 0021.
 */
@Service
@Transactional
public class AppServiceImpl implements AppService{

    @Autowired
    private AppDao appDao;

    /**
     * 查找公告
     * @param params
     * @return
     */
    public Notice findByParams(Map<String,Object> params){
        return  appDao.findByParams(params);
    }

}
