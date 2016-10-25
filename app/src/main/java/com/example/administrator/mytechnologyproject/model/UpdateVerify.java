package com.example.administrator.mytechnologyproject.model;

/**
 * Created by Administrator on 2016/9/14.
 */
public class UpdateVerify {
    private String pkgName;
    private String link;
    private String md5;
    private int version;

    public UpdateVerify(String pkgName, String link, String md5, int version) {
        this.pkgName = pkgName;
        this.link = link;
        this.md5 = md5;
        this.version = version;
    }

    public String getPkgName() {
        return pkgName;
    }

    public void setPkgName(String pkgName) {
        this.pkgName = pkgName;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "UpdateVerify{" +
                "pkgName='" + pkgName + '\'' +
                ", link='" + link + '\'' +
                ", md5='" + md5 + '\'' +
                ", version='" + version + '\'' +
                '}';
    }
}
