package com.customer.web;

import com.customer.dto.CScoreChangeDto;
import com.customer.entity.ParaMap;
import com.customer.service.CScoreChangeService;
import com.customer.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@Api(value = "善点/积分变动明", description = "善点/积分变动明细表")
@ApiIgnore
@RestController
@RequestMapping(value = "/v1/cApi/scoreChange")
public class CScoreChangeController extends BaseController {

    @Autowired
    private CScoreChangeService cScoreChangeService;

    @ApiOperation(value = "积分列表", notes = "积分列表", response = CScoreChangeDto.class)
    @RequestMapping(value = "/getJifenList", method = RequestMethod.GET)
    public JsonResult getJifenList(@RequestHeader("token") String token,
                                   @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                   @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            PageInfo<CScoreChangeDto> page = cScoreChangeService.getJifenList(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功！", page);
            }else {
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "善点列表", notes = "善点列表", response = CScoreChangeDto.class)
    @RequestMapping(value = "/getShandianList", method = RequestMethod.GET)
    public JsonResult getShandianList(@RequestHeader("token") String token,
                                      @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                      @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            PageInfo<CScoreChangeDto> page = cScoreChangeService.getShandianList(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功！", page);
            }else {
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "推荐奖励善点列表", notes = "推荐奖励善点列表", response = CScoreChangeDto.class)
    @RequestMapping(value = "/getRecommendShandianList", method = RequestMethod.GET)
    public JsonResult getRecommendShandianList(@RequestHeader("token") String token,
                                       @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                       @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            PageInfo<CScoreChangeDto> page = cScoreChangeService.getRecommendShandianList(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功！", page);
            }else {
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }
}
