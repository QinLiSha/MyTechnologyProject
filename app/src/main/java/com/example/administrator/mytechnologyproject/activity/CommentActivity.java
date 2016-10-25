package com.example.administrator.mytechnologyproject.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.adapter.CommentAdapter;
import com.example.administrator.mytechnologyproject.base.MyBaseActivity;
import com.example.administrator.mytechnologyproject.gloable.API;
import com.example.administrator.mytechnologyproject.gloable.Contacts;
import com.example.administrator.mytechnologyproject.model.Comment;
import com.example.administrator.mytechnologyproject.model.News;
import com.example.administrator.mytechnologyproject.model.parser.ParserComment;
import com.example.administrator.mytechnologyproject.util.CommonUtil;
import com.example.administrator.mytechnologyproject.util.SharedUtil;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class CommentActivity extends MyBaseActivity implements View.OnClickListener {
    private static final String TAG = "CommentActivity";
    private ListView lv_user_list_comment;
    private EditText et_myComment;
    private Button btn_commentUp;
    private RequestQueue requestQueue;
    private News news;
    private List<Comment> comments;
    private CommentAdapter commentAdapter;
    private ImageView iv_comment_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        getNews();
        initView();

        commentAdapter = new CommentAdapter(this);
        lv_user_list_comment.setAdapter(commentAdapter);

        sendRequestData_getComment();
    }

    private void getNews() {
        Bundle bundle = getIntent().getExtras();
        news = (News) bundle.getSerializable("news");//得到news对象
    }

    private void initView() {
        lv_user_list_comment = (ListView) findViewById(R.id.lv_user_list_comment);
        et_myComment = (EditText) findViewById(R.id.et_myComment);
        btn_commentUp = (Button) findViewById(R.id.btn_commentUp);
        iv_comment_back = (ImageView) findViewById(R.id.iv_comment_back);

        btn_commentUp.setOnClickListener(this);
        iv_comment_back.setOnClickListener(this);
    }

    private void sendRequestData_getComment() {
        requestQueue = Volley.newRequestQueue(this);
        String url = API.NEWS_COMMENT_LIST + "ver=" + Contacts.VER + "&nid=" + news.getNid() +
                "&type=" + 1 + "&stamp=" + 20140707 + "&cid=" + 20 + "&dir=" + 1 + "&cnt=" + 20;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        comments = ParserComment.getCommentList(jsonObject.toString());
                        commentAdapter.appendDataed(comments, true);
                        commentAdapter.updateAdapter();
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

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
                String content = (String) msg.obj;
                String res = ParserComment.getSendCommentRes(content);
                if (res == null) {
                    showToast("发表评论失败！");
                } else {
                    showToast(res);
                    et_myComment.setText(null);
                    sendRequestData_getComment();
                }
            }
        }
    };

    private void sendQuesone_sendComment(final String content, final String token) {
        final String imei = CommonUtil.getIMEI(this);
        new Thread() {
            @Override
            public void run() {
                HttpURLConnection httpURLConnection;
                try {
                    URL url = new URL(API.NEWS_COMMENT_SEND + "ver=" + Contacts.VER + "&nid=" + news.getNid() +
                            "&token=" + token + "&imei=" + imei + "&ctx=" + content);
                    httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setConnectTimeout(8000);//连接超时时间设置
                    httpURLConnection.setReadTimeout(8000);//读取超时
                    httpURLConnection.connect();//发起连接
                    InputStream in = httpURLConnection.getInputStream();//从上面地址打开一个输入流
                    BufferedReader br = new BufferedReader(new InputStreamReader(in));
                    StringBuilder str = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        str.append(line);
                    }
                    Log.i(TAG, "run: " + str);
                    Message msg = new Message();
                    msg.what = 1;
                    msg.obj = str.toString();
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                super.run();
            }
        }.start();
    }

    private void sendRequestData_sendComment(String content, String token) {
        requestQueue = Volley.newRequestQueue(this);
        String ss = null;
        try {
            ss = URLEncoder.encode(content, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String url = API.NEWS_COMMENT_SEND + "ver=" + Contacts.VER + "&nid=" + news.getNid() +
                "&token=" + token + "&imei=" + CommonUtil.getIMEI(this) + "&ctx=" + ss;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String res = ParserComment.getSendCommentRes(jsonObject.toString());
                        if (res == null) {
                            showToast("发表评论失败！");
                        } else {
                            showToast(res);
                            et_myComment.setText(null);
                            sendRequestData_getComment();
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_commentUp:
                String content = et_myComment.getText().toString();
                String token = SharedUtil.getTokey(this, "token");
                if (content == null || content.length() <= 0) {
                    showToast("评论内容不能为空！");
                } else if (token == null || content.length() <= 0) {
                    showToast("用户未登陆，请登录后再发表评论！");
                } else {
                    //sendQuesone_sendComment(content, token);
                    sendRequestData_sendComment(content, token);
                }
                break;
            case R.id.iv_comment_back:
                Bundle bundle = new Bundle();
                bundle.putSerializable("news", news);
                this.openActivity(NewsShowActivity.class, bundle);
                finish();
        }
    }
}
