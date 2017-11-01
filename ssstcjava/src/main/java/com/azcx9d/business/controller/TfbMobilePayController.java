package com.azcx9d.business.controller;

import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.business.service.BOrderFormService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.pay.service.TfbMobilePayService;
import com.azcx9d.pay.util.ExternalIpAddressFetcher;
import com.azcx9d.pay.util.RequestParamUtil;
import com.azcx9d.pay.util.StringUtils;
import com.azcx9d.pay.util.tfb.union.MD5Utils;
import com.azcx9d.pay.util.tfb.union.RequestUtils;
import com.azcx9d.pay.util.tfb.union.TFBConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Administrator on 2017/8/21 0021.
 */
@Api(value = "天付宝移动支付",description = "天付宝移动支付")
@RestController
@RequestMapping(value = "v1/bApi/mobilePay")
public class TfbMobilePayController extends BaseController {

    @Autowired
    private BOrderFormService borderFormService;
    @Autowired
    private TfbMobilePayService tfbMobilePayService;

    @ApiOperation(value = "移动支付", notes = "微信或支付宝")
    @RequestMapping(value = "/pay", method = RequestMethod.POST)
    public JsonResult pay(@RequestHeader(value = "token",defaultValue = "144961_32b2797e452d49e5bd1616e87c8fa407") String token,
                          @ApiParam(value = "商户订单号", required = true) @RequestParam String spBillno,
                          @ApiParam(value = "支付类型：<br>支付方式（支付宝）：<br>" +
                                  "扫码支付：800201<br>" +
                                  "刷卡支付：800208<br>" +
                                  "支付方式（微信）：<br>" +
                                  "扫码支付：800201<br>" +
                                  "刷卡支付：800208<br>" +
                                  "wap支付：800206<br>" +
                                  "APP支付：800205", required = true) @RequestParam String payType,
                          @ApiParam(value = "交易金额（单位分）", required = true) @RequestParam BigDecimal tranAmt,
                          @ApiParam(value = "二维码内容，（刷卡支付:800208-必填）") @RequestParam(required = false) String authCode,
                          @ApiParam(value = "商品名称", required = true) @RequestParam String itemName,
                          @ApiParam(value = "附加数据") @RequestParam(required = false) String itemAttach
    ){
        try {
            TreeMap<String, String> paramsMap = new TreeMap<>();
            BOrderForm order = borderFormService.findByOrderNo(spBillno);
            if (order == null){
                return new JsonResult(2,"该订单未找到");
            }else{
                paramsMap.put("spid", TFBConfig.spid);
                paramsMap.put("sp_billno", spBillno);
                paramsMap.put("tran_amt", tranAmt.intValue()+"");
                paramsMap.put("notify_url", TFBConfig.notify_url);
                String spbillCreateIp = ExternalIpAddressFetcher.myExternalIpAddress();
                paramsMap.put("spbill_create_ip", StringUtils.isEmpty(spbillCreateIp) ? TFBConfig.spbill_create_ip : spbillCreateIp);
                paramsMap.put("pay_type", payType);
                paramsMap.put("cur_type", TFBConfig.cur_type);
                paramsMap.put("tran_time", new SimpleDateFormat("yyyyMMddHHmmss").format(System.currentTimeMillis()));
                paramsMap.put("item_name", itemName);//商品名称或标示
                //paramsMap.put("item_attach", request.getParameter("item_attach"));

                if("800208".equals(payType) || payType.equals("800205")){
                    paramsMap.put("auth_code", authCode);
                    boolean isWechart = (!StringUtils.isEmpty(authCode)) && authCode.startsWith("13");
                    if (isWechart){
                        //微信支付
                        paramsMap.put("pay_limit", "no_credit"); // 微信扫码或微信反扫的时候，如果该参数值指定为no_credit可禁止使用信用卡支付
                        paramsMap.put("sp_udid", TFBConfig.sp_userid);
                        paramsMap.put("bank_mch_name", "201610131607");
                        paramsMap.put("bank_mch_id", "1610111");
                        //paramsMap.put("item_attach", request.getParameter("item_attach"));
                        //paramsMap.put("openid", request.getParameter("openid"));//非实名商户没有openid
                    }else {
                        //支付宝支付
                    }
                }
                return tfbMobilePayService.createPayApply(paramsMap);
            }
        }catch (Exception e){
            e.printStackTrace();
            return new JsonResult(2, e.getMessage());
        }
    }


    @ApiOperation(value = "移动支付结果异步通知",notes = "移动支付结果异步通知")
    @RequestMapping(value = "/callbackPay", method = RequestMethod.POST)
    public void callbackPay(HttpServletRequest request, HttpServletResponse response){
        PrintWriter out = null;
        String html = "";
        try {
            request.setCharacterEncoding("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Map<String, String> params = RequestParamUtil.getAllRequestParam(request);//取出所有参数
        logger.info("收到参数如下：");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            logger.info(entry.getKey() + "： " + entry.getValue());
        }
        try {
            response.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            out = response.getWriter();

            if (params.get("retcode").equals("00")) {
                //验签
                //获得签名串
                String paramSrc = RequestUtils.createLinkString(params);
                //String paramSrc = PaySignature.getSignContent(params);
                logger.info("签名原串：" + paramSrc);

                //获得签名字段
                String sign = params.get("sign");
                logger.info("服务端签名值:" + sign);

                if (MD5Utils.verify(paramSrc, sign, TFBConfig.serverEncodeType)) {
                    JsonResult result = tfbMobilePayService.notifyUrl(params);
                    if(result.getstatus() == 0){
                        html= "SUCCESS";//写出，成功
                    }else{
                        html= "FAIL（"+result.getMessage()+"）";//写出，失败
                    }
                    logger.info("异步通知返回三方平台html:"+html);
                }else{
                    html= "FAIL（验签失败）";//写出，失败
                }
            }else{
                html= "FAIL（错误代码：" + params.get("retcode") + "）";//写出，失败
            }

            out.write(html);
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (out != null)
                out.close();
        }
    }

    @ApiOperation(value = "关闭订单",notes = "关闭订单")
    @RequestMapping(value = "/closeOrder",method = RequestMethod.POST)
    public JsonResult closeOrder(
            HttpServletRequest request,
            @RequestHeader(value = "token",defaultValue = "144961_32b2797e452d49e5bd1616e87c8fa407") String token,
            @ApiParam(value = "商户订单号", required = true) @RequestParam String spBillno,
            @ApiParam(value = "交易金额（单位分）", required = true) @RequestParam BigDecimal tranAmt){
        try {
            request.setCharacterEncoding("UTF-8");

            BOrderForm order = borderFormService.findByOrderNo(spBillno);
            if (order == null){
                return new JsonResult(2,"该订单未找到");
            }else {
                TreeMap<String, String> paramsMap = new TreeMap<>();
                paramsMap.put("spid", TFBConfig.spid);
                paramsMap.put("sp_billno", spBillno);
                paramsMap.put("tran_amt", tranAmt.intValue()+"");
                paramsMap.put("tran_time", new SimpleDateFormat("yyyyMMddHHmmss").format(Calendar.getInstance().getTime()));

                return tfbMobilePayService.colseOrder(paramsMap);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new JsonResult(2, e.getMessage());
        }
    }

}
