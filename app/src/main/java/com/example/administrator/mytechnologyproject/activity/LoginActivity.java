package com.example.administrator.mytechnologyproject.activity;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.base.MyBaseActivity;

public class LoginActivity extends MyBaseActivity {
    private ImageView iv_background, iv_logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        iv_background = (ImageView) findViewById(R.id.iv_background);
        iv_logo = (ImageView) findViewById(R.id.iv_logo);
        scaleImpl(iv_background);
    }

    // 透明动画
    public void alphaImpl(View v) {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.alpha_demo);
        v.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                openActivity(HomeActivity.class);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    // 缩放动画
    public void scaleImpl(View v) {
        Animation animation = AnimationUtils.loadAnimation(this,
                R.anim.scale_demo);
        v.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                alphaImpl(iv_logo);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
