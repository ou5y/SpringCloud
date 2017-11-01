package com.customer.web.my;

import com.customer.dto.IncomeChangeDto;
import com.customer.dto.IncomeDetailDto;
import com.customer.dto.IncomeTotalDto;
import com.customer.dto.IncomeUserDto;
import com.customer.entity.ParaMap;
import com.customer.security.CheckPermission;
import com.customer.service.my.MyBusinessService;
import com.customer.util.DateUtil;
import com.customer.util.JsonResult;
import com.customer.web.BaseController;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@Api(value = "推广业务",description = "推广业务")
@RestController
@RequestMapping(value = "/v1/cApi/myBusiness")
public class MyBusinessController extends BaseController{

    @Autowired
    private MyBusinessService myBusinessService;

    @CheckPermission
    @ApiOperation(value = "总收益", notes = "总收益", response = IncomeTotalDto.class)
    @RequestMapping(value = "/getIncomeTotalList", method = RequestMethod.GET)
    public JsonResult getIncomeTotalList(@RequestHeader("token") String token){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            List<IncomeTotalDto> list = myBusinessService.getIncomeTotalList(paraMap);
            if(list.size() > 0){
                return new JsonResult(0,"查询成功！", list);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @CheckPermission
    @ApiOperation(value = "收益走势", notes = "收益走势", response = IncomeChangeDto.class)
    @RequestMapping(value = "/getIncomeChangeList", method = RequestMethod.GET)
    public JsonResult getIncomeChangeList(@RequestHeader("token") String token,
                                          @ApiParam(required = true, value = "级别id(0:全部)", defaultValue = "0") @RequestParam("levelId") int levelId,
                                          @ApiParam(value = "起始日期 格式:2017-05-01") @RequestParam(value = "startDate", required = false) String startDate,
                                          @ApiParam(value = "截止日期 格式:2017-05-01") @RequestParam(value = "endDate", required = false) String endDate){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());
            if (StringUtils.isBlank(startDate)){
                //查询最近7天的数据(包含起止日期, 所以减6)
                paraMap.put("startDate", DateUtil.getAfterDayDay(-6));
            }
            if (StringUtils.isBlank(endDate)){
                paraMap.put("endDate", DateUtil.getDay());
            }

            List<IncomeChangeDto> list = myBusinessService.getIncomeChangeList(paraMap);
            if(list.size() > 0){
                return new JsonResult(0,"查询成功！", list);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @CheckPermission
    @ApiOperation(value = "收益用户列表", notes = "收益用户列表(1,高级推广员 2,推广员 可见 商家总数量totalNums)", response = IncomeUserDto.class)
    @RequestMapping(value = "/getIncomeUserList",method = RequestMethod.GET)
    public JsonResult getIncomeUserList(@RequestHeader("token") String token,
                                        @ApiParam(required=true,value="级别id") @RequestParam("levelId") int levelId,
                                        @ApiParam(required=false,value="搜索内容") @RequestParam(value = "content", required = false) String content,
                                        @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                        @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            PageInfo<IncomeUserDto> page = myBusinessService.getIncomeUserList(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功！", page);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @CheckPermission
    @ApiOperation(value = "用户收益详情", notes = "用户收益详情", response = IncomeDetailDto.class)
    @RequestMapping(value = "/getIncomeDetailList", method = RequestMethod.GET)
    public JsonResult getIncomeDetailList(@RequestHeader("token") String token,
                                          @ApiParam(required = true,value="用户id") @RequestParam("id") int id,
                                          @ApiParam(required=true,value="级别id") @RequestParam("levelId") int levelId,
                                          @ApiParam(value = "起始日期 格式:2017-05-01") @RequestParam(value = "startDate", required = false) String startDate,
                                          @ApiParam(value = "截止日期 格式:2017-05-01") @RequestParam(value = "endDate", required = false) String endDate,
                                          @ApiParam(required = true,value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                          @ApiParam(required = true,value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());
            if (StringUtils.isBlank(startDate)){
                paraMap.put("startDate", "");
            }
            if (StringUtils.isBlank(endDate)){
                paraMap.put("endDate", "");
            }

            PageInfo<IncomeDetailDto> page = myBusinessService.getIncomeDetailList(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功！", page);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    // 我的代理
    @ApiOperation(value = "",notes = "")
    @RequestMapping(value = "",method = RequestMethod.GET)
    public JsonResult myBusiness(){
        return null;
    }

}
