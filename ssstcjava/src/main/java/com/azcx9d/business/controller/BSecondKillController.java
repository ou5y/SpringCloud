package com.azcx9d.business.controller;

import com.azcx9d.agency.entity.TokenModel;
import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.entity.BUser;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BSecondKillService;
import com.azcx9d.common.entity.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/6/2 0002.
 */

@Api(value = "秒杀",description = "秒杀")
@RestController
@RequestMapping(value = "v1/bApi/secondKill")
public class BSecondKillController extends HQBaseController {

    @Autowired
    private BSecondKillService bSecondKillService;

    @ApiOperation(value = "我要做秒杀",notes = "发布我要做秒杀")
    @RequestMapping(value = "/addSecondKill",method = RequestMethod.POST)
    public JsonResult addSecondKill(@RequestHeader("token") String token,
                                    @ApiParam(value = "联系电话") @RequestParam String phone,
                                    @ApiParam(value = "商品名称") @RequestParam String goodsName){

        try {
            TokenModel model= getTokenModel();
            int userId = model.getUserId();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", userId);
            int count = bSecondKillService.addSecondKillPlan(paraMap);
            if(count>0){
                return  new JsonResult(0,"提交成功");
            }else{
                return  new JsonResult(2,"提交失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return  new JsonResult(2,"提交失败,请稍后重试");
        }
    }
}
