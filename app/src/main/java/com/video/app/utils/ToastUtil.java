package com.video.app.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @ClassName: ToastUtil
 * @author: 张京伟
 * @date: 2016/12/27 16:38
 * @Description:
 * @version: 1.0
 */
public class ToastUtil {
    /**
     * 短时间显示Toast
     * @param info 显示的内容
     */
    public static void showToast(Context context, String info) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setText(info);
        toast.show();
    }

    /**
     * 短时间显示Toast
     * @param resId 显示的内容
     */
    public static void showToast(Context context, int resId) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        toast.setText(resId);
        toast.show();
    }

    /**
     * 短时间显示Toast
     * @param info 显示的内容
     */
    public static void showToastLong(Context context, String info) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        toast.setText(info);
        toast.show();
    }

    /**
     * 短时间显示Toast
     * @param resId 显示的内容
     */
    public static void showToastLong(Context context, int resId) {
        Toast toast = Toast.makeText(context, "", Toast.LENGTH_LONG);
        toast.setText(resId);
        toast.show();
    }
}
