package com.customer.dao;

import com.customer.dto.*;
import com.customer.entity.ParaMap;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/8 0008.
 */
public interface CHomepageMapper {

    List<Map> indexBussinessType();

    List<Map> parentBussinessType();

    List<Map> childBussinessType();

    List<CStoreListDto> recommendStore(Map paraMap);

    CStoreDetailDto storeDetail(Map paraMap);

    List<Map> smallTypeList(Map paraMap);

    List<Map> getGoodsList(Map paraMap);

    List<CIndexBannerDto> getBannerList(ParaMap paraMap);

    List<Map> getSearchList(Map paraMap);

    List<CGoodsListDto> getGoodsListByStore(Map paraMap);

    CVersionDto checkVersion(Map paraMap);

    NoticeDto queryNotice(Map paraMap);

    List<IndexNoticeDto> getIndexNotice(Map paraMap);

    IndexNoticeDto getNoticeDetails(Map paraMap);

    List<IndexNoticeDto> getNoticeList(ParaMap paraMap);

    List<RecommendGoodsDto> recommendGoods(Map paraMap);

    int countHomePageGoodsList(Map params);

    List<HomePageGoodsDto> getHomePageGoodsList(Map params);

}
