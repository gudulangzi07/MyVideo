package com.video.retrofit.okhttp.observer;

import android.content.Context;
import android.text.TextUtils;
import android.util.MalformedJsonException;
import android.view.KeyEvent;

import com.alibaba.fastjson.JSONException;
import com.google.gson.JsonParseException;
import com.video.app.base.BaseActivity;
import com.video.retrofit.okhttp.loading.LoadProgressDialog;
import com.video.retrofit.okhttp.retrofit.RxActionManager;

import java.lang.ref.SoftReference;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.text.ParseException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @ClassName: HttpObserver
 * @author: 张京伟
 * @date: 2017/10/17 14:10
 * @Description: 适合Retrofit网络请求Observer(监听者)
 * @version: 1.0
 */
public abstract class HttpObserver<T> implements Observer<T> {

    //是否展示弹出框 true弹出请求框，false不弹出请求框
    private boolean showProgress = true;
    /*软引用反正内存泄露*/
    private SoftReference<BaseActivity> mActivity;
    private String mTag;//请求标识
    /*加载框可自己定义*/
    private LoadProgressDialog progressDialog;

    public HttpObserver(BaseActivity mActivity, String mTag, boolean showProgress) {
        this.mActivity = new SoftReference<>(mActivity);
        this.mTag = mTag;
        this.showProgress = showProgress;
    }

    @Override
    public void onSubscribe(@NonNull Disposable disposable) {
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManager.getInstance().add(mTag, disposable);
        }

        showProgressDialog();
    }

    @Override
    public void onNext(@NonNull T t) {
        dismissProgressDialog();
        if (!TextUtils.isEmpty(mTag)) {
            RxActionManager.getInstance().remove(mTag);
        }

        onSuccess(t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        dismissProgressDialog();
        if (e instanceof SocketTimeoutException) {
            onError("网络超时");
        } else if (e instanceof ConnectException) {
            onError("网络连接失败");
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof MalformedJsonException) {
            onError("数据解析错误");
        } else {
            onError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 错误/异常回调
     */
    protected abstract void onError(String error);

    /**
     * 成功回调
     */
    protected abstract void onSuccess(T response);

    /**
     * 显示加载框
     */
    private void showProgressDialog() {
        if (!showProgress) return;
        Context context = mActivity.get();
        if (context == null) return;
        else {
            if (null == progressDialog) {
                progressDialog = new LoadProgressDialog(mActivity.get());
                progressDialog.setCanceledOnTouchOutside(false);

                progressDialog.setOnCancelListener(dialogInterface -> {
                    if (!TextUtils.isEmpty(mTag)) {
                        RxActionManager.getInstance().cancel(mTag);
                        RxActionManager.getInstance().remove(mTag);
                    }
                });

                progressDialog.setOnKeyListener((dialog, keyCode, event) -> {
                    if (event.getAction() == KeyEvent.ACTION_DOWN && event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                            mActivity.get().onBackPressed();
                            return true;
                        }
                    }
                    return false;
                });

                progressDialog.show();
            } else {
                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
            }
        }
    }

    /**
     * 隐藏
     */
    private void dismissProgressDialog() {
        if (!showProgress) return;
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }
}
