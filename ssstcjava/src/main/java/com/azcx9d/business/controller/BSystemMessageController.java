package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.entity.BSystemMessage;
import com.azcx9d.business.service.BSystemMessageService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;

/**
 * Created by HuangQing on 2017/4/6 0006 09:44.
 */
@Api(value = "系统消息", description = "系统消息")
@RestController
@RequestMapping("/v1/bApi/systemMessage")
//@ApiIgnore
public class BSystemMessageController extends HQBaseController {

    @Autowired
    private BSystemMessageService systemMessageService;

    @ApiOperation(value = "系统消息", notes = "系统消息列表,messageType:0:公告 1:系统消息 2:积分 3:善点",response = BSystemMessage.class)
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public JsonResult completionList(@RequestHeader("token") String token,
                                     @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                     @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize){
        HashMap<String, Object> paraMap = super.getParaMap();
        paraMap.put("userId",super.getUserId());    //获取店主id，间接得到店铺id，然后查询店铺id所在的中间表中的所有对应的用户id再关联用户表

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);

        try {
            page= systemMessageService.selectMessageList(page,paraMap);
            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功!", page);
            } else {
                return new JsonResult(4, "暂无数据!");
            }

        } catch (Exception e) {
            return new JsonResult(2,"服务器异常");
        }
    }
}
