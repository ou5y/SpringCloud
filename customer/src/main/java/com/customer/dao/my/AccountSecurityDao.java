package com.customer.dao.my;

import com.customer.entity.BankCard;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
public interface AccountSecurityDao {

    /**
     * 查询银行卡
     * @param params
     * @return
     */
    List<Map<String,Object>> queryMyBankCard(Map<String,Object> params);

    /**
     * 查询银行卡信息
     * @param params
     * @return BankCard
     */
    BankCard findBankCard(Map<String,Object> params);

    /**
     * 绑定银行卡
     * @param params
     * @return int
     */
    int addBankCard(Map<String,Object> params);

    /**
     * 解绑银行卡
     * @param params
     * @return
     */
    int deleteBankCard(Map<String,Object> params);

    /**
     * 更新交易密码
     * @param params
     * @return int
     */
    int updateTransPwd(Map<String,Object> params);

    /**
     * 更新交易密码
     * @param params
     * @return int
     */
    int updateLoginPwd(Map<String,Object> params);

    /**
     * 查询密码
     * @param params
     * @return
     */
    Map<String,Object> queryPwd(Map<String,Object> params);

    /**
     * 查询用户姓名
     * @param params
     * @return String
     */
    String queryUserXingming(Map<String,Object> params);

    /**
     * 更新用户姓名
     * @param params
     * @return 受影响行数
     */
    int updateUserXingming(Map<String,Object> params);

}
