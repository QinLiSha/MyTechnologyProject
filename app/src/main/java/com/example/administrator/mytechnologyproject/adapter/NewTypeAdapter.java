package com.example.administrator.mytechnologyproject.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.base.MyBaseAdapter;
import com.example.administrator.mytechnologyproject.model.SubType;

/**
 * Created by Administrator on 2016/9/6.
 */
public class NewTypeAdapter extends MyBaseAdapter<SubType> {

    public NewTypeAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_newtype_item, null);
            vh = new ViewHolder();
            vh.tv_newstype_item = (TextView) view.findViewById(R.id.tv_newstype_item);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        String item = getItem(i).getSubgroup();
        vh.tv_newstype_item.setText(item);
        return view;
    }

    private class ViewHolder {
        TextView tv_newstype_item;
    }
}
