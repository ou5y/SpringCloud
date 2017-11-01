package com.azcx9d.user.service;


import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.ParseException;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.Agency;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.common.entity.QRCode;
import com.azcx9d.user.entity.SystemMessage;
import com.azcx9d.user.entity.User;
import com.azcx9d.user.entity.UserSuggest;
import com.azcx9d.user.entity.YunpianReaultEntity;

/**
 * Created by fangbaoyan on 2017/3/14.
 */
public interface UserApiService {

    /**
     * 代理端登陆
     * @param agency
     * @return
     */
    LoginExecution login(Agency agency)throws Exception;
    
    
    Agency queryUserInfo(long id) throws Exception;
    
    /**
    *用户注册
    * @param user
    */
   int doRegister(User user) throws Exception;
   
   /**
    * 
    * @param user
    * @return 微信二维码生成url
    * @throws Exception
    */
   String getMyQRCode(User user) throws Exception;
   /**
    * 验证码
    * @param phone
    * @return
    */
   YunpianReaultEntity identifyingCode(String phone)throws ParseException, IOException;

   YunpianReaultEntity identifyingCodeByphone(String phone, int randNum)throws ParseException, IOException;
   
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
   BankCard findById(long id);
   
   /**
    * 查找银行卡
    * @param params
    * @return BankCard
    */
   BankCard findBankCard(Map<String,String> params);
   
   /**
    * 上传身份证正反面
    * @param files		待上传文件
    * @param files	保存地址
    * @param id	上传地址
    * @param id			用户id
    * @return int
    */
   int uploadIdentityCard(CommonsMultipartFile files[],long id) throws Exception ;
   
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
   Agency queryTransPwd(long id) throws Exception;
   
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
    * 添加用户建议反馈
    * @param suggest
    * @return int
    */
   int addUserSuggest(UserSuggest suggest);
   
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
