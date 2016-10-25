package com.example.administrator.mytechnologyproject.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.base.MyBaseAdapter;
import com.example.administrator.mytechnologyproject.bean.MyImageLoader;

/**
 * Created by Administrator on 2016/9/20.
 */
public class MyImageGridViewAdapter extends MyBaseAdapter<String>{
    public MyImageGridViewAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_image_gridview_item, null);
            vh = new ViewHolder();
            vh.imageViewItem = (ImageView) view.findViewById(R.id.iv_image_item);
            view.setTag(vh);
        }else {
            vh = (ViewHolder) view.getTag();
        }
        new MyImageLoader(context).display(getItem(i),vh.imageViewItem);
        return view;
    }

    private class ViewHolder {
        ImageView imageViewItem;
    }
}
