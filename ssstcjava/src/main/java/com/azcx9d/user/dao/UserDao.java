package com.azcx9d.user.dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.common.entity.QRCode;
import com.azcx9d.user.entity.SystemMessage;
import com.azcx9d.user.entity.User;
import com.azcx9d.user.entity.UserSuggest;

import org.springframework.stereotype.Repository;

import javax.persistence.Entity;


public interface UserDao {
	User selectUser(User user) throws SQLException;
    
    int selectByIdentify(@Param("phone") String phone)throws SQLException;
    	
    User selectUserById(long id)throws SQLException;
     
    int insertUser(User user)throws SQLException;
    
    QRCode selectQRCode(User user) throws SQLException;
    
    int updateUser(User user);
    
    /**
     * 根据id查询用户的银行卡
     * @param id
     * @return List<BankCard>
     */
    List<BankCard> queryMyBankCard(long id);
    
    /**
     * 新增银行卡
     * @param params
     * @return 受影响行数
     */
    int addBankCard(HashMap<String, String> params);
    
    /**
     * 解除银行卡绑定关系
     * @param params
     * @return 受影响行数
     */
    int deleteBankCard(HashMap<String, String> params);
    
    /**
     * 根据银行卡id查询银行卡信息
     * @param id
     * @return BankCard
     */
    BankCard findBankCardById(long id);
    
    /**
     * 查找银行卡
     * @param params
     * @return BankCard
     */
    BankCard findBankCard(Map<String,String> params);
    
    /**
     * 查询推荐记录
     * @param params
     * @return Map<String,Object>
     */
    List<Map<String,Object>> queryRecommend(Map<String,String> params);
    
    /**
     * 修改交易密码
     * @param params
     * @return int
     */
    int updateTransPwd(Map<String,String> params);
    
    /**
     * 查询用户密码
     * @param params
     * @return Map<String,String>
     */
    Map<String,String> queryPassword(Map<String,String> params);
    
    /**
     * 修改交易密码
     * @param params
     * @return int
     */
    int updateLoginPwd(Map<String,String> params);
    
    /**
     * 查询用户交易密码
     * @param id
     * @return
     * @throws Exception
     */
    Agency selectTransPwd(long id) throws Exception;
    
    /**
     * 查询系统消息
     * @param params
     * @return List<SystemMessage>
     */
    List<SystemMessage> queryMessage(Map<String,Object> params);
    
    /**
     * 更新消息阅读状态
     * @param params
     * @return int
     */
    int updateReadState (Map<String,Object> params);
    
    /**
     * 查询用户的善心善点
     * @param params
     * @return Map<String,String>
     */
    Map<String,String> queryShanxin(Map<String,String> params);
    
    /**
     * 查询大盘善心
     * @param params
     * @return Map<String,String>
     */
    Map<String,Object> queryLovePercent();
    
    /**
     * 添加用户建议反馈
     * @param suggest
     * @return int
     */
    int addUserSuggest(UserSuggest suggest);
    
    /**
     * 更新善点
     * @param params
     * @return updateShandian
     */
    int updateShandian(Map<String,Object> params);

    /**
     * 更新善点
     * @param params
     * @return updateShandian
     */
    int updateShandian2(Map<String,Object> params);

    
    /**
     * 查询更新版本信息
     * @return Map<String,String>
     */
    Map<String,Object> queryVersionInfo(Map<String,Object> params);
    
    /**
     * 根据手机号码
     * @param params
     * @return
     */
    Map<String,Object> selectByPhone(Map<String,Object> params);
    
    /**
     * 保存用户信息
     * @param params
     * @return
     */
    int saveUser(Map<String,Object> params);
    
}
