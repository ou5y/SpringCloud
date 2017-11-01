package com.azcx9d.business.controller;

import com.azcx9d.business.base.HQBaseController;
import com.azcx9d.business.dto.BusinessMemberDto;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.BBusinessMemberService;
import com.azcx9d.business.service.BOrderFormService;
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
 * Created by HuangQing on 2017/4/2 0002 14:13.
 */
@Api(value = "商家会员",description="我的会员")
@RestController
@RequestMapping("/v1/bApi/member")
public class BMemberApiController extends HQBaseController {

//    @Autowired
//    private BOrderFormService bOrderFormService;

    @Autowired
    private BBusinessMemberService memberService;

    @ApiOperation(value = "我的会员列表", notes = "会员列表", response = BusinessMemberDto.class)
    @RequestMapping(value = "/myMembers", method = RequestMethod.GET)
    public JsonResult completionList(@RequestHeader("token") String token,
                                     @ApiParam(required = true,value = "当前页",defaultValue = "1")@RequestParam("currentPage") int currentPage,
                                     @ApiParam(required = true,value = "每页条数",defaultValue = "20")@RequestParam("pageSize") int pageSize,
                                     @ApiParam(required = true,value = "商铺id")@RequestParam("businessId") int businessId){
        ParaMap paraMap = super.getParaMap();
        paraMap.put("userId",super.getUserId());    //获取店主id，间接得到店铺id，然后查询店铺id所在的中间表中的所有对应的用户id再关联用户表
        paraMap.put("businessId", businessId);

        Page page = new Page();
        page.setCurrentPage(currentPage);
        page.setPageSize(pageSize);

        try {
            page = memberService.selectMyMembersByUserId(page,paraMap);
            if (page.getPageList().size() > 0) {
                return new JsonResult(0, "查询成功", page);
            } else {
                return new JsonResult(4, "暂无数据");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2, "请求超时或者服务器出错啦，稍后再试试~~");
        }
    }
}
