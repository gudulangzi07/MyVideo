package com.video.app.base.titlebar;

import android.content.Context;
import android.view.View;

import com.video.app.R;
import com.video.titlebar.BaseTitleBar;

/**
 * @ClassName: CommonTitleBar
 * @author: 张京伟
 * @date: 2017/10/20 10:48
 * @Description: 一个公共的titleBar
 * @version: 1.0
 */
public class CommonTitleBar extends BaseTitleBar<CommonTitleParams> {

    protected CommonTitleBar(CommonTitleParams params) {
        super(params);
    }

    /**
     * 布局文件
     *
     * @return
     */
    @Override
    public int bindLayoutId() {
        return R.layout.title_bar_common;
    }

    @Override
    public void bindView() {
        setText(R.id.title, mParams.mTitle);
        setText(R.id.right_text, mParams.mRightText);
        setText(R.id.right_text, mParams.mRightText, mParams.mRightTextColor);
        setDrawable(R.id.iv_right1, mParams.mRight1);
        setDrawable(R.id.iv_right2, mParams.mRight2);

        setOnClickListener(R.id.right_text, mParams.mRightClickListener);
        // 左边 要写一个默认的  finishActivity
        setOnClickListener(R.id.back, mParams.mLeftClickListener);

        setOnClickListener(R.id.iv_right1, mParams.mRight1ClickListener);
        setOnClickListener(R.id.iv_right2, mParams.mRight2ClickListener);
    }

    /**
     * 隐藏返回按钮
     * */
    public void hideLeft(){
        hideView(R.id.back);
    }

    /**
     * 隐藏标题
     * */
    public void hideTitle(){
        hideView(R.id.title_bar_layout);
    }

    /**
     * 显示标题
     * */
    public void showTitle(){
        showView(R.id.title_bar_layout);
    }

    /**
     * 隐藏右边控件
     * */
    public void hideRight(){
        hideView(R.id.ll_right);
    }

    /**
     * 设置Tiltle
     *
     * @param title
     */
    public void setTitle(String title) {
        mParams.mTitle = title;
        setText(R.id.title, mParams.mTitle);
    }

    /**
     * 设置右侧文字
     *
     * @param text
     */
    public void setRightText(String text) {
        mParams.mRightText = text;
        setText(R.id.right_text, mParams.mRightText);
    }

    /**
     * 设置右侧文字
     *
     * @param text
     * @param rightTextRes
     */
    public void setRightText(String text, int rightTextRes) {
        mParams.mRightText = text;
        mParams.mRightTextColor = rightTextRes;
        setText(R.id.right_text, mParams.mRightText, mParams.mRightTextColor);
    }

    public void setRightClickListener(View.OnClickListener onClickListener){
        setOnClickListener(R.id.right_text, onClickListener);
    }

    public void setRightDrawable(int drawableRes1, int drawableRes2){
        mParams.mRight1 = drawableRes1;
        mParams.mRight2 = drawableRes2;
        setDrawable(R.id.iv_right1, drawableRes1);
        setDrawable(R.id.iv_right2, drawableRes2);
        showView(R.id.ll_right);
    }

    public void setOnClickDrawable1(View.OnClickListener onClickListener){
        setOnClickListener(R.id.iv_right1, onClickListener);
    }

    public void setOnClickDrawable2(View.OnClickListener onClickListener){
        setOnClickListener(R.id.iv_right2, onClickListener);
    }

    /**
     * Builder模式设置各种效果
     */
    public static class Builder extends BaseTitleBar.Builder {
        /**
         * 所有的效果参数
         */
        CommonTitleParams mBarParams;

        public Builder(Context context) {
            super(context);
            mBarParams = new CommonTitleParams(context, null);
        }

        /**
         * 设置Title，默认黑色
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            mBarParams.mTitle = title;
            return this;
        }

        /**
         * 设置右侧文字,默认黑色
         *
         * @param text
         * @return
         */
        public Builder setRightText(String text) {
            mBarParams.mRightText = text;
            return this;
        }

        /**
         * 设置右侧文字
         *
         * @param text
         * @return
         */
        public Builder setRightText(String text, int rightTextColorRes) {
            mBarParams.mRightText = text;
            mBarParams.mRightTextColor = rightTextColorRes;
            return this;
        }

        /**
         * 设置右侧点击事件
         *
         * @param listener
         * @return
         */
        public Builder setRightClickListener(View.OnClickListener listener) {
            mBarParams.mRightClickListener = listener;
            return this;
        }

        /**
         * 自定义一个左侧的点击事件
         *
         * @param listener
         * @return
         */
        public Builder setLeftClickListener(View.OnClickListener listener) {
            mBarParams.mLeftClickListener = listener;
            return this;
        }

        /**
         * 自定义一个点击事件
         * @param listener
         * @return
         * */
        public Builder setRight1ClickListener(View.OnClickListener listener){
            mBarParams.mRight1ClickListener = listener;
            return this;
        }

        /**
         * 自定义一个点击事件
         * @param listener
         * @return
         * */
        public Builder setRight2ClickListener(View.OnClickListener listener){
            mBarParams.mRight2ClickListener = listener;
            return this;
        }

        /**
         * 创建CommonNavigationBar
         *
         * @return
         */
        @Override
        public CommonTitleBar build() {
            return new CommonTitleBar(mBarParams);
        }
    }
}
