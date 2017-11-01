package com.customer.web.my;

import com.customer.entity.ParaMap;
import com.customer.service.CShoppingCartService;
import com.customer.util.JsonResult;
import com.customer.util.TokenModel;
import com.customer.web.BaseController;
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

@Api(value = "购物车", description = "购物车")
@RestController
@RequestMapping(value = "/v1/cApi/shoppingCart")
public class CShoppingCartController extends BaseController {

    @Autowired
    private CShoppingCartService cShoppingCartService;

    @ApiOperation(value = "我的购物车",notes = "我的购物车")
    @RequestMapping(value = "/myShoppingCart",method = RequestMethod.GET)
    public JsonResult myShoppingCart(@RequestHeader("token") String token,
                                     @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer currentPage,
                                     @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){
        try {
            TokenModel model = getTokenModel();
            Map<String,Object> params = new HashMap<String,Object>();
            params.put("userId",model.getId());
            PageInfo page = new PageInfo();
            page = cShoppingCartService.queryMyShoppingCart(page,params,currentPage,pageSize);
            if(page.getList()!=null && page.getList().size()>0){
                return  new JsonResult(0,"查询成功",page);
            }else {
                return  new JsonResult(4,"暂无数据",page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"查询购物车失败，请稍后重试");
        }
    }

    @ApiOperation(value = "添加到购物车",notes = "添加到购物车")
    @RequestMapping(value = "/addShoppingCart",method = RequestMethod.POST)
    public JsonResult addShopperingCart(@RequestHeader("token") String token,
                                        @ApiParam(required = true,value = "商品名称") @RequestParam String goodsName,
                                        @ApiParam(required = true,value = "商品id") @RequestParam long goodsId,
                                        @ApiParam(required = true,value = "数量") @RequestParam long quantity,
                                        @ApiParam(required = true,value = "图片地址") @RequestParam String imgUrl,
                                        @ApiParam(required = false,value = "商品价格") @RequestParam String price){
        try {
            ParaMap paraMap = super.getParaMap();
            TokenModel model = getTokenModel();
            paraMap.put("userId",model.getId());
            int count = cShoppingCartService.addShoppingCart(paraMap);
            if(count>0){
                return new JsonResult(0,"添加成功");
            }else{
                return new JsonResult(2,"添加失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"添加购物车，请稍后重试");
        }
    }

    @ApiOperation(value = "删除购物车",notes = "删除购物车收藏的商品")
    @RequestMapping(value = "/deleteShoppingCart",method = RequestMethod.DELETE)
    public JsonResult addShopperingCart(@RequestHeader("token") String toke,
                                        @ApiParam(required = true,value = "id") @RequestParam long id){
        try {
            ParaMap paraMap = super.getParaMap();
            int count  = cShoppingCartService.deleteShoppingCart(paraMap);
            if(count>0){
                return new JsonResult(0,"删除成功");
            }else{
                return new JsonResult(0,"删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"删除失败，请稍后重试");
        }
    }

    @ApiOperation(value = "修改数量",notes = "修改购物车商品数量")
    @RequestMapping(value = "editShoppingCart",method = RequestMethod.POST)
    public JsonResult editShopperingCart(@RequestHeader("token") String toke,
                                         @ApiParam(required = true,value = "id") @RequestParam long id,
                                         @ApiParam(required = true,value = "数量") @RequestParam long quantity){
        try {
            ParaMap paraMap = super.getParaMap();
            int count  = cShoppingCartService.editShoppingCart(paraMap);
            if(count>0){
                return new JsonResult(0,"删除成功");
            }else{
                return new JsonResult(0,"删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2,"删除失败，请稍后重试");
        }
    }

}
