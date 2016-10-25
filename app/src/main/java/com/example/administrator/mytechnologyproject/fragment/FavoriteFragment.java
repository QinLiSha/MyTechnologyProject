package com.example.administrator.mytechnologyproject.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.activity.HomeActivity;
import com.example.administrator.mytechnologyproject.activity.NewsShowActivity;
import com.example.administrator.mytechnologyproject.adapter.NewsListAdapter;
import com.example.administrator.mytechnologyproject.model.News;
import com.example.administrator.mytechnologyproject.util.DBManager.DBTools;

import java.util.List;

public class FavoriteFragment extends Fragment {
    private ListView lv_favorite_listView;
    private NewsListAdapter adapter;
    private TextView tv_noFavorite;
    private DBTools dbTools;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite, container, false);

        lv_favorite_listView = (ListView) view.findViewById(R.id.lv_favorite_listView);
        tv_noFavorite = (TextView) view.findViewById(R.id.tv_noFavorite);

        adapter = new NewsListAdapter(getActivity());
        lv_favorite_listView.setAdapter(adapter);
        lv_favorite_listView.setOnItemClickListener(onItemClickListener);
        dbTools = new DBTools(getContext());
        getData();
        return view;
    }

    private void getData() {
        List<News> list = dbTools.getLocalFavorite();
        if (list.size()!=0){
            adapter.appendDataed(list, true);
            adapter.updateAdapter();
        }else{
            lv_favorite_listView.setVisibility(View.GONE);
            tv_noFavorite.setVisibility(View.VISIBLE);
        }
    }

    private AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
            Bundle bundle = new Bundle();
            News news = adapter.getItem(i);
            bundle.putSerializable("news",news);
            ((HomeActivity)getActivity()).openActivity(NewsShowActivity.class,bundle);
        }
    };
}
