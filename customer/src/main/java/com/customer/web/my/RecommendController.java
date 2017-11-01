package com.customer.web.my;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.customer.dto.AgencyStatisticsDetailDto;
import com.customer.dto.RecommendListDto;
import com.customer.dto.RoleListDto;
import com.customer.entity.ParaMap;
import com.customer.entity.User;
import com.customer.service.my.RecommendService;
import com.customer.util.CalendarUtil;
import com.customer.util.JSONStringUtil;
import com.customer.util.JsonResult;
import com.customer.util.TokenModel;
import com.customer.web.BaseController;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Api(value = "推荐记录",description = "推荐记录")
@RestController
@RequestMapping(value = "/v1/cApi/recommend")
public class RecommendController extends BaseController{

    @Autowired
    private RecommendService recommendService;

    @ApiOperation(value = "角色列表",notes = "角色列表",response = RoleListDto.class)
    @RequestMapping(value = "/roleList",method = RequestMethod.GET)
    public JsonResult roleList(@RequestHeader(value = "token") String token){
        try {
            ParaMap paraMap = super.getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getId());
            List<Map<String,Object>> roleList = recommendService.queryRoleList(paraMap);
            if(roleList!=null && roleList.size()>0) {
                return new JsonResult(0, "查询角色列表成功", roleList);
            }else{
                return new JsonResult(4, "查询角色列表成功", roleList);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败,请稍后重试");
        }
    }

    @ApiOperation(value = "推荐记录列表",notes = "推荐记录列表",response = RecommendListDto.class)
    @RequestMapping(value = "/recommendList",method = RequestMethod.GET)
    public JsonResult recommendList(@RequestHeader(value = "token") String token,
                                    @ApiParam(value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                    @ApiParam(value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize,
                                    @ApiParam(value = "类型,0:会员 1:高级推广员  2:推广员 6:总监 7:副总  9:服务商") @RequestParam(required = false) Integer level,
                                    @ApiParam(required = false,value = "开始日期,yyyy-MM-dd") @RequestParam(required = false) String startDate,
                                    @ApiParam(required = false,value = "结束日期,yyyy-MM-dd") @RequestParam(required = false) String endDate){
        JsonResult result = null;
        try {
            ParaMap paraMap = super.getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getId());

            if((startDate==null && endDate!=null)||(startDate!=null && endDate==null)){
                return new JsonResult(2,"开始日期或结束日期不能为空");
            }
            if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
                Date date = new Date(CalendarUtil.formatDateByString(endDate).getTime()+(24*60*60)*1000);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("endDate",endDate);
            }
            PageInfo page = recommendService.queryRecommendList(paraMap,currentPage,pageSize);
            if(page.getList().size()>0){
                return new JsonResult(0,"查询推荐记录成功",page);
            }else{
                return new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败,请稍后重试");
        }
    }

    @ApiOperation(value = "收益详情",notes = "收益详情",response = AgencyStatisticsDetailDto.class)
    @RequestMapping(value = "/pointDetail",method = RequestMethod.GET)
    public JsonResult pointDetail(@RequestHeader(value = "token") String token,
                                  @ApiParam(value = "用户id") @RequestParam Integer userId,
                                  @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                  @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize,
                                  @ApiParam(required = false,value = "开始日期,yyyy-MM-dd") @RequestParam(required = false) String startDate,
                                  @ApiParam(required = false,value = "结束日期,yyyy-MM-dd") @RequestParam(required = false) String endDate){

        try {
            ParaMap paraMap = super.getParaMap();
            if((startDate==null && endDate!=null)||(startDate!=null && endDate==null)){
                return new JsonResult(2,"开始日期或结束日期不能为空");
            }
            if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
                Date date = new Date(CalendarUtil.formatDateByString(endDate).getTime()+(24*60*60)*1000);
                endDate = CalendarUtil.format2String(date);
                paraMap.put("endDate",endDate);
            }
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("fromId",userId);

            PageInfo page = recommendService.queryPointDetail(paraMap,currentPage,pageSize);
            if(page.getList()!=null && page.getList().size()>0){
                return  new JsonResult(0,"查询收益详情成功",page);
            }else {
                return  new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败,请稍后重试");
        }
    }

}
