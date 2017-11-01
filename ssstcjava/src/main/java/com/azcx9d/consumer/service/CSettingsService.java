package com.azcx9d.consumer.service;

import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.consumer.entity.CAddress;
import com.azcx9d.consumer.entity.CArea;
import com.azcx9d.consumer.entity.Consumer;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/4/2 0002.
 */
public interface CSettingsService {
    /**
     * 过滤区域列表
     * @param params
     * @return
     */
    List<Map> areaListByparentId(Map<String,String> params) throws Exception;

    /**
     * 新增地址
     * @param cAddress
     * @return
     */
    JsonResult addAddress(CAddress cAddress) throws Exception;

    /**
     * 省市区下拉列表
     * @param
     * @return
     */
    JsonResult allAreaList() throws Exception;

    /**
     * 收货地址列表
     * @param userId
     * @return
     */
    JsonResult addressList(long userId) throws Exception;

    /**
     * 地址详细信息
     * @param addressId
     * @return
     */
    JsonResult addressDetail(long addressId) throws Exception;

    /**
     * 修改地址
     * @param cAddress
     * @return
     */
    JsonResult updateAddress(CAddress cAddress) throws Exception;

    /**
     * 删除地址
     * @param addressId
     * @return
     */
    JsonResult deleteAddress(long addressId) throws Exception;

    /**
     * 设置/取消默认地址
     * @param addressId
     * @param defaultAddress
     * @return
     */
    JsonResult setDefaultAddress(long userId, long addressId, int defaultAddress) throws Exception;

    /**
     * 设置交易密码
     * @param params
     * @return
     */
    JsonResult addTransPwd(Map<String, String> params) throws Exception;

    /**
     * 修改交易密码
     * @param params
     * @return
     */
    JsonResult modifyTransPwd(Map<String, String> params) throws Exception;

    /**
     * 找回交易密码
     * @param params
     * @return
     */
    JsonResult findTransPwd(Map<String, String> params) throws Exception;

    /**
     * 是否已经设置交易密码
     * @param params
     * @return
     */
    JsonResult hasTransPwd(Map<String, String> params) throws Exception;

    /**
     * 修改登录密码
     * @param params
     * @return
     */
    JsonResult modifyLoginPwd(Map<String, String> params) throws Exception;

    /**
     * 保存身份证正面
     * @param consumer
     * @return
     */
    JsonResult saveIdentityCardUp(Consumer consumer) throws Exception;

    /**
     * 保存身份证反面
     * @param consumer
     * @return
     */
    JsonResult saveIdentityCardDown(Consumer consumer) throws Exception;
}
