package com.customer.web.index;

import com.customer.dto.AroundGoodsDto;
import com.customer.dto.index.*;
import com.customer.service.index.BusinessGoodsService;
import com.customer.service.index.StrollAroundService;
import com.customer.util.BaseController;
import com.customer.util.CommonUtil;
import com.customer.util.JsonResult;
import com.customer.util.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/6/3.
 */
@RestController
@RequestMapping(value = "/v1/strollAround")
@Api(value = "逛一逛",description = "逛一逛")
public class strollAroundController extends BaseController {

    public static final String SORT_TYPE_0 = "sort desc, create_time desc";
    public static final String SORT_TYPE_1 = "price";
    public static final String SORT_TYPE_2 = "price desc";
    public static final String SORT_TYPE_3 = "sales_num desc";
    public static final String SORT_TYPE_4 = "sales_num";

    @Autowired
    private StrollAroundService strollAroundService;

    @Autowired
    private BusinessGoodsService businessGoodsService;

    @ApiOperation(notes = "逛一逛首页商品列表", value = "逛一逛首页商品列表",response = Goods.class)
    @GetMapping("/randomGoods")
    public JsonResult<List<Goods>> randomGoods()
    {
        Map<String, Object> map= CommonUtil.getParameterMap(request);

       return strollAroundService.findGoodsList();
    }

    @ApiOperation(notes = "商铺首页详情", value = "商铺首页详情", response = BusinessDetailsDto.class)
    @GetMapping("/getBusinessDetails")
    public JsonResult getBusinessDetails(@ApiParam(required = true, value = "商铺id") @RequestParam("businessId") int businessId) {
        try {
            Map<String, Object> map = CommonUtil.getParameterMap(request);

            BusinessDetailsDto businessDetailsDto = businessGoodsService.getBusinessDetails(map);

            if (null != businessDetailsDto) {
                return new JsonResult(0, "查询成功!", businessDetailsDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "商铺下商品列表", value = "商铺下商品列表", response = BusinessGoodsDto.class)
    @GetMapping("/getBusinessGoodsList")
    public JsonResult getBusinessGoodsList(@ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                           @ApiParam(required = true, value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize,
                                           @ApiParam(required = true, value = "商铺id") @RequestParam("businessId") int businessId,
                                           @ApiParam(value = "搜索商品") @RequestParam(value = "content", required = false) String content,
                                           @ApiParam(required = true, value = "排序类型(0:综合, 1:价格从低到高, 2:价格从高到低, 3:销量从高到低, 4:销量从低到高)", defaultValue = "0") @RequestParam("sortType") int sortType) {
        try {
            Map<String, Object> map = CommonUtil.getParameterMap(request);
            String sort = "";
            switch (sortType) {
                case 0 : sort = SORT_TYPE_0; break;
                case 1 : sort = SORT_TYPE_1; break;
                case 2 : sort = SORT_TYPE_2; break;
                case 3 : sort = SORT_TYPE_3; break;
                case 4 : sort = SORT_TYPE_4; break;
                default: sort = ""; break;
            }
            map.put("sort", sort);

            PageInfo<BusinessGoodsDto> page = businessGoodsService.getBusinessGoodsList(map);

            if (page.getList().size() > 0) {
                return new JsonResult(0, "查询成功!", page);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "商品详情", value = "商品详情", response = GoodsDetailsDto.class)
    @GetMapping("/getGoodsDetails")
    public JsonResult getGoodsDetails(@ApiParam(required = true, value = "商品id") @RequestParam("goodsId") int goodsId) {
        try {
            Map<String, Object> map = CommonUtil.getParameterMap(request);

            GoodsDetailsDto goodsDetailsDto = businessGoodsService.getGoodsDetails(map);

            if (null != goodsDetailsDto) {
                return new JsonResult(0, "查询成功!", goodsDetailsDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }
    @GetMapping("/goodsSearch")
    @ApiOperation(notes = "逛一逛搜索",value = "逛一逛搜索",response = Goods.class)
    public JsonResult<PageInfo<Goods>> goodsSearch(  @ApiParam(value = "分类id")
                                                     @RequestParam(required = false, value = "cateGoryId") String cateGoryId,
                                                     @ApiParam(value = "价格排序类型(desc:倒序,asc:正序)")
                                                     @RequestParam(required = false, value = "priceOrderByType",defaultValue = "desc") String priceOrderByType,
                                                     @ApiParam(value = "销量排序类型(desc:倒序,asc:正序)")
                                                     @RequestParam(required = false, value = "salesNumOrderByType",defaultValue = "desc") String salesNumOrderByType,
                                                     @ApiParam(value = "综合排序类型(desc:倒序,asc:正序)")
                                                     @RequestParam(required = false, value = "allOrderByType" ,defaultValue = "desc") String allOrderByType,
                                                     @ApiParam(value = "关键字")
                                                     @RequestParam(required = false, value = "key") String key,
                                                     @ApiParam(required = true,defaultValue = "1")
                                                     @RequestParam(value = "currentPage",defaultValue = "1") String currentPage,
                                                     @ApiParam(required = true,defaultValue = "20")
                                                     @RequestParam(value = "pageSize",defaultValue = "20") String pageSize
                                           )
    {
        Map<String, Object> map= CommonUtil.getParameterMap(request);
        return strollAroundService.goodsSearch(map);
    }

    @GetMapping("/hotShow")
    @ApiOperation(notes = "热门",value  ="热门",response = HotShowModel.class)
    public JsonResult<List<HotShowModel>> hotShow(@ApiParam(required = true,value = "类型 1-商品,2-活动") @RequestParam(value = "type",defaultValue = "1") int type,
                                                  @ApiParam(required = true,value = "展示类型 1首页,2逛一逛") @RequestParam(value = "showType",defaultValue = "1") int showType)
    {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        return strollAroundService.hotShow(map);
    }

    @GetMapping("/shareGoods")
    @ApiOperation(notes = "分享商品", value = "分享商品", response = ShareDto.class)
    public JsonResult shareGoods(@ApiParam(required = true, value = "商品id") @RequestParam("goodsId") int goodsId) {
        try {
            Map<String, Object> map = CommonUtil.getParameterMap(request);

            ShareDto shareDto = businessGoodsService.shareGoods(map);

            if (null != shareDto) {
                return new JsonResult(0, "查询成功!", shareDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "逛一逛首页商品列表(新增分页)", value = "逛一逛首页商品列表(新增分页)",response = Goods.class)
    @GetMapping("/randomGoodsByPage")
    public JsonResult randomGoodsByPage(@ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                        @ApiParam(required = true, value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize)
    {
        Map<String, Object> map= CommonUtil.getParameterMap(request);

        return strollAroundService.randomGoodsByPage(map);
    }

    @ApiOperation(notes = "逛一逛商品列表(整合后)", value = "逛一逛商品列表(整合后)", response = AroundGoodsDto.class)
    @GetMapping("/getAroundGoodsList")
    public JsonResult getAroundGoodsList(@ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                         @ApiParam(required = true, value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize,
                                         @ApiParam(required = false,value = "商品名称或店铺名称") @RequestParam(required = false) String name,
                                         @ApiParam(required = false,value = "行业id") @RequestParam(required = false) String tradeId,
                                         @ApiParam(required = false,value = "区域id") @RequestParam(required = false) String areaId) {
        Map<String, Object> map = CommonUtil.getParameterMap(request);
        return strollAroundService.getAroundGoodsList(map);
    }

}
