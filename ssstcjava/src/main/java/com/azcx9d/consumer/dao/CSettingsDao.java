package com.azcx9d.consumer.dao;

import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.consumer.entity.CAddress;
import com.azcx9d.consumer.entity.CArea;
import com.azcx9d.consumer.entity.Consumer;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/4/2 0002.
 */
public interface CSettingsDao {
    /**
     * 过滤区域列表
     * @param params
     * @return
     */
    List<Map> areaListByparentId(Map<String,String> params) throws SQLException;

    /**
     * 新增地址
     * @param cAddress
     * @return
     */
    int addAddress(CAddress cAddress) throws SQLException;

    /**
     * 省市区下拉列表
     * @param type
     * @return
     */
    List<Map> allAreaList(int type) throws SQLException;

    /**
     * 收货地址列表
     * @param userId
     * @return
     */
    List<CAddress> addressList(long userId) throws SQLException;

    /**
     * 地址详细信息
     * @param id
     * @return
     */
    CAddress addressDetail(long id) throws SQLException;

    /**
     * 修改地址
     * @param cAddress
     * @return
     */
    int updateAddress(CAddress cAddress) throws SQLException;

    /**
     * 删除地址
     * @param id
     * @return
     */
    int deleteAddress(long id) throws SQLException;

    /**
     * 根据userId取消默认地址
     * @param userId
     * @return
     */
    int removeDefaultAddressByUserId(long userId) throws SQLException;

    /**
     * 根据地址id取消默认地址
     * @param id
     * @return
     */
    int removeDefaultAddressById(long id) throws SQLException;

    /**
     * 根据地址id设置默认地址
     * @param id
     * @return
     */
    int setDefaultAddress(long id) throws SQLException;

    /**
     * 检查是否已经设置了交易密码
     * @param params
     * @return
     */
    int hasTransPwd(Map<String,String> params) throws SQLException;

    /**
     * 更新交易密码
     * @param params
     * @return
     */
    int updateTransPwd(Map<String,String> params) throws SQLException;

    /**
     * 检查原交易密码是否正确
     * @param params
     * @return
     */
    int checkTransPwd(Map<String,String> params) throws SQLException;

    /**
     * 更新登录密码
     * @param params
     * @return
     */
    int updateLoginPwd(Map<String,String> params) throws SQLException;

    /**
     * 检查原登录密码是否正确
     * @param params
     * @return
     */
    int checkLoginPwd(Map<String,String> params) throws SQLException;

    /**
     * 保存身份证正面
     * @param consumer
     * @return
     */
    int saveIdentityCardUp(Consumer consumer) throws SQLException;

    /**
     * 保存身份证反面
     * @param consumer
     * @return
     */
    int saveIdentityCardDown(Consumer consumer) throws SQLException;
}
