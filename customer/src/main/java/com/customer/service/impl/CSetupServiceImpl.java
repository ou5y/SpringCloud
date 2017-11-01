package com.customer.service.impl;

import com.customer.dao.CSetupMapper;
import com.customer.dto.CUserAddressDto;
import com.customer.dto.CommonProblemDto;
import com.customer.entity.ParaMap;
import com.customer.service.CSetupService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenxl on 2017/5/12 0012.
 */
@Service
public class CSetupServiceImpl implements CSetupService {

    @Autowired
    private CSetupMapper cSetupMapper;

    @Override
    public int editAvatar(ParaMap paraMap) throws Exception {
        return cSetupMapper.editAvatar(paraMap);
    }

    @Override
    public int editUserName(ParaMap paraMap) throws Exception {
        return cSetupMapper.editUserName(paraMap);
    }

    @Override
    public int addSuggest(ParaMap paraMap) throws Exception {
        return cSetupMapper.addSuggest(paraMap);
    }

    @Override
    public int addAddress(ParaMap paraMap) throws Exception {
        return cSetupMapper.addAddress(paraMap);
    }

    @Override
    public PageInfo<CUserAddressDto> getAddressList(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "a.is_default desc, a.create_time desc");
        List<CUserAddressDto> list = cSetupMapper.getAddressList(paraMap);
        return new PageInfo<CUserAddressDto>(list);
    }

    @Override
    public int setDefaultAddress(ParaMap paraMap) throws Exception {
        cSetupMapper.removeDefaultAddressByUserId(paraMap);
        return cSetupMapper.setDefaultAddress(paraMap);
    }

    @Override
    public int deleteAddress(ParaMap paraMap) throws Exception {
        return cSetupMapper.deleteAddress(paraMap);
    }

    @Override
    public CUserAddressDto addressDetail(ParaMap paraMap) throws Exception {
        return cSetupMapper.addressDetail(paraMap);
    }

    @Override
    public int editAddress(ParaMap paraMap) throws Exception {
        return cSetupMapper.editAddress(paraMap);
    }

    @Override
    public List<CommonProblemDto> getCommonProblem(ParaMap paraMap) throws Exception {
        return cSetupMapper.getCommonProblem(paraMap);
    }
}
