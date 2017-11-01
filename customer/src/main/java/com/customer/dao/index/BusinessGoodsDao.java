package com.customer.dao.index;

import com.customer.dto.index.BusinessDetailsDto;
import com.customer.dto.index.BusinessGoodsDto;
import com.customer.dto.index.GoodsDetailsDto;
import com.customer.dto.index.ShareDto;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/6/5 0005.
 */
public interface BusinessGoodsDao {

    List<BusinessGoodsDto> getBusinessGoodsList(Map map);

    BusinessDetailsDto getBusinessDetails(Map map);

    GoodsDetailsDto getGoodsDetails(Map map);

    ShareDto shareGoods(Map map);

}
