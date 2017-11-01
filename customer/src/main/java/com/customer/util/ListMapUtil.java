package com.customer.util;

import com.customer.dto.AgencyProfit;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Administrator on 2017/5/22 0022.
 */

public class ListMapUtil {

//    /**
//     * 合并list
//     * @param oldList
//     * @param newList
//     * @return
//     */
//    public static List<Map<String,Object>> mapMerge(List<Map<String,Object>> oldList,List<Map<String,Object>> newList){
//
//        List<String> keyMap = new ArrayList<String>(0);
//        for(int i=0; i<oldList.size(); i++){
//            Map<String,Object> oldMap = oldList.get(i);
//            for(String key :oldMap.keySet()){
//                if(key.equals("bdsj")){
//                    keyMap.add(oldMap.get(key).toString());
//                }
//            }
//        }
//
//        for(int i=0; i<newList.size(); i++){
//            Map<String,Object> newMap = newList.get(i);
//            for(String key :newMap.keySet()){
//                if(key.equals("bdsj")&&!keyMap.contains(newMap.get(key).toString())){
//                    keyMap.add(newMap.get(key).toString());
//                    Map<String,Object> tempMap = new HashMap();
//                    tempMap.put("bdsj",newMap.get(key).toString());
//                    tempMap.put("bdsz",0);
//                    oldList.add(tempMap);
//                }
//            }
//        }
//
//        for(int i=0; i<oldList.size(); i++){
//            Map<String,Object> oldMap = oldList.get(i);
//            System.out.println("oldMap:"+oldMap.toString());
//            if(newList.size()>0){
//                boolean isContain = false;
//                Map<String,Object> newMap = null;
//                for(int j=0; j<newList.size();j++){
//                    newMap = newList.get(j);
//                    System.out.println("newMap:"+newMap.toString());
//                    for(String key :oldMap.keySet()){
//                        System.out.println("key:"+key+",value:"+newMap.get(key)+",oldMap:"+oldMap.get("bdsj"));
//                        if(key.equals("bdsj")&&newMap.get("bdsj").equals(oldMap.get("bdsj"))) {
//                            oldMap.put("bdsz", Arith.add(Double.valueOf(oldMap.get("bdsz").toString()),Double.valueOf(newMap.get("bdsz").toString())));
//                        }
//                    }
//                }
//            }
//        }
//        return oldList;
//    }

    /**
     * 合并list
     * @param oldList
     * @param newList
     * @return
     */
    public static List<AgencyProfit> listClassMerge(List<AgencyProfit> oldList, List<AgencyProfit> newList){

        List<String> keyMap = new ArrayList<String>(0);
        for(int i=0; i<oldList.size(); i++){
            AgencyProfit oldMap = oldList.get(i);
              keyMap.add(CalendarUtil.formatDateByString(oldMap.getBdsj()));
        }
        for(int i=0; i<newList.size(); i++){
            AgencyProfit newMap = newList.get(i);
             if(!keyMap.contains(CalendarUtil.formatDateByString(newMap.getBdsj()))){
                 AgencyProfit profit = new AgencyProfit();
                 profit.setBdsj(newMap.getBdsj());
                 profit.setBdsz(new Double(0));
                 oldList.add(profit);
            }
        }

        for(int i=0; i<oldList.size(); i++){
            AgencyProfit oldMap = oldList.get(i);
//            System.out.println("oldMap:"+oldMap.toString());
            if(newList.size()>0){
                for(int j=0; j<newList.size();j++){
                    AgencyProfit newMap = newList.get(j);
//                    System.out.println("newMap:"+newMap.toString());
                    if(CalendarUtil.formatDateByString(newMap.getBdsj()).equals(CalendarUtil.formatDateByString(oldMap.getBdsj()))) {
                        oldMap.setBdsz(Arith.add(oldMap.getBdsz(),newMap.getBdsz()));
                    }
                }
            }
        }
        CalendarComparator sort = new CalendarComparator();
        Collections.sort(oldList,sort);
//        for(int i=0;i<oldList.size();i++){
//            AgencyProfit temp = (AgencyProfit)oldList.get(i);
//            System.out.println(CalendarUtil.formatDateByString(temp.getBdsj()));
//        }
        return oldList;
    }

}
