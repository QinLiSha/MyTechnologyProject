package com.example.administrator.mytechnologyproject.activity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.base.MyBaseActivity;
import com.example.administrator.mytechnologyproject.gloable.API;
import com.example.administrator.mytechnologyproject.gloable.Contacts;
import com.example.administrator.mytechnologyproject.model.News;
import com.example.administrator.mytechnologyproject.model.parser.ParserComment;
import com.example.administrator.mytechnologyproject.model.parser.ParserNews;
import com.example.administrator.mytechnologyproject.util.CommonUtil;
import com.example.administrator.mytechnologyproject.util.DBManager.DBTools;

import org.json.JSONObject;

import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

public class NewsShowActivity extends MyBaseActivity implements View.OnClickListener {
    private static final String TAG = "NewsShowActivity";
    private WebView webView;
    private ProgressBar progressBar;
    private PopupWindow popupWindow;
    private ImageView iv_show_menu, iv_NewsShow_back;
    private TextView tv_comments;
    private DBTools dbTools;
    private News news;
    private RequestQueue requestQueue;
    private int numOfComments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!CommonUtil.isNetworkAvailable(this)) {
            setContentView(R.layout.no_network);
        } else {
            setContentView(R.layout.activity_news_show);
            ShareSDK.initSDK(this);

            dbTools = new DBTools(this);

            Bundle bundle = getIntent().getExtras();
            news = (News) bundle.getSerializable("news");//得到news对象

            initView();//加载布局

            webSet();//网页设置

            showPopupWindow();//菜单显示

            getNoComments();//跟帖数设置

        }
    }

    private void showShare() {
        ShareSDK.initSDK(this);
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间等使用
        oks.setTitle(news.getTitle());
        // titleUrl是标题的网络链接，QQ和QQ空间等使用
        oks.setTitleUrl(news.getLink());
        // text是分享文本，所有平台都需要这个字段
        oks.setText(news.getSummary());

        oks.setImageUrl(news.getIcon());
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        //oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(news.getLink());
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(news.getLink());

// 启动分享GUI
        oks.show(this);
    }

    private void initView() {
        webView = (WebView) findViewById(R.id.myWebView);
        progressBar = (ProgressBar) findViewById(R.id.pb_progressBar);
        iv_NewsShow_back = (ImageView) findViewById(R.id.iv_NewsShow_back);
        iv_show_menu = (ImageView) findViewById(R.id.iv_show_menu);
        tv_comments = (TextView) findViewById(R.id.tv_comments);

        iv_NewsShow_back.setOnClickListener(this);
        iv_show_menu.setOnClickListener(this);
        tv_comments.setOnClickListener(this);
    }

    private void getNoComments() {
        requestQueue = Volley.newRequestQueue(this);
        String url = API.NEWS_COMMENT + "ver=" + Contacts.VER + "&nid=" + news.getNid();
        Log.i(TAG, "getNoComments: ----------------------" + url);
        sendRequestData_comment(url);
    }

    private void sendRequestData_comment(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        numOfComments = ParserComment.getNuOfComments(jsonObject.toString());
                        if (numOfComments == 0) {
                            tv_comments.setVisibility(View.INVISIBLE);
                        }else {
                            tv_comments.setText("跟帖："+numOfComments);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }

    private void webSet() {
        WebSettings webSettings = webView.getSettings();

        webSettings.setJavaScriptEnabled(true);//设置支持JavaScript脚本
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点

        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        webSettings.setDisplayZoomControls(false);
        webSettings.setAllowFileAccess(true);//允许访问文件
        webSettings.setBuiltInZoomControls(true);//设置显示缩放按钮
        webSettings.setSupportZoom(true);//支持缩放

        webSettings.setLoadWithOverviewMode(true);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if (mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        } else if (mDensity == DisplayMetrics.DENSITY_XHIGH) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == DisplayMetrics.DENSITY_TV) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }

        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        WebChromeClient client = new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                progressBar.setProgress(newProgress);
                if (newProgress >= 100)
                    progressBar.setVisibility(View.GONE);
            }
        };
        webView.setWebChromeClient(client);

        /**
         * 用于WebView显示图片，可使用这个参数设置网页布局类型：
         * 1、LayoutAlgorithm.NARROW_COLUMNS:适应内容大小
         * 2、LayoutAlgorithm.SINGLE_COLUMN:适应屏幕，内容将自动缩放
         */
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        webView.loadUrl(news.getLink());
    }

    private void showPopupWindow() {
        View view = getLayoutInflater().inflate(R.layout.layout_favorite, null);
        TextView tv = (TextView) view.findViewById(R.id.tv_favorite);
        TextView tv_share = (TextView) view.findViewById(R.id.tv_share);
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tv.setOnClickListener(this);
        tv_share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_favorite:
                popupWindow.dismiss();
                boolean isSaved = dbTools.saveLocalFavorite(news);
                if (isSaved) {
                    showToast("收藏成功！");
                } else {
                    showToast("已收藏过，请重新添加！");
                }
                break;
            case R.id.tv_share:
                popupWindow.dismiss();
                showToast("跳转分享！");
                showShare();
                break;
            case R.id.iv_NewsShow_back:
                openActivity(HomeActivity.class);
                finish();
                break;
            case R.id.iv_show_menu:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else if (popupWindow != null) {
                    popupWindow.showAsDropDown(iv_show_menu, 0, 5);
                }
                break;
            case R.id.tv_comments:
                Bundle bundle = new Bundle();
                bundle.putSerializable("news", news);
                this.openActivity(CommentActivity.class, bundle);
                break;
        }
    }
}
