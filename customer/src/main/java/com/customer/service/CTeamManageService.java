package com.customer.service;

import com.customer.dto.TeamManageBusinessDto;
import com.customer.dto.TeamManageDetailDto;
import com.customer.dto.TeamManageRoleDto;
import com.customer.dto.TeamManageUserDto;
import com.customer.entity.ParaMap;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by chenxl on 2017/7/15.
 */
public interface CTeamManageService {

    List<TeamManageRoleDto> getRoleList(ParaMap paraMap) throws Exception;

    PageInfo<TeamManageUserDto> getUserList(ParaMap paraMap) throws Exception;

    PageInfo<TeamManageDetailDto> getDetailList(ParaMap paraMap) throws Exception;

    PageInfo<TeamManageBusinessDto> getBusinessList(ParaMap paraMap) throws Exception;
}
