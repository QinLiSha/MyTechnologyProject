package com.example.administrator.mytechnologyproject.model;

/**
 * Created by Administrator on 2016/9/5.
 */
public class SubType {
    private String subgroup;
    private int subid;

    public SubType(String subgroup, int subid) {
        this.subgroup = subgroup;
        this.subid = subid;
    }

    public String getSubgroup() {
        return subgroup;
    }

    public void setSubgroup(String subgroup) {
        this.subgroup = subgroup;
    }

    public int getSubid() {
        return subid;
    }

    public void setSubid(int subid) {
        this.subid = subid;
    }

    @Override
    public String toString() {
        return "SubType{" +
                "subgroup='" + subgroup + '\'' +
                ", subid=" + subid +
                '}';
    }
}
