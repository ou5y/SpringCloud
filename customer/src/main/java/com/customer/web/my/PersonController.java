package com.customer.web.my;

import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.security.CheckPermission;
import com.customer.service.IncomePredicateService;
import com.customer.service.UserService;
import com.customer.util.CalendarUtil;
import com.customer.util.CommonUtil;
import com.customer.util.JsonResult;
import com.customer.util.TokenModel;
import com.customer.web.BaseController;
import com.customer.web.UserController;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/5/12.
 */
@RestController
@Api(value = "个人中心",description = "个人信息,个人收益,业务")
@RequestMapping("/v1/person/")
public class PersonController extends BaseController {
    @Autowired
    private UserService userService;

    @Autowired
    private IncomePredicateService incomePredicateService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/detail")
    @ApiOperation(value = "个人信息",notes = "个人详情",response =UserDto.class)
    public JsonResult getUserInfo(@RequestHeader("token") String token)
    {

        Integer id =(int) request.getAttribute("userId");
        UserDto userDto=userService.getUserDetail(id);
        return new JsonResult<>(userDto);
    }


    @ApiOperation(value = "我的积分",notes = "我的积分",response = CMyIntegralDto.class)
    @RequestMapping(value = "/getMyIntegral",method = RequestMethod.GET)
    public JsonResult getMyJifen(@RequestHeader("token") String token,
//                                 @ApiParam(required = true,value = "类型 (1:积分,0:善点)") @RequestParam("type") Integer type,
                                 @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                 @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        Map<String, Object> map= CommonUtil.getParameterMap(request);
        map.put("id",request.getAttribute("userId"));
        map.put("type",1);
        return new JsonResult(userService.query(map));

    }

    @ApiOperation(value = "主动善点",notes = "主动善点",response = CMyShandianDto.class)
    @RequestMapping(value = "/getMyShandian",method = RequestMethod.GET)
    public JsonResult getMyShanDian(@RequestHeader("token") String token,
//                                 @ApiParam(required = true,value = "类型 (1:积分,0:善点)") @RequestParam("type") Integer type,
                                 @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                 @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        Map<String, Object> map= CommonUtil.getParameterMap(request);
        map.put("id",request.getAttribute("userId"));
        map.put("type",0);
        return new JsonResult(userService.query(map));


    }

    @GetMapping(value = "/getMyRecommodPoint")
    @ApiOperation(value = "推荐奖励善点",notes = "推荐奖励善点",response =MyRecommendPointDto.class )
    public JsonResult getMyRecommodPoint(@RequestHeader("token") String token,
                                         @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                         @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize)
    {
        Map<String, Object> map= CommonUtil.getParameterMap(request);
        map.put("id",request.getAttribute("userId"));
        map.put("type",2);
        return new JsonResult(userService.query(map));
    }

    @ApiOperation(value = "我的积分(新版本)",notes = "我的积分",response = CMyIntegralDto.class)
    @RequestMapping(value = "/queryMyJifen",method = RequestMethod.GET)
    public JsonResult queryMyJifen(@RequestHeader("token") String token,
                                 @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                 @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        Map<String, Object> map= CommonUtil.getParameterMap(request);
        map.put("id",request.getAttribute("userId"));
        map.put("type",1);
        return new JsonResult(incomePredicateService.queryIncomeDetail(map));

    }

    @ApiOperation(value = "主动善点(新版本)",notes = "主动善点",response = CMyShandianDto.class)
    @RequestMapping(value = "/queryMyShanDian",method = RequestMethod.GET)
    public JsonResult queryMyShanDian(@RequestHeader("token") String token,
                                    @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                    @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        Map<String, Object> map= CommonUtil.getParameterMap(request);
        map.put("id",request.getAttribute("userId"));
        map.put("type",0);
        return new JsonResult(incomePredicateService.queryIncomeDetail(map));


    }


    @ApiOperation(value = "被动善点(新版本)",notes = "被动善点",response =MyRecommendPointDto.class )
    @RequestMapping(value = "/queryMyPassiveShandian",method = RequestMethod.GET)
    public JsonResult queryMyPassiveShandian(@RequestHeader("token") String token,
                                         @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                         @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize)
    {
        Map<String, Object> map= CommonUtil.getParameterMap(request);
        map.put("id",request.getAttribute("userId"));
        map.put("userId",request.getAttribute("uid"));
        map.put("type",2);
        return new JsonResult(incomePredicateService.queryIncomeDetail(map));
    }

    @CheckPermission
    @GetMapping(value = "/getMyAgencyBenefits")
    @ApiOperation(value = "代理业务",notes = "服务商代理区域收益",response = AgencyBenefitsDto.class)
    public JsonResult getMyAgencyBenefits(@RequestHeader("token") String token,
                                          @ApiParam(value = "代理区域")@RequestParam(required = false) String areaId,
                                          @ApiParam(value = "行业")@RequestParam(required = false) String tradeId,
                                          @ApiParam(value="开始日期") @RequestParam(required = false) String startDate,
                                          @ApiParam(value = "结束日期")@RequestParam(required = false) String endDate,
                                          @ApiParam(value = "代理区域ID") @RequestParam(required = false) String parentCode){
        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("fromId",model.getId());
//            if((startDate==null && endDate!=null)||(startDate!=null && endDate==null)){
//                return new JsonResult(2,"开始日期或结束日期不能为空");
//            }
//            if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
//                Date date = new Date(CalendarUtil.formatDateByString(endDate).getTime()+(24*60*60)*1000);
//                endDate = CalendarUtil.formatDateByString(date);
//                paraMap.put("startDate",startDate);
//                paraMap.put("endDate",endDate);
//            }
//            if((startDate==null || "".equals(startDate))  && (endDate==null || "".equals(endDate))){
//                // 默认查询30天
//                Date date1 = CalendarUtil.findDatesBySpace(new Date(),6);
//                Date date = new Date(new Date().getTime()+(24*60*60)*1000);
//                startDate = CalendarUtil.formatDateByString(date1);
//                endDate = CalendarUtil.formatDateByString(date);
//                paraMap.put("startDate",startDate);
//                paraMap.put("endDate",endDate);
//            }

            List<Map<String,Object>> lists = userService.queryAgencyStatistics(paraMap);
            if(lists!=null && lists.size()>0){
                return  new JsonResult(0,"查询成功",lists);
            }else{
                return new JsonResult(4,"暂无数据",lists);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

    @CheckPermission
    @ApiOperation(value = "代理折线图",notes = "代理折线图",response = AgencyProfit.class)
    @RequestMapping(value = "/queryAgencyLineChart",method = RequestMethod.GET)
    public JsonResult queryAgencyLineChart(
            @RequestHeader("token") String token,
            @ApiParam(value = "代理区域")@RequestParam(required = false) String areaId,
            @ApiParam(value = "行业")@RequestParam(required = false) String tradeId,
            @ApiParam(value="开始日期") @RequestParam(required = false) String startDate,
            @ApiParam(value = "结束日期")@RequestParam(required = false) String endDate,
            @ApiParam(value = "代理区域ID") @RequestParam(required = false) String parentCode){

        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("fromId",model.getId());
            if((startDate==null && endDate!=null)||(startDate!=null && endDate==null)){
                return new JsonResult(2,"开始日期或结束日期不能为空");
            }
            if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
                Date date = new Date(CalendarUtil.formatDateByString(endDate).getTime()+(24*60*60)*1000);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("startDate",startDate);
                paraMap.put("endDate",endDate);
            }
            if((startDate==null || "".equals(startDate))  && (endDate==null || "".equals(endDate))){
                // 默认查询30天
                Date date1 = CalendarUtil.findDatesBySpace(new Date(),6);
                Date date = new Date(new Date().getTime()+(24*60*60)*1000);
                startDate = CalendarUtil.formatDateByString(date1);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("startDate",startDate);
                paraMap.put("endDate",endDate);
            }

            List<AgencyProfit> lists = userService.queryAgencyLineChart(paraMap);
            if(lists!=null && lists.size()>0){
                return  new JsonResult(0,"查询成功",lists);
            }else{
                return new JsonResult(4,"暂无数据",lists);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

    @ApiOperation(value = "区域详情",notes = "区域详情")
    @RequestMapping(value = "/queryAreaDetail",method = RequestMethod.GET)
    public JsonResult queryAreaDetail(@RequestHeader("token") String token,
                                      @ApiParam(value = "代理区域")@RequestParam(required = false) String areaId,
                                      @ApiParam(value = "行业")@RequestParam(required = false) String tradeId,
                                      @ApiParam(value="开始日期") @RequestParam(required = false) String startDate,
                                      @ApiParam(value = "结束日期")@RequestParam(required = false) String endDate,
                                      @ApiParam(value = "代理区域ID") @RequestParam(required = false) String parentCode,
                                      @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                      @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("fromId",model.getId());
            if((startDate==null && endDate!=null)||(startDate!=null && endDate==null)){
                return new JsonResult(2,"开始日期或结束日期不能为空");
            }
            if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
                Date date = new Date(CalendarUtil.formatDateByString(endDate).getTime()+(24*60*60)*1000);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("startDate",startDate);
                paraMap.put("endDate",endDate);
            }
//            if((startDate==null || "".equals(startDate))  && (endDate==null || "".equals(endDate))){
//                // 默认查询30天
//                Date date1 = CalendarUtil.findDatesBySpace(new Date(),6);
//                Date date = new Date(new Date().getTime()+(24*60*60)*1000);
//                startDate = CalendarUtil.formatDateByString(date1);
//                endDate = CalendarUtil.formatDateByString(date);
//                paraMap.put("startDate",startDate);
//                paraMap.put("endDate",endDate);
//            }

            PageInfo page = userService.queryAreaDetail(paraMap);
            if(page!=null && page.getList().size()>0){
                return  new JsonResult(0,"查询成功",page);
            }else{
                return new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败，请稍后重试");
        }
    }

}
