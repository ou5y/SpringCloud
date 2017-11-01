package com.customer.web;

import com.customer.dto.TeamManageBusinessDto;
import com.customer.dto.TeamManageDetailDto;
import com.customer.dto.TeamManageRoleDto;
import com.customer.dto.TeamManageUserDto;
import com.customer.entity.ParaMap;
import com.customer.service.CTeamManageService;
import com.customer.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxl on 2017/7/15.
 */
@Api(value = "团队管理", description = "团队管理")
@RestController
@RequestMapping(value = "/v1/cApi/teamManage")
public class CTeamManageController extends BaseController {

    @Autowired
    private CTeamManageService cTeamManageService;

    @ApiOperation(notes = "团队管理角色列表", value = "团队管理角色列表", response = TeamManageRoleDto.class)
    @RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
    public JsonResult getRoleList(@RequestHeader("token") String token) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());
            paraMap.put("uIds", new String[]{super.getTokenModel().getUserId() + ""});

            List<TeamManageRoleDto> list = cTeamManageService.getRoleList(paraMap);
            if (list.size() > 0) {
                return new JsonResult(0, "查询成功!", list);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "用户列表", value = "用户列表", response = TeamManageUserDto.class)
    @RequestMapping(value = "/getUserList", method = RequestMethod.POST)
    public JsonResult getUserList(@RequestHeader("token") String token,
                                  @ApiParam(required = true, value = "uIds") @RequestParam("uIds") String uIds,
                                  @ApiParam(required = false, value = "搜索内容") @RequestParam(value = "content", required = false) String content,
                                  @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                  @ApiParam(required = true, value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());
            String[] strs = uIds.split(",");
            List<String> arr = new ArrayList();
            for (String s : strs) {
                arr.add(s);
            }
            paraMap.put("uIds", arr);

            PageInfo<TeamManageUserDto> page = cTeamManageService.getUserList(paraMap);
            if (page.getList().size() > 0) {
                return new JsonResult(0, "查询成功!", page);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "收益详情列表", value = "收益详情列表", response = TeamManageDetailDto.class)
    @RequestMapping(value = "/getDetailList", method = RequestMethod.GET)
    public JsonResult getDetailList(@RequestHeader("token") String token,
                                    @ApiParam(required = true, value = "用户id") @RequestParam("userId") String userId,
                                    @ApiParam(value = "起始日期 格式:2017-05-01") @RequestParam(value = "startDate", required = false) String startDate,
                                    @ApiParam(value = "截止日期 格式:2017-05-01") @RequestParam(value = "endDate", required = false) String endDate,
                                    @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                    @ApiParam(required = true, value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("queryId", userId);

            PageInfo<TeamManageDetailDto> page = cTeamManageService.getDetailList(paraMap);
            if (page.getList().size() > 0) {
                return new JsonResult(0, "查询成功!", page);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "商家列表", value = "商家列表", response = TeamManageBusinessDto.class)
    @RequestMapping(value = "/getBusinessList", method = RequestMethod.GET)
    public JsonResult getBusinessList(@RequestHeader("token") String token,
                                      @ApiParam(required = true, value = "用户id") @RequestParam("userId") String userId,
                                      @ApiParam(value = "起始日期 格式:2017-05-01") @RequestParam(value = "startDate", required = false) String startDate,
                                      @ApiParam(value = "截止日期 格式:2017-05-01") @RequestParam(value = "endDate", required = false) String endDate,
                                      @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam("currentPage") int currentPage,
                                      @ApiParam(required = true, value = "记录数", defaultValue = "20") @RequestParam("pageSize") int pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("queryId", userId);

            PageInfo<TeamManageBusinessDto> page = cTeamManageService.getBusinessList(paraMap);
            if (page.getList().size() > 0) {
                return new JsonResult(0, "查询成功!", page);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }
}
