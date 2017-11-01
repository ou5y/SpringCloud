package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.GoodsCategoryDto;
import com.azcx9d.business.dto.GoodsDto;
import com.azcx9d.business.dto.GoodsListDto;
import com.azcx9d.business.service.BGoodsService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by chenxl on 2017/5/4 0004.
 */
@Api(value = "商户商品管理",description="商户商品管理")
@RestController
@RequestMapping("/v1/bApi/goods")
public class BGoodsApiController extends HQBaseController {
    @Autowired
    private BGoodsService bGoodsService;

    @ApiOperation(value = "新增商品", notes = "新增商品")
    @RequestMapping(value = "/addGoods", method = RequestMethod.POST)
    public JsonResult addGoods(@RequestHeader("token") String token,
                               @ApiParam(required = true, value = "商家(店铺)id") @RequestParam("businessId") Integer businessId,
                               @ApiParam(required = true, value = "商品主图") @RequestParam("firstPic") String firstPic,
                               @ApiParam(required = true, value = "轮播图(限3张)") @RequestParam("loopPics") String loopPics,
                               @ApiParam(required = true, value = "商品名称+规格名称") @RequestParam("name") String name,
                               @ApiParam(required = true, value = "商品分类id") @RequestParam("categoryId") Integer categoryId,
                               @ApiParam(required = true, value = "商品价格(原价)") @RequestParam("price") String price,
                               @ApiParam(required = true, value = "折扣价") @RequestParam("shoppingPrice") String shoppingPrice,
                               @ApiParam(required = true, value = "参数") @RequestParam("params") String params,
                               @ApiParam(required = true, value = "展示图(限9张)") @RequestParam("showPics") String showPics,
                               @ApiParam(required = true, value = "商品描述") @RequestParam("detail") String detail,
                               @ApiParam(required = true, value = "商品货号") @RequestParam("articleNumber") String articleNumber,
                               @ApiParam(required = true, value = "库存") @RequestParam("inventoryNum") Integer inventoryNum,
                               @ApiParam(required = true, value = "商品让利") @RequestParam("rangli") String rangli,
                               @ApiParam(required = true, value = "快递费用") @RequestParam("expressCost") String expressCost,
                               @ApiParam(required = true, value = "商品状态(0:放入仓库, 1:发布商品)", defaultValue = "1") @RequestParam("state") Integer state){
        Map paraMap = super.getParaMap();
        paraMap.put("salesNum", 0);
        try {
            int count = bGoodsService.addGoods(paraMap);
            if (count > 0) {
                return new JsonResult(0, "新增商品成功!");
            } else {
                return new JsonResult(2, "新增商品失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "编辑商品", notes = "编辑商品")
    @RequestMapping(value = "/updateGoods", method = RequestMethod.POST)
    public JsonResult updateGoods(@RequestHeader("token") String token,
                                  @ApiParam(required = true, value = "商品id") @RequestParam("goodsId") Integer goodsId,
                                  @ApiParam(required = true, value = "商家(店铺)id") @RequestParam("businessId") Integer businessId,
                                  @ApiParam(required = true, value = "商品主图") @RequestParam("firstPic") String firstPic,
                                  @ApiParam(required = true, value = "轮播图(限3张)") @RequestParam("loopPics") String loopPics,
                                  @ApiParam(required = true, value = "商品名称+规格名称") @RequestParam("name") String name,
                                  @ApiParam(required = true, value = "商品分类id") @RequestParam("categoryId") Integer categoryId,
                                  @ApiParam(required = true, value = "商品价格(原价)") @RequestParam("price") String price,
                                  @ApiParam(required = true, value = "折扣价") @RequestParam("shoppingPrice") String shoppingPrice,
                                  @ApiParam(required = true, value = "参数") @RequestParam("params") String params,
                                  @ApiParam(required = true, value = "展示图(限9张)") @RequestParam("showPics") String showPics,
                                  @ApiParam(required = true, value = "商品描述") @RequestParam("detail") String detail,
                                  @ApiParam(required = true, value = "商品货号") @RequestParam("articleNumber") String articleNumber,
                                  @ApiParam(required = true, value = "库存") @RequestParam("inventoryNum") Integer inventoryNum,
                                  @ApiParam(required = true, value = "商品让利") @RequestParam("rangli") String rangli,
                                  @ApiParam(required = true, value = "快递费用") @RequestParam("expressCost") String expressCost){
        Map paraMap = super.getParaMap();
        try {
            int count = bGoodsService.updateGoods(paraMap);
            if (count > 0) {
                return new JsonResult(0, "编辑商品成功!");
            } else {
                return new JsonResult(2, "编辑商品失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "商品详情", notes = "商品详情",response = GoodsDto.class)
    @RequestMapping(value = "/getGoods", method = RequestMethod.GET)
    public JsonResult getGoods(@RequestHeader("token") String token,
                               @ApiParam(required = true, value = "商品id") @RequestParam("goodsId") Integer goodsId){
        try {
            Map paraMap = super.getParaMap();

            GoodsDto goodsDto = bGoodsService.getGoods(paraMap);
            if (null != goodsDto) {
                GoodsCategoryDto goodsCategoryDto = new GoodsCategoryDto();
                goodsCategoryDto.setId(goodsDto.getCategoryId());
                goodsCategoryDto.setName(goodsDto.getCategoryDesc());
                goodsCategoryDto.setParentId(goodsDto.getCategoryParentId());
                goodsDto.setGoodsCategoryDto(goodsCategoryDto);
                return new JsonResult(0, "查询成功!", goodsDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "商家商品列表", notes = "商家商品列表<br/>totalRow:商品总条数", response = GoodsListDto.class)
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    public JsonResult getGoodsList(@RequestHeader("token") String token,
                                   @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                   @ApiParam(required = true, value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize,
                                   @ApiParam(required = true, value = "商家(店铺)id") @RequestParam("businessId") Integer businessId,
                                   @ApiParam(required = true, value = "商品状态(0:仓库中, 1:我的商品)", defaultValue = "1") @RequestParam("state") Integer state,
                                   @ApiParam(value = "商品分类id") @RequestParam(value = "categoryId", required = false) Integer categoryId,
                                   @ApiParam(value = "搜索内容") @RequestParam(value = "content", required = false) String content){
        try {
            Map paraMap = super.getParaMap();
            paraMap.put("pageSize", pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            page = bGoodsService.getGoodsList(page, paraMap);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", page);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "批量上架商品", notes = "批量上架商品")
    @RequestMapping(value = "/upBatchGoods", method = RequestMethod.POST)
    public JsonResult upBatchGoods(@RequestHeader("token") String token,
                                   @ApiParam(required=true,value="商品ids(,隔开)") @RequestParam("goodsIds") String goodsIds){
        try {
            Map paraMap = super.getParaMap();
            paraMap.put("state", 1);   //1:上架
            String[] ids = goodsIds.split(",");
            if (ids.length == 0) {
                return new JsonResult(2, "请先选择商品!");
            }
            paraMap.put("ids", ids);

            int count = bGoodsService.updateBatchGoods(paraMap);
            if (count > 0) {
                return new JsonResult(0, "上架商品成功!");
            } else {
                return new JsonResult(2, "上架商品失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "批量下架商品", notes = "批量下架商品")
    @RequestMapping(value = "/downBatchGoods", method = RequestMethod.POST)
    public JsonResult downBatchGoods(@RequestHeader("token") String token,
                                     @ApiParam(required=true,value="商品ids(,隔开)") @RequestParam("goodsIds") String goodsIds){
        try {
            Map paraMap = super.getParaMap();
            paraMap.put("state", 0);   //0:下架
            String[] ids = goodsIds.split(",");
            if (ids.length == 0) {
                return new JsonResult(2, "请先选择商品!");
            }
            paraMap.put("ids", ids);

            int count = bGoodsService.updateBatchGoods(paraMap);
            if (count > 0) {
                return new JsonResult(0, "下架商品成功!");
            } else {
                return new JsonResult(2, "下架商品失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "批量删除商品", notes = "批量删除商品")
    @RequestMapping(value = "/deleteBatchGoods", method = RequestMethod.POST)
    public JsonResult deleteBatchGoods(@RequestHeader("token") String token,
                                       @ApiParam(required=true,value="商品ids(,隔开)") @RequestParam("goodsIds") String goodsIds){
        try {
            Map paraMap = super.getParaMap();
            paraMap.put("state", -1);   //-1:删除
            String[] ids = goodsIds.split(",");
            if (ids.length == 0) {
                return new JsonResult(2, "请先选择商品!");
            }
            paraMap.put("ids", ids);

            int count = bGoodsService.updateBatchGoods(paraMap);
            if (count > 0) {
                return new JsonResult(0, "删除商品成功!");
            } else {
                return new JsonResult(2, "删除商品失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "商品类型列表", notes = "商品类型列表", response = GoodsCategoryDto.class)
    @RequestMapping(value = "/getGoodsCategoryList", method = RequestMethod.GET)
    public JsonResult getGoodsCategoryList(@RequestHeader("token") String token){
        try {
            Map paraMap = super.getParaMap();

            List<GoodsCategoryDto> list = bGoodsService.getGoodsCategoryList(paraMap);

            if (list.size() > 0) {
                return new JsonResult(0, "查询成功!", list);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "置顶商品", notes = "置顶商品")
    @RequestMapping(value = "/setTopGoods", method = RequestMethod.POST)
    public JsonResult setTopGoods(@RequestHeader("token") String token,
                                  @ApiParam(required = true, value = "商家(店铺)id") @RequestParam("businessId") Integer businessId,
                                  @ApiParam(required=true,value="商品id") @RequestParam("goodsId") String goodsId){
        try {
            Map paraMap = super.getParaMap();

            int count = bGoodsService.setTopGoods(paraMap);
            if (count > 0) {
                return new JsonResult(0, "置顶商品成功!");
            } else {
                return new JsonResult(2, "置顶商品失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "搜索类型列表", notes = "搜索类型列表", response = GoodsCategoryDto.class)
    @RequestMapping(value = "/getSearchCategoryList", method = RequestMethod.GET)
    public JsonResult getSearchCategoryList(@RequestHeader("token") String token,
                                            @ApiParam(required = true, value = "商家(店铺)id") @RequestParam("businessId") Integer businessId){
        try {
            Map paraMap = super.getParaMap();

            List<GoodsCategoryDto> list = bGoodsService.getSearchCategoryList(paraMap);

            if (list.size() > 0) {
                return new JsonResult(0, "查询成功!", list);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

}
