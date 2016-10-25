package com.example.administrator.mytechnologyproject.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
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

public class RegisterFragment extends Fragment {
    private static final String TAG = "RegisterFragment";
    private EditText et_register_email,et_register_nickname,et_register_password;
    private Button btn_reg_register;
    private CheckBox cb_register_agree;
    private RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        et_register_email = (EditText) view.findViewById(R.id.et_register_email);
        et_register_nickname = (EditText) view.findViewById(R.id.et_register_nickname);
        et_register_password = (EditText) view.findViewById(R.id.et_register_password);
        btn_reg_register = (Button) view.findViewById(R.id.btn_reg_register);
        cb_register_agree = (CheckBox) view.findViewById(R.id.cb_register_agree);
        requestQueue = Volley.newRequestQueue(getActivity());
        btn_reg_register.setOnClickListener(onClickListener);
        return view;
    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String name,pwd,email;
            email = et_register_email.getText().toString();
            name = et_register_nickname.getText().toString();
            pwd = et_register_password.getText().toString();

            if (!cb_register_agree.isChecked()) {
                Toast.makeText(getActivity(),"请仔细阅读条款，并选择同意",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!CommonUtil.verifyEmail(email)) {
                Toast.makeText(getActivity(),"请输入正确的邮箱格式",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(name)) {
                Toast.makeText(getActivity(),"请输入用户姓名",Toast.LENGTH_SHORT).show();
                return;
            }
            if (!CommonUtil.verifyPassword(pwd)) {
                Toast.makeText(getActivity(),"请输入正确的密码格式（6-16位字母与数字格式）",Toast.LENGTH_SHORT).show();
                return;
            }
            String url = API.USER_REGISTER + "ver=" + Contacts.VER + "&uid=" + name + "&email=" +
                    email + "&pwd=" + pwd;
            requestRegister(url);
        }
    };

    private void requestRegister(String url) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        BaseEntity<Register> baseRegister = ParserUser.getRegisterInfo(jsonObject.toString());
                        Register register = baseRegister.getData();
                        int result = register.getResult();
                        if(result == -2) {
                            Toast.makeText(getActivity(),"用户名重复",Toast.LENGTH_SHORT).show();
                        }
                        if (result == -3) {
                            Toast.makeText(getActivity(),"邮箱重复",Toast.LENGTH_SHORT).show();
                        }
                        if (result == 0) {
                            ((HomeActivity)getActivity()).openActivity(UserActivity.class);
                            SharedUtil.saveRegisterInfo(baseRegister,getContext());
                            //((HomeActivity)getContext()).changeFragmentStatus();
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
