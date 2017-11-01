package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.DayIncomeDto;
import com.azcx9d.business.dto.HistoryIncome;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BOrderFormService;
import com.azcx9d.business.util.DateUtil;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by HuangQing on 2017/4/5 0005 11:30.
 */
@Api(value = "我的收益",description="店铺收益")
@RestController
@RequestMapping("/v1/bApi/myIncome")
public class BMyIncomeController extends HQBaseController {

    @Autowired
    private BOrderFormService orderFormService;

    @ApiOperation(value = "日收益(查今日收益时queryDate传当天时间或空字符串)", notes = "totalIncome：总额<br/>", response = DayIncomeDto.class)
    @RequestMapping(value = "/dayIncome", method = RequestMethod.GET)
    public JsonResult todayIncome(@RequestHeader("token") String token,
                                  @ApiParam(required = true,value = "某一天的收益，格式： 2017-04-05")@RequestParam("queryDate") String queryDate,
                                  @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                  @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                  @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId",super.getUserId());
            paraMap.put("businessId", businessId);

            if (StringUtils.isBlank(queryDate)){
                paraMap.put("queryDate", DateUtil.getDay());
            }

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);

            Map orderAndTotalIncome = orderFormService.selectCountOrderAndTotalIncome(paraMap);
            orderAndTotalIncome.put("totalIncome", orderAndTotalIncome.get("totalIncome") + "");
            page.setTotalRow(Integer.valueOf(orderAndTotalIncome.get("orderNumber").toString()));
            orderFormService.selectIncome(page,paraMap);
            orderAndTotalIncome.put("page",page);

            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", orderAndTotalIncome);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "历史收益", notes = "totalMoney：历史总金额", response = HistoryIncome.class)
    @RequestMapping(value = "/historyIncome", method = RequestMethod.GET)
    public JsonResult historyIncome(@RequestHeader("token") String token,
                                    @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                    @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                    @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId",super.getUserId());
            paraMap.put("businessId", businessId);

            Page page = new Page();
            page.setCurrentPage(currentPage);
            page.setPageSize(pageSize);

            Map days = orderFormService.selectTotalMoneyAndTotalCountGroupByDays(paraMap);
            days.put("totalMoney", days.get("totalMoney") + "");
            page.setTotalRow(Integer.valueOf(days.get("dayNumber").toString()));
            orderFormService.selectIncomeByDays(page,paraMap);
            days.put("page",page);
            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", days);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    /*@ApiOperation(value = "某一天收益查询", notes = "totalIncome：总金额<br/>orderNumber：订单总数", response = DayIncomeDto.class)
    @RequestMapping(value = "/incomeByDay", method = RequestMethod.GET)
    public JsonResult selectIncomeByDay(@RequestHeader("token") String token,
                                        @ApiParam(required = true,value = "某一天的收益，格式： 2017-04-05")@RequestParam("queryDate") String queryDate,
                                        @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId",super.getUserId());
            paraMap.put("businessId", businessId);

            Map income = orderFormService.selectCountOrderAndTotalIncome(paraMap);
            return new JsonResult<Object>(income);
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }*/

}
