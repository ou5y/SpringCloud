package com.azcx9d.business.dao;


import com.azcx9d.business.dto.DeviceUserDto;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/10 0010.
 */
public interface DeviceUserDao {

    /**
     * 查询设备是否可用
     * @param params
     * @return int
     */
    DeviceUserDto queryIsEnable(Map<String, Object> params);

    /**
     * 添加设备
     * @param params
     * @return Integer
     */
    Integer addDevice(Map<String, Object> params);

    /**
     * 更新用户编号
     * @param params
     * @return
     */
    Integer updateDevice(Map<String, Object> params);


}
