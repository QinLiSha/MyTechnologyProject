package com.example.administrator.mytechnologyproject.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.adapter.MyPagerAdapter;
import com.example.administrator.mytechnologyproject.base.MyBaseActivity;
import com.example.administrator.mytechnologyproject.util.SharedUtil;

public class LeadActivity extends MyBaseActivity {
    private ViewPager viewPager;
    private MyPagerAdapter adapter;
    private ImageView[] imageViews = new ImageView[4];
    private boolean isFirstRunning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        isFirstRunning = SharedUtil.getBoolean(this,"isFirst",true);
        if(isFirstRunning) {
            isFirstRunning =false;
            savePreferences();
            setContentView(R.layout.activity_lead);
            initView();
            init();
            initData();
        }else {
            openActivity(LoginActivity.class);
            finish();
        }


    }

    /**
     * 生成用户配置信息
     */
    private void savePreferences() {
        SharedUtil.putBoolean(this,"isFirst",isFirstRunning);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        imageViews[0] = (ImageView) findViewById(R.id.lead_icon1);
        imageViews[1] = (ImageView) findViewById(R.id.lead_icon2);
        imageViews[2] = (ImageView) findViewById(R.id.lead_icon3);
        imageViews[3] = (ImageView) findViewById(R.id.lead_icon4);

        imageViews[0].setAlpha(1.0f);
    }

    /**
     * 创建适配器，设置监听
     */
    private void init() {
        adapter = new MyPagerAdapter(this);
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(listener);
    }

    /**
     * 导入数据
     */
    private void initData() {
        ImageView imageView;
        imageView = (ImageView) getLayoutInflater().inflate(R.layout.viewpage_item, null);
        imageView.setBackgroundResource(R.drawable.wy);
        adapter.addToAdapterView(imageView);

        imageView = (ImageView) getLayoutInflater().inflate(R.layout.viewpage_item, null);
        imageView.setBackgroundResource(R.drawable.welcome);
        adapter.addToAdapterView(imageView);

        imageView = (ImageView) getLayoutInflater().inflate(R.layout.viewpage_item, null);
        imageView.setBackgroundResource(R.drawable.small);
        adapter.addToAdapterView(imageView);

        imageView = (ImageView) getLayoutInflater().inflate(R.layout.viewpage_item, null);
        imageView.setBackgroundResource(R.drawable.bd);
        adapter.addToAdapterView(imageView);

        adapter.notifyDataSetChanged();
    }

    private ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            if (position >= 3) {
                new Thread() {
                    @Override
                    public void run() {
                        try {
                            sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        openActivity(LoginActivity.class);
                        finish();
                    }
                }.start();
            }
            for (int i = 0; i < imageViews.length; i++) {
                imageViews[i].setAlpha(0.4f);
            }
            imageViews[position].setAlpha(1.0f);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}
