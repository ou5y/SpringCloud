package com.azcx9d.business.service.impl;

import com.azcx9d.business.dao.BGoodsDao;
import com.azcx9d.business.dto.GoodsCategoryDto;
import com.azcx9d.business.dto.GoodsDto;
import com.azcx9d.business.dto.GoodsListDto;
import com.azcx9d.business.entity.BGoods;
import com.azcx9d.business.service.BGoodsService;
import com.azcx9d.common.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/4 0004.
 */
@Service("bGoodsService")
public class BGoodsServiceImpl implements BGoodsService {

    @Autowired
    private BGoodsDao bGoodsDao;

    @Override
    public int addGoods(Map paraMap) throws Exception {
        return bGoodsDao.addGoods(paraMap);
    }

    @Override
    public int updateGoods(Map paraMap) throws Exception {
        return bGoodsDao.updateGoods(paraMap);
    }

    @Override
    public GoodsDto getGoods(Map paraMap) throws Exception {
        return bGoodsDao.getGoods(paraMap);
    }

    @Override
    public Page getGoodsList(Page page, Map paraMap) throws Exception {
        Map map = bGoodsDao.countGoodsList(paraMap);
        page.setTotalRow(Integer.parseInt(map.get("total")+""));
        paraMap.put("offset", page.getOffset());
        List<GoodsListDto> list = bGoodsDao.getGoodsList(paraMap);
        page.setPageList(list);
        page.init();
        return page;
    }

    @Override
    public int updateBatchGoods(Map paraMap) throws Exception {
        return bGoodsDao.updateBatchGoods(paraMap);
    }

    @Override
    public List<GoodsCategoryDto> getGoodsCategoryList(Map paraMap) throws Exception {
        return bGoodsDao.getGoodsCategoryList(paraMap);
    }

    @Override
    public int setTopGoods(Map paraMap) throws Exception {
        int maxSort = bGoodsDao.getMaxSort(paraMap);
        paraMap.put("sort", maxSort);
        return bGoodsDao.setTopGoods(paraMap);
    }

    @Override
    public List<GoodsCategoryDto> getSearchCategoryList(Map paraMap) throws Exception {
        return bGoodsDao.getSearchCategoryList(paraMap);
    }
}
