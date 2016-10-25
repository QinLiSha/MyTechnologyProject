package com.example.administrator.mytechnologyproject.model.parser;

import com.example.administrator.mytechnologyproject.model.BaseEntity;
import com.example.administrator.mytechnologyproject.model.PasswordRecover;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Created by Administrator on 2016/9/26.
 */
public class ParserPasswordRecover {
    public static PasswordRecover getPassword(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<BaseEntity<PasswordRecover>>() {
        }.getType();
        BaseEntity<PasswordRecover> entity = gson.fromJson(json, type);
        return entity.getData();
    }
}
