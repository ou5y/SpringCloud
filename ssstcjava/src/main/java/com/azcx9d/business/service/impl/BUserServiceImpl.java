package com.azcx9d.business.service.impl;

import com.azcx9d.agency.dto.LoginExecution;
import com.azcx9d.agency.entity.BankCard;
import com.azcx9d.agency.entity.Business;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.dao.BUserDAO;
import com.azcx9d.business.dao.DeviceUserDao;
import com.azcx9d.business.dto.DeviceUserDto;
import com.azcx9d.business.dto.StoreDto;
import com.azcx9d.business.dto.UserDto;
import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.enums.QuataEnum;
import com.azcx9d.business.service.BUserService;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.business.util.FileUpload;
import com.azcx9d.business.util.UuidUtil;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.token.TokenManager;
import com.azcx9d.common.util.Page;
import com.azcx9d.common.util.RandomUtil;
import com.azcx9d.common.util.SystemConfig;
import com.azcx9d.user.entity.Notice;
import com.azcx9d.user.entity.SystemMessage;
import com.azcx9d.user.entity.User;
import com.azcx9d.user.entity.UserSuggest;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HuangQing on 2017/3/29 0029.
 */
@Service("businessUserService")
public class BUserServiceImpl implements BUserService {

    private Logger logger = LoggerFactory.getLogger(BUserServiceImpl.class);

    @Autowired
    private BUserDAO businessUserDAO;

    @Autowired
    private TokenManager tokenDao;

    @Autowired
    private DeviceUserDao deviceUserDao;

    @Override
    public BUser selectByPhone(BUser user) throws Exception {
        return businessUserDAO.selectByPhone(user);
    }

    @Override
    public void updatePassByPhone(BUser user) throws Exception {
        BUser bUser = businessUserDAO.selectByPhone(user);
        if(bUser==null){
            return;
        }
        businessUserDAO.updatePassByPhone(user);
    }

    // 根据id修改登录密码
    @Override
    public int updatePassById(Map<String,Object> params){
        return businessUserDAO.updatePassById(params);
    }

    @Override
    public BUser selectById(int userId) throws Exception {
        return businessUserDAO.selectById(userId);
    }

    @Override
    public LoginExecution<UserDto> login(BUser user,String deviceNo,String source) throws Exception {
        BUser bUser = businessUserDAO.selectByPhone(user);
        UserDto userDto = new UserDto();
        if (bUser==null) {
            LoginExecution loginExecution = new LoginExecution(2, "用户不存在或正在审核中");
            return loginExecution;
        }

        if (user.getPassword().equals(bUser.getPassword())) {
            TokenModel tokenModel = tokenDao.createToken(bUser.getId());
            String token = tokenModel.getUserId() + "_" + tokenModel.getToken();
            bUser.setToken(token);

            userDto.setId((int) bUser.getId());
            userDto.setUserName(bUser.getName());
            userDto.setAvatar(bUser.getAvatar());
            userDto.setIntegral(bUser.getJifen()+"");
            userDto.setRecommendEarnings(bUser.getShandian2()+"");
            userDto.setPhone(bUser.getPhone());
            userDto.setToken(bUser.getToken());
            userDto.setShandian(bUser.getShandian()+"");

            List<StoreDto> storeDtoList=businessUserDAO.getStoreList((int) bUser.getId());

            if(storeDtoList==null || storeDtoList.size()==0){
                LoginExecution loginExecution = new LoginExecution(2, "未上传店铺或正在审核中，请耐心等待");
                return loginExecution;
            }

            for (StoreDto storeDto:storeDtoList
                 ) {
                storeDto.setLoveQuotaValue((Double.valueOf(bUser.getTodayLove())+Double.valueOf(bUser.getLoveSmall())+Double.valueOf(bUser.getLoveMiddle())+Double.valueOf(bUser.getLoveLarge()))+"");
                storeDto.setLoveQuotaName(QuataEnum.DEFAULT_QUOTA.getQuataDesc());
//                if(storeDto.getQuotaType()==0)
//                {
//                    storeDto.setLoveQuotaValue(bUser.getTodayLove());
//                    storeDto.setLoveQuotaName(QuataEnum.DEFAULT_QUOTA.getQuataDesc());
//                }
//                if(storeDto.getQuotaType()==1)
//                {
//                    storeDto.setLoveQuotaValue((Double.valueOf(bUser.getLoveSmall())+Double.valueOf(bUser.getTodayLove()))+"");
//                    storeDto.setLoveQuotaName(QuataEnum.MIN.getQuataDesc());
//                }
//
//                if (storeDto.getQuotaType()==2)
//                {
//                    storeDto.setLoveQuotaValue(bUser.getLoveMiddle());
//                    storeDto.setLoveQuotaName(QuataEnum.MID.getQuataDesc());
//                }
//
//
//                if (storeDto.getQuotaType()==3)
//                {
//                    storeDto.setLoveQuotaValue(bUser.getLoveLarge());
//                    storeDto.setLoveQuotaName(QuataEnum.BIG.getQuataDesc());
//                }

            }
            userDto.setStoreDtoList(storeDtoList);

            ParaMap paraMap = new ParaMap();
            paraMap.put("id", bUser.getId());
            userDto.setHasTransPwd(1 == businessUserDAO.hasTransPwd(paraMap) ? true : false);

            LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(0, "登录成功", userDto);
            if(deviceNo!=null && !deviceNo.equals("")) {
                // 设备编号不为空
                try {
                    Map<String, Object> params = new HashMap<String, Object>(0);
                    params.put("userId", bUser.getId());
                    params.put("deviceNo", deviceNo);
                    params.put("source", source);
                    DeviceUserDto deviceUserDto = deviceUserDao.queryIsEnable(params);

                    if (deviceUserDto != null && (deviceUserDto.getUserId() == null ||
                            (deviceUserDto.getUserId() != null && !deviceUserDto.getUserId().equals(bUser.getId())))) {
                        deviceUserDao.updateDevice(params);
                    }
//                    else{
//                        deviceUserDao.addDevice(params);
//                    }
                } catch (Exception e) {
                    logger.error("查询设备失败：userId:+"+bUser.getId()+","+e.getMessage());
                }
            }

            return loginExecution;
        } else {
            LoginExecution loginExecution = new LoginExecution(1, "用户名或密码错误");
            return loginExecution;
        }
    }

    @Override
    public JsonResult addTransPwd(ParaMap paraMap) throws Exception {
        int count = businessUserDAO.hasTransPwd(paraMap);
        if(count > 0){
            return new JsonResult(2,"您已设置过交易密码!不能重复设置");
        }else{
            int updateCount = businessUserDAO.updateTransPwd(paraMap);
            if(updateCount > 0){
                return new JsonResult(0,"交易密码设置成功!");
            }else{
                return new JsonResult(2,"交易密码设置失败!请稍后试试");
            }
        }
    }

    @Override
    public JsonResult modifyTransPwd(ParaMap paraMap) throws Exception {
        int hasCount = businessUserDAO.hasTransPwd(paraMap);
        if(hasCount == 0){
            return new JsonResult(2,"您还未设置过交易密码!不能修改");
        }
        int checkCount = businessUserDAO.checkTransPwd(paraMap);
        if(checkCount > 0){
            int updateCount = businessUserDAO.updateTransPwd(paraMap);
            if(updateCount > 0){
                return new JsonResult(0,"交易密码修改成功!");
            }else{
                return new JsonResult(2,"交易密码修改失败!请稍后试试");
            }
        }else{
            return new JsonResult(2,"旧交易密码不正确!");
        }
    }

    @Override
    public JsonResult findTransPwd(ParaMap paraMap) throws Exception {
        int updateCount = businessUserDAO.updateTransPwd(paraMap);
        if(updateCount > 0){
            return new JsonResult(0,"交易密码重置成功!");
        }else{
            return new JsonResult(2,"交易密码重置失败!请稍后试试");
        }
    }

    @Override
    public JsonResult hasTransPwd(ParaMap paraMap) throws Exception {
        int hasCount = businessUserDAO.hasTransPwd(paraMap);
        if(hasCount > 0){
            return new JsonResult(2,"已设置交易密码!");
        }else{
            return new JsonResult(0,"未设置交易密码!");
        }
    }

    // 上传身份证正反面
    @Override
    public int uploadIdentityCard(CommonsMultipartFile files[], int id) throws Exception{
        int count = 0;
        Map<String,String> filePaths = new HashMap<String, String>();				//存储文件上传后的地址

        for (int i = 0; i < files.length; i++) {
            String  ffile = DateUtil.getDays(), fileName = "";
            CommonsMultipartFile file = files[i];
            if (file != null) {
                String filePath = SystemConfig.getProperty("picture","business","phycial") + ffile;//文件写入硬盘的物理路径
                fileName = FileUpload.fileUp(file, filePath,  UuidUtil.get32UUID());//执行上传
                if (!"".equals(fileName.trim())) {
                    String readPath = SystemConfig.getProperty("picture","business","url") + ffile + "/" + fileName;//文件读取路径
                    switch (i) {
                        case 0:
                            filePaths.put("identityCardUp", readPath);
                            break;
                        case 1:
                            filePaths.put("identityCardDown", readPath);
                            break;
                        default:
                            break;
                    }
                }
            } else {
                return 0;
            }
        }
        BUser user = businessUserDAO.selectById(id);
        user.setIdentityCardUp(filePaths.get("identityCardUp"));
        user.setIdentityCardDown(filePaths.get("identityCardDown"));
        count = businessUserDAO.uploadIdentityCard(user);
        return count;
    }

    @Override
    public List<BankCard> queryMyBankCard(int id) {
        return businessUserDAO.queryMyBankCard(id);
    }

    // 查询结算账户
    @Override
    public List<Map<String,Object>> queryClearanceAccount(Map<String,Object> params){
        return businessUserDAO.queryClearenceAccount(params);
    }

    @Override
    public int addBankCard(HashMap<String, String> params){
        int count = businessUserDAO.addBankCard(params);
        String xingming = businessUserDAO.queryUserXingming(params);
        if(xingming==null || xingming.equals("") || xingming.equalsIgnoreCase("null")){
            businessUserDAO.updateUserXingming(params);
        }
        return count;
    }

    @Override
    public int deleteBankCard(HashMap<String, String> params){
        return businessUserDAO.deleteBankCard(params);
    }

    @Override
    public Page queryRecommend(Map<String,Object> params, Page page) throws Exception {
        int totalRow = businessUserDAO.countTotalRecommend(params);
        page.setTotalRow(totalRow);
        params.put("offset", page.getOffset());
        List<Map<String,Object>> list = businessUserDAO.queryRecommend(params);
        page.setPageList(list);
        return page;
    }

    @Override
    public BankCard findBankCardById(int id) throws Exception {
        return businessUserDAO.findBankCardById(id);
    }

    @Override
    public Page querySystemMessage(Map<String, Object> params,Page page) throws Exception {
        int totalRow = businessUserDAO.countSystemMessage(params);
        page.setTotalRow(totalRow);
        params.put("offset", page.getOffset());
        List<SystemMessage> list = businessUserDAO.querySystemMessage(params);
        page.setPageList(list);
        return page;
    }

    @Override
    public int readSystemMessage(Map<String, Object> params) throws Exception {
        return businessUserDAO.readSystemMessage(params);
    }

    @Override
    public int addUserSuggest(UserSuggest userSuggest) throws Exception {
        return businessUserDAO.addUserSuggest(userSuggest);
    }

    @Override
    public BankCard findBankCard(Map<String,String> params) throws Exception {
        return businessUserDAO.findBankCard(params);
    }

    @Override
    public Map<String,Object> queryVersionInfo(Map<String, Object> params) throws Exception{
        return businessUserDAO.queryVersionInfo(params);
    }

    @Override
    public Page getBussinessList(Map<String, Object> params, Page page) throws Exception {
        int totalRow = businessUserDAO.countTotalBussiness(params);
        page.setTotalRow(totalRow);
        params.put("offset", page.getOffset());
        List<Map<String,Object>> list = businessUserDAO.getBussinessList(params);
        page.setPageList(list);
        return page;
    }

    /**
     * 查找公告
     * @param params
     * @return
     */
    @Override
    public Notice queryNotice(Map<String,Object> params) throws Exception {
        return  businessUserDAO.queryNotice(params);
    }

    // 解绑分账银行卡
    @Override
    public int deleteClearanceAccount(Map<String,Object> params){
        return businessUserDAO.deleteClearanceAccount(params);
    }

    // 添加分账银行卡
    @Override
    public int addClearanceAccount(Map<String,Object> params){
        return  businessUserDAO.addClearanceAccount(params);
    }

    // 统计分账银行卡数量
    @Override
    public int queryClearanceAccountCount(Map<String,Object> params){
        return businessUserDAO.queryClearanceAccountCount(params);
    }

    // 注册
    @Override
    public JsonResult addUser(Map<String,Object> params) throws Exception{
        JsonResult json = null;
        BUser parent = new BUser();
        UserDto userDto = new UserDto();
        String phone = (String) params.get("phone");

        String recommendPhone= (String) params.get("recommendPhone");

        int count = businessUserDAO.countByPhone(phone);
        if(count>0){
            return new JsonResult(2,"该手机号码已经注册了");
        }

        if (StringUtils.isBlank(recommendPhone)) {
            //TODO 推荐人号码为空,则设置默认号码
            recommendPhone="17313147515";
        }
        parent=businessUserDAO.queryByPhone(recommendPhone);

        if (parent!=null){
            params.put("parentId",parent.getId());
            params.put("directlyId",parent.getDirectlyId());
        }else{
            return new JsonResult(2,"推荐人号码未注册");
        }

        if (params.get("userName")==null){
            params.put("userName", RandomUtil.getRandomString(16));
        }

        int result = businessUserDAO.addUser(params);
        if(result>0){
            result += businessUserDAO.addUserAttribute(params);
            if(result>1){
                return new JsonResult(0,"注册成功");
            }else{
                return new JsonResult(2,"注册失败，请稍后重试");
            }
        }else{
            return new JsonResult(2,"注册失败，请重试");
        }
    }

    @Override
    public Map<String, Object> queryPwd(Map<String,Object> paraMap) {
        return businessUserDAO.queryPwd(paraMap);
    }

    // 查询用户详情
    @Override
    public Map<String,Object> queryUserDetail(Map<String,Object> paraMap) throws Exception{
        Map<String,Object> result = businessUserDAO.queryUserDetail(paraMap);
        if(result!=null){
            if(result.get("transPwd")!=null&&!"".equals(result.get("transPwd").toString())){
                result.put("hasTransPwd",true);
            }else{
                result.put("hasTransPwd",false);
            }
            result.remove("transPwd");
        }
        return result;
    }

    @Override
    public JsonResult<UserDto> detail(int id) throws Exception
    {
        UserDto userDto = new UserDto();
        BUser bUser= businessUserDAO.findUserDetailById(id);

        userDto.setId((int) bUser.getId());
        userDto.setUserName(bUser.getName());
        userDto.setAvatar(bUser.getAvatar());
        userDto.setIntegral((new Double(bUser.getJifen())).intValue()+"");//积分不要小数点
        userDto.setRecommendEarnings(bUser.getShandian2()+"");
        userDto.setPhone(bUser.getPhone());
        userDto.setShandian(bUser.getShandian()+"");

        List<StoreDto> storeDtoList=businessUserDAO.getStoreList((int) bUser.getId());

        for (StoreDto storeDto:storeDtoList
                ) {
            storeDto.setLoveQuotaValue((Double.valueOf(bUser.getTodayLove())+Double.valueOf(bUser.getLoveSmall())+Double.valueOf(bUser.getLoveMiddle())+Double.valueOf(bUser.getLoveLarge()))+"");
            storeDto.setLoveQuotaName(QuataEnum.DEFAULT_QUOTA.getQuataDesc());
//            if(storeDto.getQuotaType()==0)
//            {
//                storeDto.setLoveQuotaValue(bUser.getTodayLove());
//                storeDto.setLoveQuotaName(QuataEnum.DEFAULT_QUOTA.getQuataDesc());
//            }
//
//            if(storeDto.getQuotaType()==1)
//            {
//                storeDto.setLoveQuotaValue((Double.valueOf(bUser.getLoveSmall())+Double.valueOf(bUser.getTodayLove()))+"");
//                storeDto.setLoveQuotaName(QuataEnum.MIN.getQuataDesc());
//            }
//
//            if (storeDto.getQuotaType()==2)
//            {
//                storeDto.setLoveQuotaValue(bUser.getLoveMiddle());
//                storeDto.setLoveQuotaName(QuataEnum.MID.getQuataDesc());
//            }
//
//
//            if (storeDto.getQuotaType()==3)
//            {
//                storeDto.setLoveQuotaValue(bUser.getLoveLarge());
//                storeDto.setLoveQuotaName(QuataEnum.BIG.getQuataDesc());
//            }

        }
        userDto.setStoreDtoList(storeDtoList);

        ParaMap paraMap = new ParaMap();
        paraMap.put("id", bUser.getId());
        userDto.setHasTransPwd(1 == businessUserDAO.hasTransPwd(paraMap) ? true : false);

       return new JsonResult<UserDto>(userDto);
    }
}