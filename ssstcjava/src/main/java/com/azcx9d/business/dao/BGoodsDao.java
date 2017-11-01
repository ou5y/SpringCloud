package com.azcx9d.business.dao;

import com.azcx9d.business.dto.GoodsCategoryDto;
import com.azcx9d.business.dto.GoodsDto;
import com.azcx9d.business.dto.GoodsListDto;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/4 0004.
 */
public interface BGoodsDao {

    int addGoods(Map paraMap);

    int updateGoods(Map paraMap);

    GoodsDto getGoods(Map paraMap);

    Map countGoodsList(Map paraMap);

    List<GoodsListDto> getGoodsList(Map paraMap);

    int updateBatchGoods(Map paraMap);

    List<GoodsCategoryDto> getGoodsCategoryList(Map paraMap);

    int getMaxSort(Map paraMap);

    int setTopGoods(Map paraMap);

    List<GoodsCategoryDto> getSearchCategoryList(Map paraMap);

}
