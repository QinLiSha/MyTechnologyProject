package com.example.administrator.mytechnologyproject.application;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;
import com.example.administrator.mytechnologyproject.util.CrashHandler;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by Administrator on 2016/9/19.
 */
public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        SDKInitializer.initialize(getApplicationContext());
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getApplicationContext());
    }
}
