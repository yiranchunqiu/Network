package com.pxz.network;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * 类说明：网络api
 * 联系：530927342@qq.com
 *
 * @author peixianzhong
 * @date 2018/10/22 11:51
 */
public interface AppApi {
    @Headers("Authorization:APPCODEc868a90b992e48678d285ac57c09fa50")
    @GET("/wtr-v3/weather/all")
    Observable<Object> weatherXiaomi(@Query("latitude") String latitude,
                                     @Query("longitude") String longitude,
                                     @Query("locationKey") String locationKey,
                                     @Query("days") String days,
                                     @Query("appKey") String appKey,
                                     @Query("sign") String sign,
                                     @Query("isGlobal") String isGlobal,
                                     @Query("locale") String locale);
    @POST("/user/register")
    Observable<Object> login(@Body Bean bean);
}