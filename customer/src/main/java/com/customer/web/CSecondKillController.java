package com.customer.web;

import com.customer.entity.CSecondKill;
import com.customer.entity.ParaMap;
import com.customer.service.CSecondKillService;
import com.customer.util.JsonResult;
import com.customer.util.TokenModel;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2017/5/8 0008.
 */
@Api(value = "秒杀活动",description = "秒杀活动")
@RestController
@RequestMapping(value = "/v1/cApi/cSecondKill")
public class CSecondKillController extends BaseController{

    @Autowired
    private CSecondKillService cSecondKillService;

    @ApiOperation(value = "秒杀活动",notes = "分页查询分页查询",response = CSecondKill.class)
    @RequestMapping(value = "/queryCSecondKill",method = RequestMethod.GET)
    public JsonResult queryCSecondKill(@RequestHeader(value = "token") String token,
                                       @ApiParam(required = true,value = "页码") @RequestParam(defaultValue = "1") Integer pageIndex,
                                       @ApiParam(required = true,value = "查询条数") @RequestParam(defaultValue = "20") Integer pageSize){
        try {
            ParaMap params = super.getParaMap();
            PageInfo page = new PageInfo();
            page = cSecondKillService.queryCSecondKill(params);
            return new JsonResult(0,"查询秒杀活动成功",page);
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1,"查询秒杀活动失败");
        }
    }

    @ApiOperation(value = "新增秒杀活动",notes = "新增秒杀活动",response = CSecondKill.class)
    @RequestMapping(value = "/addCSecondKill",method = RequestMethod.POST)
    public JsonResult addCSecondKill(@RequestHeader(value = "token") String token,
                                     @ApiParam(value = "商品名称") @RequestParam String goodsName,
                                     @ApiParam(value = "商品图片") @RequestParam String goodsImg,
                                     @ApiParam(value = "价格") @RequestParam BigDecimal price,
                                     @ApiParam(value = "原价") @RequestParam BigDecimal originalPrice,
                                     @ApiParam(value = "数量") @RequestParam Integer quantity,
                                     @ApiParam(value = "开始时间") @RequestParam String startTime,
                                     @ApiParam(value = "结束时间") @RequestParam String endTime,
                                     @ApiParam(value = "秒杀活动描述") @RequestParam(required = false) String killDesc){
        try {
            ParaMap params = super.getParaMap();
            TokenModel model = getTokenModel();
            params.put("businessId",model.getId());
            int count = cSecondKillService.addCSecondKill(params);
            if(count>0){
                return new JsonResult(0,"新增秒杀活动成功");
            }else{
                return new JsonResult(1,"查询秒杀活动失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1,"新增秒杀活动失败，请稍后再试");
        }
    }

    @ApiOperation(value = "修改秒杀活动",notes = "修改秒杀活动",response = CSecondKill.class)
    @RequestMapping(value = "/editCSecondKill",method = RequestMethod.POST)
    public JsonResult editCSecondKill(@RequestHeader(value = "token") String token,
                                      @ApiParam(value = "id") @RequestParam Integer id,
                                      @ApiParam(value = "商品名称") @RequestParam String goodsName,
                                      @ApiParam(value = "商品图片") @RequestParam String goodsImg,
                                      @ApiParam(value = "价格") @RequestParam BigDecimal price,
                                      @ApiParam(value = "原价") @RequestParam BigDecimal originalPrice,
                                      @ApiParam(value = "数量") @RequestParam Integer quantity,
                                      @ApiParam(value = "开始时间") @RequestParam String startTime,
                                      @ApiParam(value = "结束时间") @RequestParam String endTime,
                                      @ApiParam(value = "秒杀活动描述") @RequestParam(required = false) String killDesc){

        try {
            ParaMap params = super.getParaMap();
            int count = cSecondKillService.updateByPrimaryKey(params);
            if(count>0){
                return new JsonResult(0,"修改秒杀活动成功");
            }else{
                return new JsonResult(1,"修改秒杀活动失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(1,"修改秒杀活动失败，请稍后再试");
        }
    }

    @ApiOperation(value = "秒杀活动详情",notes = "秒杀活动详情",response = CSecondKill.class)
    @RequestMapping(value = "/cSecondKillDetail",method = RequestMethod.POST)
    public JsonResult cSecondKillDetail(@RequestHeader(value = "token") String token,
                                        @ApiParam(value = "id") @RequestParam Integer id){

        return null;
    }

}
