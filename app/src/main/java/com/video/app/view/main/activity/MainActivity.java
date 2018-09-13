package com.video.app.view.main.activity;

import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.View;

import com.video.app.R;
import com.video.app.base.BaseActivity;
import com.video.app.base.bottombar.BottomTabBar;
import com.video.app.base.bottombar.OnTabSelectListener;
import com.video.app.base.manager.ActivityStackManager;
import com.video.app.utils.ToastUtil;
import com.video.app.view.main.adapter.MainAdapter;
import com.video.app.view.main.fragment.Fragment1;
import com.video.app.view.main.fragment.Fragment2;
import com.video.app.view.main.fragment.Fragment3;
import com.video.app.view.main.fragment.MineFragment;
import com.video.app.widget.NoScrollViewPager;
import com.video.titlebar.SystemStatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity implements OnTabSelectListener {

    @BindView(R.id.viewpager)
    NoScrollViewPager viewPager;

    @BindView(R.id.btl_bottom)
    BottomTabBar bottomTabBar;

    private long firstTime = 0;// 退出时间隔时间

    private Fragment1 fragment1;
    private Fragment2 fragment2;
    private Fragment3 fragment3;
    private MineFragment mineFragment;
    private MainAdapter mainAdapter;
    private List<Fragment> fragments;

    @Override
    protected int getContentViewId() {
        setSwipeBackEnable(false);
        return R.layout.activity_main;
    }

    @Override
    protected void initBundleData() {
        fragments = new ArrayList<>();
        fragment1 = new Fragment1();
        fragment2 = new Fragment2();
        fragment3 = new Fragment3();
        mineFragment = new MineFragment();
        mainAdapter = new MainAdapter(getSupportFragmentManager());
    }

    @Override
    protected void initView() {
        SystemStatusBarUtil.setTransparentForImageViewInFragment(this, null);
        SystemStatusBarUtil.setLightMode(this);

        bottomTabBar.setTitles(R.string.main_1, R.string.main_2, R.string.main_3, R.string.main_mine)
                .setNormalIcons(R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher, R.drawable.ic_launcher)
                .setSelectedIcons(R.drawable.ic_launcher_round, R.drawable.ic_launcher_round, R.drawable.ic_launcher_round, R.drawable.ic_launcher_round)
                .generate();

        fragments.add(fragment1);
        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(mineFragment);

        mainAdapter.setFragments(fragments);

        //设置viewPager是否可以滑动
        viewPager.setNoScroll(true);
        viewPager.setAdapter(mainAdapter);

        //显示圆点模式的徽章
        //设置容器
        bottomTabBar.setContainer(viewPager);

        //设置Badge消失的代理
        bottomTabBar.setTabListener(this);



    }

    @Override
    protected void onClickEvent(View view) {

    }

    @Override
    public void onTabSelect(int index) {

    }

    @Override
    public void onClickMiddle(View middleBtn) {

    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
            long secondTime = System.currentTimeMillis();
            if (secondTime - firstTime > 3000) {
                ToastUtil.showToast(MainActivity.this, "再按一次退出程序");
                firstTime = secondTime;// 更新第一次点击的时间
                return true;
            } else {
                ActivityStackManager.getInstance().finishAllActivity();
            }
            return true;
        }
        return super.dispatchKeyEvent(event);
    }
}
