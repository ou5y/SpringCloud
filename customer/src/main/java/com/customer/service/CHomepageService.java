package com.customer.service;

import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.util.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/8 0008.
 */
public interface CHomepageService {
    List<Map> indexBussinessType() throws Exception;

    List<Map> allBussinessType() throws Exception;

    List<CStoreListDto> recommendStore(Map paraMap) throws Exception;

    CStoreDetailDto storeDetail(Map paraMap) throws Exception;

    List<Map> smallTypeList(Map paraMap) throws Exception;

    List<Map> getGoodsList(Map paraMap) throws Exception;

    List<CIndexBannerDto> getBannerList(ParaMap paraMap) throws Exception;

    PageInfo<Map> getSearchList(Map paraMap) throws Exception;

    PageInfo<CGoodsListDto> getGoodsListByStore(Map paraMap) throws Exception;

    CVersionDto checkVersion(Map paraMap) throws Exception;

    NoticeDto queryNotice(Map paraMap) throws Exception;

    List<IndexNoticeDto> getIndexNotice(ParaMap paraMap) throws Exception;

    IndexNoticeDto getNoticeDetails(ParaMap paraMap) throws Exception;

    PageInfo<IndexNoticeDto> getNoticeList(ParaMap paraMap) throws Exception;

    List<RecommendGoodsDto> recommendGoods(Map paraMap) throws Exception;

    Page getHomePageGoodsList(Page page, Map params, Integer pageIndex, Integer pageSize) throws Exception;

}
