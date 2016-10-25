package com.example.administrator.mytechnologyproject.adapter;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.base.MyBaseAdapter;
import com.example.administrator.mytechnologyproject.bean.MyImageLoader;
import com.example.administrator.mytechnologyproject.model.Comment;

/**
 * Created by Administrator on 2016/9/12.
 */
public class CommentAdapter extends MyBaseAdapter<Comment>{

    public CommentAdapter(Context context) {
        super(context);
    }

    @Override
    public View getMyView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.layout_comment_item, null);
            vh = new ViewHolder();
            vh.tv_userList_name = (TextView) view.findViewById(R.id.tv_userList_name);
            vh.tv_userList_time = (TextView) view.findViewById(R.id.tv_userList_time);
            vh.tv_userList_content = (TextView) view.findViewById(R.id.tv_userList_content);
            vh.iv_userList_icon = (ImageView) view.findViewById(R.id.iv_userList_icon);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) view.getTag();
        }
        Comment comment = getItem(i);
        vh.tv_userList_name.setText(comment.getUid());
        vh.tv_userList_time.setText(comment.getStamp());
        vh.tv_userList_content.setText(comment.getContent());
        new MyImageLoader(context).display(comment.getPortrait(),vh.iv_userList_icon);
        return view;
    }

    private class ViewHolder {
        TextView tv_userList_name,tv_userList_time,tv_userList_content;
        ImageView iv_userList_icon;
    }
}
