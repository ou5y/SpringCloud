package com.customer.service.index;

import com.customer.dao.CGsCategoryMapper;
import com.customer.dao.index.CBannerMapper;
import com.customer.dao.index.CGoodsMapper;
import com.customer.dto.AroundGoodsDto;
import com.customer.dto.index.Goods;
import com.customer.dto.index.HotShowModel;
import com.customer.entity.CGsCategory;
import com.customer.entity.index.CBanner;
import com.customer.entity.index.CGoods;
import com.customer.entity.index.CGoodsCriteria;
import com.customer.util.JsonResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/6/3.
 */
@Service
public class StrollAroundService {

    @Autowired
    private CGoodsMapper cGoodsMapper;
    @Autowired
    private CBannerMapper cBannerMapper;


    @Autowired
    private CGsCategoryMapper cGsCategoryMapper;

    public JsonResult<List<Goods>> findGoodsList() {

        List<Map<String,Object>> goodsList=cGoodsMapper.findGoodsList();

        List<Goods> cGoodsList=new ArrayList<>();

        for (Map<String, Object> map:goodsList
             ) {
            Goods goods = new Goods();
            goods.setId(String.valueOf(map.get("id"))).
                    setName(String.valueOf(map.get("name"))).
                    setFirstPic(String.valueOf(map.get("firstPic"))).
                    setPrice(String.valueOf(map.get("price"))).
                    setSalesNum(String.valueOf(map.get("salesNum"))).
                    setShoppingPrice(String.valueOf(map.get("shoppingPrice")));
            cGoodsList.add(goods);
        }
        return new JsonResult<List<Goods>>(cGoodsList);
    }


    public  void addBanner(List<CBanner> cBannerList)
    {
        for (CBanner cBanner:cBannerList
             ) {
            cBannerMapper.insertSelective(cBanner);
        }

    }

    public JsonResult<PageInfo<Goods>> goodsSearch(Map<String, Object> map) {
        int cateGoryId =0;
        CGoodsCriteria goodsCriteria =new CGoodsCriteria();
        CGoodsCriteria.Criteria criteria= goodsCriteria.createCriteria().andStateEqualTo(1);//只查询上架商品
        StringBuilder sb = new StringBuilder();
        if (map.get("cateGoryId") != null)
        {
            cateGoryId =Integer.valueOf((String)map.get("cateGoryId"));
            CGsCategory cGsCategory=cGsCategoryMapper.selectByPrimaryKey(cateGoryId);
            List<Integer> list;
            if(cGsCategory.getParentId()==0)
            {
                list= cGsCategoryMapper.selectByParentId(cGsCategory.getId());
                list.add(cGsCategory.getId());
                if ((String) map.get("key") != null)
                {
                    criteria.andCategoryIdIn(list).andNameLike("%"+(String) map.get("key")+"%");
                }
                criteria.andCategoryIdIn(list);

            } else {
                criteria.andCategoryIdEqualTo(cateGoryId);
            }
        }
        else
        {
            if ((String) map.get("key") != null)
                criteria.andNameLike("%"+(String) map.get("key")+"%");

        }


        if (map.get("allOrderByType") != null)
        {
//            goodsCriteria.setOrderByClause("price " + map.get("allOrderByType"));
//            goodsCriteria.setOrderByClause("sales_num "+map.get("allOrderByType"));

//            sb.append("price " + map.get("allOrderByType")+",").append("sales_num "+map.get("allOrderByType"));
            sb.append("shopping_price " + map.get("allOrderByType")+",").append("sales_num "+map.get("allOrderByType"));
        }

        else
        {
            if (map.get("priceOrderByType") != null)
            {
//                goodsCriteria.setOrderByClause("price " + map.get("priceOrderByType"));
//                sb.append("price " + map.get("priceOrderByType"));
                sb.append("shopping_price " + map.get("priceOrderByType"));
            }

            if (map.get("salesNumOrderByType") != null)
            {
//                goodsCriteria.setOrderByClause("sales_num "+map.get("salesNumOrderByType"));
                sb.append("sales_num "+map.get("salesNumOrderByType"));
            }
        }
        PageHelper.startPage(Integer.valueOf((String) map.get("currentPage")), Integer.valueOf((String) map.get("pageSize")),sb.toString());
        List<Goods> goodsList=cGoodsMapper.selectByExample(goodsCriteria);

        PageInfo<Goods> pageInfo = new PageInfo<>(goodsList);
        return new JsonResult(pageInfo);
    }

    public JsonResult<List<HotShowModel>> hotShow(Map map) {
        List<HotShowModel> hotShowModels = new ArrayList<>();
        if(Integer.valueOf(map.get("type")+"")==1)
        {
            if (Integer.valueOf(map.get("showType")+"") == 1) {
                hotShowModels = cGoodsMapper.findHomePageGoodsHotList();
            } else if (Integer.valueOf(map.get("showType")+"") == 2) {
                hotShowModels = cGoodsMapper.findGoodsHotList();
            }
        }

        if (hotShowModels == null || hotShowModels.size()<=0) {
            return new JsonResult<>(4,"暂无数据",hotShowModels);
        }

        if (Integer.valueOf(map.get("type")+"")==2)
            return new JsonResult<>(4,"暂无数据",hotShowModels);
       return new JsonResult<>(hotShowModels);
    }

    public JsonResult randomGoodsByPage(Map paraMap) {

        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage")+""), Integer.parseInt(paraMap.get("pageSize")+""), "create_time desc");
        List<Goods> list = cGoodsMapper.randomGoodsByPage(paraMap);
        if (list.size() > 0) {
            return new JsonResult(0, "查询成功", new PageInfo<Goods>(list));
        } else {
            return new JsonResult(4,"暂无数据");
        }

    }

    public JsonResult getAroundGoodsList(Map paraMap) {
        PageHelper.startPage(Integer.parseInt(paraMap.get("currentPage") + ""), Integer.parseInt(paraMap.get("pageSize") + ""), "cg.create_time desc");
        List<AroundGoodsDto> list = cGoodsMapper.getAroundGoodsList(paraMap);
        if (list.size() > 0) {
            return new JsonResult(0, "查询成功", new PageInfo<AroundGoodsDto>(list));
        } else {
            return new JsonResult(4, "暂无数据");
        }

    }
}
