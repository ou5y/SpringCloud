package com.azcx9d.business.dao;

import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.agency.entity.Business;
import com.azcx9d.business.dto.StoreDto;
import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.user.entity.Notice;
import com.azcx9d.user.entity.SystemMessage;
import com.azcx9d.user.entity.UserSuggest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/3/28 0028.
 */
public interface BUserDAO {

    BUser selectByPhone(BUser user);

    int countByPhone(String phone);

    BUser queryByPhone(String phone);

    void updatePassByPhone(BUser user);

    /**
     * 根据id修改登录密码
     * @param params
     * @return int
     */
    int updatePassById(Map<String,Object> params);

    BUser selectById(int userId);

    /**
     * 检查是否已经设置了交易密码
     * @param paraMap
     * @return
     */
    int hasTransPwd(ParaMap paraMap);

    /**
     * 更新交易密码
     * @param paraMap
     * @return
     */
    int updateTransPwd(ParaMap paraMap);

    /**
     * 检查原交易密码是否正确
     * @param paraMap
     * @return
     */
    int checkTransPwd(ParaMap paraMap);

    /**
     * 更新善点
     * @param params
     * @return updateShandian
     */
    int updateShandian(Map<String,Object> params);

    /**
     * 上传身份证正反面
     * @param bUser
     * @return int
     */
    int uploadIdentityCard(BUser bUser);

    /**
     * 根据id查询用户的银行卡
     * @param id
     * @return List<BankCard>
     */
    List<BankCard> queryMyBankCard(int id);

    /**
     * 查询结算账户银行卡
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryClearenceAccount(Map<String, Object> params);

    /**
     * 新增银行卡
     * @param params
     * @return 受影响行数
     */
    int addBankCard(HashMap<String, String> params);

    /**
     * 查询用户姓名
     * @param params
     * @return String
     */
    String queryUserXingming(Map<String,String> params);

    /**
     * 更新用户姓名
     * @param params
     * @return 受影响行数
     */
    int updateUserXingming(Map<String,String> params);

    /**
     * 解除银行卡绑定关系
     * @param params
     * @return 受影响行数
     */
    int deleteBankCard(HashMap<String, String> params);

    /**
     * 查询大盘善心
     * @param
     * @return Map<String,String>
     */
    Map<String,Object> queryLovePercent();

    /**
     * 查询推荐记录
     * @param params
     * @return Map<String,Object>
     */
    List<Map<String,Object>> queryRecommend(Map<String,Object> params);

    /**
     * 查询推荐记录条数
     * @param params
     * @return int
     */
    int countTotalRecommend(Map<String,Object> params);

    /**
     * 根据银行卡id查询银行卡信息
     * @param id
     * @return BankCard
     */
    BankCard findBankCardById(int id);

    /**
     * 查询系统消息
     * @param params
     * @return SystemMessage
     */
    List<SystemMessage> querySystemMessage(Map<String,Object> params);

    /**
     * 查询系统消息条数
     * @param params
     * @return int
     */
    int countSystemMessage(Map<String,Object> params);

    /**
     * 更新系统消息已读状态
     * @param params
     * @return int
     */
    int readSystemMessage(Map<String,Object> params);

    /**
     * 添加用户建议反馈
     * @param userSuggest
     * @return int
     */
    int addUserSuggest(UserSuggest userSuggest);

    /**
     * 查找银行卡
     * @param params
     * @return BankCard
     */
    BankCard findBankCard(Map<String,String> params);

    /**
     * 查询更新版本信息
     * @param params
     * @return Map<String,String>
     */
    Map<String,Object> queryVersionInfo(Map<String, Object> params);

    /**
     * 查询店铺列表
     * @param params
     * @return List<Business>
     */
    List<Map<String,Object>> getBussinessList(Map<String,Object> params);

    /**
     * 查询店铺列表条数
     * @param params
     * @return int
     */
    int countTotalBussiness(Map<String,Object> params);

    /**
     * 更新被动善点
     * @param params
     * @return
     */
    int updateShandian2(Map<String,Object> params);

    /**
     * 查找公告
     * @param params
     * @return
     */
    Notice queryNotice(Map<String,Object> params);

    /**
     * 解绑分账银行卡
     * @param params
     * @return int
     */
    int deleteClearanceAccount(Map<String,Object> params);

    /**
     * 添加分账银行卡
     * @param params
     * @return int
     */
    int addClearanceAccount(Map<String,Object> params);

    /**
     * 统计分账银行卡数量
     * @param params
     * @return int
     */
    int queryClearanceAccountCount(Map<String,Object> params);


    List<StoreDto> getStoreList(int id);

    /**
     * 添加用户
     * @param params
     * @return int
     */
    int addUser(Map<String,Object> params);

    /**
     * 添加用户
     * @param params
     * @return int
     */
    int addUserAttribute(Map<String,Object> params);

    Map<String,Object> queryPwd(Map<String,Object> paraMap);

    /**
     * 查询用户详情
     * @param paraMap
     * @return Map<String,Object>
     */
    Map<String,Object> queryUserDetail(Map<String,Object> paraMap);

    BUser findUserDetailById(int id);
}
