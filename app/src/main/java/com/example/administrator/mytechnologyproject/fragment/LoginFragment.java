package com.example.administrator.mytechnologyproject.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.activity.HomeActivity;
import com.example.administrator.mytechnologyproject.activity.UserActivity;
import com.example.administrator.mytechnologyproject.gloable.API;
import com.example.administrator.mytechnologyproject.gloable.Contacts;
import com.example.administrator.mytechnologyproject.model.BaseEntity;
import com.example.administrator.mytechnologyproject.model.Register;
import com.example.administrator.mytechnologyproject.model.parser.ParserUser;
import com.example.administrator.mytechnologyproject.util.CommonUtil;
import com.example.administrator.mytechnologyproject.util.SharedUtil;

import org.json.JSONObject;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private Button btn_register, btn_login;
    private EditText et_loginName, et_loginPassword;
    private RequestQueue requestQueue;
    private TextView tv_notLogin;
    private Dialog noticeDialog;
    private PopupWindow popupWindow;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login, container, false);
        btn_register = (Button) view.findViewById(R.id.btn_register);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        et_loginName = (EditText) view.findViewById(R.id.et_loginName);
        et_loginPassword = (EditText) view.findViewById(R.id.et_loginPassword);
        tv_notLogin = (TextView) view.findViewById(R.id.tv_notLogin);

        btn_register.setOnClickListener(this);
        btn_login.setOnClickListener(this);
        tv_notLogin.setOnClickListener(this);

        requestQueue = Volley.newRequestQueue(getActivity());

        showPopupWindow();

        return view;
    }

    private void showPopupWindow() {
        View popView = getActivity().getLayoutInflater().inflate(R.layout.layout_popuwindow_findpass, null);
        TextView tv_find_pass = (TextView) popView.findViewById(R.id.tv_find_pass);
        TextView tv_find_cancle = (TextView) popView.findViewById(R.id.tv_find_cancle);
        popupWindow = new PopupWindow(popView, ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        tv_find_pass.setOnClickListener(this);
        tv_find_cancle.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                ((HomeActivity) getActivity()).showRegisterFragment();
                break;
            case R.id.btn_login:
                String name = et_loginName.getText().toString();
                String pwd = et_loginPassword.getText().toString();
                if (TextUtils.isEmpty(name)) {
                    Toast.makeText(getActivity(), "用户名不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!CommonUtil.verifyPassword(pwd)) {
                    Toast.makeText(getActivity(), "请输入6-16位字母与数字格式", Toast.LENGTH_SHORT).show();
                    return;
                }
                String url = API.USER_LONGIN + "ver=" + Contacts.VER + "&uid=" + name + "&pwd=" +
                        pwd + "&device=" + "0";
                requestLogin(url);
                break;
            case R.id.tv_notLogin:
                if (popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else if (popupWindow != null) {
                    popupWindow.showAtLocation(view, Gravity.BOTTOM,0,0);
                }
                break;
            case R.id.tv_find_pass:
                popupWindow.dismiss();
                ((HomeActivity)getActivity()).showPasswordRecoverFragment();
                break;
            case R.id.tv_find_cancle:
                popupWindow.dismiss();
                break;
        }
    }

    /**
     * 弹出无法登录的选择框
     */
    private void showNoticeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("无法登录");
        builder.setMessage("无法登录？请试试找回密码吧！");
        builder.setPositiveButton("找回密码", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                ((HomeActivity)getActivity()).showPasswordRecoverFragment();
            }
        });
        builder.setNegativeButton("以后再说", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        noticeDialog = builder.create();
        noticeDialog.show();
    }

    private void requestLogin(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        BaseEntity<Register> baseRegister = ParserUser.getRegisterInfo(jsonObject.toString());
                        int result = baseRegister.getStatus();
                        if (result == 0) {
                            ((HomeActivity)getActivity()).openActivity(UserActivity.class);
                            SharedUtil.saveRegisterInfo(baseRegister,getContext());
                            //((HomeActivity)getContext()).changeFragmentStatus();
                        }
                        if (result == -1) {
                            Toast.makeText(getActivity(),"用户名或密码错误",Toast.LENGTH_SHORT).show();
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
}
