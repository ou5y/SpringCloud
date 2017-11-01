package com.customer.service.impl;

import com.customer.dao.CExchangeDAO;
import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.exception.CustomerException;
import com.customer.service.CExchangeService;
import com.customer.util.Arith;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/5/13 0013 14:57.
 */
@Service
public class CExchangeServiceImpl implements CExchangeService {

    /**
     * 兑换手续费T+1
     */
    public static final double BUSINESS_POUNDAGE_1 = 0.006;

    /**
     * 兑换手续费T+3
     */
    public static final double BUSINESS_POUNDAGE_3 = 0.003;

    /**
     * 兑换手续费T+7
     */
    public static final double BUSINESS_POUNDAGE_7 = 0;

    /**
     * 可用善点比
     * */
    public static final double BUSINESS_SHANDIAN_ENABLE = 0.8;

    /**
     * 备用善点比
     * */
    public static final double BUSINESS_SHANDIAN_UNABLE = 0.2;

    @Autowired
    private CExchangeDAO cExchangeDAO;

    @Override
    public CIsExchangeDto getIsExchange(ParaMap paraMap) throws Exception {
        CIsExchangeDto cIsExchangeDto = cExchangeDAO.getIsExchange(paraMap);
        //可兑换额度
        String desc = "300";
        Map businessUserId = cExchangeDAO.selectBusinessId(paraMap);
        List<Map> userLevels = cExchangeDAO.getUserLevelList(paraMap);
        if (userLevels==null||userLevels.size()==0){
            if (businessUserId == null || businessUserId.size() == 0) {
                desc = "300";
            } else {
                desc = "500";
            }
        }
        Integer maxLevelId = 0;
        for (Map userLevel : userLevels) {
            Integer levelId = Integer.valueOf(userLevel.get("levelId").toString());
            if(levelId>maxLevelId){
                maxLevelId = levelId;
            }
        }
        // 已经获取到maxlevelId
        if(maxLevelId==9 || maxLevelId==7 || maxLevelId==6) { //该账号为服务商/副总/总监
            desc = "500";
        }
        if (maxLevelId==2||maxLevelId==1){  // 推广员或者高级推广员
            desc = "500";
        }
        cIsExchangeDto.setExchangeDesc(//"1、每日每张银行卡最多可兑换" + desc + "鼓励点或者奖励点。\n" +
                "1、手续费=选择T+1到账，费率为兑换鼓励点或者奖励点*0.6%；选择T+3到账，费率为兑换鼓励点或者奖励点*0.3%。最低手续费为5鼓励点或者奖励点。\n" +
                "2、可选到账时间：T+1（1个工作日到账）、T+3（3个工作日到账）、T+7（7个工作日到账）；如遇周末顺延。\n" +
                "3、不是邮政储蓄银行卡的用户按人民银行规定收取跨行转账费率。\n" +
                "4、目前商家支持T+1，其他身份支持T+3、T+7。");
        return cIsExchangeDto;
    }

    @Override
    public CTotalExchangeDto getTotalExchange(ParaMap paraMap) throws Exception {
        return cExchangeDAO.getTotalExchange(paraMap);
    }

    @Override
    public PageInfo<CExchangeRecordDto> getExchangeList(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<CExchangeRecordDto> list = cExchangeDAO.getExchangeList(paraMap);
        return new PageInfo<CExchangeRecordDto>(list);
    }

    @Override
    public CExchangeDetailDto getExchangeDetail(ParaMap paraMap) throws Exception {
        return cExchangeDAO.getExchangeDetail(paraMap);
    }

    @Override
    public CExchangeShandianDto toExchange(ParaMap paraMap) throws Exception {
        CExchangeShandianDto cExchangeShandianDto = cExchangeDAO.getMyShandian(paraMap);
        List<CExchangeBankcardDto> list = cExchangeDAO.getMyBankCard(paraMap);
        cExchangeShandianDto.setcExchangeBankcardDto(list);
        return cExchangeShandianDto;
    }

    @Override
    public CExchangeMoneyDto getRealMoney(ParaMap paraMap) throws Exception {
        CExchangeMoneyDto cExchangeMoneyDto = new CExchangeMoneyDto();
        CExchangedShandianDto cExchangedShandianDto = cExchangeDAO.getExchangedShandian(paraMap);
        //计算兑换比例
        double exchangeShandian = Double.parseDouble(paraMap.get("shandian")+"");
        double poundage = 0.0;
        double shandian = 0.0;
        double integral = 0.0;

        if(Integer.parseInt(paraMap.get("type")+"") == 0){
            shandian = Double.parseDouble(cExchangedShandianDto.getShandian());
            integral = Double.parseDouble(cExchangedShandianDto.getIntegral());
            if(exchangeShandian > integral) {
                throw new CustomerException(2, "积分余额不足!");
            }
        }else{
            shandian = Double.parseDouble(cExchangedShandianDto.getRecommendShandian());
        }
        if(shandian >= exchangeShandian){
            // 积分、善点 大于等于兑换善点
            switch (Integer.parseInt(paraMap.get("arrivalMode")+"")) {
                case 1:
                    poundage = Arith.mul(exchangeShandian, BUSINESS_POUNDAGE_1, 2);
                    break;
                case 3:
                    poundage = Arith.mul(exchangeShandian, BUSINESS_POUNDAGE_3, 2);
                    break;
                case 7:
                    poundage = 5;
                    break;
                default:
                    poundage = 5;
                    break;
            }

            poundage = poundage>=5?poundage:5;
            double realMoney = exchangeShandian-poundage; //实际到账金额
            cExchangeMoneyDto.setPoundage(poundage+"");
            cExchangeMoneyDto.setSjdk(realMoney+"");
            return cExchangeMoneyDto;
        }else{
            if(paraMap.get("type").toString().equals("0")){
                throw new CustomerException(2, "鼓励点余额不足!");
            }else{
                throw new CustomerException(2, "奖励点余额不足!");
            }
        }
    }

    @Override
    public CExchangeBankcardDto getBankDetail(ParaMap paraMap) throws Exception {
        return cExchangeDAO.getBankDetail(paraMap);
    }

    @Override
    public CExchangedShandianDto getCurrentShandian(ParaMap paraMap) throws Exception {
        return cExchangeDAO.getExchangedShandian(paraMap);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public CExchangedShandianDto saveExchange(ParaMap paraMap) throws Exception {
        CExchangedShandianDto cExchangedShandianDto = new CExchangedShandianDto();
        // 停止兑换T+3和T+7,后续调整
        int arrivalMode = Integer.parseInt(paraMap.get("arrivalMode") + "");
        if(arrivalMode==1){
            throw new CustomerException(2, "暂时不可选择T+1兑换模式");
        }

        int count = 0;
        //计算兑换比例
        int type = Integer.parseInt(paraMap.get("type") + "");
        BigDecimal shandian = new BigDecimal(paraMap.get("shandian") + "");
        BigDecimal change = shandian.multiply(new BigDecimal("-1")); //负的shandian
        CExchangedShandianDto user = cExchangeDAO.getExchangedShandian(paraMap);
        BigDecimal uShandian = new BigDecimal(user.getShandian());
        BigDecimal uIntegral = new BigDecimal(user.getIntegral());
        BigDecimal uRecommendShandian = new BigDecimal(user.getRecommendShandian());
        BigDecimal oldShandian = type == 0 ? uShandian : uRecommendShandian;

        if (oldShandian.compareTo(shandian) == -1) {
            if(type==0){
                throw new CustomerException(2, "鼓励点余额不足!");
            }else{
                throw new CustomerException(2, "奖励点余额不足!");
            }
        }
        if (type == 0 && uIntegral.compareTo(shandian) == -1) {
            throw new CustomerException(2, "积分余额不足!");
        }

        // 原有代码
//        // 今日已兑换金额
//        int todayExchange = 0;
//        if(type==0){
//            // 主动善点
//            todayExchange = cExchangeDAO.countTodayExchange(paraMap);
//        }else{
//            // 被动善点
//            todayExchange = cExchangeDAO.countTodayExchangePassive(paraMap);
//        }
        // 原有代码



        // 今日已兑换金额:主动善点+被动善点:消费者端,商家端之和
        int todayExchange = cExchangeDAO.countTodayExchangeTotal(paraMap);
//        // 今日已兑换金额:主动善点+被动善点:只限于商家端
        Map businessUserId = cExchangeDAO.selectBusinessId(paraMap);
//        int businessTodayExchange = 0;
//        if (businessUserId==null||businessUserId.size()==0){    // 该用户非商家
////            int businessTodayExchange = cExchangeDAO.countBusinessTodayExchange(paraMap) + cExchangeDAO.countBusinessTodayExchangePassive(paraMap);
//        } else {    // 商家:需要计算商家的兑换总额
//            paraMap.put("businessUserId",businessUserId.get("userId"));
//            businessTodayExchange = cExchangeDAO.countBusinessTodayExchange(businessUserId) + cExchangeDAO.countBusinessTodayExchangePassive(businessUserId);
//        }
//        todayExchange += businessTodayExchange; // 消费者端 + 商家端累计兑换
        // todo查询改银行卡非本账户下的兑换总额
        int todayExchangeByThisBankCard = cExchangeDAO.countTodayExchangeByThisBankCard(paraMap);
//        todayExchange += todayExchangeIsThisBankCardAndNotThisPhone; // 消费者端 + 商家端累计兑换


//        1当日账户兑换限额设置：（半个月）
//        多账号的总监号，服务商号，代理号的同一人户名当天限额5000。// todo 根据银行卡进行限制
//        单账号的被动收益和主动收益共每天累计限额5000！
//
//        2当日分类兑换限额设置：（半个月）
//        消费者每日2000（每月6万适用刷单客，等不需要刷单客时调整为1000消费者每月能回3万足以覆盖房车月供了），商户和业务每日3000（每月回9万运作市场），服务中心和副总每日5000（每月回15万运作
//        市场）！
//
//        3暂停T+1和T+3兑换周期（系统维护）（半个月）
//
//        4预计周二安卓版率先实现购买商品积分抵扣优惠点（半个月）（善点、积分、再消分）功能，当即提前几？天通知，积分兑换规则会调整为55开，以保证能更快使用到积分抵扣和更愉快体验积分兑换商品
//        体验！

//        Map oldUserId = cExchangeDAO.getUserLevelList(paraMap);

//        List<Map> userLevels = cExchangeDAO.getUserLevelList(paraMap);
        int allExchange = todayExchange+shandian.intValue();    // 用户已经兑换的善点数+用户当前兑换的善点数
        int allExchangeByThisBankCard = todayExchangeByThisBankCard+shandian.intValue();    // 该卡已经兑换的善点数+用户当前兑换的善点数
        int maxQuota = 100;   //默认额度100

        //消费者
        int customerQuota = cExchangeDAO.getCustomerQuota(paraMap);
        if (maxQuota < customerQuota) {
            maxQuota = customerQuota;
        }

        //商家
        int businessQuota = cExchangeDAO.getBusinessQuota(paraMap);
        if (maxQuota < businessQuota) {
            maxQuota = businessQuota;
        }

        //该账号为服务商/副总/总监
        int agencyQuota = cExchangeDAO.getAgencyQuota(paraMap);
        if (maxQuota < agencyQuota) {
            maxQuota = agencyQuota;
        }

        // 推广员或者高级推广员
        int promoterQuota = cExchangeDAO.getPromoterQuota(paraMap);
        if (maxQuota < promoterQuota) {
            maxQuota = promoterQuota;
        }

        if(allExchange > maxQuota){
            throw new CustomerException(2, "您现在的兑换额度为每日"+maxQuota+"点，增加消费活跃度、业绩、营业额有助于您的兑换额度提高，谢谢支持！");
        }
        if(allExchangeByThisBankCard > maxQuota){
            throw new CustomerException(2, "您现在的兑换额度为每日该卡"+maxQuota+"点，增加消费活跃度、业绩、营业额有助于您的兑换额度提高，谢谢支持！");
        }


//        int allExchange = todayExchange+shandian.intValue();    // 用户已经兑换的善点数+用户当前兑换的善点数

//        if(allExchange>100){
//            if(type==0){
//                throw new CustomerException(2, "当前兑换规则每日只能兑换100鼓励点");
//            }else{
//                throw new CustomerException(2, "当前兑换规则每日只能兑换100奖励点");
//            }
//        }

        //计算手续费,实际到款
        BigDecimal poundage = new BigDecimal("0");
        BigDecimal sjdk = new BigDecimal("0");
        BigDecimal minPoundage = new BigDecimal("5"); //最低手续费
        switch (Integer.parseInt(paraMap.get("arrivalMode") + "")) {
            case 1:
                poundage = shandian.multiply(new BigDecimal("0.006"));
                break;
            case 3:
                poundage = shandian.multiply(new BigDecimal("0.003"));
                break;
            case 7:
                poundage = minPoundage;
                break;
            default:
                poundage = minPoundage;
                break;
        }
        poundage = poundage.compareTo(minPoundage) == 1 ? poundage : minPoundage;
        sjdk = shandian.subtract(poundage);
        if(poundage.compareTo(new BigDecimal("0")) == 0){
            throw new CustomerException(2, "兑换失败!");
        }
        paraMap.put("poundage", poundage);
        paraMap.put("sjdk", sjdk);

        BigDecimal currentShandian = (type == 0 ? uShandian : uRecommendShandian).subtract(shandian);   //结余
        BigDecimal currentIntegral = uIntegral.subtract(shandian);

        paraMap.put("currentShandian", currentShandian);
        paraMap.put("currentIntegral", currentIntegral); //扣除后的积分
        paraMap.put("state", 0);
        count += cExchangeDAO.saveExchange(paraMap);
        if (type == 0) {
            if (cExchangeDAO.updateShandian(paraMap) == 0) {
                throw new CustomerException(2, "兑换失败!");
            }
            CExchangedShandianDto myShandian = cExchangeDAO.getExchangedShandian(paraMap);
            if (currentShandian.compareTo(new BigDecimal(myShandian.getShandian())) != 0) {
                throw new CustomerException(2, "兑换失败!");
            }
            paraMap.put("leixing", 0);
            paraMap.put("shandian", change);
            count += cExchangeDAO.saveShandianBill(paraMap);
            paraMap.put("leixing", 1);
            paraMap.put("integral", change);
            count += cExchangeDAO.saveIntegralBill(paraMap);
        } else {
            if (cExchangeDAO.updateRecommendShandian(paraMap) == 0) {
                throw new CustomerException(2, "兑换失败!");
            }
            CExchangedShandianDto myShandian = cExchangeDAO.getExchangedShandian(paraMap);
            if (currentShandian.compareTo(new BigDecimal(myShandian.getRecommendShandian())) != 0) {
                throw new CustomerException(2, "兑换失败!");
            }
            paraMap.put("shandian", change);
            count += cExchangeDAO.saveRecommendShandianBill(paraMap);
        }

        if (type == 0) {
            cExchangedShandianDto.setShandian(currentShandian.toString());
            cExchangedShandianDto.setIntegral(currentIntegral.toString());
        } else {
            cExchangedShandianDto.setRecommendShandian(currentShandian.toString());
        }

        if (count > 0) {
            return cExchangedShandianDto;
        } else {
            return null;
        }
    }

}
