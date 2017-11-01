package com.azcx9d.business.controller;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.AgencyStatisticsDetailDto;
import com.azcx9d.business.dto.RecommendListDto;
import com.azcx9d.business.dto.RoleListDto;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BUserService;
import com.azcx9d.business.service.RecommendService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.CalendarUtil;
import com.azcx9d.common.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/24 0024.
 */
@Api(value = "推荐",description = "推荐记录")
@RestController
@RequestMapping(value = "v1/bApi/recommend")
public class BRecommendController extends HQBaseController {

    @Autowired
    private RecommendService recommendService;

//    @ApiOperation(value = "角色列表",notes = "角色列表",response = RoleListDto.class)
//    @RequestMapping(value = "/roleList",method = RequestMethod.GET)
//    public JsonResult roleList(@RequestHeader(value = "token") String token){
//        try {
//            ParaMap paraMap = super.getParaMap();
//            TokenModel model = getTokenModel();
//            paraMap.put("userId",model.getUserId());
//            List<Map<String,Object>> roleList = recommendService.queryRoleList(paraMap);
//            if(roleList!=null && roleList.size()>0) {
//                return new JsonResult(0, "查询角色列表成功", roleList);
//            }else{
//                return new JsonResult(4, "查询角色列表成功", roleList);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new JsonResult(2,"查询失败,请稍后重试");
//        }
//    }

    @ApiOperation(value = "推荐记录列表",notes = "推荐记录列表",response = RecommendListDto.class)
    @RequestMapping(value = "/recommendList",method = RequestMethod.GET)
    public JsonResult recommendList(@RequestHeader(value = "token") String token,
                                    @ApiParam(value = "页码") @RequestParam(defaultValue = "1") int currentPage,
                                    @ApiParam(value = "查询条数") @RequestParam(defaultValue = "20") int pageSize,
//                                    @ApiParam(value = "类型,0:会员 1:高级推广员  2:推广员 6:总监 7:副总  9:服务商") @RequestParam(required = false) Integer level,
                                    @ApiParam(required = false,value = "开始日期,yyyy-MM-dd") @RequestParam(required = false) String startDate,
                                    @ApiParam(required = false,value = "结束日期,yyyy-MM-dd") @RequestParam(required = false) String endDate){
        JsonResult result = null;
        try {
            ParaMap paraMap = super.getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
            paraMap.put("pageSize",pageSize);

            if((startDate==null && endDate!=null)||(startDate!=null && endDate==null)){
                return new JsonResult(2,"开始日期或结束日期不能为空");
            }
            if(startDate!=null && !"".equals(startDate) && endDate!=null && !"".equals(endDate)){
                Date date = new Date(CalendarUtil.formatDateByString(endDate).getTime()+(24*60*60)*1000);
                endDate = CalendarUtil.formatDateByString(date);
                paraMap.put("endDate",endDate);
            }
            Page page = new Page(currentPage,pageSize);
            page = recommendService.queryRecommendList(page,paraMap,currentPage,pageSize);
            page.init();
            if(page.getPageList()!=null && page.getPageList().size()>0){
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
            paraMap.put("pageSize",pageSize);
            paraMap.put("fromId",userId);

            Page page = new Page(currentPage,pageSize);
            page = recommendService.queryPointDetail(page,paraMap,currentPage,pageSize);
            page.init();
            if(page.getPageList()!=null && page.getPageList().size()>0){
                return  new JsonResult(0,"查询收益详情成功",page);
            }else{
                return  new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询失败,请稍后重试");
        }
    }

}
