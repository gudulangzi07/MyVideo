package com.video.app.utils;

import java.util.List;

/**
 * @ClassName: NearGo
 * @author: 张京伟
 * @date: 2017/5/11 23:43
 * @Description:
 * @version: 1.0
 */

public class EmptyUtil {
    /**
     * 判断字符串是否为null或者空字符串
     */
    public static boolean isNullOrEmpty(String str) {
        boolean result = false;
        if (null == str || "".equals(str.trim())) {
            result = true;
        }
        return result;
    }

    /**
     * 判断字符串不为null或者空字符串
     */
    public static boolean isNotNullOrEmpty(String string) {
        return null != string && !"".equals(string.trim());
    }

    /**
     * 判断数组是否是null或size()==0
     */
    public static boolean isNullOrEmpty(List<?> list) {
        boolean result = false;
        if (null == list || list.size() == 0) {
            result = true;
        }
        return result;
    }

    /**
     * 判断数组是否是null或size()==0
     */
    public static boolean isNotNullOrEmpty(List<?> list) {
        boolean result = true;
        if (null == list || list.size() == 0) {
            result = false;
        }
        return result;
    }
}
