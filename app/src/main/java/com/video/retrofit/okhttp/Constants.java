package com.video.retrofit.okhttp;

/**
 * @ClassName: Constants
 * @author: 张京伟
 * @date: 2017/10/18 13:50
 * @Description:
 * @version: 1.0
 */
public class Constants {
    //    public static final String BASE_URL = "http://172.16.18.101:8880/";//网络开发环境
//    public static final String BASE_URL = "http://172.16.18.198:8010/";//网络请求地址
    public static final String BASE_URL = "https://yunketangweixin.mofangge.net/";//网络测试环境

    public static final String BASE_H5_URL = "https://yunketangweixin.mofangge.net/";//H5的测试环境

    public static final String H5_EXAMPLE_URL = BASE_H5_URL + "appstatic/#/report-list?boxNo=12345678";

    //购买单词速记同意书
    public static final String H5_VOCABULARY_AGREE_URL = BASE_H5_URL + "appstatic/#/payment";

    //购买基因检测同意书
    public static final String H5_GENEDETECTION_AGREE_URL = BASE_H5_URL + "appstatic/#/paymentGent";

    //绑定盒子的知情同意书
    public static final String H5_BINDING_AGREE_URL = BASE_H5_URL + "appstatic/#/sampling-agreee";

    //订单中使用课程页面
    public static final String H5_ORDER_URL = BASE_H5_URL + "appstatic/#/pay-success?id=";

    public static final String CODE_OK = "000000";//网络请求返回值
    public static final String APP_TOKEN_EXPIRE = "999998";//appToken过期
    public static final String APP_TOKEN_AUTH_FAIL = "999999";//appToken验证失败
}
