package com.customer.dao;

import com.customer.dto.TeamManageBusinessDto;
import com.customer.dto.TeamManageDetailDto;
import com.customer.dto.TeamManageItemDto;
import com.customer.dto.TeamManageUserDto;
import com.customer.entity.ParaMap;

import java.util.List;

/**
 * Created by chenxl on 2017/7/15.
 */
public interface CTeamManageDao {

    List<TeamManageItemDto> getRoleList(ParaMap paraMap);

    List<TeamManageUserDto> getUserList(ParaMap paraMap);

    List<TeamManageDetailDto> getDetailList(ParaMap paraMap);

    List<TeamManageBusinessDto> getBusinessList(ParaMap paraMap);

}
