package com.example.administrator.mytechnologyproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.base.MyBaseAdapter;
import com.example.administrator.mytechnologyproject.bean.MyImageLoader;
import com.example.administrator.mytechnologyproject.model.News;

/**
 * Created by Administrator on 2016/9/8.
 */
public class NewsListAdapter extends MyBaseAdapter<News>{

    public NewsListAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_news_list_item, null);
            vh = new ViewHolder();
            vh.summary = (TextView) view.findViewById(R.id.tv_news_summary);
            vh.stamp = (TextView) view.findViewById(R.id.tv_news_stamp);
            vh.title = (TextView) view.findViewById(R.id.tv_news_title);
            vh.link = (TextView) view.findViewById(R.id.tv_news_link);
            vh.icon = (ImageView) view.findViewById(R.id.iv_news_icon);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        News news = getItem(i);
        vh.title.setText(news.getTitle());
        vh.stamp.setText(news.getStamp());
        vh.summary.setText(news.getSummary());
        vh.link.setText(news.getLink());
        new MyImageLoader(context).display(getItem(i).getIcon(),vh.icon);
        return view;
    }

    private class ViewHolder {
        TextView summary,stamp,title,link;
        ImageView icon;
    }
}
