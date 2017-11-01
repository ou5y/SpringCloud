package com.azcx9d.business.controller;


import com.azcx9d.business.dto.CheckBusinessDto;
import com.azcx9d.business.entity.ParaMap;
import com.azcx9d.business.service.OrderService;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.common.util.BaseController;
import com.azcx9d.common.util.RandomUtil;
import com.azcx9d.mybatisgenerator.model.OrderForm;
import com.azcx9d.pay.entity.builder.NbpayTradePayRequestBuilder;
import com.azcx9d.pay.entity.builder.NbpayTradeQueryRequestBuilder;
import com.azcx9d.pay.service.PaySerivce;
import com.azcx9d.pay.service.impl.NbpayF2FPayResult;
import com.azcx9d.pay.util.ApiException;
import com.azcx9d.pay.util.Configs;
import com.azcx9d.pay.util.PaySignature;
import com.azcx9d.pay.util.RequestParamUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;


/**
 * Created by fangbaoyan on 2017/4/18.
 */

@RestController
@RequestMapping("/v1/bApi/makeCollections")
@Api(value = "我要收款",description = "我要收款")
public class PayContorlller extends BaseController{

    private Logger logger = LoggerFactory.getLogger(PayContorlller.class);

    @Autowired
    private PaySerivce paySerivce;

    @Autowired
    private OrderService orderServiceImpl;

    @ApiOperation(value = "我要收款")
    @RequestMapping(value = "/makeCollections",method = RequestMethod.POST )
    public JsonResult makeCollections(@RequestHeader("token") String token,
                                      @ApiParam(name = "storeId",value = "店铺id",required = true) @RequestParam("storeId") String storeId,
                                      @ApiParam(name = "rlType",value = "让利类型(1-5%,2-10%,3-20%,,4-12%)",allowableValues = "1,2,3,4") @RequestParam("rlType") int rlType,
                                      @ApiParam(name = "totalAmount",value = "订单金额",required = true) @RequestParam(value = "totalAmount") String totalAmount,
                                      @ApiParam(name ="dynamicId" ,value = "动态ID。二维码扫码信息",required = true)
                                      @RequestParam(value = "dynamicId") String dynamicId,
                                      @ApiParam(name ="subject",value = "商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。",required = false)
                                      @RequestParam(value = "subject") String subject
                                      )
    {

        ParaMap paraMap = new ParaMap();
        paraMap.put("storeId", storeId);
        CheckBusinessDto checkBusinessDto = orderServiceImpl.checkStoreById(paraMap);
        if(null == checkBusinessDto || null == checkBusinessDto.getId()){
            return new JsonResult(2,"下单失败!商户ID不正确");
        }
        if(0 == checkBusinessDto.getIsOpen()){
            return new JsonResult(2,"该商家暂不支持网络支付!");
        }
        if(Double.parseDouble(totalAmount)<1){
            return new JsonResult(2,"消费金额不能低于1元");
        }
        //商家当天订单额度
        double orderSum = Double.parseDouble(orderServiceImpl.getOrderSum(paraMap));
        double maxAmount = Double.parseDouble(checkBusinessDto.getMaxAmount());
        if((orderSum + Double.parseDouble(totalAmount)) > maxAmount){
            return new JsonResult(2,"下单失败!商户今日的剩余消费额度:"+((maxAmount-orderSum)>0?(maxAmount-orderSum):0.0)+"元");
        }

        OrderForm orderForm = new OrderForm();
        switch (rlType) {
            case 1:
                orderForm.setRangli(0.05);
                break;
            case 2:
                orderForm.setRangli(0.1);
                break;
            case 3:
                orderForm.setRangli(0.2);
                break;
            case 4:
                orderForm.setRangli(0.12);
                break;
        }

        orderForm.setMoney(Double.valueOf(totalAmount));
        orderForm.setSellerId((Integer) request.getAttribute("userId"));
        orderForm.setStoreId(Integer.valueOf(storeId));
        orderForm.setOrderId(RandomUtil.getRandomString(2)+(System.currentTimeMillis()+"").substring(3)+((int)((Math.random()*9+1)*100)));
        orderForm.setState(5);//等待裕顺付款
        orderForm.setOrdersource(1);
        orderForm.setRanliMoney(orderForm.getRangli()*orderForm.getMoney());
        orderServiceImpl.createOrder(orderForm);


        NbpayTradePayRequestBuilder nbpayTradePayRequestBuilder = new NbpayTradePayRequestBuilder();

        nbpayTradePayRequestBuilder
                .setTotalFee(totalAmount).setSubject("ssstc_order"+System.currentTimeMillis())
                .setDynamicId(dynamicId).setDynamicIdType("qr_code")
//                .setNotifyUrl("https://sixsixthree.org/business/v1/bApi/makeCollections/nbpayNotify")
                .setNotifyUrl("https://ssstc.cn/business/v1/bApi/makeCollections/nbpayNotify")
                .setRlType(rlType)
                .setRoyaltyAmnt(String.valueOf(orderForm.getRangli()*orderForm.getMoney()))
                .setStoreId(storeId)
                .setSellerId((int) request.getAttribute("userId")+"")
                .setOutTradeNo(String.valueOf(orderForm.getId()));

        ExecutorService pool = Executors.newCachedThreadPool();
        Callable<NbpayF2FPayResult> task = new Callable<NbpayF2FPayResult>() {
            @Override
            public NbpayF2FPayResult call() throws Exception {
                return paySerivce.tradePay(nbpayTradePayRequestBuilder);
            }
        };
        Future<NbpayF2FPayResult> future=pool.submit(task);
        pool.shutdown();
        try {
            NbpayF2FPayResult nbpayF2FPayResult= future.get(10, TimeUnit.SECONDS);
            switch (nbpayF2FPayResult.getTradeStatus()) {
                case SUCCESS:
                    logger.info(nbpayF2FPayResult.getResponse().getSubMsg());

                case FAILED:
                    logger.info(nbpayF2FPayResult.getResponse().getSubMsg());

                default:
                    logger.info(nbpayF2FPayResult.getResponse().getSubMsg());

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }


//         Thread thread = new Thread(()->paySerivce.tradePay(nbpayTradePayRequestBuilder));
//         thread.start();

         return new JsonResult(0,"生成订单成功,等待支付结果",orderForm);


//        NbpayF2FPayResult nbpayF2FPayResult= null;
//
//        try {
//            nbpayF2FPayResult = paySerivce.tradePay(nbpayTradePayRequestBuilder);
//            switch (nbpayF2FPayResult.getTradeStatus()) {
//                case SUCCESS:
//                    logger.info("下单成功:");
//                    return new JsonResult(0,"收款成功,等待支付结果");
//                case FAILED:
//                    logger.error("收款失败!!!");
//                    return new JsonResult(2,"收款失败");
//                default:
//                    logger.error("不支持的交易状态，交易返回异常!!!");
//                    return new JsonResult(2,"收款失败,不支持的交易状态，交易返回异常!!!");
//            }
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//            return new JsonResult(2,"收款失败,网络异常");
//        } catch (ApiException e) {
//            logger.error(e.getMessage());
//            return new JsonResult(2,"收款失败,系统异常，订单状态未知!!!");
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            return new JsonResult(2,"收款失败,网络异常");
//        }


    }

    @ApiOperation(value = "交易查询",notes = "交易查询")
    @RequestMapping(value = "/queryOrderState",method = RequestMethod.GET)
    public JsonResult queryOrderState(@RequestHeader("token") String token,
                                      @ApiParam(value = "裕顺合作方网站唯一订单号") @RequestParam String outTradeNo){

        NbpayTradeQueryRequestBuilder build  = new NbpayTradeQueryRequestBuilder();
        build.setOutTradeNo(outTradeNo);

        return paySerivce.tradeQuery(build);
    }

    @ApiOperation(value = "消费结果异步通知",notes = "消费结果异步通知")
    @RequestMapping(value = "/nbpayNotify",method = RequestMethod.POST)
    public void nbpayNotify(
//            @ApiParam(value = "通知类型") @RequestParam String notifyActionType,
//                                  @ApiParam(value = "订单金额") @RequestParam String totalFee,
//                                  @ApiParam(value = "裕顺合作方网站唯一订单号") @RequestParam String outTradeNo,
//                                  @ApiParam(value = "交易流水号",required = false) @RequestParam String tradeNo,
//                                  @ApiParam(value = "订单标题",required = false) @RequestParam String subject,
//                                  @ApiParam(value = "交易状态",required = false) @RequestParam String tradeStatus,
                                  HttpServletRequest request,HttpServletResponse response){

        PrintWriter out = null;
        String html = "";
        Map<String, String> params = RequestParamUtil.getAllRequestParam(request);//取出所有参数
        logger.info("收到参数如下：");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            logger.info(entry.getKey() + "： " + entry.getValue());
        }
        //收到异步通知后  首先把收到消息的报文写出。
        try {
            response.setHeader("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            out = response.getWriter();
            //根据业务参数 要做防止通知的 重复送达

            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date sendDate = null;
            try {
                sendDate = df.parse(params.get("noticeTime"));
            } catch (ParseException e) {
            }

            long l = System.currentTimeMillis() - sendDate.getTime();
            if (l > 300000) {// 5分钟过期 5*60*1000
                logger.info("消息发送时间与 接收时间 ，间隔过大为异常状态，不做处理");
            }

            String signstr = params.get("sign");
            params.remove("sign");
            String content = PaySignature.getSignContent(params);

            boolean res = false;
            try {
                res = PaySignature.rsaCheck(content, signstr, Configs.getNbpayPublicKey(), "UTF-8", "RSA");
            } catch (ApiException e) {
                logger.error("验证签名异常");
            }
            if (res) {

                JsonResult result = paySerivce.updatePayNotify(params);
                if(result.getstatus()==0){
                    html= "SUCCESS";//写出，成功
                }else if(result.getData()!=null && result.getData().toString().equals("1")){
                    html= "SUCCESS";//写出，成功
                }else{
                    html= "FAIL";//写出，成功
                }
                logger.info("裕顺支付异步通知响应结果："+result+",html:"+html);
            }else{
                logger.error("验证签名不通过");
                html= "FAIL";//写出，成功
            }

            out.write(html);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (out != null)
                out.close();
        }
        //获取支付宝POST过来反馈信息
//        Map<String,String> params = new HashMap<String,String>(0);
//        Map<String,String> params1 = new HashMap<String,String>(0);
//        Map<String,String[]> requestParams = request.getParameterMap();
//        for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
//            String name = (String) iter.next();
//            String[] values = (String[]) requestParams.get(name);
//            String valueStr = "";
//            for (int i = 0; i < values.length; i++) {
//                valueStr = (i == values.length - 1) ? valueStr + values[i]
//                        : valueStr + values[i] + ",";
//            }
//            //乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
//            params1.put(name, valueStr);
////            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
////            params.put(name, valueStr);
//        }
//        ParaMap paraMap = getParaMap();
    }



}
