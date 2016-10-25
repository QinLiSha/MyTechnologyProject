package com.example.administrator.mytechnologyproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.activity.HomeActivity;
import com.example.administrator.mytechnologyproject.gloable.API;
import com.example.administrator.mytechnologyproject.gloable.Contacts;
import com.example.administrator.mytechnologyproject.model.PasswordRecover;
import com.example.administrator.mytechnologyproject.model.parser.ParserPasswordRecover;
import org.json.JSONObject;


public class PasswordRecoverFragment extends Fragment {
    private EditText et_recover_email;
    private Button btn_recover_enter;
    private RequestQueue requestQueue;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_password_recover, container, false);

        et_recover_email = (EditText) view.findViewById(R.id.et_recover_email);
        btn_recover_enter = (Button) view.findViewById(R.id.btn_recover_enter);

        btn_recover_enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_recover_email.getText().toString();
                if (email == null || email.length() <= 0) {
                    ((HomeActivity) getActivity()).showToast("请输入注册邮箱！");
                }else {
                    sendRequestData(email);
                }
            }
        });

        return view;
    }

    private void sendRequestData(String email) {
        requestQueue = Volley.newRequestQueue(getContext());//实例化一个RequestQueue对象
        String url = API.PASSWORD_RECOVER + "ver=" + Contacts.VER + "&email=" + email;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        PasswordRecover passwordRecover = ParserPasswordRecover.getPassword
                                (jsonObject.toString());
                        if (passwordRecover == null) {
                            ((HomeActivity) getActivity()).showToast("发送请求失败！");
                        }else if(passwordRecover.getResult()!=0) {
                            ((HomeActivity) getActivity()).showToast(passwordRecover.getExplain());
                        }else {
                            ((HomeActivity) getActivity()).showToast(passwordRecover.getExplain());
                            ((HomeActivity) getActivity()).showHomeFragment();
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
