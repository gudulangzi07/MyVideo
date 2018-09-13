package com.video.retrofit.okhttp.retrofit;

import android.support.annotation.NonNull;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: HttpRequest
 * @author: 张京伟
 * @date: 2017/5/2 11:48
 * @Description:
 * @version: 1.0
 */
public class HttpRequest {

    public static Map<String, String> getStringHashMap(@NonNull JSONObject jsonObject) {
        Map<String, String> hashMap = new HashMap<>();
        try{
            hashMap.put("info", jsonObject.toJSONString());
        }catch (Exception e){
            e.printStackTrace();
        }

        return hashMap;
    }
}
