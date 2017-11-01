package com.customer.service.impl;

import com.customer.dao.SystemMessageDao;
import com.customer.dto.RoleListDto;
import com.customer.dto.my.UserCenterNotice;
import com.customer.service.SystemMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
@Service("systemMessageService")

public class SystemMessageServiceImpl implements SystemMessageService{

    @Autowired
    private SystemMessageDao systemMessageDao;

    // 查询消息列表
    @Override
    public PageInfo messageList(Map<String,Object> params,Integer pageIndex,Integer pageSize){
        PageHelper.startPage(pageIndex,pageSize);
        PageHelper.orderBy("creat_time desc");
        List<Map<String,Object>> lists = systemMessageDao.messageList(params);
        return new PageInfo(lists);
    }

    // 消息详情
    @Override
    public Map<String,Object> messageDetail(Map<String,Object> params){
        return systemMessageDao.messageDetail(params);
    }

    // 查询用户中心通知
    @Override
    public UserCenterNotice queryUserCenterNotice(Map<String,Object> params){
        UserCenterNotice userCenterNotice = systemMessageDao.queryUserCenterNotice(params);
        if(userCenterNotice!=null && userCenterNotice.getTarget().contains("0")){
            systemMessageDao.updateReadFlag(params);
            return userCenterNotice;
        }else if(userCenterNotice!=null){
            String target = userCenterNotice.getTarget();
            String [] levelIds = target.split(",");
            List<Integer> levelList = new ArrayList<Integer>(0);
            for(int i=0;i<levelIds.length;i++){
                levelList.add(Integer.valueOf(levelIds[i]));
            }
            params.put("list",levelList);
            List<RoleListDto> roleList = systemMessageDao.queryRoleList(params);
            if(roleList!=null && roleList.size()>0){
                systemMessageDao.updateReadFlag(params);
                return  userCenterNotice;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}
