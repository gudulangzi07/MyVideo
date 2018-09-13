package com.video.retrofit.okhttp.retrofit;

import android.support.v4.util.ArrayMap;

import io.reactivex.disposables.Disposable;

/**
 * @ClassName: RxActionManager
 * @author: 张京伟
 * @date: 2017/10/17 13:42
 * @Description:
 * @version: 1.0
 */
public class RxActionManager implements RxActionManagerImp<Object>{

    private static volatile RxActionManager rxActionManager;
    private ArrayMap<Object, Disposable> mMaps;//处理,请求列表

    public RxActionManager() {
        mMaps = new ArrayMap<>();
    }

    public static RxActionManager getInstance(){
        if (rxActionManager == null){
            synchronized (RxActionManager.class){
                if (rxActionManager == null){
                    rxActionManager = new RxActionManager();
                }
            }
        }

        return rxActionManager;
    }

    @Override
    public void add(Object tag, Disposable disposable) {
        mMaps.put(tag, disposable);
    }

    @Override
    public void remove(Object tag) {
        if (!mMaps.isEmpty())
            mMaps.remove(tag);
    }

    @Override
    public void cancel(Object tag) {
        if (mMaps.isEmpty())
            return;
        if (mMaps.get(tag) == null)
            return;
        if (!mMaps.get(tag).isDisposed())
            mMaps.get(tag).dispose();

        mMaps.remove(tag);
    }

    /**
     * 判断是否取消请求
     * @param tag
     * @return true 取消 false 没有取消
     * */
    public boolean isDisposed(Object tag){
        if (mMaps.isEmpty() || mMaps.get(tag) == null)
            return true;
        return mMaps.get(tag).isDisposed();
    }
}
