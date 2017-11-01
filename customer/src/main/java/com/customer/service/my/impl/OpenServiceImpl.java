package com.customer.service.my.impl;

import com.customer.config.MybatisConfig;
import com.customer.dao.StatisticDao;
import com.customer.dao.my.OpenDao;
import com.customer.dto.OpenLogDto;
import com.customer.dto.RecommendStatusDto;
import com.customer.dto.my.RecommendLogDto;
import com.customer.entity.User;
import com.customer.exception.CustomerException;
import com.customer.service.my.OpenService;
import com.customer.util.JsonResult;
import com.customer.util.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
@Service("openService")

public class OpenServiceImpl implements OpenService{

    private Logger logger = LoggerFactory.getLogger(OpenServiceImpl.class);

    @Autowired
    private OpenDao openDao;

    @Autowired
    private StatisticDao statisticDao;

    // 添加开通记录
    @Override
//    @Transactional(rollbackFor = Throwable.class)
    public JsonResult addOpen(Map<String,Object> params){
        RecommendStatusDto recommendStatusDto = openDao.queryRecommendStatus(params);
        if(recommendStatusDto==null){
            throw new CustomerException(2,"对不起，您没有开通权限");
        }
        int roleId = Integer.valueOf(params.get("roleId").toString());
        Map<String,Object> identityId = openDao.queryIdentityId(params);
        if(identityId!=null && identityId.size()>0){
            params.put("identityId",identityId.get("id"));
        }else{
            return new JsonResult(2,"被推荐人不存在");
        }

        int count = openDao.queryExist(params);
        if(count>0){
            return new JsonResult(2,"开通记录已提交，请等待耐心审核");
        }

        count  = openDao.queryExistRecommended(params);
        if(count>0){
            return new JsonResult(2,"被推荐号码已被开通");
        }
        // 被推荐人
        RecommendStatusDto recommendedStatusDto = openDao.queryRecommendedStatus(params);

        if(recommendedStatusDto!=null){

            if(recommendedStatusDto.getLevelId()==6){
                return new JsonResult(2,"被推荐人已经是总监");
            }

            if(recommendedStatusDto.getLevelId()==7){
                return new JsonResult(2,"被推荐人已经是副总");
            }

            if(recommendStatusDto.getLevelId()==1&&recommendedStatusDto.getLevelId()==1){
                return new JsonResult(2,"被推荐人已经是高级推广员");
            }

            if(roleId==2&&recommendedStatusDto.getLevelId()==2){
                return new JsonResult(2,"被推荐人已经是推广员");
            }
        }

        count = openDao.addOpen(params);               //添加开通记录
        if(count>0){
            return new JsonResult(0,"添加开通记录成功");
        }else{
            return new JsonResult(2,"添加开通记录失败");
        }
    }

    // 查询开通记录是否已经存在
    @Override
    public int queryExist(Map<String,Object> params){
        return openDao.queryExist(params);
    }

    // 查询开通记录
    @Override
    public PageInfo openLog(Map<String,Object> params,Integer pageIndex,Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        PageHelper.orderBy("create_time desc");
        List<Map<String,Object>> logs = openDao.openLog(params);
        return  new PageInfo(logs);
    }

    // 查询推荐人是否存在
    public int queryRecommendMan(Map<String,Object> params){
        return openDao.queryRecommendMan(params);
    }

    // 查询被推荐人是否存在
    public int queryRecommendedMan(Map<String,Object> params){
        return openDao.queryRecommendedMan(params);
    }

    /**
     * 查询Platform_status
     * @param params
     * @return int
     */
    public int queryPlatformStatus(Map<String,Object> params){
        return openDao.queryPlatformStatus(params);
    }

    // 查询IdentityId
    @Override
    public Map<String,Object> queryIdentityId(Map<String,Object> params){
        return openDao.queryIdentityId(params);
    }

    // 查询开通上限/已开通数量
    @Override
    public Map<String,Object> queryOpenLimit(Map<String,Object> params){
        Map<String,Object> result = openDao.queryOpenLimit(params);
        // 高级推广员  总监可以开通
        RecommendStatusDto recommendStatusDto = openDao.queryRecommendStatus(params);
        if(recommendStatusDto==null){
            throw new CustomerException(2,"对不起，您没有开通权限");
        }
        List<Map<String,Object>> roleList = new ArrayList<Map<String,Object>>(0);
        Map<String,Object> r1 = new HashMap<String,Object>(0);
        r1.put("roleId","1");
        r1.put("roleName","高级推广员");
        Map<String,Object> r2 = new HashMap<String,Object>(0);
        r2.put("roleId","2");
        r2.put("roleName","推广员");
        roleList.add(r1);
        roleList.add(r2);
        result.put("roleList",roleList);
        return result;
    }

    // 查询全部区域
    @Override
    public List<Map<String,Object>> queryAllAreas(Map<String,Object> params){
        List<Map<String,Object>> allAreas = statisticDao.queryAllAreas(params);
        for(Map<String,Object> obj :allAreas){
            params.put("areaId",obj.get("areaId"));
            params.put("tradeId",obj.get("tradeId"));
            int total = openDao.countPushAgentByOperator(params);
            obj.put("total",total);                 //总开通数
        }
        return allAreas;
    }

    // 配送关系生效
    @Override
    @Transactional(rollbackFor = Throwable.class)
    public JsonResult savePushAgent(Map<String,Object> params){

        // 上传人身份  6
        Map<String,Object> recommendStatus = openDao.queryRecommendUserStatus(params);
        if(recommendStatus == null){
            // 上传人不是总监
            return  new JsonResult(2,"上传人不是总监");
        }else{
            // 待开通号码推荐人的parentId
            params.put("chiefId",recommendStatus.get("userId"));                            //总监用户ID
            params.put("deputyId",recommendStatus.get("directlyId"));                    //副总用户ID
        }

        // 配送到号码 身份，1、2、6、9
        List<Map<String,Object>> agencyUserStatus = openDao.queryAgencyUserStatus(params);
        if(agencyUserStatus == null || agencyUserStatus.size()==0){
            return new JsonResult(2,params.get("agencyUserPhone")+"不符合配送条件");
        }else {
            params.put("agencyUser",agencyUserStatus.get(0).get("agencyUser"));
            params.put("parentId",agencyUserStatus.get(0).get("agencyUser"));
            params.put("directlyId",recommendStatus.get("directlyId"));                    //副总用户ID
        }

        params.put("recommendedPhone",params.get("beOpenedUserPhone"));  //待开通号码
        RecommendLogDto dto = openDao.queryRecommendLog(params);            //查询待开通号码是否已经存在c_recommend_log表中
        if(dto != null){
            return  new JsonResult(2,"待开通："+params.get("beOpenedUserPhone")+"不符合配送条件");
        }

        // 待开通号码 记录表里也不存在
        Map<String,Object> beOpenedUser = openDao.queryBeOpenedUserId(params);      //查询待开通号码用户id  user表id
        if(beOpenedUser != null){
            params.put("userId",beOpenedUser.get("id"));
            params.put("identityId",beOpenedUser.get("identityId"));
        }else{
            return  new JsonResult(2,params.get("beOpenedUserPhone")+"未注册,请先注册!");
        }

        int isExistPushAgent = openDao.queryExistPushAgent(params);              //配送银牌是否存在
        if(isExistPushAgent<=0){
            int count = openDao.addPushAgent(params);

            // 查询PlatformStatus记录是否已经存在
            try {
                int isExistPlatformStatus = openDao.queryPlatform(params);
                if(isExistPlatformStatus>0) {
                    openDao.updatePlatformStatus(params);
                }else{
                    params.put("roleId","1");
                    params.put("roleName","高级推广员");
                    count += openDao.addPlatformStatus(params);
                }

                //  更新待开通号码关系
                openDao.updateRecommendRelation(params);

            } catch (Exception e) {
                throw new CustomerException(2, "开通配送银牌失败");
            }
            return new JsonResult(0,"开通配送银牌成功");
        }else{
            return new JsonResult(2,"已经开通配送银牌");
        }
    }

    // 开通记录
    @Override
    public RecommendLogDto queryRecommendLog(Map<String,Object> params){
        return openDao.queryRecommendLog(params);
    }

    // 更新关系
    @Override
    public JsonResult updateRelationship(String phones,int operatorId){
        String [] idArr = phones.split(",");
        int count=0;
        List<Integer> ids = new ArrayList<Integer>(0);
        for(int i=0;i<idArr.length;i++){
            Map<String,Object> params = new HashMap<String,Object>(0);
            String recommendedPhone = idArr[i];
            params.put("recommendedPhone",recommendedPhone);
            RecommendLogDto dto = openDao.queryRecommendLog(params);

            if(dto!=null){
                params.put("roleId",dto.getLevelId());
                params.put("roleName",dto.getLevelName());
                params.put("recommendPhone",dto.getRecommendPhone());
                params.put("identityId",dto.getIdentityId());
                ids.add(dto.getId());
            }else{
                logger.error("开通记录，被推荐人："+recommendedPhone+"不存在");
            }

            int isExistPlatformStatus = openDao.queryPlatform(params);
            if(isExistPlatformStatus>0) {
                openDao.updatePlatformStatus(params);
            }else{
                openDao.addPlatformStatus(params);
            }

            Map<String,Object> parent = openDao.queryParentId(params);
            params.put("parentId",parent.get("id"));
            params.put("directlyId",parent.get("directlyId"));
            // 更新关系
            openDao.updateRecommendRelation(params);
            count += 1;
        }
        Map<String,Object> updateParams = new HashMap<String,Object>(0);
        updateParams.put("operatorId",operatorId);
        updateParams.put("list",ids);
        openDao.updateOperatorId(updateParams);
        if(count==idArr.length){
            return new JsonResult(0,"开通成功");
        }else{
            //失败几条
            return new JsonResult(0,"开通成功");
        }
    }

    // 查询待开通号码身份
    @Override
    public JsonResult queryBeOpenedUser(Map<String,Object> params){
        params.put("recommendedPhone",params.get("beOpenedUserPhone"));
        RecommendLogDto recommendLogDto = openDao.queryRecommendLog(params);
        if(recommendLogDto!=null){
            return  new JsonResult(2,"待开通:"+params.get("beOpenedUserPhone")+"已被别人开通");
        }

        List<Map<String,Object>> result = openDao.queryBeOpenedUser(params);
        if(result!=null&&result.size()>0){
            return new JsonResult(2,"待开通"+params.get("beOpenedUserPhone")+"已经是"+result.get(0).get("levelName"));
//            return new JsonResult(3,"待开通:"+params.get("beOpenedUserPhone")+"，是否确认开通开通配送？\r\n当前身份："+result.get("levelName"));
        }else {
            Map<String,Object> beOpenedUser = openDao.queryBeOpenedUserId(params);  //查询号码是否注册
            if(beOpenedUser != null){
                params.put("userId",beOpenedUser.get("id"));
            }else{
                return  new JsonResult(2,"待开通:"+params.get("beOpenedUserPhone")+"未注册");
            }
            return new JsonResult(0,"可开通");
        }
    }

    // 查询开通记录
    @Override
    public Page queryOperatorLog(Map<String,Object> params, Integer pageIndex, Integer pageSize){
        Page page = new Page(pageIndex,pageSize);
        params.put("pageSize",pageSize);
        int total = openDao.countOperatorLog(params);
        page.setTotal(total);
        int offset = page.getOffset();
        params.put("offset",offset);
        List<Map<String,Object>> list = openDao.queryOperatorLog(params);
        page.setList(list);
        page.init();
        return  page;
    }

    // 查询配送记录
    @Override
    public Page queryPushAgentList(Map<String,Object> params, Integer pageIndex, Integer pageSize){
        Page page = new Page(pageIndex,pageSize);
        params.put("pageSize",pageSize);
        int total = openDao.countPushAgentList(params);
        page.setTotal(total);
        int offset = page.getOffset();
        params.put("offset",offset);
        List<Map<String,Object>> list = openDao.queryPushAgentList(params);
        page.setList(list);
        page.init();
        return  page;
    }

}
