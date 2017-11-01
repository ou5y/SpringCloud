package com.customer.web;

import com.customer.dto.CollectDto;
import com.customer.entity.ParaMap;
import com.customer.service.CCollectService;
import com.customer.util.JsonResult;
import com.customer.util.TokenModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

//@Api(value = "收藏", description = "我的收藏")
//@RestController
@RequestMapping(value = "/v1/cApi/collect")
public class CCollectController extends BaseController{

    @Autowired
    private CCollectService cCollectService;

    @ApiOperation(value = "我的收藏",notes = "我的收藏",response = CollectDto.class)
    @RequestMapping(value = "/myCollect",method = RequestMethod.GET)
    public JsonResult myColllect(@RequestHeader("token") String token,
                                 @ApiParam(required = true,value = "类型") @RequestParam(defaultValue = "0") Integer type,
                                 @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                 @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){
        try {
            TokenModel model = getTokenModel();
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId",model.getId());
            PageInfo page = new PageInfo();
            page = cCollectService.queryMyCollect(page,paraMap,currentPage,pageSize);
            return new JsonResult(0,"查询成功",page);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询我的收藏失败");
        }
    }

    @ApiOperation(value = "添加收藏店铺/商品",notes = "添加店铺/商品到收藏的店铺")
    @RequestMapping(value = "/addCollect",method = RequestMethod.POST)
    public JsonResult addCollect(@RequestHeader("token") String token,
                                 @ApiParam(required = true,value = "0：商品  1：店铺") @RequestParam int type,
                                 @ApiParam(required = true,value = "名称") @RequestParam String name,
                                 @ApiParam(required = true,value = "商品/店铺id") @RequestParam long gbId,
                                 @ApiParam(required = true,value = "图片地址") @RequestParam String imgUrl,
                                 @ApiParam(required = false,value = "商品价格") @RequestParam(required = false) String price){
        try {
            ParaMap paraMap = super.getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getId());
            int count = cCollectService.addCollect(paraMap);
            if(count>0){
                return new JsonResult(0,"收藏成功");
            }else{
                return new JsonResult(2,"收藏失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"收藏失败，请稍后重试");
        }
    }

    @ApiOperation(value = "删除收藏",notes = "删除收藏的店铺/商品")
    @RequestMapping(value = "/deleteCollect",method = RequestMethod.DELETE)
    public JsonResult deleteCollect(@RequestHeader("token") String token,
                                    @ApiParam(required = true,value = "id") @RequestParam long id){
        try {
            ParaMap paraMap = super.getParaMap();
            int count = cCollectService.deleteCollect(paraMap);
            if(count>0){
                return new JsonResult(0,"删除成功");
            }else{
                return new JsonResult(2,"删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"删除失败，请稍后重试");
        }
    }

}
