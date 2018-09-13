package com.video.app;

import android.os.Environment;

/**
 * @ClassName: Constants
 * @author: 张京伟
 * @date: 2017/10/18 13:49
 * @Description:
 * @version: 1.0
 */
public class Constants {

    public static final int CROP_PHOTO = 1000;
    public static final int IMAGE_CODE = 2000;

    public static final String FILE_PATH_BASE = Environment.getExternalStorageDirectory().getAbsolutePath() + "/QTF";
    public static final String FILE_PATH_PHOTO = FILE_PATH_BASE + "/.photo";



}
