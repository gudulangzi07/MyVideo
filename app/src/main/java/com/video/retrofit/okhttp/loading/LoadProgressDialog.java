package com.video.retrofit.okhttp.loading;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;

import com.video.app.R;

public class LoadProgressDialog extends ProgressDialog{
    public LoadProgressDialog(Context context) {
        this(context, R.style.DialogStyle);
    }

    public LoadProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_load_progress);
    }

    @Override
    public void setMessage(CharSequence message) {
        super.setMessage(message);
    }
}
