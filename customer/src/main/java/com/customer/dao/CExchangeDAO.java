package com.customer.dao;

import com.customer.dto.*;
import com.customer.entity.ParaMap;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/5/13 0013 15:15.
 */
public interface CExchangeDAO {

    CIsExchangeDto getIsExchange(ParaMap paraMap);

    CTotalExchangeDto getTotalExchange(ParaMap paraMap);

    List<CExchangeRecordDto> getExchangeList(ParaMap paraMap);

    CExchangeDetailDto getExchangeDetail(ParaMap paraMap);

    CExchangeShandianDto getMyShandian(ParaMap paraMap);

    List<CExchangeBankcardDto> getMyBankCard(ParaMap paraMap);

    CExchangedShandianDto getExchangedShandian(ParaMap paraMap);

    CExchangeBankcardDto getBankDetail(ParaMap paraMap);

    int saveExchange(ParaMap paraMap);

    int updateShandian(ParaMap paraMap);

    int saveShandianBill(ParaMap paraMap);

    int saveIntegralBill(ParaMap paraMap);

    int updateRecommendShandian(ParaMap paraMap);

    int saveRecommendShandianBill(ParaMap paraMap);

    /**
     * 计算今日主动善点总兑换金额
     * @param paraMap
     * @return int
     */
    int countTodayExchange(ParaMap paraMap);

    /**
     * 计算今日被动善点总兑换金额
     * @param paraMap
     * @return int
     */
    int countTodayExchangePassive(ParaMap paraMap);

    int countTodayExchangeTotal(ParaMap paraMap);

    /**
     * 计算商家端的兑换:主动
     * @param paraMap
     * @return
     */
    @Select("select IFNULL(sum(c_shandian),0) total from convertibility_record\n" +
            "    where user_id=#{userId} and 0=DATEDIFF(create_date,now()) and type=#{type}")
    int countBusinessTodayExchange(Map paraMap);

    /**
     * 计算商家端的兑换:被动
     * @param paraMap
     * @return
     */
    @Select("select IFNULL(sum(c_shandian),0) total from convertibility_record\n" +
            "    where user_id in (select id from c_user_role_attribute where user_id=(select user_id from c_user_role_attribute where id=#{userId}))\n" +
            "    and 0=DATEDIFF(create_date,now()) and type=#{type}")
    int countBusinessTodayExchangePassive(Map paraMap);

    /**
     * 查询用户的所有角色(不包括商家角色),按照level_id从高到低排列
     * @param paraMap
     * @return
     */
    @Select("select level_id levelId from c_platform_status where identity_id=#{userId} order by level_id asc")
    List<Map> getUserLevelList(ParaMap paraMap);

    /**
     * 查询商家端id -- 参数 role_id=2
     * @param paraMap
     * @return
     */
    @Select("select id userId from c_user_role_attribute where role_id=2 and user_id=(select distinct(user_id) from c_user_role_attribute where id=#{userId})")
    Map selectBusinessId(ParaMap paraMap);

    @Select("select IFNULL(sum(c_shandian),0) total from convertibility_record\n" +
            "        where 0=DATEDIFF(create_date,now())" +
            " and bank_card_no=(select bank_card_no from bank_card where id=#{bankId})")
    int countTodayExchangeByThisBankCard(ParaMap paraMap);

    @Select("SELECT IFNULL((CASE WHEN (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 > 300 THEN 300 ELSE(IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 END), 100) quota\n" +
            "FROM order_form WHERE date_sub(curdate(),interval 1 day)=date(caiwu_time) and state = 3 AND user_id = #{userId}")
    int getCustomerQuota(ParaMap paraMap);

    @Select("SELECT IFNULL((CASE WHEN (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 > 500 THEN 500 ELSE (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 END), 100) quota\n" +
            "FROM order_form WHERE date_sub(curdate(),interval 1 day)=date(caiwu_time) and state = 3 AND store_id IN (SELECT id FROM business WHERE state = 1 AND user_id = (SELECT id from c_user_role_attribute where role_id=2 and user_id = (SELECT user_id from c_user_role_attribute where id=#{userId}) limit 1))")
    int getBusinessQuota(ParaMap paraMap);

    @Select("SELECT IFNULL((CASE WHEN (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 > 500 THEN 500 ELSE (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 END), 100) quota\n" +
            "FROM order_form WHERE date_sub(curdate(),interval 1 day)=date(caiwu_time) and state = 3 AND store_id IN (SELECT id FROM business WHERE state = 1 AND upload_user = #{userId})")
    int getPromoterQuota(ParaMap paraMap);

    //    @Select("SELECT IFNULL((CASE WHEN (sum(CASE WHEN d.type = 1 THEN o.money * d.percent / 5000000 ELSE o.money * d.percent / 3000000 END) DIV 1) * 200 + 100 > 5000 THEN 5000\n" +
//            "ELSE(sum(CASE WHEN d.type = 1 THEN o.money * d.percent / 5000000 ELSE o.money * d.percent / 3000000 END) DIV 1) * 200 + 100 END), 100) quota\n" +
//            "FROM order_form o,(SELECT b.id,c.type,c.percent FROM business b,(SELECT ua.user_id,r.type,ua.area_id,ua.percent FROM user_agency ua,region r WHERE ua.area_id = r.code AND ua.user_id = #{userId}) c\n" +
//            "WHERE (CASE WHEN c.type = 1 THEN b.province_code = c.area_id WHEN c.type = 2 THEN b.city_code = c.area_id ELSE b.area_id = c.area_id END)AND b.state = 1) d WHERE o.store_id = d.id AND o.state = 3")
    @Select("SELECT IFNULL((CASE WHEN b.maxQuota < b.totalQuota+100 THEN b.maxQuota ELSE b.totalQuota+100 END), 0) quota\n" +
            "FROM (SELECT ifnull((CASE WHEN MIN(a.type) = 1 THEN 3000 WHEN MIN(a.type) = 2 THEN 2000 WHEN MIN(a.type) = 3 THEN 1000 ELSE 0 END), 0) maxQuota,\n" +
            "floor(ifnull(sum((CASE WHEN a.type = 1 THEN 3000 WHEN a.type = 2 THEN 2000 WHEN a.type = 3 THEN 1000 ELSE 0 END) * a.percent), 0)) totalQuota\n" +
            "FROM (SELECT r.type,ua.percent FROM user_agency ua,region r,c_user_role_attribute cura WHERE ua.area_id = r.code AND ua.user_id = cura.id AND ua.trade_id = 0 \n" +
            "AND ua.user_id = #{userId}) a) b")
    int getAgencyQuota(ParaMap paraMap);
}
