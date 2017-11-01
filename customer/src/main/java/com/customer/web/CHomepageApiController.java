package com.customer.web;

import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.security.CheckPermission;
import com.customer.service.CBusinessService;
import com.customer.service.CHomepageService;
import com.customer.service.index.DeviceUserService;
import com.customer.util.CommonUtil;
import com.customer.util.JsonResult;
import com.customer.util.Page;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/4 0004.
 */
@Api(value = "首页", description = "首页,附近")
@RestController
@RequestMapping(value = "/v1/cApi/homepage")
public class CHomepageApiController extends BaseController {

    @Autowired
    private CHomepageService cHomepageService;

    @Autowired
    private CBusinessService cBusinessService;

    @Autowired
    private DeviceUserService deviceUserService;

    /*@ApiOperation(notes = "首页经营类型", value = "首页经营类型",response = CBusinessTypeDto.class)
    @RequestMapping(value = "/indexBussinessType", method= RequestMethod.GET)
    public JsonResult indexBussinessType() {
        try {
            List<Map> list = cHomepageService.indexBussinessType();
            if(list.size() > 0){
                return new JsonResult(0,"查询成功!", list);
            }else {
                return new JsonResult(4,"暂无数据!");
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "全部经营类型", value = "全部经营类型",response = CAllBusinessTypeDto.class)
    @RequestMapping(value = "/allBussinessType", method= RequestMethod.GET)
    public JsonResult allBussinessType() {
        try {
            List<Map> list = cHomepageService.allBussinessType();
            if(list.size() > 0){
                return new JsonResult(0,"查询成功!", list);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }*/

    @ApiOperation(notes = "商家推荐", value = "商家推荐",response = CStoreListDto.class)
    @RequestMapping(value = "/recommendStore", method= RequestMethod.GET)
    public JsonResult recommendStore(@ApiParam(value = "纬度") @RequestParam(value = "latitude", required = false) String latitude,
                                     @ApiParam(value = "经度") @RequestParam(value = "longitude", required = false) String longitude,
                                     @ApiParam(value = "区域id") @RequestParam(value = "areaId", required = false) String areaId) {
        try {
            ParaMap paraMap = super.getParaMap();
            if (!StringUtils.isBlank(latitude) && !StringUtils.isBlank(longitude)) {
                //暂时不限制查询范围
                //paraMap = PositionUtil.getRectangle4Point(Double.parseDouble(latitude), Double.parseDouble(longitude), 100000);
                paraMap.put("latitude", latitude);
                paraMap.put("longitude", longitude);
            } else {
                paraMap.put("latitude", "");
                paraMap.put("longitude", "");
            }
            if (StringUtils.isBlank(areaId)) {
                paraMap.put("areaId", "");
            }

            //目前只随机6个商家
            List<CStoreListDto> list = cHomepageService.recommendStore(paraMap);

            if(list.size() > 0){
                return new JsonResult(0,"查询成功!", list);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    /*@ApiOperation(notes = "商铺详情", value = "商铺详情",response = CStoreDetailDto.class)
    @RequestMapping(value = "/storeDetail", method= RequestMethod.GET)
    public JsonResult storeDetail(@ApiParam(required =true, value = "商铺id") @RequestParam("storeId") int storeId) {
        try {
            Map paraMap = new HashMap();
            paraMap.put("storeId", storeId);
            CStoreDetailDto cStoreDetailDto = cHomepageService.storeDetail(paraMap);
            if(null != cStoreDetailDto){
                return new JsonResult(0,"查询成功!", cStoreDetailDto);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "小类列表", value = "小类列表",response = CBusinessTypeDto.class)
    @RequestMapping(value = "/smallTypeList", method = RequestMethod.GET)
    public JsonResult smallTypeList(@ApiParam(required =true, value = "大类") @RequestParam("bigType") int bigType){
        try {
            Map paraMap = new HashMap();
            paraMap.put("bigType", bigType);
            List<Map> list = cHomepageService.smallTypeList(paraMap);
            if(list.size() > 0){
                return new JsonResult(0,"查询成功!", list);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "筛选商品", value = "筛选商品")
    @RequestMapping(value = "/getGoodsList", method = RequestMethod.GET)
    public JsonResult getGoodsList(@ApiParam(required =true, value = "类型") @RequestParam("type") int type){
        try {
            Map paraMap = new HashMap();
            paraMap.put("type", type);
            List<Map> list = cHomepageService.getGoodsList(paraMap);
            if(list.size() > 0){
                return new JsonResult(0,"查询成功!", list);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }*/

    @ApiOperation(notes = "banner", value = "banner",response = CIndexBannerDto.class)
    @RequestMapping(value = "/getBannerList", method = RequestMethod.GET)
    public JsonResult getBannerList(@ApiParam(required =true, value = "类型(1:首页,2:今日数据,3:逛一逛)", defaultValue="1") @RequestParam("type") int type){
        try {
            ParaMap paraMap = super.getParaMap();
            List<CIndexBannerDto> list = cHomepageService.getBannerList(paraMap);
            if(list.size() > 0){
                return new JsonResult(0,"查询成功!", list);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    /*@ApiOperation(notes = "搜索", value = "搜索",response = CStoreListDto.class)
    @RequestMapping(value = "/getSearchList", method= RequestMethod.GET)
    public JsonResult getSearchList(@ApiParam(required =true, value = "搜索内容") @RequestParam("content") String content,
                                    @ApiParam(value = "纬度") @RequestParam(value = "latitude", required = false) String latitude,
                                    @ApiParam(value = "经度") @RequestParam(value = "longitude", required = false) String longitude,
                                    @ApiParam(required=true, value="页码", defaultValue="1") @RequestParam("currentPage") int currentPage,
                                    @ApiParam(required=true, value="记录数", defaultValue="20") @RequestParam("pageSize") int pageSize) {
        try {
            Map paraMap = new HashMap();
            if (!StringUtils.isBlank(latitude) && !StringUtils.isBlank(longitude)) {
                paraMap.put("latitude", latitude);
                paraMap.put("longitude", longitude);
            } else {
                paraMap.put("latitude", "");
                paraMap.put("longitude", "");
            }
            paraMap.put("content", content);
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            PageInfo<Map> page = cHomepageService.getSearchList(paraMap);

            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功!", page);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "商铺下的商品", value = "商铺下的商品",response = CGoodsListDto.class)
    @RequestMapping(value = "/getGoodsListByStore", method = RequestMethod.GET)
    public JsonResult getGoodsListByStore(@ApiParam(required =true, value = "商铺id") @RequestParam("businessId") int businessId,
                                          @ApiParam(required=true, value="页码", defaultValue="1") @RequestParam("currentPage") int currentPage,
                                          @ApiParam(required=true, value="记录数", defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            Map paraMap = new HashMap();
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            PageInfo<CGoodsListDto> page = cHomepageService.getGoodsListByStore(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功!", page);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }*/

    @ApiOperation(value="查询版本更新信息", notes="查询版本更新信息appType取值：<br/>1：Android商户端 <br/> 2 :Android代理端<br/>3：Android用户端  <br/>"
            + "4：IOS商户端  <br/> 5：iOS代理端 <br/>6：IOS用户端<br/>",response = CVersionDto.class)
    @RequestMapping(value="/checkVersion", method=RequestMethod.GET)
    public JsonResult checkVersion(@ApiParam(required=true, value="app类型(1：Android商户端, 2 :Android代理端, 3：Android用户端, 4：IOS商户端, 5：iOS代理端, 6：IOS用户端)") @RequestParam("appType") int appType){
        try {
            ParaMap paraMap = super.getParaMap();
            CVersionDto cVersionDto = cHomepageService.checkVersion(paraMap);

            if(null != cVersionDto){
                return new JsonResult(0,"查询成功!", cVersionDto);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value="查询系统公告", notes = "查询系统公告",response = NoticeDto.class)
    @RequestMapping(value = "/queryNotice", method = RequestMethod.GET)
    public JsonResult queryNotice(@ApiParam(value = "APP类型：1：Android商户端 2 :Android代理端 3：Android用户端 4：IOS商户端 5：iOS代理端 6：IOS用户端", required = true) @RequestParam int appType){
        try {
            ParaMap paraMap = super.getParaMap();
            NoticeDto noticeDto = cHomepageService.queryNotice(paraMap);
            return new JsonResult(0,"查询成功!", noticeDto);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value="首页公告", notes = "首页公告",response = IndexNoticeDto.class)
    @RequestMapping(value = "/getIndexNotice", method = RequestMethod.GET)
    public JsonResult getIndexNotice(){
        try {
            ParaMap paraMap = super.getParaMap();
            //首页查询最近3条公告
            List<IndexNoticeDto> list = cHomepageService.getIndexNotice(paraMap);
            if(list.size() > 0){
                return new JsonResult(0,"查询成功!", list);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "获取商家经营类型", notes = "获取商家经营类型",response=TradeDto.class)
    @RequestMapping(value = "/getBusinessType",method = RequestMethod.GET)
    public JsonResult<Object> getBusinessType(){
        try{
            List<Map<String, Object>> list = cBusinessService.getBusinessType();

            return new JsonResult<Object>(0,"查询商家经营类型成功",list);
        }catch(Exception ex){
            return new JsonResult<Object>(2,"查询数据出错。");
        }
    }

    @ApiOperation(value = "查询附近店铺",notes = "根据用户坐标查询附近的店铺",response = NearbyBusinessDto.class)
    @RequestMapping(value = "/queryNearbyBusiness",method = RequestMethod.GET)
    public JsonResult queryNearbyBusiness(@ApiParam(required = false,value = "纬度") @RequestParam(required = false,defaultValue = "40.000000") String latitude,
                                          @ApiParam(required = false,value = "经度") @RequestParam(required = false,defaultValue = "116.555236") String longitude,
                                          @ApiParam(defaultValue = "5000",value = "距离,默认查询5000米范围内的店铺") @RequestParam(defaultValue = "5000") String distance,
                                          @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                          @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize,
                                          @ApiParam(required = false,value = "店铺名称") @RequestParam(required = false) String name,
                                          @ApiParam(required = false,value = "行业id") @RequestParam(required = false) Integer tradeId,
                                          @ApiParam(required = false,value = "区域id") @RequestParam(required = false) Integer areaId,
                                          @ApiParam(required = false,value = "是否升序排列，0:否  1:是") @RequestParam(required = false) Integer isSortAscending){
        try {
//            ParaMap paraMap = super.getParaMap();
//            paraMap.put("state","1");
//            if(paraMap.get("latitude")==null){
//                paraMap.put("latitude","40.000000");
//            }
//            if(paraMap.get("longitude")==null){
//                paraMap.put("longitude","116.555236");
//            }
//            if(paraMap.get("distance")==null){
//                paraMap.put("distance","5000");
//            }
//            PageInfo page = new PageInfo();
//            page = cBusinessService.queryNearbyBusiness(page,paraMap,currentPage,pageSize);
//            if(page.getList()!=null && page.getList().size()>0){
//                return new JsonResult(0,"查询附近商家成功",page);
//            }else{
//                return new JsonResult(4,"暂无数据",page);
//            }
            ParaMap paraMap = super.getParaMap();
            paraMap.put("state","1");
            if(paraMap.get("latitude")==null){
                paraMap.put("latitude","40.000000");
            }
            if(paraMap.get("longitude")==null){
                paraMap.put("longitude","116.555236");
            }
            if(paraMap.get("distance")==null){
                paraMap.put("distance","5000");
            }
            Page page = new Page(currentPage,pageSize);
            page = cBusinessService.queryNearbyBusiness(page,paraMap,currentPage,pageSize);
            if(page.getList()!=null && page.getList().size()>0){
                return new JsonResult(0,"查询附近商家成功",page);
            }else{
                return new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询商家失败");
        }
    }

    @ApiOperation(value="首页公告详情", notes = "首页公告详情",response = IndexNoticeDto.class)
    @RequestMapping(value = "/getNoticeDetails", method = RequestMethod.GET)
    public JsonResult getNoticeDetails(@ApiParam(required = true,value = "公告id") @RequestParam("noticeId") int noticeId){
        try {
            ParaMap paraMap = super.getParaMap();

            IndexNoticeDto indexNoticeDto = cHomepageService.getNoticeDetails(paraMap);
            if(null != indexNoticeDto){
                return new JsonResult(0,"查询成功!", indexNoticeDto);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(notes = "首页公告更多", value = "首页公告更多",response = IndexNoticeDto.class)
    @RequestMapping(value = "/getNoticeList", method = RequestMethod.GET)
    public JsonResult getNoticeList(@ApiParam(required=true, value="页码", defaultValue="1") @RequestParam("currentPage") int currentPage,
                                    @ApiParam(required=true, value="记录数", defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();

            PageInfo<IndexNoticeDto> page = cHomepageService.getNoticeList(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功!", page);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "商品推荐", value = "商品推荐", response = RecommendGoodsDto.class)
    @RequestMapping(value = "/recommendGoods", method = RequestMethod.GET)
    public JsonResult recommendGoods(@ApiParam(value = "纬度") @RequestParam(value = "latitude", required = false) String latitude,
                                     @ApiParam(value = "经度") @RequestParam(value = "longitude", required = false) String longitude,
                                     @ApiParam(value = "市id") @RequestParam(value = "areaId", required = false) String areaId) {
        try {
            ParaMap paraMap = super.getParaMap();
            if (!StringUtils.isBlank(latitude) && !StringUtils.isBlank(longitude)) {
                paraMap.put("latitude", latitude);
                paraMap.put("longitude", longitude);
            } else {
                paraMap.put("latitude", "");
                paraMap.put("longitude", "");
            }
            if (StringUtils.isBlank(areaId)) {
                paraMap.put("areaId", "");
            }

            //目前只随机6个商品
            List<RecommendGoodsDto> list = cHomepageService.recommendGoods(paraMap);

            if (list.size() > 0) {
                return new JsonResult(0, "查询成功!", list);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "是否是可用设备",notes = "是否是可用设备")
    @RequestMapping(value = "/isEnableDevice",method = RequestMethod.GET)
    public JsonResult isEnableDevice(@ApiParam(value = "设备号,MD5加密后的32位字符串") @RequestParam String deviceNo,
                                     @ApiParam(value="来源,0：未知   1：IOS   2:Android") @RequestParam String source){
        ParaMap paraMap = super.getParaMap();
        Integer isEnable = deviceUserService.queryIsEnable(paraMap);
        if(isEnable!=null && isEnable == 1){
            return new JsonResult(0,"设备可用");
        }else{
            return new JsonResult(2,"该设备已被拉入黑名单",isEnable);
        }
    }

    @ApiOperation(notes = "首页商品列表(整合后)", value = "首页商品列表(整合后)", response = AroundGoodsDto.class)
    @GetMapping("/getHomePageGoodsList")
    public JsonResult getHomePageGoodsList(@ApiParam(required = false, value = "纬度") @RequestParam(required = false, defaultValue = "40.000000") String latitude,
                                           @ApiParam(required = false, value = "经度") @RequestParam(required = false, defaultValue = "116.555236") String longitude,
                                           @ApiParam(defaultValue = "5000", value = "距离,默认查询5000米范围内的店铺") @RequestParam(defaultValue = "5000") String distance,
                                           @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                           @ApiParam(required = true, value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize) {
        try {
            Map<String, Object> paraMap = CommonUtil.getParameterMap(request);

            if (paraMap.get("latitude") == null) {
                paraMap.put("latitude", "40.000000");
            }
            if (paraMap.get("longitude") == null) {
                paraMap.put("longitude", "116.555236");
            }
            if (paraMap.get("distance") == null) {
                paraMap.put("distance", "5000");
            }
            Page page = new Page(currentPage, pageSize);
            page = cHomepageService.getHomePageGoodsList(page, paraMap, currentPage, pageSize);
            if (page.getList() != null && page.getList().size() > 0) {
                return new JsonResult(0, "查询成功", page);
            } else {
                return new JsonResult(4, "暂无数据", page);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }
}

