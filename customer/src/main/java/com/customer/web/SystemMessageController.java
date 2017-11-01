package com.customer.web;

import com.customer.dto.MessageDetailDto;
import com.customer.dto.MessageListDto;
import com.customer.entity.ParaMap;
import com.customer.service.SystemMessageService;
import com.customer.util.JsonResult;
import com.customer.util.TokenModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/10 0010.
 */
@Api(value = "系统消息")
@RestController
@RequestMapping(value = "/v1/cApi/message")
public class SystemMessageController extends BaseController{

    @Autowired
    private SystemMessageService systemMessageService;

    @ApiOperation(value = "系统消息列表",notes = "系统消息列表,messageType:0:公告  1:系统消息  2:积分  3:善点",response = MessageListDto.class)
    @RequestMapping(value = "/messageList",method = RequestMethod.GET)
    public JsonResult messageList(@RequestHeader(value = "token") String token,
                                  @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                  @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){
        try {
            ParaMap paraMap = getParaMap();
            TokenModel model = getTokenModel();
            PageInfo page = new PageInfo();
            paraMap.put("userId",model.getUserId());
            page  = systemMessageService.messageList(paraMap,currentPage,pageSize);
            if(page.getList()!=null && page.getList().size()>0){
                return new JsonResult(0,"查询系统消息成功",page);
            }else{
                return new JsonResult(4,"查询系统消息成功",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询系统消息列表失败");
        }
    }

//    @ApiOperation(value = "系统消息详情",notes = "系统消息详情",response = MessageDetailDto.class)
//    @RequestMapping(value = "/messageDetail",method = RequestMethod.GET)
//    public JsonResult messageDetail(@RequestHeader(value = "token") String token,
//                                    @ApiParam(value = "id") @RequestParam Integer id){
//        MessageDetailDto d = new MessageDetailDto();
//        d.setTitle("APP更新了");
//        d.setContent("1.新增消息推送 2.新增多端登录  3.新增兑换消息推送讽德诵功反倒是发的上课了韩国减肥客户端双联开关恢复了和规范了可视对讲鹤骨鸡肤");
//        d.setCreateTime(new Date());
//        return new JsonResult(0,"",d);
//    }

}
