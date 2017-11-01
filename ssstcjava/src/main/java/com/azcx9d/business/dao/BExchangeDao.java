package com.azcx9d.business.dao;

import com.azcx9d.business.dto.*;
import com.azcx9d.business.entity.ParaMap;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/4/7 0007.
 */
public interface BExchangeDao {

    IsExchangeDto getIsExchange(ParaMap paraMap);

    TotalExchangeDto getTotalExchange(ParaMap paraMap);

    int countExchangeList(ParaMap paraMap);

    List<ExchangeRecordDto> getExchangeList(ParaMap paraMap);

    ExchangeDetailDto getExchangeDetail(ParaMap paraMap);

    ExchangeShandianDto getMyShandian(ParaMap paraMap);

    List<ExchangeBankcardDto> getMyBankCard(ParaMap paraMap);

    ExchangedShandianDto getExchangedShandian(ParaMap paraMap);

    ExchangeBankcardDto getBankDetail(ParaMap paraMap);

    int saveExchange(ParaMap paraMap);

    int updateShandian(ParaMap paraMap);

    int saveShandianBill(ParaMap paraMap);

    int saveIntegralBill(ParaMap paraMap);

    int updateRecommendShandian(ParaMap paraMap);

    int saveRecommendShandianBill(ParaMap paraMap);

    /**
     * 计算今日已兑换主动善点
     * @param paraMap
     * @return int
     */
    int countTodayExchange(ParaMap paraMap);

    /**
     * 计算今日已兑换被动善点
     * @param paraMap
     * @return int
     */
    int countTodayExchangePassive(ParaMap paraMap);

    int countTodayExchangeTotal(ParaMap paraMap);

    /**
     * 计算消费者端的兑换:主动
     * @param paraMap
     * @return
     */
    @Select("select IFNULL(sum(c_shandian),0) total from convertibility_record\n" +
            "    where user_id=#{userId} and 0=DATEDIFF(create_date,now()) and type=#{type}")
    int countCustomerTodayExchange(Map paraMap);

    /**
     * 计算消费者端的兑换:被动
     * @param paraMap
     * @return
     */
    @Select("select IFNULL(sum(c_shandian),0) total from convertibility_record\n" +
            "    where user_id in (select id from c_user_role_attribute where user_id=(select user_id from c_user_role_attribute where id=#{userId}))\n" +
            "    and 0=DATEDIFF(create_date,now()) and type=#{type}")
    int countCustomerTodayExchangePassive(Map paraMap);

    /**
     * 查询消费者端id  -- 参数 role_id=1
     * @param paraMap
     * @return
     */
    @Select("select id userId from c_user_role_attribute where role_id=1 and user_id=(select distinct(user_id) from c_user_role_attribute where id=#{userId})")
    Map selectCustomerId(ParaMap paraMap);

    @Select("select IFNULL(sum(c_shandian),0) total from convertibility_record\n" +
            "        where 0=DATEDIFF(create_date,now())" +
            " and bank_card_no=(select bank_card_no from bank_card where id=#{bankId})")
    int countTodayExchangeByThisBankCard(ParaMap paraMap);

    @Select("SELECT IFNULL((CASE WHEN (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 > 300 THEN 300 ELSE(IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 END), 100) quota\n" +
            "FROM order_form WHERE date_sub(curdate(),interval 1 day)=date(caiwu_time) and state = 3 AND user_id = (SELECT id from c_user_role_attribute where role_id=1 and user_id = (SELECT user_id from c_user_role_attribute where id=#{userId}) limit 1)")
    int getCustomerQuota(ParaMap paraMap);

    @Select("SELECT IFNULL((CASE WHEN (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 > 500 THEN 500 ELSE (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 END), 100) quota\n" +
            "FROM order_form WHERE date_sub(curdate(),interval 1 day)=date(caiwu_time) and state = 3 AND store_id IN (SELECT id FROM business WHERE state = 1 AND user_id = #{userId})")
    int getBusinessQuota(ParaMap paraMap);

    @Select("SELECT IFNULL((CASE WHEN (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 > 500 THEN 500 ELSE (IFNULL(SUM(money), 0) DIV 20000) * 50 + 100 END), 100) quota\n" +
            "FROM order_form WHERE date_sub(curdate(),interval 1 day)=date(caiwu_time) and state = 3 AND store_id IN (SELECT id FROM business WHERE state = 1 AND upload_user = (SELECT id from c_user_role_attribute where role_id=1 and user_id = (SELECT user_id from c_user_role_attribute where id=#{userId}) limit 1))")
    int getPromoterQuota(ParaMap paraMap);

//    @Select("SELECT IFNULL((CASE WHEN (sum(CASE WHEN d.type = 1 THEN o.money * d.percent / 5000000 ELSE o.money * d.percent / 3000000 END) DIV 1) * 200 + 100 > 5000 THEN 5000\n" +
//            "ELSE(sum(CASE WHEN d.type = 1 THEN o.money * d.percent / 5000000 ELSE o.money * d.percent / 3000000 END) DIV 1) * 200 + 100 END), 100) quota\n" +
//            "FROM order_form o,(SELECT b.id,c.type,c.percent FROM business b,(SELECT ua.user_id,r.type,ua.area_id,ua.percent FROM user_agency ua,region r WHERE ua.area_id = r.code AND ua.user_id = (SELECT id from c_user_role_attribute where role_id=1 and user_id = (SELECT user_id from c_user_role_attribute where id=#{userId}) limit 1)) c\n" +
//            "WHERE (CASE WHEN c.type = 1 THEN b.province_code = c.area_id WHEN c.type = 2 THEN b.city_code = c.area_id ELSE b.area_id = c.area_id END)AND b.state = 1) d WHERE o.store_id = d.id AND o.state = 3")
    @Select("SELECT IFNULL((CASE WHEN b.maxQuota < b.totalQuota+100 THEN b.maxQuota ELSE b.totalQuota+100 END), 0) quota\n" +
            "FROM (SELECT ifnull((CASE WHEN MIN(a.type) = 1 THEN 3000 WHEN MIN(a.type) = 2 THEN 2000 WHEN MIN(a.type) = 3 THEN 1000 ELSE 0 END), 0) maxQuota,\n" +
            "floor(ifnull(sum((CASE WHEN a.type = 1 THEN 3000 WHEN a.type = 2 THEN 2000 WHEN a.type = 3 THEN 1000 ELSE 0 END) * a.percent), 0)) totalQuota\n" +
            "FROM (SELECT r.type,ua.percent FROM user_agency ua,region r,c_user_role_attribute cura WHERE ua.area_id = r.code AND ua.user_id = cura.id AND ua.trade_id = 0 \n" +
            "AND ua.user_id = (SELECT id from c_user_role_attribute where role_id=1 and user_id = (SELECT user_id from c_user_role_attribute where id=#{userId}) limit 1)) a) b")
    int getAgencyQuota(ParaMap paraMap);

    @Select("select IFNULL(exchangeable, 0) from c_user_role_attribute where id = #{userId}")
    double getExchangeable(ParaMap paraMap);

    @Update("UPDATE c_user_role_attribute SET exchangeable = exchangeable + #{shandian} where id = #{userId}")
    int updateExchangeable(ParaMap paraMap);

    @Insert("insert into ed_dzb (leixing, user_id, bdsz, jieyu, bdsj) values(0, #{userId}, #{shandian}, #{maxQuota}+#{shandian}, now())")
    int insertExchangeable(ParaMap paraMap);

    @Select("select IFNULL(recovery, 0) from c_user_role_attribute where id = #{userId}")
    int getRecovery(ParaMap paraMap);

    @Update("UPDATE c_user_role_attribute e,(SELECT d.id, d.afterIntegral,(CASE WHEN d.beforeIntegral * 0.8 > d.integral THEN 1 ELSE 0 END) is_recovery\n" +
            "FROM (SELECT a.id, a.integral, IFNULL(b.beforeIntegral, 0) beforeIntegral,IFNULL(c.afterIntegral, 0) afterIntegral FROM c_user_role_attribute a\n" +
            "LEFT JOIN (SELECT user_id, ROUND(IFNULL(SUM(bdsz), 0)) beforeIntegral FROM xfz_dzb WHERE user_id = #{userId} AND bdsj < '2017-07-21' AND leixing = 1 AND bdsz > 0 GROUP BY user_id) b ON a.id = b.user_id\n" +
            "LEFT JOIN (SELECT user_id, ROUND(IFNULL(SUM(bdsz), 0)) afterIntegral FROM xfz_dzb WHERE user_id = #{userId} AND bdsj >= '2017-07-21' AND leixing = 1 AND bdsz > 0 AND order_id IS NOT NULL GROUP BY user_id) c \n" +
            "ON a.id = c.user_id WHERE id = #{userId} AND IFNULL(b.beforeIntegral, 0) > 0) d) f SET e.integral = (CASE WHEN e.integral - f.afterIntegral <= 0 THEN e.integral ELSE f.afterIntegral END),\n" +
            "e.integral2 = (CASE WHEN e.integral - f.afterIntegral <= 0 THEN 0 ELSE e.integral - f.afterIntegral END),e.recovery = 1 WHERE e.id = f.id AND e.id = #{userId} AND f.is_recovery = 1 and e.recovery != 1")
    int updateRecovery(ParaMap paraMap);

    @Select("SELECT quota FROM c_quota WHERE phone = (SELECT phone from user u, c_user_role_attribute cura where u.id = cura.user_id and cura.id = #{userId})")
    Double getDayQuota(ParaMap paraMap);

}
