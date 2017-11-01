package com.azcx9d.business.controller;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.my.UserCenterNotice;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BSystemMessageService;
import com.azcx9d.common.entity.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/7/31 0031.
 */
@Api(value = "用户中心通知", description = "用户中心通知")
@RestController
@RequestMapping("/v1/bApi/centerUser")
public class BUserCenterNoticeController  extends HQBaseController {

    @Autowired
    private BSystemMessageService systemMessageService;

    @ApiOperation(value = "用户中心通知",notes = "用户中心通知",response = UserCenterNotice.class)
    @RequestMapping(value = "/notice",method = RequestMethod.GET)
    public JsonResult notice(@RequestHeader(value = "token") String token,
                             @ApiParam(value = "appType 1:用户端  2：商户端'") @RequestParam Integer appType){
        ParaMap paraMap = getParaMap();
        try {
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getUserId());
        } catch (Exception e) {
            return new JsonResult(-1,"请重新登录");
        }
        UserCenterNotice userCenterNotice = systemMessageService.queryUserCenterNotice(paraMap);
        if(userCenterNotice!=null){
            return new JsonResult(0,"查询成功",userCenterNotice);
        }else{
            return new JsonResult(4,"暂无数据");
        }
    }
}
