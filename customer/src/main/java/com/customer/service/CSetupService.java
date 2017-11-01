package com.customer.service;

import com.customer.dto.CUserAddressDto;
import com.customer.dto.CommonProblemDto;
import com.customer.entity.ParaMap;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * Created by chenxl on 2017/5/12 0012.
 */
public interface CSetupService {

    int editAvatar(ParaMap paraMap) throws Exception;

    int editUserName(ParaMap paraMap) throws Exception;

    int addSuggest(ParaMap paraMap) throws Exception;

    int addAddress(ParaMap paraMap) throws Exception;

    PageInfo<CUserAddressDto> getAddressList(ParaMap paraMap) throws Exception;

    int setDefaultAddress(ParaMap paraMap) throws Exception;

    int deleteAddress(ParaMap paraMap) throws Exception;

    CUserAddressDto addressDetail(ParaMap paraMap) throws Exception;

    int editAddress(ParaMap paraMap) throws Exception;

    List<CommonProblemDto> getCommonProblem(ParaMap paraMap) throws Exception;
}
