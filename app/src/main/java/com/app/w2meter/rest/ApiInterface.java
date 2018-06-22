package com.app.w2meter.rest;

/**
 * Created by etrans on 24/4/18.
 */



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface ApiInterface {


    @FormUrlEncoded
    @POST("teacher/login")
    Call<String> doLogin2(@Field("username") String username, @Field("password") String password, @Field("user_type") String user_type);

    @POST("start_live_stream{params}")
    Call<String> startLiveStream2(@Path("params") String params);


    @POST("start_live_stream")
    Call<String> startLiveStream(@Body String startParams);

    @FormUrlEncoded
    @POST("authenticate?")
    Call<String> doLogin(@Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST("vehicles?")
    Call<String> doVehiclesList(@Field("jsession") String jsession);

    @FormUrlEncoded
    @POST("logout?")
    Call<String> doLogout(@Field("jsession") String jsession);

    @POST("get_device_health")
    Call<String> GetDeviceHealth(@Body String startParams);

}
