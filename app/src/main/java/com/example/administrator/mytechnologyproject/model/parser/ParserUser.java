package com.example.administrator.mytechnologyproject.model.parser;

import com.example.administrator.mytechnologyproject.model.BaseEntity;
import com.example.administrator.mytechnologyproject.model.Register;
import com.example.administrator.mytechnologyproject.model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Administrator on 2016/9/8.
 */
public class ParserUser {

    /**
     * 解析用户注册信息
     * @param json
     * @return
     */
    public static BaseEntity<Register> getRegisterInfo(String json) {
        Gson gson = new Gson();
        BaseEntity<Register> registerBaseEntity = gson.fromJson(json, new
                TypeToken<BaseEntity<Register>>() {
                }.getType()
        );
        return registerBaseEntity;
    }

    /**
     * 解析登录成功的用户数据
     * @param json
     * @return
     */
    public static BaseEntity<User> getLoginSuccInfo(String json) {
        Gson gson = new Gson();
        BaseEntity<User> userBaseEntity = gson.fromJson(json, new TypeToken<BaseEntity<User>>() {
        }.getType());
        return userBaseEntity;
    }
}
