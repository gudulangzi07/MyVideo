package com.video.app.base.bottombar;

import android.view.View;

/**
 * @ClassName: OnTabSelectListener
 * @author: 张京伟
 * @date: 2017/1/3 10:29
 * @Description: 点击回调监听
 * @version: 1.0
 */
public interface OnTabSelectListener {
    /**
     *  用户每次点击不同的Tab将会触发这个方法
     * @param index
     * 当前选择的TAB的索引值
     */
    void onTabSelect(int index);

    /**
     * 用户点击中间的图标
     */
    void onClickMiddle(View middleBtn);



}
