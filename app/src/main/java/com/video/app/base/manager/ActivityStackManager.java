package com.video.app.base.manager;

import android.app.ActivityManager;
import android.app.NotificationManager;
import android.content.Context;
import android.support.v4.app.FragmentActivity;

import com.video.app.view.main.activity.MainActivity;

import java.util.Stack;

/**
 * @ClassName: BaseActivity
 * @author: 张京伟
 * @date: 2017/8/17 14:52
 * @Description: Activity页面管理
 * @version: 1.0
 */
public class ActivityStackManager {

    private static ActivityStackManager instance = null;
    private static Stack<FragmentActivity> activityStack;// 栈

    public static Stack<FragmentActivity> getActivityStack() {
        return activityStack;
    }

    /**
     * 私有构造
     */
    private ActivityStackManager() {
        activityStack = new Stack<>();
    }

    /**
     * 单例实例
     *
     * @return
     */
    public static ActivityStackManager getInstance() {
        if (instance == null) {
            synchronized (ActivityStackManager.class) {
                if (instance == null) {
                    instance = new ActivityStackManager();
                }
            }
        }
        return instance;
    }

    /**
     * 压栈
     * @param activity
     */
    public void push(FragmentActivity activity) {
        activityStack.push(activity);
    }

    /**
     * 出栈
     * @return
     */
    public FragmentActivity pop() {
        if (activityStack.isEmpty())
            return null;
        return activityStack.pop();
    }

    /**
     * 栈顶
     * @return
     */
    public FragmentActivity peek() {
        if (activityStack.isEmpty())
            return null;
        return activityStack.peek();
    }

    /**
     * 用于返回首页，清除activity
     */
    public void clearActivityToMain() {
        if (!activityStack.isEmpty()){
            int size = activityStack.size();
            for (int i = 0; i < size; i++){
                FragmentActivity activity = activityStack.peek();
                if (!(activity instanceof MainActivity)) {
                    activityStack.pop().finish();
                }
            }
        }
    }

    /**
     * 移除
     *
     * @param activity
     */
    public void remove(FragmentActivity activity) {
        if (activityStack.size() > 0 && activity == activityStack.peek())
            activityStack.pop();
        else
            activityStack.remove(activity);
    }

    /**
     * 是否存在栈
     *
     * @param activity
     * @return
     */
    public boolean contains(FragmentActivity activity) {
        return activityStack.contains(activity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        while (!activityStack.isEmpty()) {
            activityStack.pop().finish();
        }
    }

    /**
     * 退出应用程序
     *
     * @param context
     */
    public void exitApp(Context context) {
        try {
            finishAllActivity();
            ActivityManager activityManager = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            //清除通知栏
            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancelAll();
            android.os.Process.killProcess(android.os.Process.myPid());
        } catch (Exception e) {

        }
    }
}