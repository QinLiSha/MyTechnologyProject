package com.example.administrator.mytechnologyproject.model.parser;

import com.example.administrator.mytechnologyproject.model.BaseEntity;
import com.example.administrator.mytechnologyproject.model.NewsType;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class ParserNewsType {
    public static List<NewsType> getNewsTypeList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<List<NewsType>>>() {
        }.getType();
        BaseEntity<List<NewsType>> entity = gson.fromJson(json, type);
        return entity.getData();
    }
}

