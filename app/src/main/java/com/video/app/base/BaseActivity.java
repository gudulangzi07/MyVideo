package com.video.app.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.video.app.R;
import com.video.app.base.handler.RepeatedClickHandler;
import com.video.app.base.manager.ActivityStackManager;
import com.video.app.base.manager.SharedPrefManager;
import com.video.app.utils.ActivitySkipUtil;
import com.video.app.utils.KeyboardUtil;
import com.video.retrofit.base.LifeCycleListener;
import com.video.swipeback.views.SwipeBackActivity;
import com.video.swipeback.widget.SwipeBackLayout;
import com.video.titlebar.SystemStatusBarUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName: BaseActivity
 * @author: 张京伟
 * @date: 2017/8/17 14:52
 * @Description: 基类的Activity
 * @version: 1.0
 */
public abstract class BaseActivity extends SwipeBackActivity implements View.OnClickListener {

    public LifeCycleListener mListener;
    protected Unbinder unBinder;

    protected SwipeBackLayout swipeBackLayout;//设置手势关闭
    protected RepeatedClickHandler repeatedClickHandler;

    public void setOnLifeCycleListener(LifeCycleListener listener) {
        mListener = listener;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //设置状态栏与标题栏颜色
        SystemStatusBarUtil.setColorNoTranslucent(this, getResources().getColor(R.color.colorPrimary));
        SystemStatusBarUtil.setLightMode(this);

        if (mListener != null)
            mListener.onCreate(savedInstanceState);

        ActivityStackManager.getInstance().push(this);

        //设置手势关闭
        swipeBackLayout = getSwipeBackLayout();
        // 处理重复点击
        repeatedClickHandler = new RepeatedClickHandler();

        setContentView(getContentViewId());

        unBinder = ButterKnife.bind(this);
        initBundleData();
        initView();
    }

    @Override
    public void onClick(View view) {
        KeyboardUtil.hideSoftInput(this);
        onClickEvent(view);
        repeatedClickHandler.handleRepeatedClick(view);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.left_in, R.anim.right_out);
    }

    //设置XML的布局文件
    protected abstract int getContentViewId();

    //获取上一个页面的数据
    protected abstract void initBundleData();

    //设置控件
    protected abstract void initView();

    //onClick方法的封装
    protected abstract void onClickEvent(View view);

    @Override
    protected void onStart() {
        super.onStart();
        if (mListener != null) {
            mListener.onStart();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mListener != null) {
            mListener.onRestart();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mListener != null) {
            mListener.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mListener != null) {
            mListener.onPause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mListener != null) {
            mListener.onStop();
        }


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeyboardUtil.hideSoftInput(this);
        if (mListener != null)
            mListener.onDestroy();
        //移除View绑定
        if (unBinder != null)
            unBinder.unbind();
        ActivityStackManager.getInstance().remove(this);
    }

}
