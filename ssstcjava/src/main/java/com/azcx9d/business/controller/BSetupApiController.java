package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.CommonProblemDto;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BSetupService;
import com.azcx9d.common.entity.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by chenxl on 2017/5/23 0023.
 */
@Api(value = "设置", description = "设置")
@RestController
@RequestMapping("/v1/bApi/setup")
public class BSetupApiController extends HQBaseController {

    @Autowired
    private BSetupService bSetupService;

    @ApiOperation(notes = "修改头像", value = "修改头像")
    @RequestMapping(value = "/updateAvatar", method = RequestMethod.POST)
    public JsonResult updateAvatar(@RequestHeader("token") String token,
                                   @ApiParam(required = true, value = "头像") @RequestParam("avatar") String avatar) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            paraMap.put("avatar", avatar);
            int count = bSetupService.updateAvatar(paraMap);
            if(count > 0){
                return new JsonResult(0, "修改成功！");
            }else{
                return new JsonResult(2, "修改失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "修改昵称", value = "修改昵称")
    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
    public JsonResult updateUserName(@RequestHeader("token") String token,
                                     @ApiParam(required = true, value = "昵称") @RequestParam("userName") String userName) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            paraMap.put("userName", userName);
            int count = bSetupService.updateUserName(paraMap);
            if(count > 0){
                return new JsonResult(0, "修改成功！");
            }else{
                return new JsonResult(2, "修改失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "添加用户建议反馈",notes = "添加用户建议反馈")
    @RequestMapping(value = "/saveUserSuggest",method = RequestMethod.POST)
    public JsonResult saveUserSuggest(@RequestHeader("token") String token,
                                      @ApiParam(required=true,value="建议内容") @RequestParam("content") String content){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            paraMap.put("content", content);
            int count = bSetupService.saveUserSuggest(paraMap);
            if(count > 0){
                return new JsonResult(0, "添加成功");
            }else{
                return new JsonResult(2, "添加失败");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "关于,常见问题", notes = "关于,常见问题<br/>类型(1:关于全团了, 2:操作类问题, 3:商户类问题, 4:身份类问题, 5:银行卡类问题)", response = CommonProblemDto.class)
    @RequestMapping(value = "/getCommonProblem", method = RequestMethod.GET)
    public JsonResult getCommonProblem(@RequestHeader("token") String token){
        try {
            ParaMap paraMap = super.getParaMap();

            List<CommonProblemDto> list = bSetupService.getCommonProblem(paraMap);
            if(list.size() > 0){
                return new JsonResult(0,"查询成功！", list);
            }else {
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }
}
