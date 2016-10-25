package com.example.administrator.mytechnologyproject.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class User implements Serializable{
    private String uid;
    private String portrait;
    private int integration;
    private int comnum;
    private List<Loginlog> loginlog;

    public User(String uid, String portrait, int integration, int comnum, List<Loginlog> loginlog) {
        this.uid = uid;
        this.portrait = portrait;
        this.integration = integration;
        this.comnum = comnum;
        this.loginlog = loginlog;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public int getIntegration() {
        return integration;
    }

    public void setIntegration(int integration) {
        this.integration = integration;
    }

    public int getComnum() {
        return comnum;
    }

    public void setComnum(int comnum) {
        this.comnum = comnum;
    }

    public List<Loginlog> getLoginlog() {
        return loginlog;
    }

    public void setLoginlog(List<Loginlog> loginlog) {
        this.loginlog = loginlog;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", portrait='" + portrait + '\'' +
                ", integration=" + integration +
                ", comnum=" + comnum +
                ", loginlog=" + loginlog +
                '}';
    }
}
