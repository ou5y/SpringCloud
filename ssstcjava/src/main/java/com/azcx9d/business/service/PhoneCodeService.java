package com.azcx9d.business.service;

/**
 * Created by HuangQing on 2017/6/26 0026 14:04.
 */
public interface PhoneCodeService {

    String setPhoneAndCode(String phone, String code);

    boolean checkPhoneCode(String phone, String code);

}
