package com.customer.service.impl;

import com.customer.dao.CHomepageMapper;
import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.service.CHomepageService;
import com.customer.util.ListUtils;
import com.customer.util.Page;
import com.customer.util.PositionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/8 0008.
 */
@Service("cHomepageService")
public class CHomepageServiceImpl implements CHomepageService {

    @Autowired
    private CHomepageMapper cHomepageMapper;

    @Override
    public List<Map> indexBussinessType() throws Exception {
        return cHomepageMapper.indexBussinessType();
    }

    @Override
    public List<Map> allBussinessType() throws Exception {
        List<Map> parent = cHomepageMapper.parentBussinessType();
        List<Map> child = cHomepageMapper.childBussinessType();
        List<Map> all = new ArrayList<Map>();
        for(int i=0;i<parent.size();i++){
            Map m = parent.get(i);
            List<Map> item = new ArrayList<Map>();
            for(Iterator<Map> it = child.iterator(); it.hasNext(); ){
                Map n = it.next();
                if(Long.parseLong(m.get("id")+"") == Long.parseLong(n.get("parentId")+"")){
                    item.add(n);
                    it.remove();
                }else{
                    break;
                }
            }
            m.put("child", item);
            all.add(m);
        }
        return all;
    }

    @Override
    public List<CStoreListDto> recommendStore(Map paraMap) throws Exception {
        return cHomepageMapper.recommendStore(paraMap);
    }

    @Override
    public CStoreDetailDto storeDetail(Map paraMap) throws Exception {
        return cHomepageMapper.storeDetail(paraMap);
    }

    @Override
    public List<Map> smallTypeList(Map paraMap) throws Exception {
        return cHomepageMapper.smallTypeList(paraMap);
    }

    @Override
    public List<Map> getGoodsList(Map paraMap) throws Exception {
        return cHomepageMapper.getGoodsList(paraMap);
    }

    @Override
    public List<CIndexBannerDto> getBannerList(ParaMap paraMap) throws Exception {
        return cHomepageMapper.getBannerList(paraMap);
    }

    @Override
    public PageInfo<Map> getSearchList(Map paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "distance is null, distance, star desc");
        List<Map> list = cHomepageMapper.getSearchList(paraMap);
        return new PageInfo<Map>(list);
    }

    @Override
    public PageInfo<CGoodsListDto> getGoodsListByStore(Map paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "sales_num desc");
        List<CGoodsListDto> list = cHomepageMapper.getGoodsListByStore(paraMap);
        return new PageInfo<CGoodsListDto>(list);
    }

    @Override
    public CVersionDto checkVersion(Map paraMap) throws Exception {
        return cHomepageMapper.checkVersion(paraMap);
    }

    @Override
    public NoticeDto queryNotice(Map paraMap) throws Exception {
        return cHomepageMapper.queryNotice(paraMap);
    }

    @Override
    public List<IndexNoticeDto> getIndexNotice(ParaMap paraMap) throws Exception {
        return cHomepageMapper.getIndexNotice(paraMap);
    }

    @Override
    public IndexNoticeDto getNoticeDetails(ParaMap paraMap) throws Exception {
        return cHomepageMapper.getNoticeDetails(paraMap);
    }

    @Override
    public PageInfo<IndexNoticeDto> getNoticeList(ParaMap paraMap) throws Exception {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "create_time desc");
        List<IndexNoticeDto> list = cHomepageMapper.getNoticeList(paraMap);
        return new PageInfo<IndexNoticeDto>(list);
    }

    @Override
    public List<RecommendGoodsDto> recommendGoods(Map paraMap) throws Exception {
        return cHomepageMapper.recommendGoods(paraMap);
    }

    @Override
    public Page getHomePageGoodsList(Page page, Map params, Integer pageIndex, Integer pageSize) throws Exception {

        Map<String, Double> positions = PositionUtil.getRectangle4Point(Double.valueOf(params.get("latitude").toString()),
                Double.valueOf(params.get("longitude").toString()), Double.valueOf(params.get("distance").toString()));

        params.put("latitude1", positions.get("latitude1") + "");
        params.put("latitude2", positions.get("latitude2") + "");
        params.put("longitude1", positions.get("longitude1") + "");
        params.put("longitude2", positions.get("longitude2") + "");

        int totalRow = cHomepageMapper.countHomePageGoodsList(params);
        page.setTotal(totalRow);
        params.put("offset", page.getOffset());
        double latitude = Double.valueOf(params.get("latitude").toString());
        double longitude = Double.valueOf(params.get("longitude").toString());
        List<HomePageGoodsDto> lists = cHomepageMapper.getHomePageGoodsList(params);
        for (HomePageGoodsDto dto : lists) {
            if (dto.getLatitude1() != null && dto.getLongitude1() != null && !"".equals(dto.getLatitude1()) && !"".equals(dto.getLongitude1())
                    && !dto.getLatitude1().equalsIgnoreCase("null") && !dto.getLongitude1().equalsIgnoreCase("null")
                    && !dto.getLatitude1().contains("E") && !dto.getLongitude1().contains("E")) {
                double latitude1 = Double.valueOf(dto.getLatitude1());
                double longitude1 = Double.valueOf(dto.getLongitude1());
                dto.setDistance((int) PositionUtil.getDistance(latitude, longitude, latitude1, longitude1));
            } else {
                dto.setDistance(1234567890);
            }
        }

        ListUtils.sort(lists, new String[]{"sort", "distance"}, new boolean[]{false, true});

        List<HomePageGoodsDto> datas = null;
        if ((page.getOffset() + 20) <= lists.size()) {
            datas = lists.subList(page.getOffset(), (page.getOffset() + 20));
        } else {
            datas = lists.subList(page.getOffset(), lists.size());
        }

        List<AroundGoodsDto> list = new ArrayList<>();
        for(HomePageGoodsDto dto:datas){
            AroundGoodsDto agd = new AroundGoodsDto();
            agd.setId(dto.getId());
            agd.setName(dto.getName());
            agd.setbName(dto.getbName());
            agd.setBusinessAddress(dto.getBusinessAddress());
            agd.setFirstPic(dto.getFirstPic());
            agd.setPrice(dto.getPrice());
            agd.setShoppingPrice(dto.getShoppingPrice());
            list.add(agd);
        }

        page.setList(list);
        page.init();
        page.setPageNum(pageIndex);
        return page;
    }
}
