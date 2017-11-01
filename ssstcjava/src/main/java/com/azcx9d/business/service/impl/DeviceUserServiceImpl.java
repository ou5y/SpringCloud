package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.DeviceUserDao;
import com.azcx9d.business.dto.DeviceUserDto;
import com.azcx9d.business.service.DeviceUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/10 0010.
 */

@Service
public class DeviceUserServiceImpl implements DeviceUserService {

    @Autowired
    private DeviceUserDao deviceUserDao;

    // 查询设备是否可用
    @Override
    @Transactional
    public Integer queryIsEnable(Map<String,Object> params){
        DeviceUserDto deviceUserDto = deviceUserDao.queryIsEnable(params);
        if(deviceUserDto==null){
            // 不存在时，保存设备编号
            params.put("userId","0");
            deviceUserDao.addDevice(params);
            return 1;
        }else{
            return deviceUserDto.getIsEnable();
        }

    }

    // 更新用户编号
    @Override
    public Integer updateDevice(Map<String,Object> params){
        return deviceUserDao.updateDevice(params);
    }

}
