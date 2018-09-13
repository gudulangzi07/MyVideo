package com.video.app.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.telephony.TelephonyManager;

/**
 * @ClassName: PhoneAttrsUtil
 * @author: 张京伟
 * @date: 2017/10/18 14:17
 * @Description:
 * @version: 1.0
 */
public class PhoneAttrsUtil {
    /**
     * 手机系统的Android版本号
     *
     * @return
     */
    public static String getOSVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return
     */
    public static String getPhoneModel() {
        return Build.MODEL;
    }

    /**
     * 得到应用的排序号
     *
     * @return
     */
    public static int getAppVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        int versionCode = 0;
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionCode = packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionCode;
    }

    /**
     * 得到应用的版本号
     *
     * @return
     */
    public static String getAppVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        String versionName = "1.0.0";
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            versionName = packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }

    /**
     * 得到应用的版本号
     *
     * @return
     */
    public static String getAppPackageName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        PackageInfo packInfo;
        String packageName = "";
        try {
            packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            packageName = packInfo.packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageName;
    }

    /**
     * 识别符来源标志：
     * 1， IMEI（imei）；
     * 2， 序列号（sn）；
     *
     * @param context
     * @return
     */
    @SuppressLint("MissingPermission")
    public static String getDeviceId(Context context) {
        try {
            //IMEI（imei）
            TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String imei = tm.getDeviceId();
            if (!EmptyUtil.isNullOrEmpty(imei)) {
                return imei;
            }
            //序列号（sn）
            String sn = tm.getSimSerialNumber();
            if (!EmptyUtil.isNullOrEmpty(sn)) {
                return sn;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}