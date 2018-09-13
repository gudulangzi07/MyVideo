package com.video.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    public static SimpleDateFormat yyyy_MM_dd_HHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static SimpleDateFormat MM_dd = new SimpleDateFormat("MM-dd");

    public static String getMessageTime(String createTime, String timeStamp){
        SimpleDateFormat HHmm = new SimpleDateFormat("HH:mm");
        SimpleDateFormat MMddHHmm = new SimpleDateFormat("MM-dd HH:mm");
        SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date createDate = yyyy_MM_dd_HHmmss.parse(createTime);
            Date timeStampDate = yyyy_MM_dd_HHmmss.parse(timeStamp);

            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(createDate);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(timeStampDate);

            int year1 = calendar1.get(Calendar.YEAR);
            int month1 = calendar1.get(Calendar.MONTH);
            int date1 = calendar1.get(Calendar.DATE);

            int year2 = calendar2.get(Calendar.YEAR);
            int month2 = calendar2.get(Calendar.MONTH);
            int date2 = calendar2.get(Calendar.DATE);

            if (year1 == year2 && month1 == month2 && date1 == date2){
                return HHmm.format(createDate);
            }else if (year1 == year2){
                return MMddHHmm.format(createDate);
            }else{
                return yyyyMMddHHmm.format(createDate);
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return yyyyMMddHHmm.format(new Date());
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss转换成时间long
     * */
    public static long getLongTime(String time){
        if (EmptyUtil.isNullOrEmpty(time)){
            return 0;
        }

        long longTime;

        try {
            Date date = yyyy_MM_dd_HHmmss.parse(time);
            longTime = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            longTime = 0;
        }

        return longTime;
    }

    /**
     * 比较日期与当前日期大小，日期格式yyyy-MM-dd
     * */
    public static boolean isBefore(String time){
        if (EmptyUtil.isNullOrEmpty(time)){
            return false;
        }

        SimpleDateFormat yyyyMMddHHmm = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date date1 = yyyyMMddHHmm.parse(time);
            Date date2 = new Date();
            if (date1.before(date2)){
                return true;
            }else{
                return false;
            }

        } catch (ParseException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss转换成时间long
     * */
    public static String getyyyy_MM_dd_HH_mm_ss(String time){
        if (EmptyUtil.isNullOrEmpty(time)){
            return "";
        }

        String yyyy_MM_dd_HH_mm_ss = "";

        try {
            Date date = yyyy_MM_dd_HHmmss.parse(time);
            yyyy_MM_dd_HH_mm_ss = yyyy_MM_dd_HHmmss.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return yyyy_MM_dd_HH_mm_ss;
    }

    /**
     * 将yyyy-MM-dd HH:mm:ss转换成时间MM_dd
     * */
    public static String getMM_dd(String time){
        if (EmptyUtil.isNullOrEmpty(time)){
            return "";
        }

        String _MM_dd = "";

        try {
            Date date = yyyy_MM_dd_HHmmss.parse(time);
            _MM_dd = MM_dd.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return _MM_dd;
    }

}
