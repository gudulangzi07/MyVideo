package com.video.retrofit.okhttp.result;

import com.alibaba.fastjson.JSONObject;
import com.video.retrofit.okhttp.Constants;
import com.video.retrofit.okhttp.retrofit.HttpResponse;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;

/**
 * @ClassName: ResultResponse
 * @author: 张京伟
 * @date: 2017/10/17 14:33
 * @Description:
 * @version: 1.0
 */
public class ResultResponse implements Function<HttpResponse, Object> {
    @Override
    public Object apply(@NonNull HttpResponse tHttpResponse) throws Exception {
        //appToken验证失败
        if (tHttpResponse.getCode().equals(Constants.APP_TOKEN_AUTH_FAIL)){
            throw new Exception(tHttpResponse.getCode());
        }
        //appToken过期
        if (tHttpResponse.getCode().equals(Constants.APP_TOKEN_EXPIRE)){
            throw  new Exception(tHttpResponse.getCode());
        }
        if (!tHttpResponse.isSuccess()){
            throw new Exception(tHttpResponse.getMsg());
        }
        return JSONObject.toJSONString(tHttpResponse.getData());
    }
}
