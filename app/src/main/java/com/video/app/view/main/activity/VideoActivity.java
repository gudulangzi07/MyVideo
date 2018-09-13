package com.video.app.view.main.activity;

import android.net.Uri;
import android.os.Environment;
import android.view.View;

import com.aliplayer.constants.PlayParameter;
import com.aliplayer.widget.AliyunVodPlayerView;
import com.aliyun.vodplayer.media.AliyunLocalSource;
import com.video.app.R;
import com.video.app.base.BaseActivity;
import com.video.app.base.titlebar.CommonTitleBar;
import com.video.titlebar.SystemStatusBarUtil;

import butterknife.BindView;

public class VideoActivity extends BaseActivity {

    @BindView(R.id.video_view)
    AliyunVodPlayerView mAliyunVodPlayerView;

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

//        mAliyunVodPlayerView.setOnPreparedListener(new MyPrepareListener(this));
//        mAliyunVodPlayerView.setNetConnectedListener(new MyNetConnectedListener(this));
//        mAliyunVodPlayerView.setOnCompletionListener(new MyCompletionListener(this));
//        mAliyunVodPlayerView.setOnFirstFrameStartListener(new MyFrameInfoListener(this));
//        mAliyunVodPlayerView.setOnChangeQualityListener(new MyChangeQualityListener(this));
//        mAliyunVodPlayerView.setOnStoppedListener(new MyStoppedListener(this));
//        mAliyunVodPlayerView.setmOnPlayerViewClickListener(new MyPlayViewClickListener());
//        mAliyunVodPlayerView.setOrientationChangeListener(new MyOrientationChangeListener(this));
//        mAliyunVodPlayerView.setOnUrlTimeExpiredListener(new MyOnUrlTimeExpiredListener(this));
//        mAliyunVodPlayerView.setOnShowMoreClickListener(new MyShowMoreClickLisener(this));
//        mAliyunVodPlayerView.setOnPlayStateBtnClickListener(new MyPlayStateBtnClickListener(this));
//        mAliyunVodPlayerView.setOnSeekCompleteListener(new MySeekCompleteListener(this));
//        mAliyunVodPlayerView.setOnSeekStartListener(new MySeekStartListener(this));
//        mAliyunVodPlayerView.enableNativeLog();
    }

    @Override
    protected void onClickEvent(View view) {

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
}
