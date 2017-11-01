package com.azcx9d.pay.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.azcx9d.mybatisgenerator.dao.BusinessSupportInfoMapper;
import com.azcx9d.business.dao.CPayInfoMapper;
import com.azcx9d.mybatisgenerator.dao.OrderFormMapper;
import com.azcx9d.mybatisgenerator.model.BusinessSupportInfo;
import com.azcx9d.business.entity.CPayInfo;
import com.azcx9d.mybatisgenerator.model.OrderForm;
import com.azcx9d.business.dao.BOrderFormDAO;
import com.azcx9d.business.entity.BOrderForm;
import com.azcx9d.common.entity.JsonResult;
import com.azcx9d.pay.entity.NbpayTradePayResponse;
import com.azcx9d.pay.entity.TradeStatus;
import com.azcx9d.pay.entity.builder.NbpayTradePayRequestBuilder;
import com.azcx9d.pay.entity.builder.NbpayTradeQueryRequestBuilder;
import com.azcx9d.pay.service.PaySerivce;
import com.azcx9d.pay.urlConnection.UrlUtils;
import com.azcx9d.pay.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fangbaoyan on 2017/6/9.
 */
@Service
public class PaySerivceImpl implements PaySerivce{

    private Logger logger = LoggerFactory.getLogger(PaySerivceImpl.class);

    @Autowired
    private CPayInfoMapper cPayInfoMapper;

    @Autowired
    private OrderFormMapper orderFormMapper;

    @Autowired
    private BOrderFormDAO bOrderFormDao;

    @Autowired
    private BusinessSupportInfoMapper businessSupportInfoMapper;

    static {
        Configs.init("nbpayinfo.properties");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public NbpayF2FPayResult tradePay(NbpayTradePayRequestBuilder builder) {
        NbpayTradePayResponse  nbpayTradePayResponse = new NbpayTradePayResponse();

        NbpayF2FPayResult nbpayF2FPayResult = new NbpayF2FPayResult(nbpayTradePayResponse);

        BusinessSupportInfo businessSupportInfo = businessSupportInfoMapper.selectByStoreId(Integer.valueOf(builder.getStoreId()));

        if(businessSupportInfo==null)
        {
            nbpayF2FPayResult.setTradeStatus(TradeStatus.FAILED);
            nbpayF2FPayResult.getResponse().setSubMsg("该商户在裕顺不存在或未审核通过");
            logger.error("该商户在裕顺不存在或未审核通过");
            return nbpayF2FPayResult;
        }

        Map<String, String> params =new HashMap<>();

        params.put("signType", Configs.getSignType());
        params.put("charSet", "UTF-8");
        params.put("partnerId", Configs.getPid());

        params.put("merchantNumber",businessSupportInfo.getMerchantNumber());

        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        params.put("timestamp", dateString);


        params.put("outTradeNo",builder.getOutTradeNo());
        params.put("subject",builder.getSubject());
        params.put("totalFee",builder.getTotalFee());
        params.put("dynamicIdType",builder.getDynamicIdType());
        params.put("dynamicId",builder.getDynamicId());
        params.put("notifyUrl",builder.getNotifyUrl());
        params.put("operatorId",builder.getOperatorId());
        params.put("royaltyType",builder.getRoyaltyType());
        params.put("royaltyAmnt",builder.getRoyaltyAmnt());

        String content= PaySignature.getSignContent(params);

        String signstr=null;
        try {
            signstr = PaySignature.rsaSign(content,Configs.getPrivateKey(), "UTF-8",Configs.getSignType());
        } catch (ApiException e) {

            logger.error(e.getErrMsg());

            nbpayF2FPayResult.setTradeStatus(TradeStatus.UNKNOWN);
            nbpayF2FPayResult.getResponse().setSubMsg(e.getErrMsg());
            nbpayF2FPayResult.getResponse().setSubCode(e.getErrCode());
            return nbpayF2FPayResult;
        }
        try {
            content=content+"&sign="+ URLEncoder.encode(signstr,"UTF-8");
        } catch (UnsupportedEncodingException e) {

            logger.error(e.getMessage());

            nbpayF2FPayResult.setTradeStatus(TradeStatus.UNKNOWN);
            nbpayF2FPayResult.getResponse().setSubMsg(e.getMessage());

            return nbpayF2FPayResult;
        }

        HttpClient httpClient=new HttpClient();

        String result= null;
        try {
            result = httpClient.sendHttp(Configs.getCreateandpayApiDomain(), content);
        } catch (IOException e) {

           logger.error(e.getMessage());

            nbpayF2FPayResult.setTradeStatus(TradeStatus.UNKNOWN);

            nbpayF2FPayResult.getResponse().setSubMsg(e.getMessage());

            return nbpayF2FPayResult;

        }


        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);


        String code = jsonObject.getString("errorCode");

        String message = jsonObject.getString("message");


        logger.debug("--------+"+code+"-------"+message);


        CPayInfo cPayInfo =new CPayInfo();

        if(!"SUCCESS".equals(code)){


            OrderForm orderForm =  new OrderForm();

            orderForm.setId(Integer.valueOf(builder.getOutTradeNo()));

            orderForm.setState(4);

            orderFormMapper.updateByPrimaryKeySelective(orderForm);

            nbpayTradePayResponse.setCode(code);

            nbpayTradePayResponse.setMsg(jsonObject.getString("message"));

             nbpayF2FPayResult = new NbpayF2FPayResult(nbpayTradePayResponse);

            nbpayF2FPayResult.setTradeStatus(TradeStatus.FAILED);

            cPayInfo.setOrderNo(Long.valueOf(builder.getOutTradeNo()));
            cPayInfo.setPlatformStatus(code);
            cPayInfo.setPayPlatform(1);
            cPayInfo.setDescription(jsonObject.getString("message"));
            cPayInfoMapper.insertSelective(cPayInfo);
            return nbpayF2FPayResult;

        }
        else
        {
                JSONObject dataObject = jsonObject.getJSONObject("data");



                cPayInfo.setUserId(Integer.valueOf(dataObject.getString("partnerUserId")));

                cPayInfo.setOrderNo(Long.valueOf(dataObject.getString("outTradeNo")));

                cPayInfo.setPayPlatform(1);

                cPayInfo.setPlatformNumber(dataObject.getString("tradeNo "));

                cPayInfo.setPlatformStatus("WAITING_PAY");

                cPayInfoMapper.insertSelective(cPayInfo);

                OrderForm orderForm =  new OrderForm();

                orderForm.setId(Integer.valueOf(dataObject.getString("outTradeNo")));

                orderForm.setUserId(Integer.valueOf(dataObject.getString("partnerUserId")));

                orderFormMapper.updateByPrimaryKeySelective(orderForm);

                 nbpayF2FPayResult = new  NbpayF2FPayResult(nbpayTradePayResponse);

                nbpayF2FPayResult.setTradeStatus(TradeStatus.SUCCESS);

                return nbpayF2FPayResult;
        }
    }


    // 交易查询
    @Override
    public JsonResult tradeQuery(NbpayTradeQueryRequestBuilder builder){

        BOrderForm order = bOrderFormDao.findByOrderNo(builder.getOutTradeNo());

        BusinessSupportInfo businessSupportInfo = businessSupportInfoMapper.selectByStoreId(Integer.valueOf(order.getStoreId()));

        if(order==null) {
            logger.error(builder.getOutTradeNo()+"订单不存在");
            return new JsonResult(2,"订单不存在");
        }

        Map<String, String> params =new HashMap<>();

        params.put("signType", Configs.getSignType());
        params.put("charSet", "UTF-8");
        params.put("partnerId", Configs.getPid());
        //TODO ???商家Id 083330093990039
//        params.put("partnerUserId",builder.getSellerId());
//        params.put("partnerUserId",Configs.getPid());
//        params.put("partnerUserId",Configs.getPid());
        params.put("partnerId", Configs.getPid());
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        params.put("timestamp", dateString);
        params.put("outTradeNo",builder.getOutTradeNo());
        params.put("queryType","01");
        params.put("merchantNumber",businessSupportInfo.getMerchantNumber());

        String content= PaySignature.getSignContent(params);

        String signstr=null;
        try {
            signstr = PaySignature.rsaSign(content,Configs.getPrivateKey(), "UTF-8",Configs.getSignType());
        } catch (ApiException e) {
            logger.error(e.getErrMsg());
        }
        try {
            content=content+"&sign="+ URLEncoder.encode(signstr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }

        HttpClient httpClient=new HttpClient();
        String result= null;
        try {
            result = httpClient.sendHttp(Configs.getTradeQuery(), content);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        if(jsonObject.getString("errorCode").equals("SUCCESS")){
            JSONObject data = jsonObject.getJSONObject("data");
            if(data.getString("tradeStatus").equals("TRADE_SUCCESS")){
                return new JsonResult(0,"交易成功");
            }else if(data.getString("tradeStatus").equals("TRADE_CANCEL")){
                return new JsonResult(2,"交易已撤销");
            }else if(data.getString("tradeStatus").equals("TRADE_REFUND")){
                return new JsonResult(2,"交易已全额退款");
            }else if(data.getString("tradeStatus").equals("TRADE_FINISHED")){
                return new JsonResult(0,"交易成功且结束，即不可再做任何操作");
            }else{
                return new JsonResult(2,"订单状态未知，请重新查询");
            }
        }else{
            return  new JsonResult(2,jsonObject.getString("message"));
        }
    }

    // 消费结果异步通知
    @Override
    public JsonResult updatePayNotify(Map<String,String> params){
        String outTradeNo = params.get("outTradeNo").toString();            //系统唯一订单编号
        BOrderForm order = bOrderFormDao.findByOrderNo(outTradeNo);
        if(order==null) {
            logger.error(outTradeNo+"订单不存在");
            return new JsonResult(2,"订单不存在");
        }
        if(order.getState()==3){return new JsonResult(0,"更新订单状态成功");}
        params.put("orderFee",order.getMoney()+"");
        params.put("payPlatform","1");
        params.put("sellerId",order.getSellerId()+"");
        params.put("userId",order.getUserId()+"");
        params.put("orderId",order.getId()+"");
        int count = bOrderFormDao.updateNbpayInfo(params);                   //保存第三方支付记录
        if(count==0) {
            return new JsonResult(2, "保存支付信息失败");
        }
//        if(params.get("tradeStatus").equals("TRADE_SUCCESS")){
//            // 交易成功，且可对该交易做操作，如：多级分润、退款等
//            params.put("state","2");
//            count = bOrderFormDao.updateOrerPayState(params);
//            if(count>0){
//                int settlementFlag = updateOrderSettlement(order.getId());
//                logger.info("订单结算状态："+settlementFlag);
//                return new JsonResult(0,"更新订单状态成功");
//            }else{
//                return new JsonResult(2,"更新订单状态失败");
//            }
//        }else if(params.get("tradeStatus").equals("TRADE_CANCEL")){
//            // 交易已撤销
//            params.put("state","4");
//            count = bOrderFormDao.updateOrerPayState(params);
//            if(count>0){
//                int settlementFlag = updateOrderSettlement(order.getId());
//                logger.info("订单结算状态："+settlementFlag);
//                return new JsonResult(0,"更新订单状态成功");
//            }else{
//                return new JsonResult(2,"更新订单状态失败");
//            }
//        }else if(params.get("tradeStatus").equals("TRADE_REFUND")){
//            // 交易已全额退款
//            params.put("state","5");
//            count = bOrderFormDao.updateOrerPayState(params);
//            if(count>0){
//                return new JsonResult(0,"更新订单状态成功");
//            }else{
//                return new JsonResult(2,"更新订单状态失败");
//            }
//        }else if(params.get("tradeStatus").equals("TRADE_FINISHED")){
//            //交易成功且结束，即不可再做任何操作
//            params.put("state","2");
//            count = bOrderFormDao.updateOrerPayState(params);
//            if(count>0){
//                int settlementFlag = updateOrderSettlement(order.getId());
//                logger.info("订单结算状态："+settlementFlag);
//                return new JsonResult(0,"更新订单状态成功");
//            }else{
//                return new JsonResult(2,"更新订单状态失败");
//            }
//        }else{
//            return new JsonResult(2,"订单状态未知，请重新查询");
//        }
        if(params.get("tradeStatus").equals("0")){
            // 交易成功，且可对该交易做操作，如：多级分润、退款等
            params.put("state","2");
            count = bOrderFormDao.updateOrerPayState(params);
            if(count>0){
                int settlementFlag = updateOrderSettlement(order.getId());
                logger.info("订单结算状态："+settlementFlag);
                return new JsonResult(0,"更新订单状态成功");
            }else{
                return new JsonResult(2,"更新订单状态失败");
            }
        }else if(params.get("tradeStatus").equals("1")){
            // 支付失败，修改订单状态为
            logger.error("errorCode:"+params.get("errorCode")+"     message:"+params.get("message"));
            params.put("state","4");
            count = bOrderFormDao.updateOrerPayState(params);
            return new JsonResult(2,params.get("message"),1);
        }else{
            return new JsonResult(2,"订单状态未知，请重新查询");
        }
    }

    // 订单结算
    @Override
    public int updateOrderSettlement(final int orderId){
        HttpClient httpClient=new HttpClient();
        String result= null;
//        try {
            String content = "?id="+orderId;
            logger.error("订单结算："+Configs.getOrderSettlementUrl()+"?"+content);
//            result = httpClient.sendHttp(Configs.getOrderSettlementUrl(), content);
            result = UrlUtils.getJsonByInternet(Configs.getOrderSettlementUrl()+content);
            logger.error("订单结算返回结果："+result);
//        } catch (IOException e) {
//            logger.error(e.getMessage());
//            e.printStackTrace();
//            return 0;
//        }

        JSONObject jsonObject = (JSONObject) JSONObject.parse(result);
        if(jsonObject.getIntValue("returnCode")==0){
            return 1;
        }else{
            return 0;
        }
    }

}
