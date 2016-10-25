package com.example.administrator.mytechnologyproject.model.parser;


import android.util.Log;

import com.example.administrator.mytechnologyproject.model.BaseEntity;
import com.example.administrator.mytechnologyproject.model.Comment;
import com.example.administrator.mytechnologyproject.model.SendCommentRes;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public class ParserComment {
    private static final String TAG = "ParserComment";
    public static int getNuOfComments(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<Integer>>() {
        }.getType();
        BaseEntity<Integer> entity = gson.fromJson(json, type);
        return entity.getData();
    }
    public static List<Comment> getCommentList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<List<Comment>>>() {
        }.getType();
        BaseEntity<List<Comment>> entity = gson.fromJson(json,type);
        return entity.getData();
    }
    public static String getSendCommentRes(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<SendCommentRes>>() {
        }.getType();
        BaseEntity<SendCommentRes> entity = gson.fromJson(json,type);
        String res = entity.getData().getExplain();
        Log.i(TAG, "getSendCommentRes: ------------------------------------" + res);
        return res;
    }
}
