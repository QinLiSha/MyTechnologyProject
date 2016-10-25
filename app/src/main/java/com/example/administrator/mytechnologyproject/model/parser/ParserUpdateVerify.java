package com.example.administrator.mytechnologyproject.model.parser;

import com.example.administrator.mytechnologyproject.model.BaseEntity;
import com.example.administrator.mytechnologyproject.model.UpdateVerify;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/9/14.
 */
public class ParserUpdateVerify {
    public static BaseEntity<UpdateVerify> getUpdateVerify(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<UpdateVerify>>() {
        }.getType();
        BaseEntity<UpdateVerify> entity = gson.fromJson(json, type);
        return entity;
    }
}
