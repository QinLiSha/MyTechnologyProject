package com.example.administrator.mytechnologyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.base.MyBaseAdapter;
import com.example.administrator.mytechnologyproject.model.Loginlog;

/**
 * Created by Administrator on 2016/9/9.
 */
public class LoginAdapter extends MyBaseAdapter<Loginlog>{

    public LoginAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_login_item, null);
            vh = new ViewHolder();
            vh.tv_loginTime = (TextView) view.findViewById(R.id.tv_loginTime);
            vh.tv_loginAddress = (TextView) view.findViewById(R.id.tv_loginAddress);
            vh.tv_loginDevice = (TextView) view.findViewById(R.id.tv_loginDevice);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        vh.tv_loginTime.setText(getItem(i).getTime());
        vh.tv_loginAddress.setText(getItem(i).getAddress());
        vh.tv_loginDevice.setText(getItem(i).getDevice() + "");
        return view;
    }

    private class ViewHolder {
        TextView tv_loginTime,tv_loginAddress,tv_loginDevice;
    }
}
