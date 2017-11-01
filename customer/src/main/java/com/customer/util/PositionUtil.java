package com.customer.util;

import com.customer.entity.Position;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/5/5 0005.
 * 位置计算工具类
 */
public class PositionUtil {

    /**
     * 地球半径 ,米
     */
    private static final double EARTH_RADIUS = 6378138;
    /**
     * 范围距离
     */
    private double distance;
    /**
     * 左上角
     */
    private Position left_top = null;
    /**
     * 右上角
     */
    private Position right_top = null;
    /**
     * 左下角
     */
    private Position left_bottom = null;
    /**
     * 右下角
     */
    private Position right_bottom = null;

    public PositionUtil(){

    }

    private PositionUtil(double distance) {
        this.distance = distance;
    }

    /**
     * 获取四个点坐标
     * @param lat 纬度 x
     * @param lng 经度 y
     */
    private void getRectangle4Point(double lat, double lng) {
        double dlng = 2 * Math.asin(Math.sin(distance/(2*EARTH_RADIUS))/Math.cos(Math.toRadians(lat)));
        dlng = Math.toDegrees(dlng);

        double dlat = distance / EARTH_RADIUS;
        dlat = Math.toDegrees(dlat); // # 弧度转换成角度

        left_top = new Position(lat + dlat, lng - dlng);
        right_top = new Position(lat + dlat, lng + dlng);
        left_bottom = new Position(lat - dlat, lng - dlng);
        right_bottom = new Position(lat - dlat, lng + dlng);

    }

    /**
     * 公式计算球面两点间的距离,单位米
     * @param lat0 纬度0 x
     * @param lng0 经度0 y
     * @param lat1 纬度1 x
     * @param lng1 经度1 y
     * @return double 距离
     */
    public static double getDistance(double lat0, double lng0, double lat1,double lng1) {
        double radLat1 = Math.toRadians(lat0);
        double radLat2 = Math.toRadians(lat1);
        double a = radLat1 - radLat2;
        double b = Math.toRadians(lng0) - Math.toRadians(lng1);
        double distance = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        distance = distance * EARTH_RADIUS;
        distance = Math.round(distance * 10000) / 10000;
        return distance;
    }

    /**
     * 根据经纬度、距离获取4个点坐标
     * @param lat
     * @param lng
     * @param distance
     * @return Map<String,Double>
     */
    public static Map<String,Double> getRectangle4Point(double lat, double lng,double distance) {
        PositionUtil positionUtil = new PositionUtil(distance);
        positionUtil.getRectangle4Point(lat, lng);
        Map<String, Double> positions = new HashMap<String, Double>(0);
        positions.put("latitude1",positionUtil.left_bottom.getLatitude());
        positions.put("latitude2",positionUtil.left_top.getLatitude());
        positions.put("longitude1",positionUtil.left_bottom.getLongitude());
        positions.put("longitude2",positionUtil.right_bottom.getLongitude());
        return positions;
    }

}
