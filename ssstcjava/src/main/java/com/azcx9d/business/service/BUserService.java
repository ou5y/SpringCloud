package com.azcx9d.business.service;

import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.business.dto.UserDto;
import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Page;
import com.azcx9d.user.entity.Notice;
import com.azcx9d.user.entity.SystemMessage;
import com.azcx9d.user.entity.UserSuggest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/3/29 0029.
 */
public interface BUserService {

    /**
     * 根据手机号查询商户详情
     * @param user 包含phone,type
     * @return
     */
    BUser selectByPhone(BUser user) throws Exception;

    /**
     * 修改用于密码
     * @param user 包括phone  以及新密码pass
     * @throws Exception
     */
    void updatePassByPhone(BUser user) throws Exception;

    /**
     * 根据id修改登录密码
     * @param params
     * @return int
     */
    int updatePassById(Map<String,Object> params);

    /**
     * 根据用户id查询对应的用户详情
     * @param userId 用户id
     * @throws Exception
     */
    BUser selectById(int userId) throws Exception;

    LoginExecution<UserDto> login(BUser user,String deviceNo,String source) throws Exception;

    /**
     * 设置交易密码
     * @param paraMap
     * @return
     */
    JsonResult addTransPwd(ParaMap paraMap) throws Exception;

    /**
     * 修改交易密码
     * @param paraMap
     * @return
     */
    JsonResult modifyTransPwd(ParaMap paraMap) throws Exception;

    /**
     * 找回交易密码
     * @param paraMap
     * @return
     */
    JsonResult findTransPwd(ParaMap paraMap) throws Exception;

    /**
     * 是否设置交易密码
     * @param paraMap
     * @return
     */
    JsonResult hasTransPwd(ParaMap paraMap) throws Exception;

    /**
     * 上传身份证正反面
     * @param files		待上传文件
     * @param id		用户id
     * @return int
     */
    int uploadIdentityCard(CommonsMultipartFile files[], int id) throws Exception;

    /**
     * 根据id查询用户的银行卡
     * @param id
     * @return List<BankCard>
     */
    List<BankCard> queryMyBankCard(int id);

    /**
     * 查询结算账户
     * @param params
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> queryClearanceAccount(Map<String,Object> params);

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
     * 查询推荐记录
     * @param params
     * @return Map<String,Object>
     */
    Page queryRecommend(Map<String,Object> params, Page page) throws Exception;

    /**
     * 根据银行卡id查询银行卡信息
     * @param id
     * @return BankCard
     */
    BankCard findBankCardById(int id) throws Exception;

    /**
     * 查询系统消息
     * @param params
     * @return Page
     */
    Page querySystemMessage(Map<String,Object> params, Page page) throws Exception;

    /**
     * 更新系统消息已读状态
     * @param params
     * @return int
     */
    int readSystemMessage(Map<String,Object> params) throws Exception;

    /**
     * 添加用户建议反馈
     * @param userSuggest
     * @return int
     */
    int addUserSuggest(UserSuggest userSuggest) throws Exception;

    /**
     * 查找银行卡
     * @param params
     * @return BankCard
     */
    BankCard findBankCard(Map<String,String> params) throws Exception;

    /**
     * 查询更新版本信息
     * @return Map<String,String>
     */
    Map<String,Object> queryVersionInfo(Map<String, Object> params) throws Exception;

    /**
     * 查询店铺列表
     * @param params
     * @return Page
     */
    Page getBussinessList(Map<String,Object> params, Page page) throws Exception;

    /**
     * 查找公告
     * @param params
     * @return
     */
    Notice queryNotice(Map<String,Object> params) throws Exception;

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

    /**
     * 注册
     * @param params
     * @return JsonResult
     */
    JsonResult addUser(Map<String,Object> params) throws Exception;

    Map<String,Object> queryPwd(Map<String,Object> paraMap);

    /**
     * 查询用户详情
     * @param paraMap
     * @return Map<String,Object>
     */
    Map<String,Object> queryUserDetail(Map<String,Object> paraMap) throws Exception;

    JsonResult<UserDto> detail(int id) throws Exception;
}
