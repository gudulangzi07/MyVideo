package com.video.retrofit.okhttp.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * @version 1.0
 * @ClassName: HttpResponseBody
 * @author: 张京伟
 * @Description:
 * @date: 2017/11/16 19:00
 */
public class HttpResponseBody extends ResponseBody implements Interceptor {
    public static final String TAG = HttpResponseBody.class.getName();
    private ResponseBody responseBody;
    private BufferedSource bufferedSource;
    private String logName;
    private boolean isLog;

    public HttpResponseBody(boolean isLog, String logName, ResponseBody body) {
        this.isLog = isLog;
        this.logName = logName;
        this.responseBody = body;
    }

    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource == null) {
            bufferedSource = Okio.buffer(mySource(responseBody.source()));
        }
        return bufferedSource;
    }

    private Source mySource(Source source) {

        return new ForwardingSource(source) {
            long totalBytesRead = 0L;

            @Override
            public long read(Buffer sink, long byteCount) throws IOException {
                long bytesRead = super.read(sink, byteCount);
                totalBytesRead += bytesRead != -1 ? bytesRead : 0;
                return bytesRead;
            }
        };

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        if (isLog)
            Log.v(logName, "请求request:" + request.url());
        long time1 = System.nanoTime();
        Response response = chain.proceed(request);
        long time2 = System.nanoTime();
        if (isLog)
            Log.v(logName, "网络请求耗时：" + (time2 - time1) / 1000 / 1000 + "毫秒");

        String content = response.body().string();
        if (isLog)
            Log.v(logName, "请求返回response:" + response.request().url() + "===" + content);

        MediaType mediaType = response.body().contentType();

        return response.newBuilder().body(ResponseBody.create(mediaType, content)).build();
    }
}