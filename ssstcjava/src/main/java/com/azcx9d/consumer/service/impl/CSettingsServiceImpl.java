package com.azcx9d.consumer.service.impl;

import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.consumer.dao.CSettingsDao;
import com.azcx9d.consumer.entity.CAddress;
import com.azcx9d.consumer.entity.CArea;
import com.azcx9d.consumer.entity.Consumer;
import com.azcx9d.consumer.service.CSettingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/4/2 0002.
 */
@Service
public class CSettingsServiceImpl implements CSettingsService {
    @Autowired
    private CSettingsDao dao;

    @Override
    public List<Map> areaListByparentId(Map<String, String> params) throws Exception {
        return dao.areaListByparentId(params);
    }

    @Override
    public JsonResult addAddress(CAddress cAddress) throws Exception {
        int count = dao.addAddress(cAddress);
        if(count > 0){
            return new JsonResult(0,"收货地址添加成功!");
        }else{
            return new JsonResult(1,"收货地址添加失败!请重新试试");
        }
    }

    @Override
    public JsonResult allAreaList() throws Exception {
        Map<String, List> result = new HashMap<String, List>();
        List<Map> provinceList = dao.allAreaList(1);//1:省
        List<Map> cityList = dao.allAreaList(2);//2:市
        List<Map> areaList = dao.allAreaList(3);//3:区
        result.put("provinceList", provinceList);
        result.put("cityList", cityList);
        result.put("areaList", areaList);
        return new JsonResult(result);
    }

    @Override
    public JsonResult addressList(long userId) throws Exception {
        List<CAddress> addressList = dao.addressList(userId);
        if(addressList.size() > 0){
            return new JsonResult(addressList);
        }else{
            return new JsonResult(1, "暂时还没有收货地址!");
        }
    }

    @Override
    public JsonResult addressDetail(long addressId) throws Exception {
        CAddress cAddress = dao.addressDetail(addressId);
        if(null == cAddress){
            return new JsonResult(1, "暂时还没有收货地址详细信息!");
        }else{
            return new JsonResult(cAddress);
        }
    }

    @Override
    public JsonResult updateAddress(CAddress cAddress) throws Exception {
        int count = dao.updateAddress(cAddress);
        if(count > 0){
            return new JsonResult(0,"收货地址修改成功!");
        }else{
            return new JsonResult(1,"收货地址修改失败!请重新试试");
        }
    }

    @Override
    public JsonResult deleteAddress(long addressId) throws Exception {
        int count = dao.deleteAddress(addressId);
        if(count > 0){
            return new JsonResult(0,"收货地址删除成功!");
        }else{
            return new JsonResult(1,"收货地址删除失败!请重新试试");
        }
    }

    @Override
    public JsonResult setDefaultAddress(long userId, long addressId, int defaultAddress) throws Exception {
        if(0 == defaultAddress){
            dao.removeDefaultAddressByUserId(userId);
            int count = dao.setDefaultAddress(addressId);
            if(count > 0){
                return new JsonResult(0,"默认地址设置成功!");
            }else{
                new JsonResult(1,"默认地址设置失败!请重新试试");
            }
        }else if(1 == defaultAddress){
            int count = dao.removeDefaultAddressById(addressId);
            if(count > 0){
                return new JsonResult(0,"默认地址取消成功!");
            }else{
                new JsonResult(1,"默认地址取消失败!请重新试试");
            }
        }
        return new JsonResult(1,"默认地址设置失败!请重新试试");
    }

    @Override
    public JsonResult addTransPwd(Map<String, String> params) throws Exception {
        int count = dao.hasTransPwd(params);
        if(count > 0){
            return new JsonResult(1,"您已设置过交易密码!不能重复设置");
        }else{
            int updateCount = dao.updateTransPwd(params);
            if(updateCount > 0){
                return new JsonResult(0,"交易密码设置成功!");
            }else{
                return new JsonResult(1,"交易密码设置失败!请稍后试试");
            }
        }
    }

    @Override
    public JsonResult modifyTransPwd(Map<String, String> params) throws Exception {
        int hasCount = dao.hasTransPwd(params);
        if(hasCount == 0){
            return new JsonResult(1,"您还未设置过交易密码!不能修改");
        }
        int checkCount = dao.checkTransPwd(params);
        if(checkCount > 0){
            int updateCount = dao.updateTransPwd(params);
            if(updateCount > 0){
                return new JsonResult(0,"交易密码修改成功!");
            }else{
                return new JsonResult(1,"交易密码修改失败!请稍后试试");
            }
        }else{
            return new JsonResult(1,"旧交易密码不正确!");
        }
    }

    @Override
    public JsonResult findTransPwd(Map<String, String> params) throws Exception {
        int updateCount = dao.updateTransPwd(params);
        if(updateCount > 0){
            return new JsonResult(0,"交易密码重置成功!");
        }else{
            return new JsonResult(1,"交易密码重置失败!请稍后试试");
        }
    }

    @Override
    public JsonResult hasTransPwd(Map<String, String> params) throws Exception {
        int hasCount = dao.hasTransPwd(params);
        if(hasCount > 0){
            return new JsonResult(1,"已设置交易密码!");
        }else{
            return new JsonResult(0,"未设置交易密码!");
        }
    }

    @Override
    public JsonResult modifyLoginPwd(Map<String, String> params) throws Exception {
        int checkCount = dao.checkLoginPwd(params);
        if(checkCount > 0){
            int updateCount = dao.updateLoginPwd(params);
            if(updateCount > 0){
                return new JsonResult(0,"登录密码修改成功!");
            }else{
                return new JsonResult(1,"登录密码修改失败!请稍后试试");
            }
        }else{
            return new JsonResult(1,"旧登录密码不正确!");
        }
    }

    @Override
    public JsonResult saveIdentityCardUp(Consumer consumer) throws Exception {
        int count = dao.saveIdentityCardUp(consumer);
        if(count > 0){
            return new JsonResult(0,"上传身份证正面成功!");
        }else{
            return new JsonResult(1,"上传身份证正面失败!请稍后试试");
        }
    }

    @Override
    public JsonResult saveIdentityCardDown(Consumer consumer) throws Exception {
        int count = dao.saveIdentityCardDown(consumer);
        if(count > 0){
            return new JsonResult(0,"上传身份证反面成功!");
        }else{
            return new JsonResult(1,"上传身份证反面失败!请稍后试试");
        }
    }
}
