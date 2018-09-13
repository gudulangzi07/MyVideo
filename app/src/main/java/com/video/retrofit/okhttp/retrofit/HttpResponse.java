package com.video.retrofit.okhttp.retrofit;

import com.video.app.utils.EmptyUtil;
import com.video.retrofit.okhttp.Constants;

import java.io.Serializable;

/**
 * @ClassName: HttpResponse
 * @author: 张京伟
 * @date: 2017/8/17 16:13
 * @Description: 网络返回数据
 *
 * @version: 1.0
 */
public class HttpResponse<T> implements Serializable{
    //返回code
    private String code;
    //返回提示信息
    private String msg;
    //返回数据
    private T data;

    /**
     * 判断是否请求成功
     * 与后台预定，"000000"代表网络请求成功
     */
    public boolean isSuccess(){
        return EmptyUtil.isNotNullOrEmpty(code) ? (code.equals(Constants.CODE_OK) ? true : false) : false;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
