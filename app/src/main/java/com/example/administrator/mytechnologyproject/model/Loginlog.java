package com.example.administrator.mytechnologyproject.model;

/**
 * Created by Administrator on 2016/9/9.
 */
public class Loginlog {
    private String time;
    private String address;
    private int device;

    public Loginlog(String time, String address, int device) {
        this.time = time;
        this.address = address;
        this.device = device;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDevice() {
        return device;
    }

    public void setDevice(int device) {
        this.device = device;
    }

    @Override
    public String toString() {
        return "Loginlog{" +
                "time='" + time + '\'' +
                ", address='" + address + '\'' +
                ", device=" + device +
                '}';
    }
}
