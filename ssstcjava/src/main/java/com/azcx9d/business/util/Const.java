package com.azcx9d.business.util;

/**
 * Created by HuangQing on 2017/3/29 0029.
 */
public class Const {
    public static final String NO_APITOKEN_PATH = ".*/((v1/bApi/mobilePay/callbackPay)|(v1/bApi/user)|(v1/bApi/redirect)|(v1/bApi/test)|(v1/bApi/common)|(v1/bApi/payBusiness)|(v1/bApi/makeCollections/nbpayNotify)).*";

    /**
     * 商户兑换手续费T+1
     */
    public static final double BUSINESS_POUNDAGE_1 = 0.006;

    /**
     * 商户兑换手续费T+3
     */
    public static final double BUSINESS_POUNDAGE_3 = 0.003;

    /**
     * 商户兑换手续费T+7
     */
    public static final double BUSINESS_POUNDAGE_7 = 0;

    /**
     * 商户兑换代扣税
     */
    public static final double BUSINESS_TAX = 0.01;

    /**
     * 商户可用善点比
     * */
    public static final double BUSINESS_SHANDIAN_ENABLE = 0.8;

    /**
     * 商户备用善点比
     * */
    public static final double BUSINESS_SHANDIAN_UNABLE = 0.2;
}
