package com.example.administrator.mytechnologyproject.model;

import java.util.List;

/**
 * Created by Administrator on 2016/9/5.
 */
public class NewsType {
    private List<SubType> subgrp;
    private int gid;
    private String group;

    public List<SubType> getSubgrp() {
        return subgrp;
    }

    public void setSubgrp(List<SubType> subgrp) {
        this.subgrp = subgrp;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "NewsType{" +
                "subgrp=" + subgrp +
                ", gid=" + gid +
                ", group='" + group + '\'' +
                '}';
    }
}
