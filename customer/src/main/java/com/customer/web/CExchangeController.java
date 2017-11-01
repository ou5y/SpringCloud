package com.customer.web;

import com.customer.dto.*;
import com.customer.entity.ParaMap;
import com.customer.exception.CustomerException;
import com.customer.service.CExchangeService;
import com.customer.util.Arith;
import com.customer.util.CalendarUtil;
import com.customer.util.JsonResult;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by chenxl on 2017/5/10 0010.
 */
@Api(value = "兑换中心", description = "兑换中心")
@RestController
@RequestMapping(value = "/v1/cApi/exchange")
public class CExchangeController extends BaseController {

    @Autowired
    private CExchangeService cExchangeService;

    @ApiOperation(value = "是否可兑换",notes = "查询累计兑换金额", response=CIsExchangeDto.class)
    @RequestMapping(value = "/getIsExchange",method = RequestMethod.GET)
    public JsonResult getIsExchange(@RequestHeader("token") String token){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            CIsExchangeDto cIsExchangeDto = cExchangeService.getIsExchange(paraMap);
            if(null != cIsExchangeDto){
                return new JsonResult(0,"查询成功！", cIsExchangeDto);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "累计兑换金额",notes = "查询累计兑换金额", response=CTotalExchangeDto.class)
    @RequestMapping(value = "/totalExchange",method = RequestMethod.GET)
    public JsonResult getTotalExchange(@RequestHeader("token") String token){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            CTotalExchangeDto cTotalExchangeDto = cExchangeService.getTotalExchange(paraMap);
            if(null != cTotalExchangeDto){
                return new JsonResult(0,"查询成功！", cTotalExchangeDto);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "兑换记录列表",notes = "兑换记录列表",response=CExchangeRecordDto.class)
    @RequestMapping(value = "/getExchangeList",method = RequestMethod.GET)
    public JsonResult getExchangeList(@RequestHeader("token") String token,
                                      @ApiParam(required=true,value="兑换类型 0:主动善点(默认), 1:推荐奖励善点",defaultValue="0") @RequestParam("type") int type,
                                      @ApiParam(required=true,value="页码",defaultValue="1") @RequestParam("currentPage") int currentPage,
                                      @ApiParam(required=true,value="记录数",defaultValue="20") @RequestParam("pageSize") int pageSize){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());
            paraMap.put("uId", super.getTokenModel().getUserId());

            PageInfo<CExchangeRecordDto> page = cExchangeService.getExchangeList(paraMap);
            if(page.getList().size() > 0){
                return new JsonResult(0,"查询成功！", page);
            }else {
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value = "兑换详情",notes = "兑换详情",response=CExchangeDetailDto.class)
    @RequestMapping(value = "/getExchangeDetail",method = RequestMethod.GET)
    public JsonResult getExchangeDetail(@RequestHeader("token") String token,
                                        @ApiParam(required=true,value="兑换记录id") @RequestParam("exchangeId") int exchangeId){
        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());

            CExchangeDetailDto cExchangeDetailDto = cExchangeService.getExchangeDetail(paraMap);
            if(null != cExchangeDetailDto){
                return new JsonResult(0,"查询成功！", cExchangeDetailDto);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~！");
        }
    }

    @ApiOperation(value="进入我要兑换",notes="跳转到我要兑换页面", response=CExchangeShandianDto.class)
    @RequestMapping(value="/toExchange",method = RequestMethod.GET)
    public JsonResult toExchange(@RequestHeader("token") String token){

        try {
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());

            CExchangeShandianDto cExchangeShandianDto = cExchangeService.toExchange(paraMap);
            if(null != cExchangeShandianDto){
                return new JsonResult(0,"查询成功！", cExchangeShandianDto);
            }else{
                return new JsonResult(4,"暂无数据！");
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "计算实际可到账金额",notes = "计算实际可到账金额", response=CExchangeMoneyDto.class)
    @RequestMapping(value = "/getRealMoney",method = RequestMethod.GET)
    public JsonResult getRealMoney(@RequestHeader("token") String token,
                                   @ApiParam(required=true,value="兑换善点") @RequestParam("shandian") String shandian,
                                   @ApiParam(required=true,value="到账方式,可取值1:T+1、3:T+3、7:T+7") @RequestParam("arrivalMode") int arrivalMode,
                                   @ApiParam(required=true,value="兑换类型 0:主动善点(默认), 1:推荐奖励善点",defaultValue="0") @RequestParam("type") int type){
        try {
            if(Double.parseDouble(shandian) >= 100 && Double.parseDouble(shandian)%100 == 0){
                ParaMap paraMap = super.getParaMap();
                paraMap.put("userId", super.getUserId());

                CExchangeMoneyDto cExchangeMoneyDto = cExchangeService.getRealMoney(paraMap);
                if(null != cExchangeMoneyDto){
                    return new JsonResult(0,"查询成功！", cExchangeMoneyDto);
                }else{
                    if(type==0) {
                        return new JsonResult(2, "鼓励点或积分余额不足!");
                    }else{
                        return new JsonResult(2, "奖励点不足!");
                    }
                }
            }else {
                return new JsonResult(2, "只能兑换100的整数倍!");
            }
        } catch (CustomerException ce) {
            return new JsonResult(2, ce.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }

    @ApiOperation(value = "添加兑换记录",notes = "添加兑换记录",response=CExchangedShandianDto.class)
    @RequestMapping(value = "/addExchange",method = RequestMethod.POST)
    public JsonResult addExchange(@RequestHeader("token") String token,
                                  @ApiParam(required=true,value="银行卡号Id") @RequestParam("bankId") String bankId,
                                  @ApiParam(required=true,value="善点") @RequestParam("shandian") String shandian,
                                  @ApiParam(required=true,value="手续费") @RequestParam("poundage") String poundage,
                                  @ApiParam(required=true,value="可到账金额") @RequestParam("sjdk") String sjdk,
                                  @ApiParam(required=true,value="到账方式") @RequestParam("arrivalMode") int arrivalMode,
                                  @ApiParam(required=true,value="0:兑换主动善点(默认),1:兑换推荐奖励善点",defaultValue="0") @RequestParam("type") int type){
//        return new JsonResult(2,"抱歉！兑换系统调整中，暂时不可兑换");
        try {

            if(StringUtils.isBlank(bankId)){
                return new JsonResult(2, "请先绑定银行卡!");
            }
//            if(Double.parseDouble(shandian) > 5000) {
//                return new JsonResult(2, "单笔兑换不可超过5000!");
//            }
            if(!(Double.parseDouble(shandian) >= 100 && Double.parseDouble(shandian)%100 == 0)) {
                return new JsonResult(2, "只能兑换100的整数倍!");
            }
//            if (!(CalendarUtil.getDay() <= 25)) {
//                return new JsonResult(2, "善点仅每月1-25日可兑换!");
//            }
            ParaMap paraMap = super.getParaMap();
            paraMap.put("userId", super.getUserId());

            CExchangeBankcardDto  bankCard = cExchangeService.getBankDetail(paraMap);
            if(bankCard==null) {
                return new JsonResult(2, "银行卡不存在!");
            }
            paraMap.put("bankCardNo", StringUtils.isBlank(bankCard.getBankCardNo()) ? "" : bankCard.getBankCardNo());
            paraMap.put("bankName", StringUtils.isBlank(bankCard.getBankName()) ? "" : bankCard.getBankName());
            paraMap.put("cardHolder", StringUtils.isBlank(bankCard.getCardHolder()) ? "" : bankCard.getCardHolder());

            CExchangedShandianDto cExchangedShandianDto = cExchangeService.saveExchange(paraMap);
            if (cExchangedShandianDto != null) {
                return new JsonResult(0, "兑换成功!", cExchangedShandianDto);
            } else {
                return new JsonResult(0, "兑换失败!");
            }
        } catch (CustomerException ce) {
            logger.error(ce.getMessage());
            return new JsonResult(ce.getCode(), ce.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage());
            return new JsonResult(2,"请求超时或者服务器出错啦，稍后再试试~~");
        }
    }
}
