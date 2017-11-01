package com.customer.web;

import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.service.CIncomeService;
import com.customer.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenxl on 2017/7/7 0011.
 */
@Api(value = "收益管理", description = "收益管理")
@RestController
@RequestMapping(value = "/v1/cApi/income")
public class CIncomeController extends BaseController {

    @Autowired
    private CIncomeService cIncomeService;

    @ApiOperation(notes = "收益管理首页", value = "收益管理首页", response = CTotalIncomeDto.class)
    @RequestMapping(value = "/getTotalIncome", method = RequestMethod.GET)
    public JsonResult getTotalIncome(@RequestHeader("token") String token) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            CTotalIncomeDto cTotalIncomeDto = cIncomeService.getTotalIncome(paraMap);
            if (null != cTotalIncomeDto) {
                return new JsonResult(0, "查询成功!", cTotalIncomeDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "推荐消费奖励列表", value = "推荐消费奖励列表", response = CRecommendIncomeListDto.class)
    @RequestMapping(value = "/getRecommendIncomeList", method = RequestMethod.GET)
    public JsonResult getRecommendIncomeList(@RequestHeader("token") String token,
                                             @ApiParam(required = true, value = "1,今日收益 2,历史收益", defaultValue = "1") @RequestParam("type") Integer type,
                                             @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                             @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            CRecommendIncomeListDto cRecommendIncomeListDto = cIncomeService.getRecommendIncomeList(paraMap);
            if (cRecommendIncomeListDto != null && cRecommendIncomeListDto.getPage().getList().size() > 0) {
                return new JsonResult(0, "查询成功!", cRecommendIncomeListDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "用户推荐消费奖励列表", value = "用户推荐消费奖励列表", response = CRecommendIncomeUserDto.class)
    @RequestMapping(value = "/getRecommendIncomeUser", method = RequestMethod.GET)
    public JsonResult getRecommendIncomeUser(@RequestHeader("token") String token,
                                             @ApiParam(required = true, value = "来源id") @RequestParam("fromId") String fromId,
                                             @ApiParam(value = "起始日期 格式:2017-05-01") @RequestParam(value = "startDate", required = false) String startDate,
                                             @ApiParam(value = "截止日期 格式:2017-05-01") @RequestParam(value = "endDate", required = false) String endDate,
                                             @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                             @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            PageInfo<CRecommendIncomeUserDto> page = cIncomeService.getRecommendIncomeUser(paraMap);
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

    @ApiOperation(notes = "业务奖励列表", value = "业务奖励列表", response = CBusinessIncomeListDto.class)
    @RequestMapping(value = "/getBusinessIncomeList", method = RequestMethod.GET)
    public JsonResult getBusinessIncomeList(@RequestHeader("token") String token,
                                            @ApiParam(required = true, value = "1,今日收益 2,历史收益", defaultValue = "1") @RequestParam("type") Integer type,
                                            @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                            @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            CBusinessIncomeListDto cBusinessIncomeListDto = cIncomeService.getBusinessIncomeList(paraMap);
            if (cBusinessIncomeListDto != null && cBusinessIncomeListDto.getPage().getList().size() > 0) {
                return new JsonResult(0, "查询成功!", cBusinessIncomeListDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "用户业务奖励列表", value = "用户业务奖励列表", response = CBusinessIncomeUserDto.class)
    @RequestMapping(value = "/getBusinessIncomeUser", method = RequestMethod.GET)
    public JsonResult getBusinessIncomeUser(@RequestHeader("token") String token,
                                            @ApiParam(required = true, value = "来源id") @RequestParam("fromId") String fromId,
                                            @ApiParam(value = "起始日期 格式:2017-05-01") @RequestParam(value = "startDate", required = false) String startDate,
                                            @ApiParam(value = "截止日期 格式:2017-05-01") @RequestParam(value = "endDate", required = false) String endDate,
                                            @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                            @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            PageInfo<CBusinessIncomeUserDto> page = cIncomeService.getBusinessIncomeUser(paraMap);
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

    @ApiOperation(notes = "配送收益列表", value = "配送收益列表", response = CDistributionIncomeListDto.class)
    @RequestMapping(value = "/getDistributionIncomeList", method = RequestMethod.GET)
    public JsonResult getDistributionIncomeList(@RequestHeader("token") String token,
                                                @ApiParam(required = true, value = "1,今日收益 2,历史收益", defaultValue = "1") @RequestParam("type") Integer type,
                                                @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                                @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            CDistributionIncomeListDto cDistributionIncomeListDto = cIncomeService.getDistributionIncomeList(paraMap);
            if (cDistributionIncomeListDto != null && cDistributionIncomeListDto.getPage().getList().size() > 0) {
                return new JsonResult(0, "查询成功!", cDistributionIncomeListDto);
            } else {
                return new JsonResult(4, "暂无数据!");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "用户配送收益列表", value = "用户配送收益列表", response = CDistributionIncomeUserDto.class)
    @RequestMapping(value = "/getDistributionIncomeUser", method = RequestMethod.GET)
    public JsonResult getDistributionIncomeUser(@RequestHeader("token") String token,
                                                @ApiParam(required = true, value = "来源id") @RequestParam("fromId") String fromId,
                                                @ApiParam(value = "起始日期 格式:2017-05-01") @RequestParam(value = "startDate", required = false) String startDate,
                                                @ApiParam(value = "截止日期 格式:2017-05-01") @RequestParam(value = "endDate", required = false) String endDate,
                                                @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                                @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            PageInfo<CDistributionIncomeUserDto> page = cIncomeService.getDistributionIncomeUser(paraMap);
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

    @ApiOperation(notes = "查询推荐消费奖励", value = "查询推荐消费奖励", response = CRecommendIncomeDto.class)
    @RequestMapping(value = "/queryRecommendIncome", method = RequestMethod.GET)
    public JsonResult queryRecommendIncome(@RequestHeader("token") String token,
                                           @ApiParam(value = "店铺名称") @RequestParam(value = "bName", required = false) String bName,
                                           @ApiParam(value = "查询日期 格式:2017-05-01") @RequestParam(value = "queryDate", required = false) String queryDate,
                                           @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                           @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            PageInfo<CRecommendIncomeDto> page = cIncomeService.queryRecommendIncome(paraMap);
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

    @ApiOperation(notes = "查询业务奖励", value = "查询业务奖励", response = CBusinessIncomeDto.class)
    @RequestMapping(value = "/queryBusinessIncome", method = RequestMethod.GET)
    public JsonResult queryBusinessIncome(@RequestHeader("token") String token,
                                          @ApiParam(value = "店铺名称") @RequestParam(value = "bName", required = false) String bName,
                                          @ApiParam(value = "查询日期 格式:2017-05-01") @RequestParam(value = "queryDate", required = false) String queryDate,
                                          @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                          @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            PageInfo<CBusinessIncomeDto> page = cIncomeService.queryBusinessIncome(paraMap);
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

    @ApiOperation(notes = "查询配送收益", value = "查询配送收益", response = CDistributionIncomeDto.class)
    @RequestMapping(value = "/queryDistributionIncome", method = RequestMethod.GET)
    public JsonResult queryDistributionIncome(@RequestHeader("token") String token,
                                              @ApiParam(value = "店铺名称") @RequestParam(value = "bName", required = false) String bName,
                                              @ApiParam(value = "查询日期 格式:2017-05-01") @RequestParam(value = "queryDate", required = false) String queryDate,
                                              @ApiParam(required = true, value = "页码", defaultValue = "1") @RequestParam(defaultValue = "1") Integer currentPage,
                                              @ApiParam(required = true, value = "查询条数", defaultValue = "20") @RequestParam(defaultValue = "20") Integer pageSize) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            PageInfo<CDistributionIncomeDto> page = cIncomeService.queryDistributionIncome(paraMap);
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
