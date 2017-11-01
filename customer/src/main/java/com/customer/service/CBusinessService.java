package com.customer.service;

import com.customer.entity.CBusiness;
import com.customer.util.JsonResult;
import com.customer.util.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/5 0005.
 */
public interface CBusinessService {

    /**
     * 分页查询附近的商家
     * @param page      分页对象
     * @param params    查询参数
     * @param pageIndex 页码
     * @param pageSize   条数
     * @return             Page
     */
    PageInfo queryNearbyBusiness(PageInfo page, Map<String, Object> params, Integer pageIndex, Integer pageSize);

    /**
     * 分页查询附近的商家
     * @param page      分页对象
     * @param params    查询参数
     * @param pageIndex 页码
     * @param pageSize   条数
     * @return             Page
     */
    Page queryNearbyBusiness(Page page, Map<String, Object> params, Integer pageIndex, Integer pageSize);

    /**
     * 查询店铺详细信息
     * @param params
     * @return
     */
    Map<String,Object> queryBusinessDetail(Map<String, Object> params);

    /**
     * 根据手机号码查询用户id，用户类型
     * @param params
     * @return Map<String,Object>
     */
    List<Map<String,Object>> selectUserTypeByPhone(Map<String,Object> params);

    /**
     * 上传商家
     * @param business
     * @return int
     */
    int addBusiness(CBusiness business);

    /**
     * 上传商家
     * @param business
     * @return int
     */
    int addBusinessSimple(CBusiness business);

    /**
     * 修改商家
     * @param business
     * @return int
     */
    int updateBusiness(CBusiness business);

    /**
     * 修改商家
     * @param business
     * @return int
     */
    int updateBusinessSimple(CBusiness business);

    /**
     * 获取商家列表
     * @param params
     * @return List<Map<String,Object>>
     */
    PageInfo getBusinessList(Map<String, Object> params);

    /**
     * 获取商家经营类型
     * @return List<Map<String, Object>>
     */
    List<Map<String, Object>> getBusinessType();

    /**
     * 根据id查询商家相信信息
     * @param params 商家id，必填
     * @return CBusiness
     */
    CBusiness queryDetail(Map<String, String> params);

    /**
     * 查询省市区对应中文名称
     * @param params
     * @return List<String, Object>
     */
    List<String> queryAreaName(Map<String, String> params);

    /**
     * 更新店铺状态
     * @param params
     * @return int
     */
    int updateBusinessState(Map<String, String> params);

    /**
     * 添加结算账户银行信息
     * @param business
     * @return int
     */
    int addClearanceAccount(CBusiness business);

    /**
     * 商户管理
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryBusinessStatistics(Map<String, String> params);

    /**
     * 添加商户角色
     * @param params
     * @return int
     */
    int addUserRoleAttribute(Map<String, Object> params);

    /**
     * 店铺收益详情
     * @param params
     * @return PageInfo
     */
    PageInfo queryStoreRevenue(Map<String, Object> params);

    /**
     * 查询店铺名是否被注册
     * @param business
     * @return int
     */
    int queryExistBusiness(CBusiness business);

}
