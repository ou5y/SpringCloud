package com.customer.dto;

import com.customer.util.CalendarUtil;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by fangbaoyan on 2017/5/18.
 */
public class AgencyProfit{

    private Double bdsz;

    private Date bdsj;

    public Double getBdsz() {
        return bdsz;
    }

    public void setBdsz(Double bdsz) {
        this.bdsz = bdsz;
    }

    public Date getBdsj() {
        return bdsj;
    }

    public void setBdsj(Date bdsj) {
        this.bdsj = bdsj;
    }

    // 重写继承自父类Object的方法，满足Book类信息描述的要求
    public String toString() {
        String showStr = CalendarUtil.formatDateByString(bdsj) + "\t" + bdsz; // 定义显示类信息的字符串
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy年MM月dd日");
        return showStr; // 返回类信息字符串
    }

}
