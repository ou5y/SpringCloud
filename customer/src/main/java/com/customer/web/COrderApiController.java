package com.customer.web;

import com.customer.dto.COrderListDto;
import com.customer.dto.OrderTotalDto;
import com.customer.dto.ScanBusinessDto;
import com.customer.entity.COrder;
import com.customer.entity.ParaMap;
import com.customer.exception.CustomerException;
import com.customer.service.COrderService;
import com.customer.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chenxl on 2017/5/8.
 */
@Api(value = "我要消费/订单", description = "我要消费/订单")
@RestController
@RequestMapping(value = "/v1/cApi/order")
public class COrderApiController extends BaseController {
    @Autowired
    private COrderService cOrderService;

    /*@ApiOperation(notes = "我要消费", value = "我要消费")
    @RequestMapping(value = "/createOrder", method = RequestMethod.POST)
    @ApiIgnore
    public JsonResult createOrder(@RequestHeader("token") String token,
                                  @ApiParam(required =true, value = "商户ID") @RequestParam("storeId") int storeId,
                                  @ApiParam(required =true, value = "消费金额") @RequestParam("money") String money) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());

            int count = cOrderService.createOrder(paraMap);
            if(count > 0){
                return new JsonResult(0,"下单成功!");
            }else{
                return new JsonResult(2,"下单失败!");
            }
        } catch (CustomerException ce) {
            return new JsonResult(ce.getCode(), ce.getMessage(), ce.getData());
        }catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }*/

    @ApiOperation(notes = "待奖励订单", value = "待奖励订单", response = COrderListDto.class)
    @RequestMapping(value = "/getStartOrder", method = RequestMethod.GET)
    public JsonResult getStartOrder(@RequestHeader("token") String token,
                                    @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                    @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("type", 0);//待奖励

            PageInfo<COrderListDto> page = cOrderService.getStartOrder(paraMap);
            OrderTotalDto orderTotalDto = cOrderService.getOrderTotal(paraMap);
            Map map = new HashMap();
            map.put("page", page);
            map.put("total", orderTotalDto.getTotalMoney());
            if(page.getList().size() > 0 && null != orderTotalDto){
                return new JsonResult(0,"查询成功!", map);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "已完结订单", value = "已完结订单", response = COrderListDto.class)
    @RequestMapping(value = "/getEndOrder", method = RequestMethod.GET)
    public JsonResult getEndOrder(@RequestHeader("token") String token,
                                  @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                  @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("type", 1);//已完结

            PageInfo<COrderListDto> page = cOrderService.getEndOrder(paraMap);
            OrderTotalDto orderTotalDto = cOrderService.getOrderTotal(paraMap);
            Map map = new HashMap();
            map.put("page", page);
            map.put("total", orderTotalDto.getTotalMoney());
            if(page.getList().size() > 0 && null != orderTotalDto){
                return new JsonResult(0,"查询成功!", map);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "已关闭订单列表", value = "已关闭订单列表<br/>state:-1:商家关闭,-2:审核失败", response = COrderListDto.class)
    @RequestMapping(value = "/getClosedOrder", method = RequestMethod.GET)
    public JsonResult getClosedOrder(@RequestHeader("token") String token,
                                      @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                      @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("type", 2);//已关闭

            PageInfo<COrderListDto> page = cOrderService.getClosedOrder(paraMap);
            OrderTotalDto orderTotalDto = cOrderService.getOrderTotal(paraMap);
            Map map = new HashMap();
            map.put("page", page);
            map.put("total", orderTotalDto.getTotalMoney());
            if(page.getList().size() > 0 && null != orderTotalDto){
                return new JsonResult(0,"查询成功!", map);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "网络支付等待支付订单", value = "网络支付网络支付等待支付订单<br/>state:5:等待支付", response = COrderListDto.class)
    @RequestMapping(value = "/getStartNetWorkOrder", method = RequestMethod.GET)
    public JsonResult getStartNetWorkOrder(@RequestHeader("token") String token,
                                           @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                           @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("type", 0);//等待支付

            PageInfo<COrderListDto> page = cOrderService.getStartNetWorkOrder(paraMap);
            OrderTotalDto orderTotalDto = cOrderService.getNetWorkOrderTotal(paraMap);
            Map map = new HashMap();
            map.put("page", page);
            map.put("total", orderTotalDto.getTotalMoney());
            if(page.getList().size() > 0 && null != orderTotalDto){
                return new JsonResult(0,"查询成功!", map);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "网络支付已完结订单", value = "网络支付已完结订单", response = COrderListDto.class)
    @RequestMapping(value = "/getEndNetWorkOrder", method = RequestMethod.GET)
    public JsonResult getEndNetWorkOrder(@RequestHeader("token") String token,
                                         @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                         @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("type", 3);//已完结

            PageInfo<COrderListDto> page = cOrderService.getEndNetWorkOrder(paraMap);
            OrderTotalDto orderTotalDto = cOrderService.getNetWorkOrderTotal(paraMap);
            Map map = new HashMap();
            map.put("page", page);
            map.put("total", orderTotalDto.getTotalMoney());
            if(page.getList().size() > 0 && null != orderTotalDto){
                return new JsonResult(0,"查询成功!", map);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "网络支付付款失败订单列表", value = "网络支付付款失败订单列表<br/>state:4", response = COrderListDto.class)
    @RequestMapping(value = "/getClosedNetWorkOrder", method = RequestMethod.GET)
    public JsonResult getClosedNetWorkOrder(@RequestHeader("token") String token,
                                            @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                            @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("type", 1);//付款失败

            PageInfo<COrderListDto> page = cOrderService.getClosedNetWorkOrder(paraMap);
            OrderTotalDto orderTotalDto = cOrderService.getNetWorkOrderTotal(paraMap);
            Map map = new HashMap();
            map.put("page", page);
            map.put("total", orderTotalDto.getTotalMoney());
            if(page.getList().size() > 0 && null != orderTotalDto){
                return new JsonResult(0,"查询成功!", map);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "网络支付付款成功订单列表", value = "网络支付付款成功订单列表<br/>state:2", response = COrderListDto.class)
    @RequestMapping(value = "/getSuccessNetWorkOrder", method = RequestMethod.GET)
    public JsonResult getSuccessNetWorkOrder(@RequestHeader("token") String token,
                                             @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam int currentPage,
                                             @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("type", 2);//付款成功

            PageInfo<COrderListDto> page = cOrderService.getSuccessNetWorkOrder(paraMap);
            OrderTotalDto orderTotalDto = cOrderService.getNetWorkOrderTotal(paraMap);
            Map map = new HashMap();
            map.put("page", page);
            map.put("total", orderTotalDto.getTotalMoney());
            if(page.getList().size() > 0 && null != orderTotalDto){
                return new JsonResult(0,"查询成功!", map);
            }else {
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "自由支付订单", value = "自由支付订单")
    @RequestMapping(value = "/saveFreeOrder", method = RequestMethod.POST)
    public JsonResult saveFreeOrder(@RequestHeader("token") String token,
                                    @ApiParam(required =true, value = "商户ID") @RequestParam("storeId") int storeId,
                                    @ApiParam(required =true, value = "金额") @RequestParam("money") String money,
                                    @ApiParam(required =true, value = "让利") @RequestParam("rangli") String rangli,
                                    @ApiParam(value = "抵扣类型") @RequestParam(value = "offsetType", required = false) String offsetType,
                                    @ApiParam(value = "抵扣值") @RequestParam(value = "offsetNum", required = false) String offsetNum) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            if("".equals(offsetType) || null == offsetType){
                paraMap.put("offsetType", "0");
            }
            if("".equals(offsetNum) || null == offsetNum){
                paraMap.put("offsetNum", "0");
            }

            int count = cOrderService.saveFreeOrder(paraMap);
            if(count > 0){
                return new JsonResult(0,"下单成功!");
            }else{
                return new JsonResult(2,"下单失败!");
            }
        } catch (CustomerException ce) {
            return new JsonResult(ce.getCode(), ce.getMessage());
        }catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "扫一扫商家信息", value = "扫一扫商家信息", response = ScanBusinessDto.class)
    @RequestMapping(value = "/scanBusiness", method = RequestMethod.GET)
    public JsonResult scanBusiness(@RequestHeader("token") String token,
                                   @ApiParam(required =true, value = "商户ID") @RequestParam("storeId") int storeId) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());

            ScanBusinessDto scanBusinessDto = cOrderService.scanBusiness(paraMap);
            if(null != scanBusinessDto){
                return new JsonResult(0,"查询成功!", scanBusinessDto);
            }else{
                return new JsonResult(4,"暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }
}