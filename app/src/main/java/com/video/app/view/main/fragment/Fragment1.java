package com.video.app.view.main.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.video.app.R;
import com.video.app.base.BaseFragment;
import com.video.app.utils.ActivitySkipUtil;
import com.video.app.view.main.activity.VideoActivity;

import butterknife.BindView;

public class Fragment1 extends BaseFragment{

    @BindView(R.id.btn)
    Button btn;

    @Override
    protected View onLayoutView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.frament_1, null);
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView(View container) {
        btn.setOnClickListener(this);
    }

    @Override
    protected void onclickEvent(View view) {
        switch (view.getId()){
            case R.id.btn:
                ActivitySkipUtil.skip(getActivity(), VideoActivity.class);
                break;
        }
    }

    @Override
    protected void onUserVisible() {

    }
}
