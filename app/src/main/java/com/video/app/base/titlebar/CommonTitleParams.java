package com.video.app.base.titlebar;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.ViewGroup;

import com.video.app.R;
import com.video.titlebar.BaseTitleParams;

/**
 * @ClassName: CommonTitleParams
 * @author: 张京伟
 * @date: 2017/10/20 10:48
 * @Description: 定义该NaviagtionBar支持的所有效果，包括主标题，右标题等
 * @version: 1.0
 */
public class CommonTitleParams extends BaseTitleParams {
    /**
     * 主标题
     */
    public String mTitle;
    /**
     * 标题右侧文字
     */
    public String mRightText;
    /**
     * 标题右侧文字颜色
     * */
    public int mRightTextColor;
    /**
     * 右侧图片1
     * */
    public int mRight1;
    /**
     * 右侧图片2
     * */
    public int mRight2;
    /**
     * 右侧图片1的点击事件
     * */
    public View.OnClickListener mRight1ClickListener;
    /**
     * 右侧图片2的点击事件
     * */
    public View.OnClickListener mRight2ClickListener;
    /**
     * 右侧点击事件
     */
    public View.OnClickListener mRightClickListener;
    /**
     * 左侧点击事件
     */
    public View.OnClickListener mLeftClickListener = view -> {
        // 关闭当前Activity
        ((FragmentActivity) mContext).finish();
        ((FragmentActivity) mContext).overridePendingTransition(R.anim.left_in, R.anim.right_out);
    };

    public CommonTitleParams(Context context, ViewGroup parent) {
        super(context, parent);
    }
}
