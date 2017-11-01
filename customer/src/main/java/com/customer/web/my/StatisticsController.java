package com.customer.web.my;

import com.customer.dto.TodayStatisticsDto;
import com.customer.dto.statistic.AreaAgencyDto;
import com.customer.dto.statistic.IncomeDetailDto;
import com.customer.dto.statistic.IncomeStatisticDto;
import com.customer.entity.ParaMap;
import com.customer.service.StatisticService;
import com.customer.util.*;
import com.customer.web.BaseController;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/11 0011.
 */
@Api(value = "统计",description = "报表统计")
@RestController
@RequestMapping(value = "/v1/cApi/statistics")
public class StatisticsController extends BaseController{

    @Autowired
    private StatisticService statisticService;

    @ApiOperation(value = "今日统计",notes = "今日统计",response = TodayStatisticsDto.class)
    @RequestMapping(value = "/todayStatistics",method = RequestMethod.GET)
    public JsonResult todayStatistics(@RequestHeader(value = "token") String token){
        try {
            //查询是否开启今日数据
            Map<String, String> todayData = statisticService.queryTodayData();
            if (null != todayData && todayData.size() > 0) {
                return new JsonResult(0, "查询数据成功", todayData);
            }
            Map<String,String> datas = statisticService.queryTotalDatas();
            BigDecimal totalAmount = new BigDecimal(Arith.div(Double.valueOf(datas.get("totalAmount").toString()),10000)).setScale(0, BigDecimal.ROUND_HALF_UP);
            datas.put("totalAmount",totalAmount.toString());
//            for(String key : datas.keySet()){
//                if(Integer.valueOf(datas.get(key).toString())>0){
//                    int d1 = (int)(Arith.mul(Double.parseDouble(datas.get(key).toString()),3.0,2));
//                    datas.put(key,d1+"");
//                }
//            }
            return new JsonResult(0,"查询数据成功",datas);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败,请稍后重试");
        }
    }

    @ApiOperation(value = "区域收益",notes = "区域收益",response = AreaAgencyDto.class)
    @RequestMapping(value = "/areaStatistic",method = RequestMethod.GET)
    public JsonResult areaStatistic(@RequestHeader(value = "token") String token){
        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("fromId",model.getId());
            paraMap.put("tradeId",0);

            Date date1 = new Date();
            Date date = new Date(new Date().getTime()+(24*60*60)*1000);
            String startDate = CalendarUtil.formatDateByString(date1);
            String endDate = CalendarUtil.formatDateByString(date);
            paraMap.put("startDate",startDate);
            paraMap.put("endDate",endDate);

            AreaAgencyDto areaAgencyDto = statisticService.queryAreaStatistic(paraMap);
            if(areaAgencyDto!=null && areaAgencyDto.getList()!=null && areaAgencyDto.getList().size()>0){
                return  new JsonResult(0,"查询成功",areaAgencyDto);
            }else{
                return new JsonResult(4,"暂无数据",areaAgencyDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

    @ApiOperation(value = "区域收益明细",notes = "区域收益明细",response = AreaAgencyDto.class)
    @RequestMapping(value = "/areaStatisticDetail",method = RequestMethod.GET)
    public JsonResult areaStatisticDetail(@RequestHeader(value = "token") String token,
                                          @ApiParam(value = "代理区域")@RequestParam(required = false) String areaId,
                                         @ApiParam(value = "是否查询全部,0:查询今日收益明细   1:查询历史收益明细",defaultValue = "0") @RequestParam int isAll,
                                          @ApiParam(value = "店铺名称,可选")@RequestParam(required = false) String bName,
                                         @ApiParam(value="开始日期,可选") @RequestParam(required = false) String startDate,
                                         @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                         @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){

        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("fromId",model.getId());
            paraMap.put("tradeId",0);
            String endDate = null;
            if(isAll==0){
                // 默认今天
                Date date1 = new Date();
                Date date = new Date(new Date().getTime()+(24*60*60)*1000);
                startDate = CalendarUtil.formatDateByString(date1);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("startDate",startDate);
                paraMap.put("endDate",endDate);
            }
            if(isAll==1&&startDate!=null && !"".equals(startDate)){
                paraMap.put("startDate",startDate);
                Date date = new Date(CalendarUtil.formatDateByString(startDate).getTime()+(24*60*60)*1000);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("endDate",endDate);
            }

            Map<String,Object> result = statisticService.queryAreaDetail(paraMap,currentPage,pageSize);
            if(result!=null && result.get("page")!=null&& ((Page)result.get("page")).getList().size()>0){
                return  new JsonResult(0,"查询成功",result);
            }else{
                return new JsonResult(4,"暂无数据",result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

    @ApiOperation(value = "行业收益",notes = "行业收益",response = AreaAgencyDto.class)
    @RequestMapping(value = "/tradeStatistic",method = RequestMethod.GET)
    public JsonResult tradeStatistic(@RequestHeader(value = "token") String token){
        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("fromId",model.getId());
            paraMap.put("tradeId",1);

            Date date1 = new Date();
            Date date = new Date(new Date().getTime()+(24*60*60)*1000);
            String startDate = CalendarUtil.formatDateByString(date1);
            String endDate = CalendarUtil.formatDateByString(date);
            paraMap.put("startDate",startDate);
            paraMap.put("endDate",endDate);

            AreaAgencyDto areaAgencyDto = statisticService.queryAreaStatistic(paraMap);
            if(areaAgencyDto!=null && areaAgencyDto.getList()!=null && areaAgencyDto.getList().size()>0){
                return  new JsonResult(0,"查询成功",areaAgencyDto);
            }else{
                return new JsonResult(4,"暂无数据",areaAgencyDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

    @ApiOperation(value = "行业收益明细",notes = "行业收益明细",response = AreaAgencyDto.class)
    @RequestMapping(value = "/tradeStatisticDetail",method = RequestMethod.GET)
    public JsonResult tradeStatisticDetail(@RequestHeader(value = "token") String token,
                                          @ApiParam(value = "代理区域")@RequestParam(required = false) String areaId,
                                          @ApiParam(value = "行业")@RequestParam(required = false) String tradeId,
                                          @ApiParam(value = "是否查询全部,0:查询今日收益明细   1:查询历史收益明细",defaultValue = "0") @RequestParam int isAll,
                                          @ApiParam(value = "店铺名称,可选")@RequestParam(required = false) String bName,
                                          @ApiParam(value="开始日期,可选") @RequestParam(required = false) String startDate,
                                          @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                          @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){

        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("fromId",model.getId());
            String endDate = null;
            if(isAll==0){
                // 默认今天
                Date date1 = new Date();
                Date date = new Date(new Date().getTime()+(24*60*60)*1000);
                startDate = CalendarUtil.formatDateByString(date1);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("startDate",startDate);
                paraMap.put("endDate",endDate);
            }
            if(isAll==1&&startDate!=null && !"".equals(startDate)){
                paraMap.put("startDate",startDate);
                Date date = new Date(CalendarUtil.formatDateByString(startDate).getTime()+(24*60*60)*1000);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("endDate",endDate);
            }

            Map<String,Object> result = statisticService.queryTradeDetail(paraMap,currentPage,pageSize);
            if(result!=null && result.get("page")!=null&& ((Page)result.get("page")).getList().size()>0){
                return  new JsonResult(0,"查询成功",result);
            }else{
                return new JsonResult(4,"暂无数据",result);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

}
