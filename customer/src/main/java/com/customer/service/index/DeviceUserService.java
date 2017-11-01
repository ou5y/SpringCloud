package com.customer.service.index;

import com.customer.dto.index.DeviceUserDto;

import java.util.Map;

/**
 * Created by Administrator on 2017/7/10 0010.
 */
public interface DeviceUserService {

    /**
     * 查询设备是否可用
     * @param params
     * @return int
     */
    Integer queryIsEnable(Map<String,Object> params);

    /**
     * 更新用户编号
     * @param params
     * @return
     */
    Integer updateDevice(Map<String,Object> params);

}
