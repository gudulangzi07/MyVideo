package com.video.retrofit.okhttp.listener;

import io.reactivex.Observable;

/**
 * @ClassName: HttpOnNextListener
 * @author: 张京伟
 * @date: 2017/8/24 18:30
 * @Description:
 * @version: 1.0
 */
public abstract class HttpOnNextListener<T> {
    /**
     * 成功后回调方法
     * @param t
     */
    public abstract void onSuccess(T t);

    /**
     * 缓存回调结果
     * @param string
     */
    public void onCacheNext(String string){

    }

    /**
     * 成功后的Observable返回，扩展链接式调用
     * @param observable
     */
    public void onNext(Observable observable){

    }

    /**
     * 失败或者错误方法
     * 主动调用，更加灵活
     * @param e
     */
    public  void onError(Throwable e){

    }

    /**
     * 取消回調
     */
    public void onCancel(){

    }

}
