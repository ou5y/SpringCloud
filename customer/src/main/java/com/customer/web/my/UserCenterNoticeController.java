package com.customer.web.my;

import com.customer.dto.my.UserCenterNotice;
import com.customer.entity.ParaMap;
import com.customer.service.SystemMessageService;
import com.customer.util.JsonResult;
import com.customer.util.TokenModel;
import com.customer.web.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Administrator on 2017/7/31 0031.
 */

@Api(value = "用户中心通知",description = "用户中心通知")
@RestController
@RequestMapping(value = "/v1/cApi/centerUser")
public class UserCenterNoticeController extends BaseController {

    @Autowired
    private SystemMessageService systemMessageService;

    @ApiOperation(value = "用户中心通知",notes = "用户中心通知",response = UserCenterNotice.class)
    @RequestMapping(value = "/notice",method = RequestMethod.GET)
    public JsonResult notice(@RequestHeader(value = "token") String token,
                             @ApiParam(value = "appType 1:用户端  2：商户端'") @RequestParam Integer appType){
        ParaMap paraMap = getParaMap();
        try {
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getId());
        } catch (Exception e) {
            return new JsonResult(2,"请重新登录");
        }
        UserCenterNotice userCenterNotice = systemMessageService.queryUserCenterNotice(paraMap);
        if(userCenterNotice!=null){
            return new JsonResult(0,"查询成功",userCenterNotice);
        }else{
            return new JsonResult(4,"暂无数据");
        }
    }

}
