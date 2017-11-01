package com.customer.web;

import com.customer.dto.CUserAddressDto;
import com.customer.dto.CommonProblemDto;
import com.customer.entity.ParaMap;
import com.customer.service.CSetupService;
import com.customer.util.JsonResult;
import com.customer.util.MobileServerUtils;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenxl on 2017/5/10 0010.
 * 个人设置
 */
@Api(value = "个人设置", description = "个人设置")
@RestController
@RequestMapping(value = "/v1/cApi/setup")
public class CSetupController extends BaseController {

    @Autowired
    private CSetupService cSetupService;

    @ApiOperation(notes = "修改头像", value = "修改头像")
    @RequestMapping(value = "/editAvatar", method= RequestMethod.POST)
    public JsonResult editAvatar(@RequestHeader("token") String token,
                                 @ApiParam(required =true, value = "头像") @RequestParam("avatar") String avatar) {
        try {
            ParaMap paraMap = super.getParaMap();
           // paraMap.put("avatar",URLDecoder.decode(paraMap.getString("avatar")));
            paraMap.put("userId", request.getAttribute("userId"));
            int count = cSetupService.editAvatar(paraMap);
            if(count > 0){
                return new JsonResult(0,"修改成功！");
            }else{
                return new JsonResult(2,"修改失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(notes = "修改昵称", value = "修改昵称")
    @RequestMapping(value = "/editUserName", method= RequestMethod.POST)
    public JsonResult editUserName(@RequestHeader("token") String token,
                                   @ApiParam(required =true, value = "昵称") @RequestParam("userName") String userName) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            int count = cSetupService.editUserName(paraMap);
            if(count > 0){
                return new JsonResult(0,"修改成功！");
            }else{
                return new JsonResult(2,"修改失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    //todo 换绑手机暂时不做
    /*@ApiOperation(notes = "换绑手机", value = "换绑手机")
    @RequestMapping(value = "/changePhone", method= RequestMethod.POST)
    public JsonResult changePhone(@RequestHeader("token") String token,
                                  @ApiParam(required =true, value = "phone") @RequestParam("phone") String phone,
                                  @ApiParam(required =true, value = "code") @RequestParam("code") String code) {
        try {
            if (MobileServerUtils.checkCode(phone, code)) {
                ParaMap paraMap = super.getParaMap();
                paraMap.put("userId", request.getAttribute("userId"));
                int count = cSetupService.changePhone(paraMap);
                if(count > 0){
                    return new JsonResult(0,"换绑手机成功！");
                }else{
                    return new JsonResult(2,"换绑手机失败！");
                }
            }else {
                return new JsonResult(2,"验证码错误！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }*/

    @ApiOperation(notes = "意见反馈", value = "意见反馈")
    @RequestMapping(value = "/addSuggest", method= RequestMethod.POST)
    public JsonResult addSuggest(@RequestHeader("token") String token,
                                 @ApiParam(required=true,value="建议内容") @RequestParam("content") String content) {
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            int count = cSetupService.addSuggest(paraMap);
            if(count > 0){
                return new JsonResult(0,"提交意见成功！");
            }else{
                return new JsonResult(2,"提交意见失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "收货地址列表", notes = "收货地址列表",response = CUserAddressDto.class)
    @RequestMapping(value = "/getAddressList", method = RequestMethod.GET)
    public JsonResult getAddressList(@RequestHeader("token") String token,
                                     @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                     @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            PageInfo<CUserAddressDto> page = cSetupService.getAddressList(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功！", page);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "设置默认地址", notes = "设置默认地址")
    @RequestMapping(value = "/setDefaultAddress", method = RequestMethod.POST)
    public JsonResult setDefaultAddress(@RequestHeader("token") String token,
                                        @ApiParam(required =true, value = "地址id") @RequestParam("addressId") int addressId){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            int count = cSetupService.setDefaultAddress(paraMap);
            if(count > 0){
                return new JsonResult(0,"设置默认地址成功！");
            }else{
                return new JsonResult(2,"设置默认地址失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "删除地址", notes = "删除地址")
    @RequestMapping(value = "/deleteAddress", method = RequestMethod.DELETE)
    public JsonResult deleteAddress(@RequestHeader("token") String token,
                                    @ApiParam(required =true, value = "地址id") @RequestParam("addressId") int addressId){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            int count = cSetupService.deleteAddress(paraMap);
            if(count > 0){
                return new JsonResult(0,"删除地址成功！");
            }else{
                return new JsonResult(2,"删除地址失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "新增收货地址", notes = "新增收货地址")
    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public JsonResult addAddress(@RequestHeader("token") String token,
                                 @ApiParam(required =true, value = "收货人姓名") @RequestParam("name") String name,
                                 @ApiParam(required =true, value = "收货人手机") @RequestParam("phone") String phone,
                                 @ApiParam(required =true, value = "省") @RequestParam("province") int province,
                                 @ApiParam(required =true, value = "市") @RequestParam("city") int city,
                                 @ApiParam(required =true, value = "区") @RequestParam("area") int area,
                                 @ApiParam(required =true, value = "详细地址") @RequestParam("fullAddress") String fullAddress){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            int count = cSetupService.addAddress(paraMap);
            if(count > 0){
                return new JsonResult(0,"添加新地址成功！");
            }else{
                return new JsonResult(2,"添加新地址失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "地址详细信息", notes = "地址详细信息", response = CUserAddressDto.class)
    @RequestMapping(value = "/getAddressDetail", method = RequestMethod.GET)
    public JsonResult addressDetail(@RequestHeader("token") String token,
                                    @ApiParam(required =true, value = "地址id") @RequestParam("addressId") int addressId){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            CUserAddressDto cUserAddressDto = cSetupService.addressDetail(paraMap);
            if(null != cUserAddressDto){
                return new JsonResult(0,"查询成功！", cUserAddressDto);
            }else {
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "编辑收货地址", notes = "编辑收货地址")
    @RequestMapping(value = "/editAddress", method = RequestMethod.POST)
    public JsonResult editAddress(@RequestHeader("token") String token,
                                  @ApiParam(required =true, value = "地址id") @RequestParam("addressId") int addressId,
                                  @ApiParam(required =true, value = "收货人姓名") @RequestParam("name") String name,
                                  @ApiParam(required =true, value = "收货人手机") @RequestParam("phone") String phone,
                                  @ApiParam(required =true, value = "省") @RequestParam("province") int province,
                                  @ApiParam(required =true, value = "市") @RequestParam("city") int city,
                                  @ApiParam(required =true, value = "区") @RequestParam("area") int area,
                                  @ApiParam(required =true, value = "详细地址") @RequestParam("fullAddress") String fullAddress){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", request.getAttribute("userId"));
            int count = cSetupService.editAddress(paraMap);
            if(count > 0){
                return new JsonResult(0,"编辑收货地址成功！");
            }else{
                return new JsonResult(2,"编辑收货地址失败！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "关于,常见问题", notes = "关于,常见问题<br/>类型(1:关于全团了, 2:操作类问题, 3:商户类问题, 4:身份类问题, 5:银行卡类问题)", response = CommonProblemDto.class)
    @RequestMapping(value = "/getCommonProblem", method = RequestMethod.GET)
    public JsonResult getCommonProblem(@RequestHeader("token") String token){
        try {
            ParaMap paraMap = super.getParaMap();

            List<CommonProblemDto> list = cSetupService.getCommonProblem(paraMap);
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
