package com.azcx9d.business.service;

import com.azcx9d.business.dto.GoodsCategoryDto;
import com.azcx9d.business.dto.GoodsDto;
import com.azcx9d.common.util.Page;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/4 0004.
 */
public interface BGoodsService {
    int addGoods(Map paraMap) throws Exception;

    int updateGoods(Map paraMap) throws Exception;

    GoodsDto getGoods(Map paraMap) throws Exception;

    Page getGoodsList(Page page, Map paraMap) throws Exception;

    int updateBatchGoods(Map paraMap) throws Exception;

    List<GoodsCategoryDto> getGoodsCategoryList(Map paraMap) throws Exception;

    int setTopGoods(Map paraMap) throws Exception;

    List<GoodsCategoryDto> getSearchCategoryList(Map paraMap) throws Exception;
}
