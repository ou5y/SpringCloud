package com.customer.util;

import com.customer.dto.AgencyProfit;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
public class CalendarComparator implements Comparator {
    @Override
    public int compare(Object object1, Object object2){
        AgencyProfit profit1 = (AgencyProfit)object1;
        AgencyProfit profit2 = (AgencyProfit)object2;
        if(profit1.getBdsj().getTime()<profit2.getBdsj().getTime()){
            return -1;
        }else if(profit1.getBdsj().getTime()>profit2.getBdsj().getTime()){
            return 1;
        }else{
            return 0;
        }
    }
}
