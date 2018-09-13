package com.video.app;

import android.content.Context;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import com.video.retrofit.okhttp.retrofit.RetrofitUtils;

import java.io.File;

/**
 * @ClassName: SoftApplication
 * @author: 张京伟
 * @date: 2017/10/18 13:31
 * @Description:
 * @version: 1.0
 */
public class SoftApplication extends MultiDexApplication {
    public static SoftApplication softApplication;
    public static boolean isDebug = true;//上线的时候修改为false

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        softApplication = this;

        //请求是否打印日志
        RetrofitUtils.getInstance().setDebug(isDebug);

        //创建一下内部文件夹
        File file = new File(Constants.FILE_PATH_PHOTO);
        if (!file.exists())
            file.mkdirs();
    }

}
