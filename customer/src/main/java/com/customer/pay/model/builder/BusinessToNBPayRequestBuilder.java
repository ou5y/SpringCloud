package com.customer.pay.model.builder;

import com.google.gson.annotations.SerializedName;

/**
 * Created by fangbaoyan on 2017/6/3.
 */
public class BusinessToNBPayRequestBuilder extends RequestBuilder{


    private BizContent bizContent = new BizContent();



    @Override
    public boolean validate() {
        return true;
    }




    @Override
    public Object getBizContent() {
        return null;
    }

    public static class BizContent {

        //商户对外经营名称
        @SerializedName("merchant_name")
        private String merchantName;

        //商户地区编码
        private String merchDiv;

        //商户类型编号（可根据自己的商户类型定义发送）
        private String mccNum;

        //商户类型（可根据自己的商户类型定义发送）
        private String mcc;

        //商户工商注册名称
        private String companyName;

        //商户英文名称
        private String merchEngName;

        //营业执照照片地址
        private String licenceLink;

        //银行代码
        private String bankCode;

        //开户银行
        private String bankName;

        //开户银行支行名称
        private String bankBranch;

        //开户许可证照片地址
        private String bankCertLink;

        //公司对公账户
        private String bankAccountNo;

        //公司对公账户名称
        private String bankAccountName;

        //开户银行所在城市，省
        private String bankProvince;

        //开户银行所在城市，市
        private String bankCity;

        //法定代表人归属地；0：中国大陆；1：港澳；2：台湾；3：外籍
        private String location;

        //证件类型。01：居民身份证02: 临时居民身份证;03: 户口簿;04军人身份证;05: 港澳居民往来内地通行证;06: 台湾居民来往大陆通行证;07: 护照;08: 其他证件;
        private String certType;

        //法人代表真实姓名
        private String realName;

        //法人代表证件编号
        private String certId;

        //法人证件照片地址1（如果是身份证，请上传身份证正面照片）
        private String certLink1;

        //法人证件照片地址2（如果是身份证，请上传身份证反面照片）
        private String certLink2;

        //法人代表手机号
        private String phoneNoCipher;


        //业务联系人
        private String bizLinkman;

        //业务联系电话
        private String bizPhone;

        /*裕顺返回商户编号，用于审核失败后，修改信息重新发起请求
        新增商户时，此项为空
        更新商户时，此项填写裕顺返回的该商户的商户编号*/
        private String returnMerchantNumber;

    }

}
