package com.pxz.pxznetwork;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * 类说明：网络请求工具类
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2019/9/12 14:30
 */
public class HttpUtil {
    /**
     * Retrofit
     */
    private Retrofit mRetrofit;
    /**
     * 默认超时
     */
    private static final int DEFAULT_TIMEOUT = 10;
    /**
     * 读写超时
     */
    private static final int READ_OR_WRITE_TIMEOUT = 20;
    /**
     * log前缀
     */
    private  String logTag = "HttpUtil";

    public <T> T createService(Class<T> tClass) {
        return mRetrofit.create(tClass);
    }

    /**
     * 设置Retrofit
     */
    public HttpUtil(String httpUrlDns) {
        mRetrofit = new Retrofit.Builder()
                .client(initClient())
                .baseUrl(httpUrlDns)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(CustomConverterFactory.create())
                .build();
    }

    /**
     * 设置okHttp
     *
     * @return okHttp
     */
    private OkHttpClient initClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //添加log日志
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLogger(logTag));
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }
        //设置超时
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.readTimeout(READ_OR_WRITE_TIMEOUT, TimeUnit.SECONDS);
        builder.writeTimeout(READ_OR_WRITE_TIMEOUT, TimeUnit.SECONDS);
        //错误重连
        builder.retryOnConnectionFailure(true);
        return builder.build();
    }
}