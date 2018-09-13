package com.video.app.utils;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: ValidateUtil
 * @author: 张京伟
 * @date: 2017/5/15 17:33
 * @Description: 各种验证
 * @version: 1.0
 */
public class ValidateUtil {
    /**
     * 匹配短信中间的6个数字（验证码等）
     */
    public static String patternCode(String patternContent) {
        if (TextUtils.isEmpty(patternContent)) {
            return null;
        }
        Pattern p = Pattern.compile("(?<!--\\d)\\d{6}(?!\\d)");
        Matcher matcher = p.matcher(patternContent);
        if (matcher.find()) {
            return matcher.group();
        }
        return null;
    }

    /**
     * 验证手机号码的格式是否正确
     */
    public static boolean isMobileVerify(String mobileString) {
        if (mobileString == null || "".equals(mobileString.trim())) {
            return false;
        } else {
            String mobileTrim = mobileString.trim();
            Pattern patternMobile = Pattern.compile("^1[0-9][0-9]{9}$");
            Matcher matcherMobile = patternMobile.matcher(mobileTrim);
            return matcherMobile.matches();
        }
    }

    /**
     * 验证是否是中文
     */
    public static boolean isChinese(String chinese) {
        if (chinese == null || "".equals(chinese.trim())) {
            return false;
        } else {
            String chineseTrim = chinese.trim();
            Pattern patternChinese = Pattern.compile("[\\u4e00-\\u9fa5]");
            Matcher matcherChinese = patternChinese.matcher(chineseTrim);
            return matcherChinese.matches();
        }
    }

    /**
     * 验证姓名格式
     */
    public static boolean isChineseEnglish(String chineseEnglish) {
        if (chineseEnglish == null || "".equals(chineseEnglish.trim())) {
            return false;
        } else {
            String chineseTrim = chineseEnglish.trim();
            Pattern patternChinese = Pattern.compile("^[\\u4e00-\\u9fa5\\s-a-zA-Z0-9\\·]+$");
            Matcher matcherChinese = patternChinese.matcher(chineseTrim);
            return matcherChinese.matches();
        }
    }

    /**
     * 验证手机号码的格式是否正确
     */
    public static boolean isNumeric(String str) {
        if (str == null || "".equals(str.trim())) {
            return false;
        } else {
            String numeric = str.trim();
            Pattern patternNumeric = Pattern.compile("[0-9]*");
            Matcher matcherNumeric = patternNumeric.matcher(numeric);
            return matcherNumeric.matches();
        }
    }
}
