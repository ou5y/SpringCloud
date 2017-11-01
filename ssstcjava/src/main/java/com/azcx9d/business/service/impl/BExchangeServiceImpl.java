package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BExchangeDao;
import com.azcx9d.business.dto.*;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.exception.BusinessException;
import com.azcx9d.business.service.BExchangeService;
import com.azcx9d.common.util.Arith;
import com.azcx9d.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/4/7 0007.
 */
@Service("bExchangeService")
public class BExchangeServiceImpl implements BExchangeService {

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
    private BExchangeDao bExchangeDao;

    @Override
    public IsExchangeDto getIsExchange(ParaMap paraMap) throws Exception {
        IsExchangeDto isExchangeDto = bExchangeDao.getIsExchange(paraMap);
        isExchangeDto.setExchangeDesc(//"1、每日每张银行卡最多可兑换3000鼓励点或者奖励点。\n" +
                "1、手续费=选择T+1到账，费率为兑换鼓励点或者奖励点*0.6%；选择T+3到账，费率为兑换鼓励点或者奖励点*0.3%。最低手续费为5鼓励点或者奖励点。\n" +
                "2、可选到账时间：T+1（1个工作日到账）、T+3（3个工作日到账）、T+7（7个工作日到账）；如遇周末顺延。\n" +
                "3、不是邮政储蓄银行卡的用户按人民银行规定收取跨行转账费率。\n" +
                "4、目前商家支持T+1，其他身份支持T+3、T+7。");
        return isExchangeDto;
    }

    @Override
    public TotalExchangeDto getTotalExchange(ParaMap paraMap) throws Exception {
        return bExchangeDao.getTotalExchange(paraMap);
    }

    @Override
    public Page getExchangeList(Page page, ParaMap paraMap) throws Exception {
        int totalRow = bExchangeDao.countExchangeList(paraMap);
        page.setTotalRow(totalRow);
        paraMap.put("offset", page.getOffset());
        List<ExchangeRecordDto> list = bExchangeDao.getExchangeList(paraMap);
        page.setPageList(list);
        return page;
    }

    @Override
    public ExchangeDetailDto getExchangeDetail(ParaMap paraMap) throws Exception {
        return bExchangeDao.getExchangeDetail(paraMap);
    }

    @Override
    public ExchangeShandianDto toExchange(ParaMap paraMap) throws Exception {
        ExchangeShandianDto exchangeShandianDto = bExchangeDao.getMyShandian(paraMap);
        List<ExchangeBankcardDto> list = bExchangeDao.getMyBankCard(paraMap);
        exchangeShandianDto.setcExchangeBankcardDto(list);
        return exchangeShandianDto;
    }

    @Override
    public ExchangeMoneyDto getRealMoney(ParaMap paraMap) throws Exception {
        ExchangeMoneyDto exchangeMoneyDto = new ExchangeMoneyDto();
        ExchangedShandianDto exchangedShandianDto = bExchangeDao.getExchangedShandian(paraMap);
        //计算兑换比例
        double exchangeShandian = Double.parseDouble(paraMap.get("shandian")+"");
        double poundage = 0.0;
        double shandian = 0.0;
        double integral = 0.0;
//todo暂时修改,兼容ios
//        if(Integer.parseInt(paraMap.get("type")+"") == 0){
//            shandian = Double.parseDouble(exchangedShandianDto.getShandian());
//            integral = Double.parseDouble(exchangedShandianDto.getIntegral());
//            if(exchangeShandian > integral) {
//                throw new BusinessException(2, "积分余额不足!");
//            }
//        }else{
//            shandian = Double.parseDouble(exchangedShandianDto.getRecommendShandian());
//        }
//        if(shandian >= exchangeShandian){
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
            exchangeMoneyDto.setPoundage(poundage+"");
            exchangeMoneyDto.setSjdk(realMoney+"");
            return exchangeMoneyDto;
//        }else{
//            if(Integer.parseInt(paraMap.get("type")+"") == 0){
//                throw new BusinessException(2, "鼓励点余额不足!");
//            }else{
//                throw new BusinessException(2, "奖励点余额不足!");
//            }
//        }
    }

    @Override
    public ExchangeBankcardDto getBankDetail(ParaMap paraMap) throws Exception {
        return bExchangeDao.getBankDetail(paraMap);
    }

    @Override
    public ExchangedShandianDto getCurrentShandian(ParaMap paraMap) throws Exception {
        return bExchangeDao.getExchangedShandian(paraMap);
    }

//    @Override
//    @Transactional(rollbackFor = Throwable.class)
//    public ExchangedShandianDto saveExchange(ParaMap paraMap) throws Exception {
//        ExchangedShandianDto exchangedShandianDto = new ExchangedShandianDto();
//        int count = 0;
//        //计算兑换比例
//        int type = Integer.parseInt(paraMap.get("type") + "");
//        BigDecimal shandian = new BigDecimal(paraMap.get("shandian") + "");
//        BigDecimal change = shandian.multiply(new BigDecimal("-1")); //负的shandian
//        ExchangedShandianDto user = bExchangeDao.getExchangedShandian(paraMap);
//        BigDecimal uShandian = new BigDecimal(user.getShandian());
//        BigDecimal uIntegral = new BigDecimal(user.getIntegral());
//        BigDecimal uRecommendShandian = new BigDecimal(user.getRecommendShandian());
//        BigDecimal oldShandian = type == 0 ? uShandian : uRecommendShandian;
//
//        if (oldShandian.compareTo(shandian) == -1) {
//            if(type == 0){
//                throw new BusinessException(2, "鼓励点余额不足!");
//            }else{
//                throw new BusinessException(2, "奖励点余额不足!");
//            }
//        }
//        if (type == 0 && uIntegral.compareTo(shandian) == -1) {
//            throw new BusinessException(2, "积分余额不足!");
//        }
//
//        // 今日已兑换金额
//        int todayExchange = 0;
//        if(type==0){
//            // 主动善点
//            todayExchange = bExchangeDao.countTodayExchange(paraMap);
//        }else{
//            // 被动善点
//            todayExchange = bExchangeDao.countTodayExchangePassive(paraMap);
//        }
//
//        int allExchange = todayExchange+shandian.intValue();
//        if(allExchange>5000){
//            if(type == 0){
//                throw new BusinessException(2, "每日只能兑换5000鼓励点,今日已兑换"+todayExchange);
//            }else{
//                throw new BusinessException(2, "每日只能兑换5000奖励点,今日已兑换"+todayExchange);
//            }
//        }
//
//        //计算手续费,实际到款
//        BigDecimal poundage = new BigDecimal("0");
//        BigDecimal sjdk = new BigDecimal("0");
//        BigDecimal minPoundage = new BigDecimal("5"); //最低手续费
//        switch (Integer.parseInt(paraMap.get("arrivalMode") + "")) {
//            case 1:
//                poundage = shandian.multiply(new BigDecimal("0.006"));
//                break;
//            case 3:
//                poundage = shandian.multiply(new BigDecimal("0.003"));
//                break;
//            case 7:
//                poundage = minPoundage;
//                break;
//            default:
//                poundage = minPoundage;
//                break;
//        }
//        poundage = poundage.compareTo(minPoundage) == 1 ? poundage : minPoundage;
//        sjdk = shandian.subtract(poundage);
//        if(poundage.compareTo(new BigDecimal("0")) == 0){
//            throw new BusinessException(2, "兑换失败!");
//        }
//        paraMap.put("poundage", poundage);
//        paraMap.put("sjdk", sjdk);
//
//        BigDecimal currentShandian = (type == 0 ? uShandian : uRecommendShandian).subtract(shandian);   //结余
//        BigDecimal currentIntegral = uIntegral.subtract(shandian);
//
//        paraMap.put("currentShandian", currentShandian);
//        paraMap.put("currentIntegral", currentIntegral); //扣除后的积分
//        paraMap.put("state", 0);
//        count += bExchangeDao.saveExchange(paraMap);
//        if (type == 0) {
//            if (bExchangeDao.updateShandian(paraMap) == 0) {
//                throw new BusinessException(2, "兑换失败!");
//            }
//            ExchangedShandianDto myShandian = bExchangeDao.getExchangedShandian(paraMap);
//            if (currentShandian.compareTo(new BigDecimal(myShandian.getShandian())) != 0) {
//                throw new BusinessException(2, "兑换失败!");
//            }
//            paraMap.put("leixing", 0);
//            paraMap.put("shandian", change);
//            count += bExchangeDao.saveShandianBill(paraMap);
//            paraMap.put("leixing", 1);
//            paraMap.put("integral", change);
//            count += bExchangeDao.saveIntegralBill(paraMap);
//        } else {
//            if (bExchangeDao.updateRecommendShandian(paraMap) == 0) {
//                throw new BusinessException(2, "兑换失败!");
//            }
//            ExchangedShandianDto myShandian = bExchangeDao.getExchangedShandian(paraMap);
//            if (currentShandian.compareTo(new BigDecimal(myShandian.getRecommendShandian())) != 0) {
//                throw new BusinessException(2, "兑换失败!");
//            }
//            paraMap.put("shandian", change);
//            count += bExchangeDao.saveRecommendShandianBill(paraMap);
//        }
//
//        if (type == 0) {
//            exchangedShandianDto.setShandian(currentShandian.toString());
//            exchangedShandianDto.setIntegral(currentIntegral.toString());
//        } else {
//            exchangedShandianDto.setRecommendShandian(currentShandian.toString());
//        }
//
//        if (count > 0) {
//            return exchangedShandianDto;
//        } else {
//            return null;
//        }
//    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public ExchangedShandianDto saveExchange(ParaMap paraMap) throws Exception {
        ExchangedShandianDto exchangedShandianDto = new ExchangedShandianDto();
        // 停止兑换T+3和T+7
//        int arrivalMode = Integer.parseInt(paraMap.get("arrivalMode") + "");
//        if(arrivalMode==1||arrivalMode==3){
//            throw new BusinessException(2, "暂时不可选择T+1和T+3兑换模式");
//        }

        int count = 0;
        //计算兑换比例
        int type = Integer.parseInt(paraMap.get("type") + "");
        BigDecimal shandian = new BigDecimal(paraMap.get("shandian") + "");
        BigDecimal change = shandian.multiply(new BigDecimal("-1")); //负的shandian
        ExchangedShandianDto user = bExchangeDao.getExchangedShandian(paraMap);
        BigDecimal uShandian = new BigDecimal(user.getShandian());
        BigDecimal uIntegral = new BigDecimal(user.getIntegral());
        BigDecimal uRecommendShandian = new BigDecimal(user.getRecommendShandian());
        BigDecimal oldShandian = type == 0 ? uShandian : uRecommendShandian;

        if (oldShandian.compareTo(shandian) == -1) {
            if(type == 0){
                throw new BusinessException(2, "鼓励点余额不足!");
            }else{
                throw new BusinessException(2, "奖励点余额不足!");
            }
        }
        if (type == 0 && uIntegral.compareTo(shandian) == -1) {
            throw new BusinessException(2, "积分余额不足!");
        }
        // 原有代码
//        // 今日已兑换金额
//        int todayExchange = 0;
//        if(type==0){
//            // 主动善点
//            todayExchange = bExchangeDao.countTodayExchange(paraMap);
//        }else{
//            // 被动善点
//            todayExchange = bExchangeDao.countTodayExchangePassive(paraMap);
//        }
        // 原有代码

        // 今日已兑换金额:主动善点+被动善点:消费者端,商家端之和
        int todayExchange = bExchangeDao.countTodayExchangeTotal(paraMap);
        // 今日已兑换金额:主动善点+被动善点:只限于商家端
//        Map customerUserId = bExchangeDao.selectCustomerId(paraMap);
//        int customerTodayExchange = 0;
//        if (customerUserId==null||customerUserId.size()==0){    // 该用户非商家
////            int customerTodayExchange = cExchangeDAO.countCustomerTodayExchange(paraMap) + cExchangeDAO.countCustomerTodayExchangePassive(paraMap);
//        } else {    // 商家:需要计算商家的兑换总额
//            paraMap.put("customerUserId",customerUserId.get("userId"));
//            customerTodayExchange = bExchangeDao.countCustomerTodayExchange(customerUserId) + bExchangeDao.countCustomerTodayExchangePassive(customerUserId);
//        }
//        todayExchange += customerTodayExchange; // 消费者端 + 商家端累计兑换
        // todo查询改银行卡非本账户下的兑换总额
        int todayExchangeByThisBankCard = bExchangeDao.countTodayExchangeByThisBankCard(paraMap);
//        todayExchange += todayExchangeIsThisBankCardAndNotThisPhone; // 消费者端 + 商家端累计兑换


//        1当日账户兑换限额设置：（半个月）
//        多账号的总监号，服务商号，代理号的同一人户名当天限额5000。
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

        int allExchange = todayExchange+shandian.intValue();    // 用户已经兑换的善点数+用户当前兑换的善点数
        int allExchangeByThisBankCard = todayExchangeByThisBankCard+shandian.intValue();    // 该卡已经兑换的善点数+用户当前兑换的善点数
        double maxQuota = bExchangeDao.getExchangeable(paraMap);
        paraMap.put("maxQuota", maxQuota);

//        //消费者
//        int customerQuota = bExchangeDao.getCustomerQuota(paraMap);
//        if (maxQuota < customerQuota) {
//            maxQuota = customerQuota;
//        }
//
//        //商家
//        int businessQuota = bExchangeDao.getBusinessQuota(paraMap);
//        if (maxQuota < businessQuota) {
//            maxQuota = businessQuota;
//        }
//
//        //该账号为服务商/副总/总监
//        int agencyQuota = bExchangeDao.getAgencyQuota(paraMap);
//        if (maxQuota < agencyQuota) {
//            maxQuota = agencyQuota;
//        }
//
//        // 推广员或者高级推广员
//        int promoterQuota = bExchangeDao.getPromoterQuota(paraMap);
//        if (maxQuota < promoterQuota) {
//            maxQuota = promoterQuota;
//        }

        Double dayQuota = bExchangeDao.getDayQuota(paraMap);
        if (null != dayQuota) {
            if(allExchange > dayQuota){
                throw new BusinessException(2, "根据您在【开放兑换周期】期间的消费或交易，今日的积分奖励兑换额度为"+String.format("%.2f", dayQuota)+"，可累计到100点兑换，小提示：增加活跃消费或交易有助于提高您的当日兑换额度哦！");
            }
            if(allExchangeByThisBankCard > dayQuota){
                throw new BusinessException(2, "根据您在【开放兑换周期】期间的消费或交易，今日的积分奖励兑换额度为"+String.format("%.2f", dayQuota)+"，可累计到100点兑换，小提示：增加活跃消费或交易有助于提高您的当日兑换额度哦！");
            }
        } else {
            if(shandian.intValue() > maxQuota){
                throw new BusinessException(2, "根据您在【开放兑换周期】期间的消费或交易，今日的积分奖励兑换额度为"+String.format("%.2f", maxQuota)+"，可累计到100点兑换，小提示：增加活跃消费或交易有助于提高您的当日兑换额度哦！");
            }
            if(shandian.intValue() > maxQuota){
                throw new BusinessException(2, "根据您在【开放兑换周期】期间的消费或交易，今日的积分奖励兑换额度为"+String.format("%.2f", maxQuota)+"，可累计到100点兑换，小提示：增加活跃消费或交易有助于提高您的当日兑换额度哦！");
            }

            if(allExchange > 10000){
                throw new BusinessException(2, "根据您在【开放兑换周期】期间的消费或交易，今日的积分奖励兑换额度为10000，可累计到100点兑换，小提示：增加活跃消费或交易有助于提高您的当日兑换额度哦！");
            }
            if(allExchangeByThisBankCard > 10000){
                throw new BusinessException(2, "根据您在【开放兑换周期】期间的消费或交易，今日的积分奖励兑换额度为10000，可累计到100点兑换，小提示：增加活跃消费或交易有助于提高您的当日兑换额度哦！");
            }
        }

//        if(allExchange > 100){
//            throw new BusinessException(2, "当前兑换规则每日只能兑换100");
//        }
//        if(allExchangeByThisBankCard > 100){
//            throw new BusinessException(2, "当前兑换规则每日该卡只能兑换100");
//        }

//        Integer maxLevelId = 0;
//        for (Map userLevel : userLevels) {
//            Integer levelId = Integer.valueOf(userLevel.get("levelId").toString());
//            if(levelId>maxLevelId){
//                maxLevelId = levelId;
//            }
//        }
//        // 已经获取到maxlevelId
//        if(maxLevelId==9 || maxLevelId==7 || maxLevelId==6){ //该账号为服务商/副总/总监
//            if(allExchange > 5000){
//                throw new BusinessException(2, "服务商/副总/总监每日只能兑换5000");
//            }
//        }
//        if (maxLevelId==2||maxLevelId==1){  // 推广员或者高级推广员
//            if(allExchange > 3000){
//                throw new BusinessException(2, "推广员/高级推广员每日只能兑换3000");
//            }
//        }



//        int allExchange = todayExchange+shandian.intValue();

        // 原有代码
//        if(allExchange>100){
//            if(type == 0){
//                throw new BusinessException(2, "每日只能兑换100鼓励点");
//            }else{
//                throw new BusinessException(2, "每日只能兑换100奖励点");
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
            throw new BusinessException(2, "兑换失败!");
        }
        paraMap.put("poundage", poundage);
        paraMap.put("sjdk", sjdk);

        BigDecimal currentShandian = (type == 0 ? uShandian : uRecommendShandian).subtract(shandian);   //结余
        BigDecimal currentIntegral = uIntegral.subtract(shandian);

        paraMap.put("currentShandian", currentShandian);
        paraMap.put("currentIntegral", currentIntegral); //扣除后的积分
        paraMap.put("state", 0);
        count += bExchangeDao.saveExchange(paraMap);
        if (type == 0) {
            if (bExchangeDao.updateShandian(paraMap) == 0) {
                throw new BusinessException(2, "兑换失败!");
            }
            ExchangedShandianDto myShandian = bExchangeDao.getExchangedShandian(paraMap);
            if (currentShandian.compareTo(new BigDecimal(myShandian.getShandian())) != 0) {
                throw new BusinessException(2, "兑换失败!");
            }
            paraMap.put("leixing", 0);
            paraMap.put("shandian", change);
            count += bExchangeDao.saveShandianBill(paraMap);
            paraMap.put("leixing", 1);
            paraMap.put("integral", change);
            count += bExchangeDao.saveIntegralBill(paraMap);
//            int recovery = bExchangeDao.getRecovery(paraMap);
//            if(recovery == 0){
//                count += bExchangeDao.updateRecovery(paraMap);
//            }
        } else {
            if (bExchangeDao.updateRecommendShandian(paraMap) == 0) {
                throw new BusinessException(2, "兑换失败!");
            }
            ExchangedShandianDto myShandian = bExchangeDao.getExchangedShandian(paraMap);
            if (currentShandian.compareTo(new BigDecimal(myShandian.getRecommendShandian())) != 0) {
                throw new BusinessException(2, "兑换失败!");
            }
            paraMap.put("shandian", change);
            count += bExchangeDao.saveRecommendShandianBill(paraMap);
        }

        if (null == dayQuota) {
            count += bExchangeDao.updateExchangeable(paraMap);
            count += bExchangeDao.insertExchangeable(paraMap);
        }

        if (type == 0) {
            exchangedShandianDto.setShandian(currentShandian.toString());
            exchangedShandianDto.setIntegral(currentIntegral.toString());
        } else {
            exchangedShandianDto.setRecommendShandian(currentShandian.toString());
        }

        if (count > 0) {
            return exchangedShandianDto;
        } else {
            return null;
        }
    }

}
