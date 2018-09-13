package com.video.app.base.manager;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {
    /**
     * SharedPreferences的名字
     */
    private static final String SP_FILE_NAME = "APPLICATION_SP";
    private static SharedPrefManager sharedPrefHelper = null;
    /**
     * 保存app_token
     * */
    private static final String APP_TOKEN = "APPTOKEN";
    /**
     * 保存手机号
     * */
    private static final String TELEPHONE = "TELEPHONE";
    /**
     * 保存头像地址
     * */
    private static final String AVATAR = "AVATAR";
    /**
     * 保存姓名
     * */
    private static final String NICKNAME = "NICKNAME";
    /**
     * 是否认证
     * */
    private static final String AUTH = "AUTH";
    /**
     * 极光推送的alias
     * */
    private static final String ALIAS = "ALIAS";
    /**
     * 极光推送的tag
     * */
    private static final String TAG = "TAG";
    /**
     * 极光推送的tag
     * */
    private static final String OPENID = "OPENID";
    /**
     * 是否有推送的消息
     * */
    private static final String JPUSHNUM = "JPUSH_NUM";
    /**
     * 渠道id
     * */
    private static final String RELATIONSHIP = "RELATIONSHIP";
    /**
     * 推广权限(推广权限为1的时候表示用户购买过产品)
     * */
    private static final String ISSPREAD = "ISSPREAD";

    public static synchronized SharedPrefManager getInstance() {
        if (null == sharedPrefHelper) {
            synchronized (SharedPrefManager.class) {
                if (sharedPrefHelper == null)
                    sharedPrefHelper = new SharedPrefManager();
            }
        }
        return sharedPrefHelper;
    }

    private SharedPrefManager() {

    }

    public void setTelephone(Context context, String phoneNumber) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(TELEPHONE, phoneNumber).apply();
    }

    public String getTelephone(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TELEPHONE, "");
    }

    public void setAppToken(Context context, String app_token) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(APP_TOKEN, app_token).apply();
    }

    public String getAppToken(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(APP_TOKEN, "");
    }

    public void setAvatar(Context context, String avatar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(AVATAR, avatar).apply();
    }

    public String getAvatar(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AVATAR, "");
    }

    public void setNickname(Context context, String nickName) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(NICKNAME, nickName).apply();
    }

    public String getNickname(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(NICKNAME, "");
    }

    public void setAuth(Context context, String auth) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(AUTH, auth).apply();
    }

    public String getAuth(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(AUTH, "");
    }

    public void clearDatas(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public void setAlias(Context context, String alias) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(ALIAS, alias).apply();
    }

    public String getAlias(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ALIAS, "");
    }

    public void setTag(Context context, String tag) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(TAG, tag).apply();
    }

    public String getTag(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TAG, "");
    }

    public void setOpenid(Context context, String openId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(OPENID, openId).apply();
    }

    public String getOpenid(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(OPENID, "");
    }

    public void setJpushnum(Context context, int num) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putInt(JPUSHNUM, num).apply();
    }

    public int getJpushnum(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(JPUSHNUM, 0);
    }

    public void setRelationship(Context context, String setRelationship) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(RELATIONSHIP, setRelationship).apply();
    }

    public String getRelationship(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(RELATIONSHIP, "");
    }

    public void setIsspread(Context context, String isspread) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(ISSPREAD, isspread).apply();
    }

    public String getIsspread(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SP_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(ISSPREAD, "");
    }
}
