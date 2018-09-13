package com.video.retrofit.okhttp.retrofit;

import io.reactivex.disposables.Disposable;

/**
 * @ClassName: RxActionManagerImp
 * @author: 张京伟
 * @date: 2017/10/17 13:41
 * @Description:
 * @version: 1.0
 */
public interface RxActionManagerImp<T> {
    /**
     * 添加
     *
     * @param tag
     * @param disposable
     */
    void add(T tag, Disposable disposable);

    /**
     * 移除
     *
     * @param tag
     */
    void remove(T tag);

    /**
     * 取消
     *
     * @param tag
     */
    void cancel(T tag);

}
