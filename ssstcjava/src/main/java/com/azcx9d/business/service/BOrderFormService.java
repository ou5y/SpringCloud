package com.azcx9d.business.service;

import com.azcx9d.business.dto.GetPayDto;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.common.util.Page;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/3/30 0030.
 */
public interface BOrderFormService {

    /**
     *
     * @param page
     * @param paraMap 包括当前登录商家用户userId，订单状态state
     * @throws Exception
     */
    void selectByStoreUserId(Page page,Map paraMap) throws Exception;

    void selectAuditByStoreId(Page page,Map paraMap) throws Exception;

    /**
     *
     * @param page
     * @param paraMap 包括当前登录商家用户userId
     * @throws Exception
     */
    void selectByStoreUserIdFinish(Page page,Map paraMap) throws Exception;

    /**
     * 统计：待让利列表
     * @param userIdAndState 包括当前登录商家用户userId，订单状态state
     * @return
     * @throws Exception
     */
    Map selectCountOrderAndSumMoney(Map userIdAndState) throws Exception;

    /**
     *
     * @param paramMap 包含ids 所有要修改状态的订单的id数组， 包含bOrderForm订单对象->包含rangli ： 订单的让利比例
     * @throws Exception
     */
    int updateOrderStateByIds(Map paramMap) throws Exception;

    /**
     * 统计：处理中总条数和总让利
     * @param userId 包括当前登录商家用户userId
     * @return
     * @throws Exception
     */
    Map selectSumRangliAndSumMoney(HashMap<String, Object> userId) throws Exception;

    /**
     * 统计：已完结总条数和总让利
     * @param userId 包括当前登录商家用户userId
     * @return
     * @throws Exception
     */
    Map selectSumRangliAndSumMoneyFinish(HashMap<String, Object> userId);

    /**
     * 根据当前登录商户的id获取该商户所有的会员，分页
     * @param page
     * @param paraMap 包含userId：当前登录会员的id
     */
//    void selectMyMembersByUserId(Page page, HashMap<String, Object> paraMap) throws Exception;

    /**
     * 根据当前登录商户的id获取该商户某一天的收益列表，分页
     * @param page
     * @param paraMap 包含userId：当前登录会员的id
     */
    void selectIncome(Page page, ParaMap paraMap) throws Exception;

    /**
     * 查询当日收益总订单数和总收益金额
     * @param paraMap 包含queryDate : 日期，格式 2017-04-06
     * @return
     */
    Map selectCountOrderAndTotalIncome(ParaMap paraMap) throws Exception;

    Map selectTotalIncomeGroupByDays(ParaMap paraMap) throws Exception;

    void selectIncomeByDays(Page page, ParaMap paraMap) throws Exception;

    Map selectTotalMoneyAndTotalCountGroupByDays(ParaMap paraMap) throws Exception;


    int cancelOrder(HashMap<String, Object> paraMap) throws Exception;

    int countHandlingOrder(HashMap<String, Object> paraMap) throws Exception;

    int updateConsumptionPic(HashMap<String, Object> paraMap) throws Exception;

    Map selectCloseOrderAndSumMoney(Map paraMap) throws Exception;

    void selectCloseList(Page page,Map paraMap) throws Exception;

    Map getFailedOrder(Page page, Map paraMap) throws Exception;

    Map getEndedOrder(Page page, Map paraMap) throws Exception;

    Map getClosedOrder(Page page, Map paraMap) throws Exception;

    Map getSuccessOrder(Page page, Map paraMap) throws Exception;

    Map getStayOrder(Page page, Map paraMap) throws Exception;

    GetPayDto getPay(Map paraMap) throws Exception;



//    ====================================================================================================================
//    system模块功能
//    ====================================================================================================================

    void selectOrderByState(Page page, HashMap<String, Object> paraMap) throws Exception;

    void updateOrderStateInIds(HashMap<String, Object> paramMap) throws Exception;

    void auditByBusinessIdAndEndTime(ParaMap paraMap);

    /**
     * 按照商家id、以及订单不同的比例分组
     * @param paraMap
     */
    void auditListByBusinessId(Page page,ParaMap paraMap);

    /**
     * 按照商家id、以及订单不同的比例分组
     * @param paraMap
     */
    void auditListByBusinessId2(Page page,ParaMap paraMap);

    /**
     * 根据商家id和让利比例 修改订单状态，用当前时间作为财务审核时间
     * @param paraMap
     */
    void auditByBusinessIdAndRangli(ParaMap paraMap) throws Exception;


    /**
     * 查询某一天的所有交易待审核列表，主要用于导出excel
     * @param paraMap
     * @return
     */
    List<Map> selectAllByQueryDay(ParaMap paraMap) throws Exception;

    /**
     * 根据businessId和seller_time=queryDate查询所有订单
     * @param page
     * @param paraMap
     */
    void selectByBIdAndTime(Page page, ParaMap paraMap) throws Exception;

    void selectAuditRecordsList(Page page, ParaMap paraMap) throws Exception;

    /**
     * 查询要导出excel的审核记录
     * @param paraMap
     * @return
     */
    List<Map> selectExportAudit(ParaMap paraMap) throws Exception;

    /**
     * 将excel中的读取到的所有转账记录放入list中，传到该方法中进行数据库查询判断，额度是否一致，然后审核订单
     * @param transferRecordsList
     */
    void auditOrderByExcelTransferRecords(List<Map> transferRecordsList,ParaMap paraMap) throws Exception;

    BOrderForm findByOrderNo(String orderNo) throws Exception;

     /**
     * 更新订单信息-只更新state值
     * @param params
     */
    int updateOrderState(Map<String,String> params) throws Exception;
}
