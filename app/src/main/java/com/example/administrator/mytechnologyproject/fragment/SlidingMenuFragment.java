package com.example.administrator.mytechnologyproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.activity.BaiduMapActivity;
import com.example.administrator.mytechnologyproject.activity.HomeActivity;

import java.util.ArrayList;
import java.util.List;


public class SlidingMenuFragment extends Fragment implements View.OnClickListener {
    private RelativeLayout relayout_news, relayout_favorite, relayout_local,
            relayout_comment, relayout_photo;
    private RelativeLayout[] reList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sliding_menu, container, false);
        relayout_news = (RelativeLayout) view.findViewById(R.id.relayout_news);
        relayout_favorite = (RelativeLayout) view.findViewById(R.id.relayout_favorite);
        relayout_local = (RelativeLayout) view.findViewById(R.id.relayout_local);
        relayout_comment = (RelativeLayout) view.findViewById(R.id.relayout_comment);
        relayout_photo = (RelativeLayout) view.findViewById(R.id.relayout_photo);

        reList = new RelativeLayout[]{relayout_news, relayout_favorite, relayout_local,
                relayout_comment, relayout_photo};

        relayout_news.setOnClickListener(this);
        relayout_favorite.setOnClickListener(this);
        relayout_local.setOnClickListener(this);
        relayout_comment.setOnClickListener(this);
        relayout_photo.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.relayout_news:
                ((HomeActivity) getActivity()).showHomeFragment();
                setBackColor(relayout_news);
                break;
            case R.id.relayout_favorite:
                ((HomeActivity) getActivity()).showFavoriteFragment();
                setBackColor(relayout_favorite);
                break;
            case R.id.relayout_local:
                ((HomeActivity) getActivity()).openActivity(BaiduMapActivity.class);
                setBackColor(relayout_local);
                break;
            case R.id.relayout_comment:
                break;
            case R.id.relayout_photo:
                ((HomeActivity) getActivity()).showImageFragment();
                setBackColor(relayout_photo);
                break;
        }
    }

    private void setBackColor(RelativeLayout relayout) {
        for (int i = 0; i < reList.length; i++) {
            reList[i].setBackgroundColor(0xC3F3FD);
        }
        relayout.setBackgroundColor(0x33C85555);
    }
}
