package com.azcx9d.business.dao;

import com.azcx9d.business.dto.*;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.business.entity.ParaMap;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/3/30 0030.
 */
public interface BOrderFormDAO {

    int countTotal(Map para);

    int countAuditTotal(Map para);

    List<FreeOrderDto> selectByStoreUserId(Map para);

    List<FreeOrderDto> selectAuditByStoreId(Map para);

    Map selectCountOrderAndSumMoney(Map para);

    void updateOrderStateByIds(Map paramMap);

    int updateOrderStateById(Map paramMap);

    Map selectSumRangliAndSumMoney(HashMap<String, Object> userId);

    Map selectSumRangliAndSumMoneyFinish(HashMap<String, Object> userId);

    List<DayIncomeDto> selectMyIncomeByUserId(ParaMap paraMap);

    Map selectCountOrderAndTotalIncome(ParaMap paraMap);

    Map selectTotalIncomeGroupByDays(ParaMap paraMap);

    /**
     * 获取历史收益总条数和总金额
     * @param paraMap
     * @return dayNumber 总天数  totalMoney 总金额
     */
    Map selectTotalMoneyAndTotalCountGroupByDays(ParaMap paraMap);

    List<HistoryIncome> selectIncomeByDays(ParaMap paraMap);


    int cancelOrder(HashMap<String, Object> paraMap);

    int countHandlingOrder(HashMap<String, Object> paraMap);

    int updateConsumptionPic(HashMap<String, Object> paraMap);

    Map selectCloseOrderAndSumMoney(Map paraMap);

    int countCloseTotal(Map paraMap);

    List<FreeOrderDto> selectCloseList(Map para);

    Map countFailedOrder(Map paraMap);

    List<NetWorkOrderDto> getFailedOrder(Map paraMap);

    Map countEndedOrder(Map paraMap);

    List<NetWorkOrderDto> getEndedOrder(Map paraMap);

    Map countClosedOrder(Map paraMap);

    List<NetWorkOrderDto> getClosedOrder(Map paraMap);

    Map countSuccessOrder(Map paraMap);

    List<NetWorkOrderDto> getSuccessOrder(Map paraMap);

    Map countStayOrder(Map paraMap);

    List<NetWorkOrderDto> getStayOrder(Map paraMap);

    GetPayDto getPay(Map paraMap);

    Map getOffsetType(Map paraMap);

    int saveXfzDzb(Map paraMap);

    int updateShandian(Map paraMap);

    int savePassiveShandian(Map paraMap);

    int updateShandian2(Map paraMap);

    int updateReusePoint(Map paraMap);


//    ====================================================================================================================
//    system模块功能
//    ====================================================================================================================

    int countTotalByState(HashMap<String, Object> paraMap);

    List<BOrderForm> selectOrderByState(HashMap<String, Object> paraMap);

    void updateOrderStateInIds(HashMap<String, Object> paramMap);

    int countTotalByBusinessIdAndState(ParaMap paraMap);

    List<Map> selectOrderByBusinessIdAndState(ParaMap paraMap);

    int countTotalByBusinessIdAndState2(ParaMap paraMap);

    List<Map> selectOrderByBusinessIdAndState2(ParaMap paraMap);

    void auditByBusinessIdAndRangli(ParaMap paraMap);

    List<Map> selectAllByQueryDay(ParaMap paraMap);

    int countTotalByBIdAndTime(ParaMap paraMap);

    List<Map> selectByBIdAndTime(ParaMap paraMap);

    int countTotalFinish(Map paraMap);

    List<FreeOrderDto> selectByStoreUserIdFinish(Map paraMap);

    Map selectAuditRecordCountAndTotalRangli(ParaMap paraMap);
    List<Map> selectAuditRecordsList(ParaMap paraMap);

    List<Map> selectExportAudit(ParaMap paraMap);

    @Select("select group_concat(id) orderIds from order_form\n" +
            "where state=#{state}\n" +
            "and store_id=#{businessId}\n" +
            "and seller_time<#{sellerTimeEnd}\n" +
            "and seller_time>#{sellerTimeStart}\n" +
            "group by store_id\n" +
            "having truncate(sum(rangli*money),2)=#{totalMoney}")
    Map selectOrderIdsByBusinessIdAndTotalMoney(Map businessIdAndTotalMoney);

//    @UpdateProvider(type = )
//    void auditOrderByOrderIdsArr(String[] orderIdsArr);

    /**
     * 更新订单信息
     * @param params
     * @return int
     */
    int updateOrerPayState(Map<String,String> params);

    /**
     * 更新订单信息-只更新state值
     * @param params
     */
    int updateOrderState(Map<String,String> params);

    /**
     * 保存第三方支付信息
     * @param params
     * @return int
     */
    int addPayInfo(Map<String,String> params);

    /**
     * 更新支付信息
     * @param params
     * @return int
     */
    int updateNbpayInfo(Map<String,String> params);

    /**
     * 根据订单号查找订单
     * @param orderNo
     * @return BOrderForm
     */
    BOrderForm findByOrderNo(String orderNo);

    /**
     * @Description: 根据params更新订单信息
     * @date 2017/8/25 0025
     */
    int updateOrder(Map<String, String> params) throws Exception;

}
