package com.video.retrofit.okhttp.result;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import okhttp3.ResponseBody;

/**
 * @version 1.0
 * @ClassName: DownloadResultResponse
 * @author: 张京伟
 * @Description:
 * @date: 2017/11/14 18:13
 */
public class DownloadResultResponse implements Function<ResponseBody, byte[]> {
    @Override
    public byte[] apply(@NonNull ResponseBody responseBody) throws Exception {
        return responseBody.bytes();
    }
}