package com.azcx9d.pay.entity.builder;

import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang.StringUtils;

import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by fangbaoyan on 2017/6/9.
 */
public class NbpayTradePayRequestBuilder extends RequestBuilder {

    private NbpayTradePayRequestBuilder.BizContent bizContent = new NbpayTradePayRequestBuilder.BizContent();




    @Override
    public boolean validate() {
        if(StringUtils.isEmpty(this.bizContent.scene)) {
            throw new NullPointerException("scene should not be NULL!");
        } else if(StringUtils.isEmpty(this.bizContent.authCode)) {
            throw new NullPointerException("auth_code should not be NULL!");
        } else if(!Pattern.matches("^\\d{10,}$", this.bizContent.authCode)) {
            throw new IllegalStateException("invalid auth_code!");
        } else if(StringUtils.isEmpty(this.bizContent.outTradeNo)) {
            throw new NullPointerException("out_trade_no should not be NULL!");
        } else if(StringUtils.isEmpty(this.bizContent.totalFee)) {
            throw new NullPointerException("totalFee should not be NULL!");
        } else if(StringUtils.isEmpty(this.bizContent.subject)) {
            throw new NullPointerException("subject should not be NULL!");
        } else if(StringUtils.isEmpty(this.bizContent.dynamicIdType)) {
            throw new NullPointerException("dynamicIdType should not be NULL!");
        }else if(StringUtils.isEmpty(this.bizContent.dynamicId)) {
            throw new NullPointerException("dynamicId should not be NULL!");
        } else {
            return true;
        }
    }



    @Override
    public Object getBizContent() {
        return this.getBizContent();
    }



    public NbpayTradePayRequestBuilder() {
        this.bizContent.scene = "bar_code";
    }

    public NbpayTradePayRequestBuilder setNotifyUrl(String notifyUrl) {
        return (NbpayTradePayRequestBuilder)super.setNotifyUrl(notifyUrl);
    }

    public String getScene() {
        return this.bizContent.scene;
    }

    public NbpayTradePayRequestBuilder setScene(String scene) {
        this.bizContent.scene = scene;
        return this;
    }


    public String getOutTradeNo() {
        return this.bizContent.outTradeNo;
    }

    public NbpayTradePayRequestBuilder setOutTradeNo(String outTradeNo) {
        this.bizContent.outTradeNo = outTradeNo;
        return this;
    }


    public String getSellerId() {
        return this.bizContent.sellerId;
    }

    public NbpayTradePayRequestBuilder setSellerId(String sellerId) {
        this.bizContent.sellerId = sellerId;
        return this;
    }


    public String getTotalFee() {
        return this.bizContent.totalFee;
    }

    public NbpayTradePayRequestBuilder setTotalFee(String totalFee) {
        this.bizContent.totalFee = totalFee;
        return this;
    }

    public String getSubject() {
        return this.bizContent.subject;
    }

    public NbpayTradePayRequestBuilder setSubject(String subject) {
        this.bizContent.subject = subject;
        return this;
    }

    public String getDynamicIdType() {return this.bizContent.dynamicIdType;}

    public NbpayTradePayRequestBuilder setDynamicIdType(String dynamicIdType)
    {
        this.bizContent.dynamicIdType = dynamicIdType;
        return this;
    }

    public String getDynamicId() {return  this.bizContent.dynamicId;}

    public NbpayTradePayRequestBuilder setDynamicId(String dynamicId)
    {
        this.bizContent.dynamicId=dynamicId;
        return this;
    }


    public String getCurrency() { return this.bizContent.currency;}


    public NbpayTradePayRequestBuilder setCurrency(String currency) {
        this.bizContent.currency = currency;
        return this;
    }

    public String getRoyaltyType() {return this.bizContent.royaltyType;}

    public NbpayTradePayRequestBuilder setRoyaltyType(String royaltyType)
    {
        this.bizContent.royaltyType = royaltyType;
        return this;
    }


    public String getRoyaltyAmnt() {return this.bizContent.royaltyAmnt;}

    public NbpayTradePayRequestBuilder setRoyaltyAmnt(String royaltyAmnt) {
        this.bizContent.royaltyAmnt = royaltyAmnt;
        return this;
    }

    public String getOperatorId() {return this.bizContent.operatorId;}



    public NbpayTradePayRequestBuilder setOperatorId(String  operatorId){
        this.bizContent.operatorId = operatorId;
        return this;
    }

    public int getRlType()
    {
        return this.bizContent.rlType;
    }

    public NbpayTradePayRequestBuilder setRlType(int rlType)
    {
        this.bizContent.rlType = rlType;
        return this;

    }

    public String getStoreId()
    {
        return this.bizContent.storeId;
    }

    public NbpayTradePayRequestBuilder setStoreId(String storeId){
        this.bizContent.storeId = storeId;
        return this;
    }

    public static class BizContent {

        private String scene;
        @SerializedName("auth_code")
        private String authCode;
        @SerializedName("out_trade_no")
        private String outTradeNo;
        @SerializedName("seller_id")
        private String sellerId;

        @SerializedName("rl_type")
        private int rlType;

        private String storeId;

        //该笔订单的资金总额，取值范围[0.01,100000000]，精确到小数点后2位。
        @SerializedName("total_fee")
        private String totalFee ;

        //动态ID类型： qr_code：二维码
        private String dynamicIdType;

        //动态ID。二维码扫码信息
        private String dynamicId;

        //商品的标题/交易标题/订单标题/订单关键字等。该参数最长为128个汉字。
        private String subject;

        /*订单金额币种：
        目前只支持传入156（人民币）。
        如果为空，则默认设置为156。*/
        private String currency ="156";

        /*商户的分账类型，目前只支持传入ROYALTY（普通分账类型）。
        商户需要进行分账时，该参数不可空。*/
        private String royaltyType ="ROYALTY";

        //分账的金额。
        private String royaltyAmnt;


//        @SerializedName("goods_detail")
//        private List<GoodsDetail> goodsDetailList;

        /*操作员的类型：
        0：裕顺操作员
        1：商户的操作员
        如果传入其它值或者为空，则默认设置为1。*/
        @SerializedName("operator_type")
        private String operatorType;

        @SerializedName("operator_id")
        private String operatorId;


        @SerializedName("timeout_express")
        private String timeoutExpress;


        public BizContent() {
        }


    }
}
