package com.customer.service.impl;

import com.customer.dao.CScoreChangeMapper;
import com.customer.dto.CScoreChangeDto;
import com.customer.entity.ParaMap;
import com.customer.service.CScoreChangeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chenxl on 2017/5/12 0012.
 */
@Service
public class CScoreChangeServiceImpl implements CScoreChangeService {

    @Autowired
    private CScoreChangeMapper cScoreChangeMapper;

    @Override
    public PageInfo<CScoreChangeDto> getJifenList(ParaMap paraMap) {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<CScoreChangeDto> list = cScoreChangeMapper.getJifenList(paraMap);
        return new PageInfo<CScoreChangeDto>(list);
    }

    @Override
    public PageInfo<CScoreChangeDto> getShandianList(ParaMap paraMap) {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<CScoreChangeDto> list = cScoreChangeMapper.getShandianList(paraMap);
        return new PageInfo<CScoreChangeDto>(list);
    }

    @Override
    public PageInfo<CScoreChangeDto> getRecommendShandianList(ParaMap paraMap) {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""),
                Integer.parseInt(paraMap.get("pageSize")+""), "id desc");
        List<CScoreChangeDto> list = cScoreChangeMapper.getRecommendShandianList(paraMap);
        return new PageInfo<CScoreChangeDto>(list);
    }
}
