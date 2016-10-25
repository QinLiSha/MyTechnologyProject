package com.example.administrator.mytechnologyproject.model.parser;

import com.example.administrator.mytechnologyproject.model.BaseEntity;
import com.example.administrator.mytechnologyproject.model.News;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by Administrator on 2016/9/9.
 */
public class ParserNews {
    public static List<News> getNewsList(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<List<News>>>() {
        }.getType();
        BaseEntity<List<News>> entity = gson.fromJson(json, type);
        List<News> newsList = entity.getData();
        return newsList;
    }
}
