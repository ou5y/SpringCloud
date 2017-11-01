package com.azcx9d.business.service.impl;

import com.azcx9d.Thread.OrderClean;
import com.azcx9d.business.dao.BOrderFormDAO;
import com.azcx9d.business.dto.*;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BOrderFormService;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.common.util.Page;
import com.azcx9d.system.dao.SSystemAuditRecordDAO;
import com.azcx9d.user.dao.ProfitCenterDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by HuangQing on 2017/3/30 0030.
 */
@Service("businessOrderFormService")
public class BOrderFormServiceImpl implements BOrderFormService {

    @Autowired
    private BOrderFormDAO businessOrderFormDAO;

    @Autowired
    private SSystemAuditRecordDAO systemAuditRecordDAO;
    
    @Autowired
    private ProfitCenterDao profitCenterDao;

    @Override
    public void selectByStoreUserId(Page page, Map paraMap) throws Exception {
        int total = businessOrderFormDAO.countTotal(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<FreeOrderDto> list = businessOrderFormDAO.selectByStoreUserId(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public void selectAuditByStoreId(Page page, Map paraMap) throws Exception {
        int total = businessOrderFormDAO.countAuditTotal(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<FreeOrderDto> list = businessOrderFormDAO.selectAuditByStoreId(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public void selectByStoreUserIdFinish(Page page, Map paraMap) throws Exception {
        int total = businessOrderFormDAO.countTotalFinish(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<FreeOrderDto> list = businessOrderFormDAO.selectByStoreUserIdFinish(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public Map selectCountOrderAndSumMoney(Map userIdAndState) throws Exception {
        return businessOrderFormDAO.selectCountOrderAndSumMoney(userIdAndState);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int updateOrderStateByIds(Map paramMap) throws Exception {
        //保存提交记录
        String[] idsArrs = (String[]) paramMap.get("idsArr");
        int counts = 0;
        for (String orderId : idsArrs) {
            paramMap.put("orderId",orderId);
            counts += businessOrderFormDAO.updateOrderStateById(paramMap);
        }
        return counts;
    }

    @Override
    public Map selectSumRangliAndSumMoney(HashMap<String, Object> userId) throws Exception {
        return businessOrderFormDAO.selectSumRangliAndSumMoney(userId);
    }

    @Override
    public Map selectSumRangliAndSumMoneyFinish(HashMap<String, Object> userId) {
        return businessOrderFormDAO.selectSumRangliAndSumMoneyFinish(userId);
    }

//    @Override
//    public void selectMyMembersByUserId(Page page, HashMap<String, Object> paraMap) throws Exception {
//        int total = businessOrderFormDAO.countTotal(paraMap);
//        page.setTotalRow(total);
//
//        paraMap.put("offset", page.getOffset());
//        paraMap.put("pageSize", page.getPageSize());
//
//        List<BOrderForm> list = businessOrderFormDAO.selectMyMembersByUserId(paraMap);
//        page.setPageList(list);
//        page.init();
//    }

    @Override
    public void selectIncome(Page page, ParaMap paraMap) throws Exception {
        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<DayIncomeDto> list = businessOrderFormDAO.selectMyIncomeByUserId(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public Map selectCountOrderAndTotalIncome(ParaMap paraMap) throws Exception {
        return businessOrderFormDAO.selectCountOrderAndTotalIncome(paraMap);
    }

    @Override
    public Map selectTotalIncomeGroupByDays(ParaMap paraMap) throws Exception {
        return businessOrderFormDAO.selectTotalIncomeGroupByDays(paraMap);
    }

    @Override
    public void selectIncomeByDays(Page page, ParaMap paraMap) throws Exception {
        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<HistoryIncome> list = businessOrderFormDAO.selectIncomeByDays(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public Map selectTotalMoneyAndTotalCountGroupByDays(ParaMap paraMap) throws Exception {
        return businessOrderFormDAO.selectTotalMoneyAndTotalCountGroupByDays(paraMap);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int cancelOrder(HashMap<String, Object> paraMap) throws Exception{
        Map map = businessOrderFormDAO.getOffsetType(paraMap);
        if (null == map || map.size() == 0) {
            return 0;
        }
        Integer userId = Integer.parseInt(map.get("userId")+"");
        Integer offsetType = Integer.parseInt(map.get("offsetType")+"");
        Double offsetNum = Double.parseDouble(map.get("offsetNum")+"");
        Double reusePoint = Double.parseDouble(map.get("reusePoint")+"");
        Double shandian = Double.parseDouble(map.get("shandian")+"");
        Double shandian2 = Double.parseDouble(map.get("shandian2")+"");
        paraMap.put("userId", userId);
        if(offsetType == 1){
            Double bdsz = offsetNum;
            Double jieyu = reusePoint + offsetNum;

            //保存xfz_dzb
            paraMap.put("leixing", 20); //再消分
            paraMap.put("bdsz", bdsz);
            paraMap.put("jieyu", jieyu);
            businessOrderFormDAO.saveXfzDzb(paraMap);
            businessOrderFormDAO.updateReusePoint(paraMap);
        }
        if(offsetType == 2){
            Double bdsz = offsetNum;
            Double jieyu = shandian + offsetNum;

            //保存xfz_dzb
            paraMap.put("leixing", 0);
            paraMap.put("bdsz", bdsz);
            paraMap.put("jieyu", jieyu);
            businessOrderFormDAO.saveXfzDzb(paraMap);
            businessOrderFormDAO.updateShandian(paraMap);
        }
        if(offsetType == 3){
            Double bdsz = offsetNum;
            Double jieyu = shandian2 + offsetNum;

            //保存passive_shandian
            paraMap.put("bdsz", bdsz);
            paraMap.put("jieyu", jieyu);
            businessOrderFormDAO.savePassiveShandian(paraMap);
            businessOrderFormDAO.updateShandian2(paraMap);
        }
        int count = businessOrderFormDAO.cancelOrder(paraMap);
        return count;
    }

    @Override

    public int countHandlingOrder(HashMap<String, Object> paraMap) throws Exception{
        return businessOrderFormDAO.countHandlingOrder(paraMap);
    }


    @Override
    public int updateConsumptionPic(HashMap<String, Object> paraMap) throws Exception{
        return businessOrderFormDAO.updateConsumptionPic(paraMap);
    }

    @Override
    public Map selectCloseOrderAndSumMoney(Map paraMap) throws Exception {
        return businessOrderFormDAO.selectCloseOrderAndSumMoney(paraMap);
    }

    @Override
    public void selectCloseList(Page page, Map paraMap) throws Exception {
        int total = businessOrderFormDAO.countCloseTotal(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<FreeOrderDto> list = businessOrderFormDAO.selectCloseList(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public Map getFailedOrder(Page page, Map paraMap) throws Exception {
        Map map = businessOrderFormDAO.countFailedOrder(paraMap);
        map.put("totalMoney", map.get("totalMoney") + "");
        page.setTotalRow(Integer.parseInt(map.get("orderNumber")+""));
        paraMap.put("offset", page.getOffset());
        List<NetWorkOrderDto> list = businessOrderFormDAO.getFailedOrder(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        return map;
    }

    @Override
    public Map getEndedOrder(Page page, Map paraMap) throws Exception {
        Map map = businessOrderFormDAO.countEndedOrder(paraMap);
        map.put("totalMoney", map.get("totalMoney") + "");
        page.setTotalRow(Integer.parseInt(map.get("orderNumber")+""));
        paraMap.put("offset", page.getOffset());
        List<NetWorkOrderDto> list = businessOrderFormDAO.getEndedOrder(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        return map;
    }

    @Override
    public Map getClosedOrder(Page page, Map paraMap) throws Exception {
        Map map = businessOrderFormDAO.countClosedOrder(paraMap);
        map.put("totalMoney", map.get("totalMoney") + "");
        page.setTotalRow(Integer.parseInt(map.get("orderNumber")+""));
        paraMap.put("offset", page.getOffset());
        List<NetWorkOrderDto> list = businessOrderFormDAO.getClosedOrder(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        return map;
    }

    @Override
    public Map getSuccessOrder(Page page, Map paraMap) throws Exception {
        Map map = businessOrderFormDAO.countSuccessOrder(paraMap);
        map.put("totalMoney", map.get("totalMoney") + "");
        page.setTotalRow(Integer.parseInt(map.get("orderNumber")+""));
        paraMap.put("offset", page.getOffset());
        List<NetWorkOrderDto> list = businessOrderFormDAO.getSuccessOrder(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        return map;
    }

    @Override
    public Map getStayOrder(Page page, Map paraMap) throws Exception {
        Map map = businessOrderFormDAO.countStayOrder(paraMap);
        map.put("totalMoney", map.get("totalMoney") + "");
        page.setTotalRow(Integer.parseInt(map.get("orderNumber")+""));
        paraMap.put("offset", page.getOffset());
        List<NetWorkOrderDto> list = businessOrderFormDAO.getStayOrder(paraMap);
        page.setPageList(list);
        page.init();
        map.put("page", page);
        return map;
    }

    @Override
    public GetPayDto getPay(Map paraMap) throws Exception {
        return businessOrderFormDAO.getPay(paraMap);
    }


    //    ====================================================================================================================
//    system模块功能
//    ====================================================================================================================


    @Override
    public void selectOrderByState(Page page, HashMap<String, Object> paraMap) throws Exception {
        int total = businessOrderFormDAO.countTotalByState(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<BOrderForm> list = businessOrderFormDAO.selectOrderByState(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateOrderStateInIds(HashMap<String, Object> paramMap) throws Exception {
        businessOrderFormDAO.updateOrderStateInIds(paramMap);
        String [] idsArr = paramMap.get("ids").toString().split("\\,");
        for(String id : idsArr){
        	OrderClean oc = new OrderClean(profitCenterDao,Integer.valueOf(id));
        	Thread t = new Thread(oc);
        	t.start();
        }
    }

    @Override
    public void auditByBusinessIdAndEndTime(ParaMap paraMap) {

    }

    @Override
    public void auditListByBusinessId(Page page, ParaMap paraMap) {
        int total = businessOrderFormDAO.countTotalByBusinessIdAndState(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<Map> list = businessOrderFormDAO.selectOrderByBusinessIdAndState(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public void auditListByBusinessId2(Page page, ParaMap paraMap) {
        int total = businessOrderFormDAO.countTotalByBusinessIdAndState2(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<Map> list = businessOrderFormDAO.selectOrderByBusinessIdAndState2(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public void auditByBusinessIdAndRangli(ParaMap paraMap) throws Exception {
        String[] bids = paraMap.getString("businessIds").split(",");
        String[] rangs = paraMap.getString("ranglis").split(",");
        for (int i = 0; i < bids.length; i++) {
            int businessId = Integer.valueOf(bids[i]);
            Double rangli = Double.valueOf(rangs[i]);
            paraMap.put("businessId",businessId);
            paraMap.put("rangli",rangli);
            businessOrderFormDAO.auditByBusinessIdAndRangli(paraMap);
        }
    }

    @Override
    public List<Map> selectAllByQueryDay(ParaMap paraMap) throws Exception {
        return businessOrderFormDAO.selectAllByQueryDay(paraMap);
    }

    @Override
    public void selectByBIdAndTime(Page page, ParaMap paraMap) throws Exception {
        int total = businessOrderFormDAO.countTotalByBIdAndTime(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<Map> list = businessOrderFormDAO.selectByBIdAndTime(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public void selectAuditRecordsList(Page page, ParaMap paraMap) throws Exception {
        Map totalStoreAndTotalRangli = businessOrderFormDAO.selectAuditRecordCountAndTotalRangli(paraMap);
        page.setTotalRow(Integer.valueOf(totalStoreAndTotalRangli.get("totalStore").toString()));
        paraMap.put("totalMoney",totalStoreAndTotalRangli.get("totalMoney"));

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<Map> list = businessOrderFormDAO.selectAuditRecordsList(paraMap);
        page.setPageList(list);
        page.init();
    }

    @Override
    public List<Map> selectExportAudit(ParaMap paraMap) throws Exception{
        return businessOrderFormDAO.selectExportAudit(paraMap);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void auditOrderByExcelTransferRecords(List<Map> transferRecordsList,ParaMap paraMap) throws Exception {
        //1、获取到第一个map
        //2、将map中的businessId和totalMoney传入数据库查询，select id from order_form where
        List repeatList = new ArrayList();
        List successList = new ArrayList();
        List faildList = new ArrayList();
        List testList = new ArrayList();
//        List testList = new ArrayList();
        //获取到今天的00:00:00的时间戳毫秒
        String millisecondsStart = DateUtil.getMillisecondsAfterDays(DateUtil.timeStamp(), -4); //应让利总和统计开始时间
        String sellerTimeStart = DateUtil.timeStamp2Date(millisecondsStart,null); //年月日时分秒字符串
        String milliseconds = DateUtil.getMillisecondsAfterDays(DateUtil.timeStamp(), -1);  //应让利总和统计结束时间
        String sellerTimeEnd = DateUtil.timeStamp2Date(milliseconds,null); //年月日时分秒字符串
        //获取财务审核时间
        Date createTime = new Date();
        Date caiwuTime = createTime;
        paraMap.put("createTime",createTime);
        for (Map businessIdAndTotalMoney : transferRecordsList) {
            businessIdAndTotalMoney.put("sellerTimeStart",sellerTimeStart);
            businessIdAndTotalMoney.put("sellerTimeEnd",sellerTimeEnd);
            businessIdAndTotalMoney.put("state",2); //先查询已经审核的，如果匹配，则不将该条记录归入失败列表
            Map auditFinishIds = businessOrderFormDAO.selectOrderIdsByBusinessIdAndTotalMoney(businessIdAndTotalMoney);
            if (auditFinishIds != null && auditFinishIds.size()!=0){//1,该条记录今天提交过，并且成功了，所以不放入失败历史
                repeatList.add(auditFinishIds);
                continue;
            }
            businessIdAndTotalMoney.put("state",1);
            Map orderIds = businessOrderFormDAO.selectOrderIdsByBusinessIdAndTotalMoney(businessIdAndTotalMoney);
            if (orderIds==null||orderIds.size()==0){
                faildList.add(businessIdAndTotalMoney);
                // TODO 匹配失败，放入数据库中，continue进行下一次循环
                paraMap.put("businessId",businessIdAndTotalMoney.get("businessId"));
                paraMap.put("money",businessIdAndTotalMoney.get("totalMoney"));
                paraMap.put("status",0);
                systemAuditRecordDAO.insertSystemAuditRecord(paraMap);
                continue;
            }
            String orderIdsStr = orderIds.get("orderIds").toString();
            String[] idsArr = orderIdsStr.split(",");
            // TODO 匹配成功，放入数据库中
            ParaMap idsMap = new ParaMap();
            idsMap.put("idsArr",idsArr);
            idsMap.put("caiwuTime",caiwuTime);
            idsMap.put("caiwuId",-1);
            //进行批量修改操作
            businessOrderFormDAO.updateOrderStateInIds(idsMap);
            if (orderIds!=null){
                successList.add(idsArr);
            }
        }

        System.out.println(successList);
        System.out.println(successList.size());
        System.out.println(faildList);
        System.out.println(faildList.size());
        System.out.println(repeatList);
        System.out.println(repeatList.size());
        System.out.println(testList);
        System.out.println(testList.size());

//        int a = 1/0;
    }

    @Override
    public BOrderForm findByOrderNo(String orderNo) throws Exception{
         return businessOrderFormDAO.findByOrderNo(orderNo);
    }


     /**
     * 更新订单信息-只更新state值
     * @param params
     */
    @Transactional
    public int updateOrderState(Map<String,String> params) throws Exception{
        return businessOrderFormDAO.updateOrderState(params);
    }


}
