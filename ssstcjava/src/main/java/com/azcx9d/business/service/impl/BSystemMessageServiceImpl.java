package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BSystemMessageDAO;
import com.azcx9d.business.dto.RoleListDto;
import com.azcx9d.business.dto.my.UserCenterNotice;
import com.azcx9d.business.service.BSystemMessageService;
import com.azcx9d.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by HuangQing on 2017/4/6 0006 09:46.
 */
@Service("bSystemMessageService")
public class BSystemMessageServiceImpl implements BSystemMessageService {

    @Autowired
    private BSystemMessageDAO messageDAO;

    @Override
    public Page selectMessageList(Page page, HashMap<String, Object> paraMap) throws Exception {

        int total = messageDAO.countTotal(paraMap);
        page.setTotalRow(total);

        paraMap.put("offset", page.getOffset());
        paraMap.put("pageSize", page.getPageSize());

        List<Map<String,Object>> list = messageDAO.selectMessageList(paraMap);
        page.setPageList(list);
        page.init();
        return page;
    }

    /**
     * 查询用户中心通知
     * @param params
     * @return UserCenterNotice
     */
    public UserCenterNotice queryUserCenterNotice(Map<String,Object> params){
        UserCenterNotice userCenterNotice = messageDAO.queryUserCenterNotice(params);
        if(userCenterNotice!=null && userCenterNotice.getTarget().contains("0")){
            messageDAO.updateReadFlag(params);
            return userCenterNotice;
        }else if(userCenterNotice!=null){
            String target = userCenterNotice.getTarget();
            String [] levelIds = target.split(",");
            List<Integer> levelList = new ArrayList<Integer>(0);
            for(int i=0;i<levelIds.length;i++){
                levelList.add(Integer.valueOf(levelIds[i]));
            }
            params.put("list",levelList);
            List<RoleListDto> roleList = messageDAO.queryRoleList(params);
            if(roleList!=null && roleList.size()>0){
                messageDAO.updateReadFlag(params);
                return  userCenterNotice;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}
