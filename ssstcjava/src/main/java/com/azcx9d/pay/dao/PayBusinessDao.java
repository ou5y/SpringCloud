package com.azcx9d.pay.dao;

import com.azcx9d.pay.entity.BusinessInNet;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/15 0015.
 */
public interface PayBusinessDao {

    List<BusinessInNet> getBusinessInNetList();

    int updateBusinessInNetState(Map params);

    int businessInNetNotify(Map params);

    List<BusinessInNet> getFailBusinessList();
}
