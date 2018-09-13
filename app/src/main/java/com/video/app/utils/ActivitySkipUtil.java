package com.video.app.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.video.app.R;

/**
 * @ClassName: ActivitySkipUtil
 * @author: 张京伟
 * @date: 2017/10/19 11:39
 * @Description: 页面跳转
 * @version: 1.0
 */
public class ActivitySkipUtil {

    public static void skip(Activity activity, Class clazz) {
        Intent it = new Intent(activity, clazz);
        activity.startActivity(it);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public static void skip(Activity activity, Class clazz, Bundle extras) {
        Intent it = new Intent(activity, clazz);
        it.putExtras(extras);
        activity.startActivity(it);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public static void skipForResult(Activity activity, Class clazz, int requestCode) {
        Intent it = new Intent(activity, clazz);
        activity.startActivityForResult(it, requestCode);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public static void skipForResult(Activity activity, Class clazz, Bundle extras, int requestCode) {
        Intent it = new Intent(activity, clazz);
        it.putExtras(extras);
        activity.startActivityForResult(it, requestCode);
        activity.overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public static void skipForResult(Fragment fragment, Class clazz, Bundle extras, int requestCode) {
        Intent it = new Intent(fragment.getActivity(), clazz);
        it.putExtras(extras);
        fragment.startActivityForResult(it, requestCode);
        fragment.getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }

    public static void skipForResult(Fragment fragment, Class clazz, int requestCode) {
        Intent it = new Intent(fragment.getActivity(), clazz);
        fragment.startActivityForResult(it, requestCode);
        fragment.getActivity().overridePendingTransition(R.anim.right_in, R.anim.left_out);
    }
}
