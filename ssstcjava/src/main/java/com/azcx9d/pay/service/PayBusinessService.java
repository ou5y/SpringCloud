package com.azcx9d.pay.service;

import com.azcx9d.common.entity.JsonResult;

import java.io.IOException;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
public interface PayBusinessService {

    JsonResult businessInNet() throws Exception;

    JsonResult businessInNetNotify(Map params) throws Exception;

    JsonResult businessModify(Map params) throws Exception;

    JsonResult modifyFailBusiness() throws Exception;
}
