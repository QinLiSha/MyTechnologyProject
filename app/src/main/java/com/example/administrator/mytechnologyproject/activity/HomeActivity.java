package com.example.administrator.mytechnologyproject.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.mytechnologyproject.R;
import com.example.administrator.mytechnologyproject.base.MyBaseActivity;
import com.example.administrator.mytechnologyproject.fragment.FavoriteFragment;
import com.example.administrator.mytechnologyproject.fragment.HomeFragment;
import com.example.administrator.mytechnologyproject.fragment.ImageFragment;
import com.example.administrator.mytechnologyproject.fragment.LoginFragment;
import com.example.administrator.mytechnologyproject.fragment.PasswordRecoverFragment;
import com.example.administrator.mytechnologyproject.fragment.RegisterFragment;
import com.example.administrator.mytechnologyproject.fragment.SlidingMenuFragment;
import com.example.administrator.mytechnologyproject.fragment.SlidingRightFragment;
import com.example.administrator.mytechnologyproject.util.CommonUtil;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

public class HomeActivity extends MyBaseActivity implements View.OnClickListener{
    private static final String TAG = "HomeActivity";
    private Fragment homeFragment,slidingMenuFrament,slidingRightFragment,
            loginFragment,registerFragment,favoriteFragment,imageFragment,
            passwordRecoverFragment;
    private SlidingMenu slidingMenu;
    private ImageView iv_home_left,iv_home_right;
    private TextView tv_home_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        iv_home_left = (ImageView) findViewById(R.id.iv_home_left);
        iv_home_right = (ImageView) findViewById(R.id.iv_home_right);
        tv_home_title = (TextView) findViewById(R.id.tv_home_title);

        iv_home_left.setOnClickListener(this);
        iv_home_right.setOnClickListener(this);

        String imei = CommonUtil.getIMEI(HomeActivity.this);
        Log.i(TAG, "onCreate: -----------------"+imei);
        showLoadingDialong(HomeActivity.this,"数据加载中...",true);
        loadHomeFragment();
        initSlidingMenu();
    }

    private void loadHomeFragment() {
        homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.contianer,homeFragment).commit();
    }

    /**
     * 显示密码找回的Fragment
     */
    public void showPasswordRecoverFragment() {
        setTitle("密码找回");
        slidingMenu.showContent();
        if (passwordRecoverFragment == null)
            passwordRecoverFragment = new PasswordRecoverFragment();
        getSupportFragmentManager().beginTransaction().replace
                (R.id.contianer,passwordRecoverFragment).commit();
    }

    /**
     * 显示主页面的Fragment
     */
    public void showHomeFragment() {
        setTitle("资讯");
        slidingMenu.showContent();
        if (homeFragment == null)
            homeFragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace
                (R.id.contianer,homeFragment).commit();
    }

    /**
     * 新闻图片界面
     */
    public void showImageFragment() {
        setTitle("新闻图片");
        slidingMenu.showContent();
        if (imageFragment == null)
            imageFragment = new ImageFragment();
        getSupportFragmentManager().beginTransaction().replace
                (R.id.contianer,imageFragment).commit();
    }

    /**
     * 收藏界面
     */
    public void showFavoriteFragment() {
        setTitle("我的收藏");
        slidingMenu.showContent();
        if (favoriteFragment == null)
            favoriteFragment = new FavoriteFragment();
        getSupportFragmentManager().beginTransaction().replace
                (R.id.contianer,favoriteFragment).commit();
    }

    /**
     * 登录界面
     */
    public void showLoginFragment() {
        setTitle("用户登录");
        if (loginFragment == null)
            loginFragment = new LoginFragment();
        slidingMenu.showContent();
        getSupportFragmentManager().beginTransaction().replace
                (R.id.contianer,loginFragment).commit();
    }

    /**
     * 注册界面
     */
    public void showRegisterFragment() {
        setTitle("用户注册");
        if (registerFragment == null)
            registerFragment = new RegisterFragment();
        getSupportFragmentManager().beginTransaction().replace
                (R.id.contianer,registerFragment).commit();
    }

    private void initSlidingMenu() {
        slidingMenuFrament = new SlidingMenuFragment();
        slidingRightFragment = new SlidingRightFragment();

        slidingMenu = new SlidingMenu(this);//初始化SlidingMenu
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);//设置SlidingMenu左右滑
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置可滑动区域为全屏
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);//SlidngMenu滑出时主页剩余宽度
        slidingMenu.setFadeDegree(0.35f);//SlidngMenu滑动时的渐变成都
        slidingMenu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);//使SlidngMenu附着在Activity上
        slidingMenu.setMenu(R.layout.layout_menu);//设置SlidingMenu的布局文件
        slidingMenu.setSecondaryMenu(R.layout.layout_right);

        getSupportFragmentManager().beginTransaction().add(R.id.slingMenu_contianer,
                slidingMenuFrament).commit();
        getSupportFragmentManager().beginTransaction().add(R.id.layout_right_contianer,
                slidingRightFragment).commit();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_home_left:
                if(slidingMenu != null && slidingMenu.isMenuShowing()) {
                    slidingMenu.showContent();
                }else if (slidingMenu!=null) {
                    slidingMenu.showMenu();
                }
                break;
            case R.id.iv_home_right:
                if(slidingMenu!=null && slidingMenu.isMenuShowing()) {
                    slidingMenu.showContent();
                }else if(slidingMenu!=null) {
                    slidingMenu.showSecondaryMenu();
                }
                break;
        }
    }

    public void changeFragmentStatus() {
        ((SlidingRightFragment)slidingRightFragment).changeView();
    }

    public void setTitle(String name) {
        tv_home_title.setText(name);
    }
}
