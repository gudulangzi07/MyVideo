package com.video.retrofit.base;

import android.os.Bundle;

/**
 * @ClassName: LifeCycleListener
 * @author: 张京伟
 * @date: 2017/10/17 16:43
 * @Description: 生命周期监听
 * @version: 1.0
 */
public interface LifeCycleListener {

    void onCreate(Bundle savedInstanceState);

    void onStart();

    void onRestart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
