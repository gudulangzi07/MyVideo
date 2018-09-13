package com.video.app.utils;

import android.os.CountDownTimer;
import android.widget.TextView;

import com.video.app.R;

public class CountDownTimerUtil extends CountDownTimer {
    private TextView mTextView;

    public CountDownTimerUtil(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mTextView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        mTextView.setClickable(false);//设置不可点击
        mTextView.setText(millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {
        mTextView.setText(R.string.retrieve);
        mTextView.setClickable(true);
        cancel();
    }
}
