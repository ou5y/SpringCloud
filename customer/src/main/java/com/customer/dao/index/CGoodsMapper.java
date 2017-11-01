package com.customer.dao.index;


import com.customer.dto.AroundGoodsDto;
import com.customer.dto.index.Goods;
import com.customer.dto.index.HotShowModel;
import com.customer.entity.index.CGoods;
import com.customer.entity.index.CGoodsCriteria;
import com.customer.entity.index.CGoodsKey;
import com.customer.entity.index.CGoodsWithBLOBs;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface CGoodsMapper {
    long countByExample(CGoodsCriteria example);

    int deleteByExample(CGoodsCriteria example);

    int deleteByPrimaryKey(CGoodsKey key);

    int insert(CGoodsWithBLOBs record);

    int insertSelective(CGoodsWithBLOBs record);

    List<CGoodsWithBLOBs> selectByExampleWithBLOBs(CGoodsCriteria example);

    List<Goods> selectByExample(CGoodsCriteria example);

    CGoodsWithBLOBs selectByPrimaryKey(CGoodsKey key);

    int updateByExampleSelective(@Param("record") CGoodsWithBLOBs record, @Param("example") CGoodsCriteria example);

    int updateByExampleWithBLOBs(@Param("record") CGoodsWithBLOBs record, @Param("example") CGoodsCriteria example);

    int updateByExample(@Param("record") CGoods record, @Param("example") CGoodsCriteria example);

    int updateByPrimaryKeySelective(CGoodsWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(CGoodsWithBLOBs record);

    int updateByPrimaryKey(CGoods record);

    List<Map<String,Object>> findGoodsList();

    List<HotShowModel> findGoodsHotList();

    List<HotShowModel> findHomePageGoodsHotList();

    List<Goods> randomGoodsByPage(Map paraMap);

    List<AroundGoodsDto> getAroundGoodsList(Map paraMap);
}