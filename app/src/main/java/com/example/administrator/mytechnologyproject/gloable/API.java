package com.example.administrator.mytechnologyproject.gloable;

/**
 * Created by Administrator on 2016/9/5.
 */
public class API {
    public static final String ServerIP = "http://118.244.212.82:9092/newsClient/";

    /**
     * 首页列表
     */
    public static final String NEWS_LIST = ServerIP + "news_list?";

    /**
     * 标题列表
     */
    public static final String NEWS_SORT = ServerIP + "news_sort?";

    /**
     * 用户登录接口
     */
    public static final String USER_LONGIN = ServerIP + "user_login?";

    /**
     * 用户注册接口
     */
    public static final String USER_REGISTER = ServerIP + "user_register?";

    /**
     * 用户中心数据
     */
    public static final String USER_CENTER_DATA = ServerIP + "user_home?";

    /**
     * 跟帖数接口
     */
    public static final String NEWS_COMMENT = ServerIP + "cmt_num?";

    /**
     * 评论接口
     */
    public static final String NEWS_COMMENT_LIST = ServerIP + "cmt_list?";

    /**
     * 发表评论接口
     */
    public static final String NEWS_COMMENT_SEND = ServerIP + "cmt_commit?";

    /**
     * 更新Apk接口
     */
    public static final String VERIFY_UPDATE_APK = ServerIP + "update?";

    /**
     * 找回密码接口
     */
    public static final String PASSWORD_RECOVER = ServerIP + "user_forgetpass?";
}
