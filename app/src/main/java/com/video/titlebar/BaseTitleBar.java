package com.video.titlebar;

import android.app.Activity;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Email: 1273482124@qq.com
 * Created by qiyei2015 on 2017/5/14.
 * Version: 1.0
 * Description:
 */
public abstract class BaseTitleBar<T extends BaseTitleParams> implements ITitleBar {

    protected static final String TAG = BaseTitleBar.class.getSimpleName();

    /**
     * 参数
     */
    protected T mParams;
    /**
     * 导航View
     */
    protected View mNavigationView;

    public BaseTitleBar(T params) {
        this.mParams = params;
        bindViewToActivityRoot();
    }

    /**
     * 隐藏View
     * */
    protected void hideView(int viewId){
        View view = getView(viewId);
        view.setVisibility(View.GONE);
    }

    /**
     * 隐藏View
     * */
    protected void showView(int viewId){
        View view = getView(viewId);
        view.setVisibility(View.VISIBLE);
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     */
    protected void setText(int viewId, CharSequence text) {
        TextView tv = getView(viewId);
        if (!TextUtils.isEmpty(text) && tv != null) {
            tv.setVisibility(View.VISIBLE);
            tv.setText(text);
        }
    }

    /**
     * 获取文本内容
     * @param viewId
     * @return
     * */
    protected String getText(int viewId) {
        TextView tv = getView(viewId);
        if (tv != null)
            return tv.getText().toString().trim();
        else return "";
    }

    /**
     * 设置文本
     *
     * @param viewId
     * @param text
     * @param colorRes
     */
    protected void setText(int viewId, CharSequence text, int colorRes) {
        TextView tv = getView(viewId);
        if (!TextUtils.isEmpty(text) && tv != null) {
            tv.setText(text);
            tv.setTextColor(colorRes);
            tv.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置图片
     *
     * @param viewId
     * @param resId
     */
    protected void setDrawable(int viewId, int resId) {
        ImageView imv = getView(viewId);
        if (imv != null) {
            imv.setImageResource(resId);
            imv.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置点击事件
     *
     * @param viewId
     * @param listener
     */
    protected void setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    /**
     * 获取view
     *
     * @param viewId
     * @param <V>
     * @return
     */
    public <V extends View> V getView(int viewId) {
        return (V) mNavigationView.findViewById(viewId);
    }

    /**
     * 绑定View到ActivityRoot上
     */
    private void bindViewToActivityRoot() {
        ViewGroup activityRoot = null;
        //创建View
        if (mParams.mActivityRoot == null || activityRoot == null) {
            //获取Activity的根布局
            activityRoot = (ViewGroup) ((Activity) mParams.mContext).findViewById(android.R.id.content);
            //获取我们在Activity中设置ContentView的View
            mParams.mActivityRoot = (ViewGroup) activityRoot.getChildAt(0);
        }
        if (mParams.mActivityRoot == null) {
            return;
        }

        mNavigationView = LayoutInflater.from(mParams.mContext)
                .inflate(bindLayoutId(), activityRoot, false);

        if (mParams.mActivityRoot instanceof RelativeLayout || mParams.mActivityRoot instanceof ConstraintLayout) {
            //相对布局
            //先构造一个线性布局,指定垂直排列
            LinearLayout newActivityRoot = new LinearLayout(mParams.mContext);
            newActivityRoot.setOrientation(LinearLayout.VERTICAL);

            //移除原有的activityRoot的parent，否则会报"The specified child already has a parent. " +
            //"You must call removeView() on the child's parent first. 异常
            ViewGroup viewParent = (ViewGroup) mParams.mActivityRoot.getParent();
            viewParent.removeAllViews();

            //将titleBar添加为第一个child,原来的activityRoot为第二个
            newActivityRoot.addView(mNavigationView, 0);
            newActivityRoot.addView(mParams.mActivityRoot, 1);
            //将新的activityRoot添加到android.R.id.content中
            activityRoot.addView(newActivityRoot, 0);

        } else {
            //添加View到mParams.mParent中
            mParams.mActivityRoot.addView(mNavigationView, 0);
        }
        //绑定View
        bindView();
    }

    /**
     * Builder设计模式,主要用于设置各种效果
     */
    public static abstract class Builder {

        public Builder(Context context) {

        }

        public abstract BaseTitleBar build();
    }

}
