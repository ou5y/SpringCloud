package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.MyPointDto;
import com.azcx9d.business.dto.UserDto;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BPersonService;
import com.azcx9d.business.service.BUserService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by chenxl on 2017/5/24 0024.
 */
@Api(value = "个人中心",description="积分善点推荐奖励")
@RestController
@RequestMapping("/v1/bApi/person")
public class BPersonController extends HQBaseController {

    @Autowired
    private BPersonService bPersonService;

    @Autowired
    private BUserService bUserService;

    @ApiOperation(value = "我的积分", notes = "我的积分", response=MyPointDto.class)
    @RequestMapping(value = "/getMyIntegral", method = RequestMethod.GET)
    public JsonResult getMyIntegral(@RequestHeader("token") String token,
                                    @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                    @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Map map = bPersonService.getMyIntegral(paraMap);
            if (((Page)map.get("page")).getPageList().size() > 0) {
                return new JsonResult(0, "查询成功", map);
            } else {
                return new JsonResult(4, "暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "我的善点", notes = "我的善点", response=MyPointDto.class)
    @RequestMapping(value = "/getMyShandian", method = RequestMethod.GET)
    public JsonResult getMyShandian(@RequestHeader("token") String token,
                                    @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                    @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Map map = bPersonService.getMyShandian(paraMap);
            if (((Page)map.get("page")).getPageList().size() > 0) {
                return new JsonResult(0, "查询成功", map);
            } else {
                return new JsonResult(4, "暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "我的推荐奖励善点", notes = "我的推荐奖励善点", response=MyPointDto.class)
    @RequestMapping(value = "/getMyRecommendShandian", method = RequestMethod.GET)
    public JsonResult getMyRecommendShandian(@RequestHeader("token") String token,
                                             @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                             @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Map map = bPersonService.getMyRecommendShandian(paraMap);
            if (((Page)map.get("page")).getPageList().size() > 0) {
                return new JsonResult(0, "查询成功", map);
            } else {
                return new JsonResult(4, "暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "我的积分（新版）", notes = "我的积分（新版）", response=MyPointDto.class)
    @RequestMapping(value = "/queryMyIntegral", method = RequestMethod.GET)
    public JsonResult queryMyIntegral(@RequestHeader("token") String token,
                                    @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                    @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Map map = bPersonService.queryMyIntegral(paraMap);
            if (((Page)map.get("page")).getPageList().size() > 0) {
                return new JsonResult(0, "查询成功", map);
            } else {
                return new JsonResult(4, "暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "我的善点（新版）", notes = "我的善点（新版）", response=MyPointDto.class)
    @RequestMapping(value = "/queryMyShandian", method = RequestMethod.GET)
    public JsonResult queryMyShandian(@RequestHeader("token") String token,
                                    @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                    @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Map map = bPersonService.queryMyShandian(paraMap);
            if (((Page)map.get("page")).getPageList().size() > 0) {
                return new JsonResult(0, "查询成功", map);
            } else {
                return new JsonResult(4, "暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "被动善点（新版）", notes = "被动善点（新版）", response=MyPointDto.class)
    @RequestMapping(value = "/queryPassiveShandian", method = RequestMethod.GET)
    public JsonResult queryPassiveShandian(@RequestHeader("token") String token,
                                             @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                             @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("currentPage", currentPage);
            paraMap.put("pageSize", pageSize);

            Map map = bPersonService.queryPassiveShandian(paraMap);
            if (((Page)map.get("page")).getPageList().size() > 0) {
                return new JsonResult(0, "查询成功", map);
            } else {
                return new JsonResult(4, "暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ApiOperation(value = "商家信息", notes = "商家详细",response=UserDto.class)
    public JsonResult<UserDto> detail(@RequestHeader("token") String token)
    {

        int id= super.getUserId();

        try {
            return bUserService.detail(id);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }

    }

}
