package com.customer.service.impl;

import com.customer.config.RedisManager;
import com.customer.dao.CPlatformStatusMapper;
import com.customer.dao.CUserRoleAttributeMapper;
import com.customer.dao.UserDao;
import com.customer.dao.index.DeviceUserDao;
import com.customer.dto.*;
import com.customer.dto.index.DeviceUserDto;
import com.customer.entity.*;
import com.customer.enums.QuataEnum;
import com.customer.exception.CustomerException;
import com.customer.service.UserService;
import com.customer.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * Created by fangbaoyan on 2017/4/24.
 */
@Service

public class UserServiceImpl implements UserService{

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private CUserRoleAttributeMapper cUserRoleAttributeMapper;

    @Autowired
    private CPlatformStatusMapper cPlatformStatusMapper;

    @Autowired
    private TokenManager tokenService;

    @Autowired
    private RedisManager redisManager;

    @Autowired
    private DeviceUserDao deviceUserDao;



    public static final Map<Integer,String> mapLevel =new HashMap<Integer,String>();
    static {
        mapLevel.put(1,"高级推广员");
        mapLevel.put(2,"推广员");
    }

    public static final Integer ROLEID=1;//消费者

    @Override
    public User getUserByName(String username)throws Exception {
        return userDao.findByName(username);
    }


    public PageInfo<User> findAll() throws Exception{
        PageHelper.startPage(2, 10);
        List<User> list = userDao.findAll();
        return new PageInfo<User>(list);
    }




    public LoginExecution<UserDto> login(Map<String, Object> map) throws Exception {

           User user= userDao.findByPhone((String) map.get("phone"));

        if (user == null) {
            LoginExecution loginExecution = new LoginExecution<>(2, "用户不存在");
            return loginExecution;
        }else {
            map.put("userId",user.getId());
            UserDto  customer=userDao.findUserByUserIdAndPass(map);

            if (customer != null) {
                List<LoveContent> loveContents = Arrays.asList(
                        new LoveContent(customer.getTodayLove(),QuataEnum.DEFAULT_QUOTA.getQuataDesc(),QuataEnum.DEFAULT_QUOTA.getCode()),
                        new LoveContent(customer.getLoveSmall(),QuataEnum.MIN.getQuataDesc(),QuataEnum.MIN.getCode()),
                        new LoveContent(customer.getLoveMiddle(),QuataEnum.MID.getQuataDesc(),QuataEnum.MID.getCode()),
                        new LoveContent(customer.getLoveLarge(),QuataEnum.BIG.getQuataDesc(),QuataEnum.BIG.getCode())
                );
//            Collections.sort(loveContents);
                customer.setLoveContents(loveContents);
                List<RoleListDto> roleListDto =this.setRole(customer) ;

                if(roleListDto.size()>=1) {
                    customer.setLevelName(roleListDto.get(0).getLevelName());
                    customer.setLevelId(roleListDto.get(0).getLevelId());
                }
                else
                    customer.setLevelName("会员");
                customer.setRoleListDto(roleListDto);

                TokenModel tokenModel = tokenService.createToken(customer.getId(),customer.getUserId());


                String token =tokenModel.getId()+"_"+tokenModel.getUserId() + "_" + tokenModel.getToken();
                customer.setToken(token);

                Map<String,Object> hasTransPwd = userDao.queryTransPwd(map);
                if(hasTransPwd!=null && hasTransPwd.size()>0){
                    customer.setHasTransPwd(true);
                }else{
                    customer.setHasTransPwd(false);
                }

                LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(0, "登录成功", customer);

                if(map.get("deviceNo")!=null && !map.get("deviceNo").equals("")) {
                    // 设备编号不为空
                    verifyDevice(map,customer);
                }

                return loginExecution;
            } else {
                LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(2, "用户名密码错误");
                return loginExecution;
            }
        }
    }

    private List<RoleListDto> setRole(UserDto customer)
    {
        List<RoleListDto> roleListDto ;
//        List list=redisManager.getList(customer.getId()+"");
//        if (list!=null && list.size()>0)
//            roleListDto = list;
//        else
//        {
//            roleListDto=userDao.findUserRole(customer.getId());
//            redisManager.setList(customer.getId()+"",roleListDto);
//        }
        return roleListDto=userDao.findUserRole(customer.getId());
    }

    public LoginExecution<UserDto> login(String phone) throws Exception {
        Map<String, Object> map = new HashMap<String,Object>();
        User user= userDao.findByPhone(phone);
        if (user == null) {//用户不存在自动注册
            logger.info(phone+"对应用户不存在!");
            map.put("phone",phone);
            map.put("password","888888");

            UserDto  customer=this.addUser(map);
            CUserRoleAttribute  cUserRoleAttribute=userDao.findByUserIdWithRoleId(customer.getId());
            customer= userDao.findById(cUserRoleAttribute.getId());

            TokenModel tokenModel = tokenService.createToken(customer.getId(),customer.getUserId());

            String token =tokenModel.getId()+"_"+tokenModel.getUserId() + "_" + tokenModel.getToken();
            customer.setToken(token);
            Map<String,Object> hasTransPwd = userDao.queryTransPwd(map);
            if(hasTransPwd!=null && hasTransPwd.size()>0){
                customer.setHasTransPwd(true);
            }else{
                customer.setHasTransPwd(false);
            }

            List<LoveContent> loveContents = Arrays.asList(
                    new LoveContent(customer.getTodayLove(),QuataEnum.DEFAULT_QUOTA.getQuataDesc(),QuataEnum.DEFAULT_QUOTA.getCode()),
                    new LoveContent(customer.getLoveSmall(),QuataEnum.MIN.getQuataDesc(),QuataEnum.MIN.getCode()),
                    new LoveContent(customer.getLoveMiddle(),QuataEnum.MID.getQuataDesc(),QuataEnum.MID.getCode()),
                    new LoveContent(customer.getLoveLarge(),QuataEnum.BIG.getQuataDesc(),QuataEnum.BIG.getCode())
            );
            customer.setLoveContents(loveContents);

            LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(0, "登录成功", customer);

            if(map.get("deviceNo")!=null && !map.get("deviceNo").equals("")) {
                // 设备编号不为空
                verifyDevice(map,customer);
            }

            return loginExecution;
        }else {

            CUserRoleAttribute  cUserRoleAttribute=userDao.findByUserIdWithRoleId(user.getId());
            UserDto customer=userDao.findById(cUserRoleAttribute.getId());

            if (customer != null) {
                List<RoleListDto> roleListDto = this.setRole(customer);

                if(roleListDto.size()>=1){
                    customer.setLevelName(roleListDto.get(0).getLevelName());
                    customer.setLevelId(roleListDto.get(0).getLevelId());
                }
                else
                    customer.setLevelName("会员");
                customer.setRoleListDto(roleListDto);

                TokenModel tokenModel = tokenService.createToken(customer.getId(),customer.getUserId());

                String token =tokenModel.getId()+"_"+tokenModel.getUserId() + "_" + tokenModel.getToken();
                customer.setToken(token);
                map.put("phone",phone);
                Map<String,Object> hasTransPwd = userDao.queryTransPwd(map);
                if(hasTransPwd!=null && hasTransPwd.size()>0){
                    customer.setHasTransPwd(true);
                }else{
                    customer.setHasTransPwd(false);
                }
                List<LoveContent> loveContents = Arrays.asList(
                        new LoveContent(customer.getTodayLove(),QuataEnum.DEFAULT_QUOTA.getQuataDesc(),QuataEnum.DEFAULT_QUOTA.getCode()),
                        new LoveContent(customer.getLoveSmall(),QuataEnum.MIN.getQuataDesc(),QuataEnum.MIN.getCode()),
                        new LoveContent(customer.getLoveMiddle(),QuataEnum.MID.getQuataDesc(),QuataEnum.MID.getCode()),
                        new LoveContent(customer.getLoveLarge(),QuataEnum.BIG.getQuataDesc(),QuataEnum.BIG.getCode())
                );
                customer.setLoveContents(loveContents);

                LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(0, "登录成功", customer);

                if(map.get("deviceNo")!=null && !map.get("deviceNo").equals("")) {
                    // 设备编号不为空
                    verifyDevice(map,customer);
                }

                return loginExecution;
            } else {
                LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(2, "用户名密码错误");
                return loginExecution;
            }
        }
    }




    @Override
    @Transactional()
    public UserDto addUser(Map<String, Object> customerDto) {
        User user=new User();
        User parent = new User();
        UserDto userDto = new UserDto();
        String phone = (String) customerDto.get("phone");

        String recommendPhone= (String) customerDto.get("recommendPhone");
        Map<String,Object> registerReward = userDao.queryRegisterReward(customerDto);               //注册赠送再消分
        Map<String,Object> recommendParams = new HashMap<String,Object>(0);
        Map<String,Object> cUserParams = new HashMap<String,Object>(0);
        String leixing = "1";       //积分

        user = userDao.findByPhone(phone);


        if (StringUtils.isBlank(recommendPhone)) {
            //TODO 推荐人号码为空,则设置默认号码
            recommendPhone="17313147515";
        }
        parent=userDao.findByPhone(recommendPhone);

        if (parent!=null)
        {
            customerDto.put("parentId",parent.getId());
            customerDto.put("directlyId",parent.getDirectlyId());
            // 查询推荐人id，积分、善点、再消分
            recommendParams = cUserRoleAttributeMapper.queryRecommendId(parent.getId());
        }
        else
        {
            throw new CustomerException(2,"推荐人号码未注册");
        }

        if (user == null)
        {
            if (customerDto.get("userName")==null)
            {
                customerDto.put("userName",phone);
            }


            User param =new User();
            try {
                BeanUtils.copyProperties(param, customerDto);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage());
            }

            int result=userDao.insert(param);
            CUserRoleAttribute cUserRoleAttribute = new CUserRoleAttribute();
            cUserRoleAttribute.setUserId(param.getId());
            cUserRoleAttribute.setRoleId(ROLEID);
            if(registerReward!=null){
                String number = registerReward.get("number").toString();
                cUserRoleAttribute.setNumber(number);
                String type = registerReward.get("type").toString();

                recommendParams.put("number",number);
                Double jieyu = 0.0;
                if("0".equals(type)){
                    //0.主动善点
                    leixing = "0";
                    cUserRoleAttributeMapper.saveCUserShandian(cUserRoleAttribute);
                    jieyu = Arith.add(Double.parseDouble(recommendParams.get("shandian").toString()),
                            Double.parseDouble(number));
                    recommendParams.put("jieyu",jieyu);
                    cUserRoleAttributeMapper.updateUserShandian(recommendParams);
                }else if("1".equals(type)){
                    // 1 被动善点
                    leixing = "3";
                }else if("2".equals(type)){
                    // 2.积分
                    leixing = "1";
                    cUserRoleAttributeMapper.saveCUserIntegral(cUserRoleAttribute);
                    jieyu = Arith.add(Double.parseDouble(recommendParams.get("integral").toString()),
                            Double.parseDouble(number));
                    recommendParams.put("jieyu",jieyu);
                    cUserRoleAttributeMapper.updateUserIntegral(recommendParams);
                }else{
                    // 3.再消分
                    leixing = "20";
                    cUserRoleAttributeMapper.saveCUserReusePoint(cUserRoleAttribute);
                    jieyu = Arith.add(Double.parseDouble(recommendParams.get("reusePoint").toString()),
                            Double.parseDouble(number));
                    recommendParams.put("jieyu",jieyu);
                    cUserRoleAttributeMapper.updateUserReusePoint(recommendParams);
                }

                cUserParams.put("leixing",leixing);
                cUserParams.put("cuserId",cUserRoleAttribute.getId());
                cUserParams.put("number",number);
                cUserParams.put("jieyu",number);
                cUserRoleAttributeMapper.addChangeRecord(cUserParams);

                recommendParams.put("leixing",leixing);
                recommendParams.put("jieyu",jieyu);
                cUserRoleAttributeMapper.addRecommChangeRecord(recommendParams);

            }

            //成为推广员
            if (customerDto.get("type")!=null)
            {
                int platform_status = Integer.valueOf((String) customerDto.get("type"));
                if (platform_status==1||platform_status==2)
                {
                    CPlatformStatus cPlatformStatus = new CPlatformStatus();
                    cPlatformStatus.setIdentityId(cUserRoleAttribute.getId());
                    cPlatformStatus.setLevelId(platform_status);
                    cPlatformStatus.setLevelName(mapLevel.get(platform_status));
                    cPlatformStatusMapper.insert(cPlatformStatus);
                }
            }

            userDto.setId(cUserRoleAttribute.getId());
            try {
                BeanUtils.copyProperties(userDto,param);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage());
            }
            return userDto;
        }
        else
        {
            CUserRoleAttribute cUserRoleAttribute= userDao.findByUserIdWithRoleId(user.getId());

            if (cUserRoleAttribute == null) {
               cUserRoleAttribute= new CUserRoleAttribute();
                cUserRoleAttribute.setRoleId(ROLEID);
                cUserRoleAttribute.setUserId(user.getId());

                if(registerReward!=null){
                    String number = registerReward.get("number").toString();
                    cUserRoleAttribute.setNumber(number);
                    String type = registerReward.get("type").toString();
                    recommendParams.put("type",type);
                    recommendParams.put("number",number);
                    Double jieyu = 0.0;
                    if("0".equals(type)){
                        //0.主动善点
                        leixing = "0";
                        cUserRoleAttributeMapper.saveCUserShandian(cUserRoleAttribute);
                        jieyu = Arith.add(Double.parseDouble(recommendParams.get("shandian").toString()),
                                Double.parseDouble(number));
                        recommendParams.put("jieyu",jieyu);
                        cUserRoleAttributeMapper.updateUserShandian(recommendParams);
                    }else if("1".equals(type)){
                        // 1 被动善点
                        leixing = "3";
                    }else if("2".equals(type)){
                        // 2.积分
                        leixing = "1";
                        cUserRoleAttributeMapper.saveCUserIntegral(cUserRoleAttribute);
                        jieyu = Arith.add(Double.parseDouble(recommendParams.get("integral").toString()),
                                Double.parseDouble(number));
                        recommendParams.put("jieyu",jieyu);
                        cUserRoleAttributeMapper.updateUserIntegral(recommendParams);
                    }else{
                        // 3.再消分
                        leixing = "20";
                        cUserRoleAttributeMapper.saveCUserReusePoint(cUserRoleAttribute);
                        jieyu = Arith.add(Double.parseDouble(recommendParams.get("reusePoint").toString()),
                                Double.parseDouble(number));
                        recommendParams.put("jieyu",jieyu);
                        cUserRoleAttributeMapper.updateUserReusePoint(recommendParams);
                    }
                    cUserParams.put("leixing",leixing);
                    cUserParams.put("cuserId",cUserRoleAttribute.getId());
                    cUserParams.put("number",number);
                    cUserParams.put("jieyu",number);
                    cUserRoleAttributeMapper.addChangeRecord(cUserParams);

                    cUserRoleAttributeMapper.addRecommChangeRecord(recommendParams);
                }

                //成为推广员
                if (customerDto.get("type")!=null)
                {
                    int platform_status = Integer.valueOf((String) customerDto.get("type"));
                    if (platform_status==1||platform_status==2)
                    {
                        CPlatformStatus cPlatformStatus = new CPlatformStatus();
                        cPlatformStatus.setIdentityId(cUserRoleAttribute.getId());
                        cPlatformStatus.setLevelId(platform_status);
                        cPlatformStatus.setLevelName(mapLevel.get(platform_status));
                        cPlatformStatusMapper.insert(cPlatformStatus);
                    }

                }


                try {
                    BeanUtils.copyProperties(userDto, customerDto);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage());
                }
                userDto.setId(cUserRoleAttribute.getId());

                return userDto;
            }
            else
            {

                throw new CustomerException(2,"用户已经存在");

            }

        }

    }

    public UserDto getUserDetail(Integer id)
    {
        UserDto userDto=userDao.findById(id);
        List<RoleListDto> roleListDto = this.setRole(userDto);
        if(roleListDto.size()>=1) {
            userDto.setLevelName(roleListDto.get(0).getLevelName());
            userDto.setLevelId(roleListDto.get(0).getLevelId());
        }
        else
            userDto.setLevelName("会员");
        userDto.setRoleListDto(roleListDto);

        if(userDto!=null) {
            Map<String,Object> params = new HashMap<String,Object>(0);
            params.put("phone",userDto.getPhone());
            Map<String,Object> hasTransPwd = userDao.queryTransPwd(params);
            if(hasTransPwd!=null && hasTransPwd.size()>0){
                userDto.setHasTransPwd(true);
            }else{
                userDto.setHasTransPwd(false);
            }
            List<LoveContent> loveContents = Arrays.asList(
                    new LoveContent(userDto.getTodayLove(),QuataEnum.DEFAULT_QUOTA.getQuataDesc(),QuataEnum.DEFAULT_QUOTA.getCode()),
                    new LoveContent(userDto.getLoveSmall(),QuataEnum.MIN.getQuataDesc(),QuataEnum.MIN.getCode()),
                    new LoveContent(userDto.getLoveMiddle(),QuataEnum.MID.getQuataDesc(),QuataEnum.MID.getCode()),
                    new LoveContent(userDto.getLoveLarge(),QuataEnum.BIG.getQuataDesc(),QuataEnum.BIG.getCode())
            );
            userDto.setLoveContents(loveContents);
            return userDto;
        }
        throw new CustomerException(4,"无数据");

    }


    @Override
    public CIncomeEntity query(Map<String,Object> map) {
        Integer type = (Integer) map.get("type");
        UserDto userDto = userDao.findById((int) map.get("id"));

        Integer total = userDao.findTotalIncome(map);
        if (type==1) {

            CMyIntegralDto cMyIntegralDto = new CMyIntegralDto();
            cMyIntegralDto.setCurrent(userDto.getIntegral());
            cMyIntegralDto.setTotal(total + "");

            cMyIntegralDto.setRecode(this.getIntegralRecode(map));
            return cMyIntegralDto;
        }
        if(type==0)//主动善点
        {
            CMyShandianDto cMyShandianDto = new CMyShandianDto();
            cMyShandianDto.setCurrent(userDto.getShandian());
            cMyShandianDto.setTotal(total + "");

            cMyShandianDto.setRecode(this.getLovePointRecode(map));
            return cMyShandianDto;
        }

        if(type==2)//推荐奖励善点
        {
            total = userDao.findTotalRecommodIncome(map);
            MyRecommendPointDto recommendPointDto = new MyRecommendPointDto();
            recommendPointDto.setCurrent(userDto.getRecommendEarnings());
            recommendPointDto.setTotal(total+"");

            recommendPointDto.setRecode(this.getRecommendPointRecord(map));
            return recommendPointDto;
        }

        throw new CustomerException(4,"无数据");

    }


    private PageInfo<CMyIntegralRecordDto> getIntegralRecode(Map<String,Object> map)
    {

        List<CMyIntegralRecordDto> list;
        PageHelper.startPage(Integer.valueOf((String) map.get("currentPage")), Integer.valueOf((String) map.get("pageSize")), "x.id desc");
        list = userDao.findIntegralRecodeAll(map);
        PageInfo<CMyIntegralRecordDto> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    private PageInfo<CMyShandianRecode> getLovePointRecode(Map<String,Object> map)
    {
        List<CMyShandianRecode> list;

        PageHelper.startPage(Integer.valueOf((String) map.get("currentPage")), Integer.valueOf((String) map.get("pageSize")), "x.id desc");
        list=userDao.findLovePointRecode(map);

        PageInfo<CMyShandianRecode> pageInfo=new PageInfo(list);

        return pageInfo;
    }

    private PageInfo<MyRecommendPointRecordDto> getRecommendPointRecord(Map<String,Object> map)
    {
        List<MyRecommendPointRecordDto>  list;
        PageHelper.startPage(Integer.valueOf((String) map.get("currentPage")), Integer.valueOf((String) map.get("pageSize")), "ps.id desc");
        list=userDao.findRecommendPointRecord(map);
        PageInfo<MyRecommendPointRecordDto> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    // 代理业务统计
    public List<Map<String,Object>> queryAgencyStatistics(Map<String,Object> params){
        List<Map<String,Object>> all = new ArrayList<Map<String,Object>>(0);
        Object areaId = params.get("areaId");
        if(areaId==null || "".equals(areaId)){
            List<Map<String,Object>> allAreas = userDao.queryAllAreas(params);
            for(Map<String,Object> area: allAreas){
                Map<String,Object> agencyBenefitsDto = new HashMap<String,Object>(0);
                agencyBenefitsDto.put("areaId",area.get("areaId"));
                agencyBenefitsDto.put("areaName",area.get("fullName"));
                agencyBenefitsDto.put("tradeName",area.get("tradeName"));
                agencyBenefitsDto.put("tradeId",area.get("tradeId"));

                String tempCode = area.get("areaId").toString();
                Integer tradeId = Integer.valueOf(area.get("tradeId").toString());
                if(tempCode.substring(2,6).equals("0000")){
                    params.put("areaLevel",0);
                    if(tradeId>0){
                        params.put("grantType",3);
                        params.put("fromType",4);
                    }else{
                        params.put("grantType",2);
                        params.put("fromType",1);
                    }
                }else if(tempCode.substring(4,6).equals("00")){
                    params.put("areaLevel",1);
                    if(tradeId>0){
                        params.put("grantType",3);
                        params.put("fromType",5);
                    }else{
                        params.put("grantType",2);
                        params.put("fromType",2);
                    }
                }else{
                    params.put("areaLevel",2);
                    if(tradeId>0){
                        params.put("grantType",3);
                        params.put("fromType",5);
                    }else{
                        params.put("grantType",2);
                        params.put("fromType",3);
                    }
                }
                params.put("areaId",tempCode);
                params.put("tradeId",area.get("tradeId"));
                params.put("parentCode",tempCode);
                Map<String,Object> countMap = userDao.queryAgencyStatistics(params);
                agencyBenefitsDto.put("total",countMap.get("total"));
                agencyBenefitsDto.put("count",countMap.get("num"));
                all.add(agencyBenefitsDto);
            }
        }else{
            Map<String,Object> area = userDao.queryAreaInfo(params);

            String tempCode = params.get("areaId").toString();
            String parentCode = params.get("parentCode").toString();
            Integer tradeId = Integer.valueOf(params.get("tradeId").toString());
            params.put("areaId",tempCode);
            params.put("tradeId",params.get("tradeId"));

            if(parentCode.substring(2,6).equals("0000")){
                if(tradeId>0){
                    params.put("grantType",3);
                    params.put("fromType",4);
                }else{
                    params.put("grantType",2);
                    params.put("fromType",1);
                }
            }else if(parentCode.substring(4,6).equals("00")){
                if(tradeId>0){
                    params.put("grantType",3);
                    params.put("fromType",5);
                }else{
                    params.put("grantType",2);
                    params.put("fromType",2);
                }
            }else{
                if(tradeId>0){
                    params.put("grantType",3);
                    params.put("fromType",5);
                }else{
                    params.put("grantType",2);
                    params.put("fromType",3);
                }
            }
            if(tempCode.substring(2,6).equals("0000")){
                params.put("areaLevel",0);
                all = userDao.queryAgencyProvince(params);
            }else if(tempCode.substring(4,6).equals("00")){
                params.put("areaLevel",1);
                all = userDao.queryAgencyCity(params);
            }else{
                params.put("areaLevel",2);
                Map<String,Object> agencyBenefitsDto = userDao.querySingleArea(params);
                agencyBenefitsDto.put("areaId",params.get("areaId"));
                Map<String,Object> countMap = userDao.queryAgencyStatistics(params);
                agencyBenefitsDto.put("total",countMap.get("total"));
                agencyBenefitsDto.put("count",countMap.get("num"));
                all.add(agencyBenefitsDto);
            }
        }
        return all;
    }

    // 列表查询每天总收益
    @Override
    public List<AgencyProfit> queryAgencyLineChart(Map<String,Object> params){
        Object areaId = params.get("areaId");

        List<AgencyProfit> result = new ArrayList<AgencyProfit>(0);
        if(areaId==null || "".equals(areaId)) {
            List<Map<String, Object>> allAreas = userDao.queryAllAreas(params);
            for(Map<String,Object> area: allAreas){
                Integer tradeId = Integer.valueOf(area.get("tradeId").toString());
                String tempCode = area.get("areaId").toString();
                if(tempCode.substring(2,6).equals("0000")){
                    params.put("areaLevel",0);
                    if(tradeId>0){
                        params.put("grantType",3);
                        params.put("fromType",4);
                    }else{
                        params.put("grantType",2);
                        params.put("fromType",1);
                    }
                }else if(tempCode.substring(4,6).equals("00")){
                    params.put("areaLevel",1);
                    if(tradeId>0){
                        params.put("grantType",3);
                        params.put("fromType",5);
                    }else{
                        params.put("grantType",2);
                        params.put("fromType",2);
                    }
                }else{
                    params.put("areaLevel",2);
                    if(tradeId>0){
                        params.put("grantType",3);
                        params.put("fromType",5);
                    }else{
                        params.put("grantType",2);
                        params.put("fromType",3);
                    }
                }
                params.put("areaId",tempCode);
                params.put("tradeId",area.get("tradeId"));
                params.put("parentCode",tempCode);
                List<AgencyProfit> currentMap = userDao.queryAgencyLineChart(params);
                result = ListMapUtil.listClassMerge(result,currentMap);
            }
        }else{
            String tempCode = areaId.toString();
            Integer tradeId = Integer.valueOf(params.get("tradeId").toString());
            String parentCode = params.get("parentCode").toString();
            if(parentCode.substring(2,6).equals("0000")){
                if(tradeId>0){
                    params.put("grantType",3);
                    params.put("fromType",4);
                }else{
                    params.put("grantType",2);
                    params.put("fromType",1);
                }
            }else if(parentCode.substring(4,6).equals("00")){
                if(tradeId>0){
                    params.put("grantType",3);
                    params.put("fromType",5);
                }else{
                    params.put("grantType",2);
                    params.put("fromType",2);
                }
            }else{
                if(tradeId>0){
                    params.put("grantType",3);
                    params.put("fromType",5);
                }else{
                    params.put("grantType",2);
                    params.put("fromType",3);
                }
            }
            if(tempCode.substring(2,6).equals("0000")){
                params.put("areaLevel",0);
            }else if(tempCode.substring(4,6).equals("00")){
                params.put("areaLevel",1);
            }else{
                params.put("areaLevel",2);
            }
            result = userDao.queryAgencyLineChart(params);
        }
        return result;
    }

    // 查询区域详情
    @Override
    public PageInfo queryAreaDetail(Map<String,Object> params){
        Integer tradeId = Integer.valueOf(params.get("tradeId").toString());
        String parentCode = params.get("parentCode").toString();
        if(parentCode.substring(2,6).equals("0000")){
            if(tradeId>0){
                params.put("grantType",3);
                params.put("fromType",4);
            }else{
                params.put("grantType",2);
                params.put("fromType",1);
            }
        }else if(parentCode.substring(4,6).equals("00")){
            if(tradeId>0){
                params.put("grantType",3);
                params.put("fromType",5);
            }else{
                params.put("grantType",2);
                params.put("fromType",2);
            }
        }else{
            if(tradeId>0){
                params.put("grantType",3);
                params.put("fromType",5);
            }else{
                params.put("grantType",2);
                params.put("fromType",3);
            }
        }
        PageHelper.startPage(Integer.valueOf(params.get("currentPage").toString()),Integer.valueOf(params.get("pageSize").toString()));
        PageHelper.orderBy("p.id desc");
        return new PageInfo(userDao.queryAreaDetail(params));
    }

    /**
     * 查询全部区域编号
     * @param areaId
     * @return List<Object>
     */
    public List<Object> queryAllAreaIds(String areaId){
        List<Object> allAreaIds = new ArrayList<Object>(0);
        if(areaId.substring(2,6).equals("0000")){
            allAreaIds = userDao.queryByProvinceCode(areaId);

        }else if(areaId.substring(4,6).equals("00")){
            allAreaIds = userDao.queryByCityCode(areaId);
        }else{
            allAreaIds.add(areaId);
        }
        return allAreaIds;
    }

    @Override
    public LoginExecution<UserDto> weixinLgoin(Map<String, Object> map) throws Exception {

        User user = userDao.findByOpenid((String) map.get("openid"));

        if (user == null) {
            //99 : 表示还未绑定手机号
            LoginExecution loginExecution = new LoginExecution<>(99, "请先绑定手机号!");
            return loginExecution;
        } else {
            map.put("userId", user.getId());
            map.put("password", user.getPassword());
            UserDto customer = userDao.findUserByUserIdAndPass(map);

            if (customer != null) {
                List<LoveContent> loveContents = Arrays.asList(
                        new LoveContent(customer.getTodayLove(), QuataEnum.DEFAULT_QUOTA.getQuataDesc(), QuataEnum.DEFAULT_QUOTA.getCode()),
                        new LoveContent(customer.getLoveSmall(), QuataEnum.MIN.getQuataDesc(), QuataEnum.MIN.getCode()),
                        new LoveContent(customer.getLoveMiddle(), QuataEnum.MID.getQuataDesc(), QuataEnum.MID.getCode()),
                        new LoveContent(customer.getLoveLarge(), QuataEnum.BIG.getQuataDesc(), QuataEnum.BIG.getCode())
                );
//            Collections.sort(loveContents);
                customer.setLoveContents(loveContents);
                List<RoleListDto> roleListDto = this.setRole(customer);

                if (roleListDto.size() >= 1) {
                    customer.setLevelName(roleListDto.get(0).getLevelName());
                    customer.setLevelId(roleListDto.get(0).getLevelId());
                } else
                    customer.setLevelName("会员");
                customer.setRoleListDto(roleListDto);

                TokenModel tokenModel = tokenService.createToken(customer.getId(), customer.getUserId());


                String token = tokenModel.getId() + "_" + tokenModel.getUserId() + "_" + tokenModel.getToken();
                customer.setToken(token);

                Map<String, Object> hasTransPwd = userDao.queryTransPwd(map);
                if (hasTransPwd != null && hasTransPwd.size() > 0) {
                    customer.setHasTransPwd(true);
                } else {
                    customer.setHasTransPwd(false);
                }

                LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(0, "微信登录成功", customer);

                if(map.get("deviceNo")!=null && !map.get("deviceNo").equals("")) {
                    // 设备编号不为空
                    verifyDevice(map,customer);
                }

                return loginExecution;
            } else {
                LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(2, "微信登录失败");
                return loginExecution;
            }
        }
    }

    @Override
    @org.springframework.transaction.annotation.Transactional(rollbackFor = Throwable.class)
    public LoginExecution<UserDto> weixinBind(Map<String, Object> params) throws Exception {
        User checkUser = userDao.findByOpenid(params.get("openid") + "");
        if (checkUser != null) {
            LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(2, "该微信号已经绑定了手机号!");
            return loginExecution;
        }
        User user = userDao.findByPhone(params.get("phone") + "");
        if (user == null) {//手机号码未注册
            UserDto customer = this.weixinAddUser(params);
            CUserRoleAttribute cUserRoleAttribute = userDao.findByUserIdWithRoleId(customer.getId());
            customer = userDao.findById(cUserRoleAttribute.getId());

            TokenModel tokenModel = tokenService.createToken(customer.getId(), customer.getUserId());

            String token = tokenModel.getId() + "_" + tokenModel.getUserId() + "_" + tokenModel.getToken();
            customer.setToken(token);
            Map<String, Object> hasTransPwd = userDao.queryTransPwd(params);
            if (hasTransPwd != null && hasTransPwd.size() > 0) {
                customer.setHasTransPwd(true);
            } else {
                customer.setHasTransPwd(false);
            }

            List<LoveContent> loveContents = Arrays.asList(
                    new LoveContent(customer.getTodayLove(), QuataEnum.DEFAULT_QUOTA.getQuataDesc(), QuataEnum.DEFAULT_QUOTA.getCode()),
                    new LoveContent(customer.getLoveSmall(), QuataEnum.MIN.getQuataDesc(), QuataEnum.MIN.getCode()),
                    new LoveContent(customer.getLoveMiddle(), QuataEnum.MID.getQuataDesc(), QuataEnum.MID.getCode()),
                    new LoveContent(customer.getLoveLarge(), QuataEnum.BIG.getQuataDesc(), QuataEnum.BIG.getCode())
            );
            customer.setLoveContents(loveContents);
            List<RoleListDto> roleListDto = this.setRole(customer);

            if (roleListDto.size() >= 1) {
                customer.setLevelName(roleListDto.get(0).getLevelName());
                customer.setLevelId(roleListDto.get(0).getLevelId());
            } else
                customer.setLevelName("会员");
            customer.setRoleListDto(roleListDto);

            LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(0, "登录成功", customer);

            if(params.get("deviceNo")!=null && !params.get("deviceNo").equals("")) {
                // 设备编号不为空
                verifyDevice(params,customer);
            }

            return loginExecution;
        } else {
            //手机号已经注册,则只绑定微信号
            params.put("userId", user.getId());
            if (userDao.updateUserOpenid(params) == 0) {
                return new LoginExecution<UserDto>(2, "绑定手机号失败!");
            }
            CUserRoleAttribute cUserRoleAttribute = userDao.findByUserIdWithRoleId(user.getId());
            UserDto customer = userDao.findById(cUserRoleAttribute.getId());

            TokenModel tokenModel = tokenService.createToken(customer.getId(), customer.getUserId());

            String token = tokenModel.getId() + "_" + tokenModel.getUserId() + "_" + tokenModel.getToken();
            customer.setToken(token);
            Map<String, Object> hasTransPwd = userDao.queryTransPwd(params);
            if (hasTransPwd != null && hasTransPwd.size() > 0) {
                customer.setHasTransPwd(true);
            } else {
                customer.setHasTransPwd(false);
            }

            List<LoveContent> loveContents = Arrays.asList(
                    new LoveContent(customer.getTodayLove(), QuataEnum.DEFAULT_QUOTA.getQuataDesc(), QuataEnum.DEFAULT_QUOTA.getCode()),
                    new LoveContent(customer.getLoveSmall(), QuataEnum.MIN.getQuataDesc(), QuataEnum.MIN.getCode()),
                    new LoveContent(customer.getLoveMiddle(), QuataEnum.MID.getQuataDesc(), QuataEnum.MID.getCode()),
                    new LoveContent(customer.getLoveLarge(), QuataEnum.BIG.getQuataDesc(), QuataEnum.BIG.getCode())
            );
            customer.setLoveContents(loveContents);
            List<RoleListDto> roleListDto = this.setRole(customer);

            if (roleListDto.size() >= 1) {
                customer.setLevelName(roleListDto.get(0).getLevelName());
                customer.setLevelId(roleListDto.get(0).getLevelId());
            } else
                customer.setLevelName("会员");
            customer.setRoleListDto(roleListDto);

            LoginExecution<UserDto> loginExecution = new LoginExecution<UserDto>(0, "登录成功", customer);

            if(params.get("deviceNo")!=null && !params.get("deviceNo").equals("")) {
                // 设备编号不为空
                verifyDevice(params,customer);
            }

            return loginExecution;
        }
    }

    @org.springframework.transaction.annotation.Transactional(rollbackFor = Throwable.class)
    public UserDto weixinAddUser(Map<String, Object> customerDto) {
        User user = new User();
        User parent = new User();
        UserDto userDto = new UserDto();
        String phone = (String) customerDto.get("phone");

        String recommendPhone = (String) customerDto.get("recommendPhone");

        user = userDao.findByPhone(phone);

        if (StringUtils.isBlank(recommendPhone)) {
            //TODO 推荐人号码为空,则设置默认号码
            recommendPhone = "17313147515";
        }
        parent = userDao.findByPhone(recommendPhone);

        if (parent != null) {
            customerDto.put("parentId", parent.getId());
            customerDto.put("directlyId", parent.getDirectlyId());
        } else {
            throw new CustomerException(2, "推荐人号码未注册");
        }

        if (user == null) {
            if (null == customerDto.get("avatar") || "".equals(customerDto.get("avatar"))) {
                customerDto.put("avatar", "");
            } else {
                String avatar = FileUpload.fileUpUrl(customerDto.get("avatar")+"", "1");
                customerDto.put("avatar", avatar);
            }

            if (null == customerDto.get("userName") || "".equals(customerDto.get("userName"))) {
                customerDto.put("userName", phone);
            }

            UserWeixin param = new UserWeixin();
            try {
                BeanUtils.copyProperties(param, customerDto);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage());
            }

            int result = userDao.weixinInsert(param);
            CUserRoleAttribute cUserRoleAttribute = new CUserRoleAttribute();
            cUserRoleAttribute.setUserId(param.getId());
            cUserRoleAttribute.setRoleId(ROLEID);
            int result1 = cUserRoleAttributeMapper.insert(cUserRoleAttribute);

            //成为推广员
            if (customerDto.get("type") != null) {
                int platform_status = Integer.valueOf((String) customerDto.get("type"));
                if (platform_status == 1 || platform_status == 2) {
                    CPlatformStatus cPlatformStatus = new CPlatformStatus();
                    cPlatformStatus.setIdentityId(cUserRoleAttribute.getId());
                    cPlatformStatus.setLevelId(platform_status);
                    cPlatformStatus.setLevelName(mapLevel.get(platform_status));
                    cPlatformStatusMapper.insert(cPlatformStatus);
                }

            }

            userDto.setId(cUserRoleAttribute.getId());
            try {
                BeanUtils.copyProperties(userDto, param);
            } catch (IllegalAccessException e) {
                logger.error(e.getMessage());
            } catch (InvocationTargetException e) {
                logger.error(e.getMessage());
            }
            return userDto;
        } else {
            CUserRoleAttribute cUserRoleAttribute = userDao.findByUserIdWithRoleId(user.getId());

            if (cUserRoleAttribute == null) {
                cUserRoleAttribute = new CUserRoleAttribute();
                cUserRoleAttribute.setRoleId(ROLEID);
                cUserRoleAttribute.setUserId(user.getId());

                int result = cUserRoleAttributeMapper.insert(cUserRoleAttribute);


                //成为推广员
                if (customerDto.get("type") != null) {
                    int platform_status = Integer.valueOf((String) customerDto.get("type"));
                    if (platform_status == 1 || platform_status == 2) {
                        CPlatformStatus cPlatformStatus = new CPlatformStatus();
                        cPlatformStatus.setIdentityId(cUserRoleAttribute.getId());
                        cPlatformStatus.setLevelId(platform_status);
                        cPlatformStatus.setLevelName(mapLevel.get(platform_status));
                        cPlatformStatusMapper.insert(cPlatformStatus);
                    }

                }

                try {
                    BeanUtils.copyProperties(userDto, customerDto);
                } catch (IllegalAccessException e) {
                    logger.error(e.getMessage());
                } catch (InvocationTargetException e) {
                    logger.error(e.getMessage());
                }
                userDto.setId(cUserRoleAttribute.getId());

                return userDto;
            } else {

                throw new CustomerException(2, "用户已经存在");

            }

        }

    }

    /**
     * 验证设备
     * @param map
     * @param customer
     */
    private void verifyDevice(Map<String,Object> map,UserDto  customer){
        try {
            DeviceUserDto deviceUserDto = deviceUserDao.queryIsEnable(map);
            Map<String, Object> params = new HashMap<String, Object>(0);
            params.put("userId", customer.getId());
            params.put("deviceNo", map.get("deviceNo"));
            params.put("source", map.get("source"));
            if (deviceUserDto != null && (deviceUserDto.getUserId() == null ||
                    (deviceUserDto.getUserId() != null && !deviceUserDto.getUserId().equals(customer.getId())))) {
                deviceUserDao.updateDevice(params);
            }
//            else{
//                deviceUserDao.addDevice(params);
//            }
        } catch (Exception e) {
            logger.error("查询设备失败：userId:+"+customer.getId()+","+e.getMessage());
        }
    }

}
