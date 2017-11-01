package com.customer.dao;

import com.customer.dto.CUserAddressDto;
import com.customer.dto.CommonProblemDto;
import com.customer.entity.ParaMap;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by chenxl on 2017/5/12 0012.
 */
public interface CSetupMapper {

    int editAvatar(ParaMap paraMap);

    int editUserName(ParaMap paraMap);

    int addSuggest(ParaMap paraMap);

    int addAddress(ParaMap paraMap);

    List<CUserAddressDto> getAddressList(ParaMap paraMap);

    int removeDefaultAddressByUserId(ParaMap paraMap);

    int setDefaultAddress(ParaMap paraMap);

    int deleteAddress(ParaMap paraMap);

    CUserAddressDto addressDetail(ParaMap paraMap);

    int editAddress(ParaMap paraMap);

    List<CommonProblemDto> getCommonProblem(ParaMap paraMap);
}
