package com.example.administrator.mytechnologyproject.model;

/**
 * Created by Administrator on 2016/9/13.
 */
public class SendCommentRes {
    private int result;
    private String explain;

    public SendCommentRes(int result, String explain) {
        this.result = result;
        this.explain = explain;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public String getExplain() {
        return explain;
    }

    public void setExplain(String explain) {
        this.explain = explain;
    }

    @Override
    public String toString() {
        return "SendCommentRes{" +
                "result=" + result +
                ", explain='" + explain + '\'' +
                '}';
    }
}
