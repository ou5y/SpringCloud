package com.azcx9d.business.controller;

import com.alibaba.fastjson.JSON;
import com.azcx9d.agency.entity.Business;
import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.BusinessManageDto;
import com.azcx9d.business.entity.BBusiness;
import com.azcx9d.business.entity.BTrade;
import com.azcx9d.business.service.BBusinessService;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BTradeService;
import com.azcx9d.common.entity.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by HuangQing on 2017/4/2 0002 16:59.
 */
@Api(value = "店铺管理",description="店铺管理")
@RestController
@RequestMapping("/v1/bApi/businessInfo")
public class BBusinessInfoController extends HQBaseController {

    @Autowired
    private BBusinessService businessService;

    @Autowired
    private BTradeService tradeService;

    /**
     * 店铺管理：店铺详情
     * @param token
     * @return
     */
    @RequestMapping(value = "/businessInfo",method = RequestMethod.POST)
    @ApiOperation(value = "店铺详情",notes = "operateTypeName：店铺类型名字", response = BusinessManageDto.class)
    public JsonResult businessInfo(@RequestHeader("token") String token,
                                   @ApiParam(required = true,value = "商铺id") @RequestParam("businessId") int businessId){
        try {
            BusinessManageDto businessManageDto = businessService.selectByBusinessId(businessId);
            if (null != businessManageDto) {
                return new JsonResult(0, "查询成功!", businessManageDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }


    @RequestMapping(value = "/allTypeOfBusiness",method = RequestMethod.POST)
    @ApiOperation(value = "查询所有的店铺分类",response = BTrade.class)
    public JsonResult allType(@RequestHeader("token") String token){
        try {
            List<BTrade> trades = tradeService.selectAll();
            if (trades.size() > 0) {
                return new JsonResult(0, "查询成功!", trades);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }


    @RequestMapping(value = "/editBusiness",method = RequestMethod.POST)
    @ApiOperation(value = "编辑店铺详情<br/>businessPhoto：字符串<br/>showPics：店铺展示图多张图片用英文半角逗号(,)分开<br/>")
    public JsonResult editBusinessInfo(@RequestHeader("token") String token,
                                       @ApiParam(required = true,value = "店铺id")@RequestParam("id") int id,
                                       @ApiParam(required = true,value = "店铺主图")@RequestParam("businessPhoto") String businessPhoto,
                                       @ApiParam(required = true,value = "店铺展示图")@RequestParam("showPics") String showPics,
                                       @ApiParam(required = true,value = "营业时间：起，格式：hh：mm")@RequestParam("openTime") String openTime,
                                       @ApiParam(required = true,value = "营业时间：止，格式：hh：mm")@RequestParam("closeTime") String closeTime,
                                       @ApiParam(required = true,value = "店铺介绍")@RequestParam("intro") String intro,
                                       @ApiParam(required = true,value = "店铺电话")@RequestParam("phone") String phone,
                                       @ApiParam(required = true,value = "店铺经度")@RequestParam("longitude1") String longitude1,
                                       @ApiParam(required = true,value = "店铺纬度")@RequestParam("latitude1") String latitude1,
                                       @ApiParam(required = true,value = "店铺地址")@RequestParam("businessAddress") String businessAddress){
        try {
            ParaMap paraMap = super.getParaMap();
            int count = businessService.updateByBusinessId(paraMap);
            if (count > 0) {
                return new JsonResult(0, "修改成功!");
            } else {
                return new JsonResult(2, "修改失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

}
