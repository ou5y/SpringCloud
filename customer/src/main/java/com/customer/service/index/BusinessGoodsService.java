package com.customer.service.index;

import com.customer.dao.index.BusinessGoodsDao;
import com.customer.dto.index.BusinessDetailsDto;
import com.customer.dto.index.BusinessGoodsDto;
import com.customer.dto.index.GoodsDetailsDto;
import com.customer.dto.index.ShareDto;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/6/5 0005.
 */
@Service
public class BusinessGoodsService {

    @Autowired
    private BusinessGoodsDao businessGoodsDao;

    public PageInfo<BusinessGoodsDto> getBusinessGoodsList(Map map){
        PageHelper.startPage(Integer.parseInt(map.get("currentPage")+""), Integer.parseInt(map.get("pageSize")+""), map.get("sort")+"");
        List<BusinessGoodsDto> list = businessGoodsDao.getBusinessGoodsList(map);
        return new PageInfo<BusinessGoodsDto>(list);
    }

    public BusinessDetailsDto getBusinessDetails(Map map){
        return businessGoodsDao.getBusinessDetails(map);
    }

    public GoodsDetailsDto getGoodsDetails(Map map){
        GoodsDetailsDto goodsDetailsDto = businessGoodsDao.getGoodsDetails(map);
        map.put("businessId", goodsDetailsDto.getBusinessId());
        goodsDetailsDto.setBusinessDetailsDto(businessGoodsDao.getBusinessDetails(map));
        return goodsDetailsDto;
    }

    public ShareDto shareGoods(Map map){
        return businessGoodsDao.shareGoods(map);
    }
}
