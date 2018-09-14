package com.video.app.view.main.activity;

import android.net.Uri;
import android.os.Environment;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;

import com.aliplayer.constants.PlayParameter;
import com.aliplayer.widget.AliyunVodPlayerView;
import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.video.app.R;
import com.video.app.base.BaseActivity;
import com.video.titlebar.SystemStatusBarUtil;

import butterknife.BindView;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.video_view)
    AliyunVodPlayerView mAliyunVodPlayerView;//播放视图

    @BindView(R.id.rl_small)
    RelativeLayout rl_small;//小视图父布局

    @BindView(R.id.surface_view)
    SurfaceView mSurfaceView;//小视图

    private SurfaceHolder mAliyunVodPlayerHolder;
    private SurfaceHolder smallHolder;

//    private int beforeRemoteWidth;
//    private int beforeLocalWidth;
//    private int beforeRemoteHeight;
//    private int beforeLocalHeight;

    private int bigWidth;
    private int bigHeight;
    private int smallWidth;
    private int smallHeight;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_video;
    }

    @Override
    protected void initBundleData() {

    }

    @Override
    protected void initView() {
        SystemStatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));

        AliyunLocalSource.AliyunLocalSourceBuilder alsb = new AliyunLocalSource.AliyunLocalSourceBuilder();
        alsb.setSource(PlayParameter.PLAY_PARAM_URL);
        Uri uri = Uri.parse(PlayParameter.PLAY_PARAM_URL);
        if ("rtmp".equals(uri.getScheme())) {
            alsb.setTitle("");
        }
        AliyunLocalSource localSource = alsb.build();
        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.setLocalSource(localSource);
        }

        //保持屏幕敞亮
        mAliyunVodPlayerView.setKeepScreenOn(true);
        String sdDir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/test_save_cache";
        mAliyunVodPlayerView.setPlayingCache(false, sdDir, 60 * 60 /*时长, s */, 300 /*大小，MB*/);
        mAliyunVodPlayerView.setTheme(AliyunVodPlayerView.Theme.Red);
        //mAliyunVodPlayerView.setCirclePlay(true);
        mAliyunVodPlayerView.setAutoPlay(true);

        rl_small.setOnClickListener(this);
        mAliyunVodPlayerView.setOnClickListener(this);

//        zoomOpera(rl_small, mSurfaceView, mAliyunVodPlayerView.getPlayerView(), mAliyunVodPlayerView, 400, 200, RelativeLayout.CENTER_IN_PARENT);

    }

    @Override
    protected void onClickEvent(View view) {
        switch (view.getId()){
            case R.id.rl_small:
                smallToBig(rl_small, mAliyunVodPlayerView);
                break;
            case R.id.video_view:

                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.onResume();
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mAliyunVodPlayerView != null) {
            mAliyunVodPlayerView.onStop();
        }
    }

    /**
     * 小视图转换成大视图
     * */
    private void smallToBig(View smallView, View bigView){
        RelativeLayout parentView = (RelativeLayout) bigView.getParent();
        bigWidth = bigView.getMeasuredWidth();
        bigHeight = bigView.getMeasuredHeight();

        smallWidth = smallView.getMeasuredWidth();
        smallHeight = smallView.getMeasuredHeight();

        parentView.removeView(smallView);
        parentView.removeView(bigView);

        RelativeLayout.LayoutParams smallParams = new RelativeLayout.LayoutParams(smallWidth, smallHeight);
        smallParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT | RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        RelativeLayout.LayoutParams bigParams = new RelativeLayout.LayoutParams(bigWidth, bigHeight);

        smallView.setLayoutParams(bigParams);
        bigView.setLayoutParams(smallParams);

        parentView.addView(smallView);
        parentView.addView(bigView);

        mAliyunVodPlayerView.getPlayerView().setZOrderOnTop(true);
    }

//    //具体的视图操作
//    private void zoomOpera(View sourcView, SurfaceView beforeview,
//                           SurfaceView afterview, View detView, int beforLocalweith,
//                           int beforLocalHeigth, int rule) {
//        RelativeLayout.LayoutParams params1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
//                RelativeLayout.LayoutParams.MATCH_PARENT);
//        params1.addRule(rule, RelativeLayout.TRUE);
//        afterview.setLayoutParams(params1);
//        afterview.setBackgroundResource(android.R.color.transparent);
//        params1 = new RelativeLayout.LayoutParams(beforLocalweith, beforLocalHeigth);
//        params1.addRule(rule, RelativeLayout.TRUE);
//        detView.setLayoutParams(params1);
//
//    }

//    //放大远端的视图
//    private void zoomRemoteout(int weith2, int heigth2, SurfaceView localView,
//                               SurfaceView remoteView) {
//        beforeLocalHeight = localView.getMeasuredHeight();
//        beforeLocalWidth = localView.getMeasuredWidth();
//        beforeRemoteHeight = remoteView.getMeasuredHeight();
//        beforeRemoteWidth = remoteView.getMeasuredWidth();
//        zoomOpera(rl_small, mSurfaceView, mAliyunVodPlayerView.getPlayerView(), mAliyunVodPlayerView, getResources().getDisplayMetrics().widthPixels, beforeLocalHeight, RelativeLayout.CENTER_IN_PARENT);
//    }
//
//    //缩小远端的视图
//    private void zoomRemoteViewint(int weith2, int heigth2) {
//        RelativeLayout paretview = (RelativeLayout) rl_small.getParent();
//        paretview.removeView(mAliyunVodPlayerView);
//        paretview.removeView(rl_small);
//        zoomOpera(rl_small, mSurfaceView, mAliyunVodPlayerView.getPlayerView(), mAliyunVodPlayerView, beforeLocalWidth, beforeLocalHeight, RelativeLayout.ALIGN_PARENT_BOTTOM | RelativeLayout.ALIGN_PARENT_RIGHT);
//        paretview.addView(rl_small);
//        paretview.addView(mAliyunVodPlayerView);
//        mAliyunVodPlayerView.getPlayerView().setZOrderOnTop(true);
//
//    }
//    //放大本端的视图
//    private void zoomlocalViewout(int weith2, int heigth2,
//                                  SurfaceView localView, SurfaceView remoteView) {
//        beforeLocalHeight = localView.getMeasuredHeight();
//        beforeLocalWidth = localView.getMeasuredWidth();
//        beforeRemoteHeight = remoteView.getMeasuredHeight();
//        beforeRemoteWidth = remoteView.getMeasuredWidth();
//        zoomOpera(mAliyunVodPlayerView, mAliyunVodPlayerView.getPlayerView(), mSurfaceView, rl_small, beforeRemoteWidth, beforeRemoteHeight, RelativeLayout.CENTER_IN_PARENT);
//    }
//    //减小本端的视图
//    private void zoomlocalViewint(int weith2, int heigth2) {
//        RelativeLayout paretview = (RelativeLayout) rl_small.getParent();
//        paretview.removeView(mAliyunVodPlayerView);
//        paretview.removeView(rl_small);
//        zoomOpera(mAliyunVodPlayerView, mAliyunVodPlayerView.getPlayerView(), mSurfaceView, rl_small, beforeRemoteWidth, beforeRemoteHeight, RelativeLayout.ALIGN_PARENT_BOTTOM | RelativeLayout.ALIGN_PARENT_RIGHT);
//        paretview.addView(mAliyunVodPlayerView);
//        paretview.addView(rl_small);
//        mSurfaceView.setZOrderOnTop(true);
//    }
}
