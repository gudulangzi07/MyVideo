package com.video.app.base.bottombar.animate;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringSystem;

/**
 * @ClassName: Animatable
 * @author: 张京伟
 * @date: 2017/1/3 10:29
 * @Description: 实现弹性的动画父类
 * @version: 1.0
 */
public abstract class BouncingAnimater {
    //动画对象
    private Spring mSpring;
    //是否正在播放动画
    private boolean mPlaying;
    //对应的监听器

    public Spring getSpring() {
        return mSpring;
    }

    public boolean isPlaying(){
        return mPlaying;
    }

    public void setPlaying(boolean play){
        this.mPlaying = play;
        if (!play&&mSpring!=null){
            mSpring.setAtRest();
        }
    }

    public Spring buildSpring(){
        SpringSystem mSystem = SpringSystem.create();
        mSpring = mSystem.createSpring();
        mSpring.setSpringConfig(SpringConfig.fromOrigamiTensionAndFriction(50, 2));
        return mSpring;
    }
}
