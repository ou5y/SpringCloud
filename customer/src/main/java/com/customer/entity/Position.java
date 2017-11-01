package com.customer.entity;

/**
 * Created by Administrator on 2017/5/5 0005.
 * 位置
 */
public class Position {

    private double latitude;		    //纬度
    private double longitude;		//经度

    public Position(){

    }

    public Position(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
