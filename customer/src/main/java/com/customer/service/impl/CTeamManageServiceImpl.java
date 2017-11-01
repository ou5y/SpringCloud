package com.customer.service.impl;

import com.customer.dao.CTeamManageDao;
import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.service.CTeamManageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxl on 2017/7/15.
 */
@Service
public class CTeamManageServiceImpl implements CTeamManageService {

    @Autowired
    private CTeamManageDao cTeamManageDao;

    @Override
    public List<TeamManageRoleDto> getRoleList(ParaMap paraMap) throws Exception {
        List<TeamManageRoleDto> roles = new ArrayList();

        TeamManageRoleDto role1 = new TeamManageRoleDto("1", "高级推广员");//高级推广员
        List<String> uIds1 = new ArrayList();
        Integer totalNum1 = 0;

        TeamManageRoleDto role2 = new TeamManageRoleDto("2", "推广员");//推广员
        List<String> uIds2 = new ArrayList();
        Integer totalNum2 = 0;

        TeamManageRoleDto role6 = new TeamManageRoleDto("6", "总监");//总监
        List<String> uIds6 = new ArrayList();
        Integer totalNum6 = 0;

        TeamManageRoleDto role7 = new TeamManageRoleDto("7", "副总");//副总
        List<String> uIds7 = new ArrayList();
        Integer totalNum7 = 0;

        TeamManageRoleDto role9 = new TeamManageRoleDto("9", "服务商");//服务商
        List<String> uIds9 = new ArrayList();
        Integer totalNum9 = 0;

        List<TeamManageItemDto> list;
        do {
            list = cTeamManageDao.getRoleList(paraMap);
            List<String> uIds = new ArrayList();
            if (list.size() > 0) {
                for (TeamManageItemDto item : list) {
                    switch (item.getLevelId()) {
                        case "1":
                            uIds1.add(item.getuId());
                            totalNum1++;
                            break;
                        case "2":
                            uIds2.add(item.getuId());
                            totalNum2++;
                            break;
                        case "6":
                            uIds6.add(item.getuId());
                            totalNum6++;
                            break;
                        case "7":
                            uIds7.add(item.getuId());
                            totalNum7++;
                            break;
                        case "9":
                            uIds9.add(item.getuId());
                            totalNum9++;
                            break;
                    }
                    uIds.add(item.getuId());
                }
            }
            paraMap.put("uIds", uIds);
        } while (list.size() > 0);

        if (totalNum1 > 0) {
            role1.setuIds(StringUtils.join(uIds1, ","));
            role1.setTotalNum(totalNum1 + "");
            roles.add(role1);
        }
        if (totalNum2 > 0) {
            role2.setuIds(StringUtils.join(uIds2, ","));
            role2.setTotalNum(totalNum2 + "");
            roles.add(role2);
        }
        if (totalNum6 > 0) {
            role6.setuIds(StringUtils.join(uIds6, ","));
            role6.setTotalNum(totalNum6 + "");
            roles.add(role6);
        }
        if (totalNum7 > 0) {
            role7.setuIds(StringUtils.join(uIds7, ","));
            role7.setTotalNum(totalNum7 + "");
            roles.add(role7);
        }
        if (totalNum9 > 0) {
            role9.setuIds(StringUtils.join(uIds9, ","));
            role9.setTotalNum(totalNum9 + "");
            roles.add(role9);
        }
        return roles;
    }

    @Override
    public PageInfo<TeamManageUserDto> getUserList(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "isChild desc, totalIncome desc");
        List<TeamManageUserDto> list = cTeamManageDao.getUserList(paraMap);
        return new PageInfo<TeamManageUserDto>(list);
    }

    @Override
    public PageInfo<TeamManageDetailDto> getDetailList(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "ps.id desc");
        List<TeamManageDetailDto> list = cTeamManageDao.getDetailList(paraMap);
        return new PageInfo<TeamManageDetailDto>(list);
    }

    @Override
    public PageInfo<TeamManageBusinessDto> getBusinessList(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "b.id desc");
        List<TeamManageBusinessDto> list = cTeamManageDao.getBusinessList(paraMap);
        return new PageInfo<TeamManageBusinessDto>(list);
    }
}
