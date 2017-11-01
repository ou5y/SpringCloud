package com.customer.util;

import com.customer.dto.AgencyProfit;
import com.customer.dto.NearbyBusinessDto;

import java.util.Comparator;

/**
 * Created by Administrator on 2017/5/22 0022.
 */
public class NearbyBusinessComparator implements Comparator {
    @Override
    public   int  compare(Object o1, Object o2) {
        return  ((NearbyBusinessDto) o1).getDistance()  -  ((NearbyBusinessDto) o2).getDistance();
    }
}
