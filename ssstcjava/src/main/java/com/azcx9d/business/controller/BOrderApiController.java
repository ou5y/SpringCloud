package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.FreeOrderDto;
import com.azcx9d.business.dto.GetPayDto;
import com.azcx9d.business.dto.NetWorkOrderDto;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.business.service.BOrderFormService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.*;

/**
 * Created by HuangQing on 2017/3/30 0030.
 */
@Api(value = "订单",description="订单")
@RestController
@RequestMapping("/v1/bApi/order")
public class BOrderApiController extends HQBaseController {

    @Autowired
    private BOrderFormService orderService;

    /**
     * 待让利列表
     * @return
     */
    @ApiOperation(value = "待让利列表", notes = "获取待让利列表和总金额、总订单数<br/>orderNumber：待让利订单总数<br/>totalMoney：订单总额", response=FreeOrderDto.class)
    @RequestMapping(value = "/pendingList", method = RequestMethod.GET)
    public JsonResult pendingList(@RequestHeader("token") String token,
                                  @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                  @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                  @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId",super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("state",0);
            paraMap.put("currentPage",currentPage);
            paraMap.put("pageSize",pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);

            Map rangliAndMoney = orderService.selectCountOrderAndSumMoney(paraMap);
            rangliAndMoney.put("totalMoney", rangliAndMoney.get("totalMoney") + "");
            orderService.selectByStoreUserId(page, paraMap);
            rangliAndMoney.put("page",page);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", rangliAndMoney);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 处理中列表
     * @return
     */
    @ApiOperation(value = "处理中列表", notes = "获取处理中列表和总金额、总订单数<br/>totalRangli：应让利<br/>totalMoney：订单总额", response=FreeOrderDto.class)
    @RequestMapping(value = "/auditList", method = RequestMethod.GET)
    public JsonResult auditList(@RequestHeader("token") String token,
                                @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId",super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage",currentPage);
            paraMap.put("pageSize",pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);

            Map rangliAndMoney = orderService.selectSumRangliAndSumMoney(paraMap);
            rangliAndMoney.put("totalMoney", rangliAndMoney.get("totalMoney") + "");
            rangliAndMoney.put("totalRangli", rangliAndMoney.get("totalRangli") + "");
            orderService.selectAuditByStoreId(page, paraMap);
            rangliAndMoney.put("page",page);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", rangliAndMoney);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 已完结列表
     * @return
     */
    @ApiOperation(value = "已完结列表", notes = "获取已完结列表和总金额、总订单数<br/>totalRangli：已让利<br/>totalMoney：订单总额", response=FreeOrderDto.class)
    @RequestMapping(value = "/completionList", method = RequestMethod.GET)
    public JsonResult completionList(@RequestHeader("token") String token,
                                     @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                     @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                     @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId",super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage",currentPage);
            paraMap.put("pageSize",pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);

            Map rangliAndMoney = orderService.selectSumRangliAndSumMoneyFinish(paraMap);
            rangliAndMoney.put("totalMoney", rangliAndMoney.get("totalMoney") + "");
            rangliAndMoney.put("totalRangli", rangliAndMoney.get("totalRangli") + "");
            orderService.selectByStoreUserIdFinish(page, paraMap);
            rangliAndMoney.put("page",page);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", rangliAndMoney);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 批量设置订单状态为待处理状态 state：1
     * @param ids 多个订单id ，字符串数组
     * @return
     */
    @ApiOperation(value = "提交审核(可批量)", notes = "提交审核(可批量)")
    @RequestMapping(value = "/pendingOrders", method = RequestMethod.POST)
    public JsonResult pendingOrders(@RequestHeader("token") String token,
                                    @ApiParam(required = true,value = "多个id ，数组") @RequestParam("ids") String[] ids,
                                    /*@ApiParam(required = true,value = "让利比例，小数，不能用百分号-%") @RequestParam("rangli") String rangli,*/
                                    @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("userId",super.getUserId());
        paramMap.put("businessId", businessId);

        paramMap.put("idsArr",ids);
        /*paramMap.put("rangli",rangli);*/
        paramMap.put("sellerTime",new Date()); //商家确认时间，用当前系统时间
        try {
            int count  = orderService.countHandlingOrder(paramMap);//统计处理中订单数
            if(count > 0){
                return new JsonResult(2,"您今日已经提交过订单!不能多次提交");
            }
            int counts = orderService.updateOrderStateByIds(paramMap);
            if(counts > 0){
                return new JsonResult(0, "提交成功");
            }else{
                return new JsonResult(2, "提交失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 取消订单
     * @return
     */
    @ApiOperation(value = "关闭订单", notes = "关闭订单")
    @RequestMapping(value = "/cancelOrder", method = RequestMethod.POST)
    public JsonResult cancelOrder(@RequestHeader("token") String token,
                                  @ApiParam(required = true,value = "订单id")@RequestParam("id") int id){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("id", id);
            paraMap.put("state", -1);//-1:订单取消

            int count = orderService.cancelOrder(paraMap);
            if(count > 0){
                return new JsonResult<Object>(0, "取消订单成功!");
            }else{
                return new JsonResult<Object>(2, "取消订单失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 更新消费图片URL
     * @return
     */
    @ApiOperation(value = "更新消费图片URL", notes = "更新消费图片URL")
    @RequestMapping(value = "/updateConsumptionPic", method = RequestMethod.POST)
    public JsonResult updateConsumptionPic(@RequestHeader("token") String token,
                                           @ApiParam(required = true,value = "订单id")@RequestParam("id") int id,
                                           @ApiParam(required = true,value = "消费凭证图片") @RequestParam("consumptionPic") String consumptionPic){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("id", id);
            paraMap.put("consumptionPic", consumptionPic);

            int count = orderService.updateConsumptionPic(paraMap);
            if(count > 0){
                return new JsonResult<Object>(0, "消费凭证上传成功!");
            }else{
                return new JsonResult<Object>(2, "消费凭证上传失败!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 关闭订单列表
     * @return
     */
    @ApiOperation(value = "关闭订单列表", notes = "获取关闭订单总金额,关闭订单总数<br/>orderNumber:订单数量<br/>totalMoney：订单总额<br/>", response=FreeOrderDto.class)
    @RequestMapping(value = "/closeList", method = RequestMethod.GET)
    public JsonResult closeList(@RequestHeader("token") String token,
                                @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId",super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage",currentPage);
            paraMap.put("pageSize",pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);

            Map rangliAndMoney = orderService.selectCloseOrderAndSumMoney(paraMap);
            rangliAndMoney.put("totalMoney", rangliAndMoney.get("totalMoney") + "");
            orderService.selectCloseList(page, paraMap);
            rangliAndMoney.put("page",page);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", rangliAndMoney);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 让利比例列表
     * @return
     */
    @ApiIgnore
    @ApiOperation(value = "让利比例列表", notes = "让利比例列表")
    @RequestMapping(value = "/getRangli", method = RequestMethod.GET)
    public JsonResult getRangli(@RequestHeader("token") String token){
        try {
            List<Double> rangli = new ArrayList<>();
            Map result = new HashMap();
            rangli.add(0.2);             //暂时只能是0.2,后面要加就按大小顺序添加
            result.put("rangli",rangli);
            return new JsonResult<Object>(result);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 网络订单_付款失败列表
     */
    @ApiOperation(value = "网络订单_付款失败列表", notes = "获取付款失败列表和总金额、总订单数<br/>orderNumber：订单总数<br/>totalMoney：订单总额<br/>", response=NetWorkOrderDto.class)
    @RequestMapping(value = "/getFailedOrder", method = RequestMethod.GET)
    public JsonResult getFailedOrder(@RequestHeader("token") String token,
                                     @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                     @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                     @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId", super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            Map map = orderService.getFailedOrder(page, paraMap);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", map);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 网络订单_已完结列表
     */
    @ApiOperation(value = "网络订单_已完结列表", notes = "获取已完结列表和总金额、总订单数<br/>orderNumber：订单总数<br/>totalMoney：订单总额<br/>", response=NetWorkOrderDto.class)
    @RequestMapping(value = "/getEndedOrder", method = RequestMethod.GET)
    public JsonResult getEndedOrder(@RequestHeader("token") String token,
                                    @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                    @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                    @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId", super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            Map map = orderService.getEndedOrder(page, paraMap);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", map);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 网络订单_已关闭列表
     */
    @ApiIgnore
    @ApiOperation(value = "网络订单_已关闭列表", notes = "获取已关闭列表和总金额、总订单数<br/>orderNumber：订单总数<br/>totalMoney：订单总额<br/>", response=NetWorkOrderDto.class)
    @RequestMapping(value = "/getClosedOrder", method = RequestMethod.GET)
    public JsonResult getClosedOrder(@RequestHeader("token") String token,
                                     @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                     @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                     @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId", super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            Map map = orderService.getClosedOrder(page, paraMap);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", map);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 网络订单_已关闭列表
     */
    @ApiOperation(value = "网络订单_付款成功列表", notes = "获取付款成功列表和总金额、总订单数<br/>orderNumber：订单总数<br/>totalMoney：订单总额<br/>", response=NetWorkOrderDto.class)
    @RequestMapping(value = "/getSuccessOrder", method = RequestMethod.GET)
    public JsonResult getSuccessOrder(@RequestHeader("token") String token,
                                      @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                      @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                      @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId", super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            Map map = orderService.getSuccessOrder(page, paraMap);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", map);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /**
     * 网络订单_已关闭列表
     */
    @ApiOperation(value = "网络订单_待付款列表", notes = "获取待付款列表和总金额、总订单数<br/>orderNumber：订单总数<br/>totalMoney：订单总额<br/>", response=NetWorkOrderDto.class)
    @RequestMapping(value = "/getStayOrder", method = RequestMethod.GET)
    public JsonResult getStayOrder(@RequestHeader("token") String token,
                                   @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                   @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                   @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("userId", super.getUserId());
            paraMap.put("businessId", businessId);
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);
            Map map = orderService.getStayOrder(page, paraMap);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", map);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(notes = "去收款", value = "去收款", response = GetPayDto.class)
    @RequestMapping(value = "/getPay", method = RequestMethod.GET)
    public JsonResult getPay(@RequestHeader("token") String token,
                             @ApiParam(required = true, value = "订单ID(纯数字id)") @RequestParam("orderId") int orderId) {
        try {
            HashMap<String, Object> paraMap = new HashMap<>();
            paraMap.put("orderId", orderId);

            GetPayDto getPayDto = orderService.getPay(paraMap);
            if (null != getPayDto) {
                return new JsonResult(0, "查询成功!", getPayDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

}
