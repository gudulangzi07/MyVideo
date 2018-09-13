package com.video.app.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.video.app.base.handler.RepeatedClickHandler;
import com.video.app.utils.KeyboardUtil;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @ClassName: BaseFragment
 * @author: 张京伟
 * @date: 2017/10/17 16:48
 * @Description: 基类的fragment
 * @version: 1.0
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener{
    protected Unbinder unBinder;
    private RepeatedClickHandler repeatedClickHandler;
    protected boolean isInit = false;//是否初始化页面
    protected boolean isLoad = false;//是否在加载数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        isInit = true;
        // 处理重复点击
        repeatedClickHandler = new RepeatedClickHandler();
        View rootView = onLayoutView(inflater, container);

        if (rootView != null)
            unBinder = ButterKnife.bind(this, rootView);
        initBundleData();
        initView(rootView);
        isFragmentVisible();
        return rootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isFragmentVisible();
    }

    private void isFragmentVisible(){
        if (!isInit){
            return;
        }

        if (getUserVisibleHint()){
            onUserVisible();
            isLoad = true;
        }else{
            if (isLoad){
                onUserInvisible();
            }
        }
    }

    /**
     * 初始化布局
     */
    protected abstract View onLayoutView(LayoutInflater inflater, ViewGroup container);

    /**
     * 页面初始化之前要做的事情
     */
    protected abstract void initBundleData();

    /**
     * 页面初始化
     */
    protected abstract void initView(View container);

    protected abstract void onclickEvent(View view);

    protected abstract void onUserVisible();

    protected void onUserInvisible(){

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        isInit = false;
        isLoad = false;
    }

    @Override
    public void onClick(View view) {
        KeyboardUtil.hideSoftInput(getActivity());
        repeatedClickHandler.handleRepeatedClick(view);
        onclickEvent(view);
    }
}