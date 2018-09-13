package com.video.app.base.bottombar.animate;

import android.view.View;

/**
 * @ClassName: Animatable
 * @author: 张京伟
 * @date: 2017/1/3 10:29
 * @Description: 动画接口
 * @version: 1.0
 */
public interface Animatable {

    /**
     * 点击播放动画
     */
    void playAnimate(View target, boolean selected);

    /**
     * 页面切换时播放动画
     */
    void onPageAnimate(View target, float offset);

    /**
     * 是否需要页面切换动画
     */
    boolean isNeedPageAnimate();




}
