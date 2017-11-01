package com.azcx9d.consumer.controller;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.token.TokenManagerDao;
import com.azcx9d.common.util.Arith;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.common.util.Page;
import com.azcx9d.consumer.entity.OrderEntity;
import com.azcx9d.consumer.service.ConsumptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenxl on 2017/3/30 0030.
 * 用户消费
 */
@Api(value = "用户端订单", description = "用户端订单")
@RestController
@RequestMapping(value = "/cApi/order")
public class COrderApiController extends BaseController {
    @Autowired
    private ConsumptionService consumptionService;
    @Autowired
    private TokenManagerDao tokenDao;

    @ApiOperation(notes = "我要消费", value = "我要消费")
    @RequestMapping(value = "/createOrder/v1", method= RequestMethod.POST)
    public JsonResult createOrder(@ApiParam(required =true, value = "商户ID") @RequestParam("storeId") long storeId,
                                  @ApiParam(required =true, value = "消费金额") @RequestParam("money") String money,
                                  @RequestHeader("token") String token) {
        long userId = (long) request.getAttribute("userId");
        JsonResult result;
        try {
            result = consumptionService.createOrder(userId, storeId, Double.parseDouble(money));
        } catch (Exception e) {
            logger.error(e.getMessage());
            return result = new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
        return result;
    }

    @ApiOperation(notes = "个人订单", value = "个人订单",response = OrderEntity.class)
    @RequestMapping(value = "/getOrderList/v1",method = RequestMethod.GET)
    public JsonResult getOrderList(@RequestHeader("token") String token) {
        long userId = (long) request.getAttribute("userId");
        JsonResult result;
        try {
            result = consumptionService.getOrderList(userId);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return result = new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
        return result;
    }

    @ApiOperation(notes = "根据订单状态查询订单", value = "根据订单状态查询订单<br/>storeId:商户id",response = OrderEntity.class)
    @RequestMapping(value = "/getOrderByState/v1",method = RequestMethod.GET)
    public JsonResult getOrderByState(@RequestHeader("token") String token,
                                      @ApiParam(required =true, value = "订单状态(0:等待商家让利,1:等待财务收款,2:订单已完结)") @RequestParam("state") int state,
                                      @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                      @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        long userId = (long) request.getAttribute("userId");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("state", state);
        params.put("pageSize", pageSize);
        Page page = new Page(currentPage,pageSize);
        try {
            page = consumptionService.getOrderByState(params,page);
            page.init();
            return new JsonResult(0,"查询成功!", page);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "关闭订单列表", value = "关闭订单列表<br/>state:-1:商家关闭,-2:审核失败 storeId:商户id",response = OrderEntity.class)
    @RequestMapping(value = "/getClosedOrder/v1",method = RequestMethod.GET)
    public JsonResult getClosedOrder(@RequestHeader("token") String token,
                                      @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                      @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        long userId = (long) request.getAttribute("userId");
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("userId", userId);
        params.put("pageSize", pageSize);
        Page page = new Page(currentPage,pageSize);
        try {
            page = consumptionService.getClosedOrder(params,page);
            page.init();
            return new JsonResult(0,"查询成功!", page);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(500,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }
}