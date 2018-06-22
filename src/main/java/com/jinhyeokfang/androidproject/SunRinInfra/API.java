package com.jinhyeokfang.androidproject.SunRinInfra;

import org.jetbrains.annotations.NotNull;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {
    @POST("/user/signin")
    @FormUrlEncoded
    @NotNull
    Call<ResponseBody> logIn(@Field("id") @NotNull String id, @Field("pw") @NotNull String pw);

    @POST("/user/signup")
    @FormUrlEncoded
    @NotNull
    Call<ResponseBody> logUp(@Field("name") @NotNull String name, @Field("id") @NotNull String id, @Field("pw") @NotNull String pw);

    @GET("/time/{g}/{c}")
    @NotNull
    Call<ResponseBody> loadTimeTable(@Path("g") @NotNull String g, @Path("c") @NotNull String c);

    @POST("/find")
    @FormUrlEncoded
    @NotNull
    Call<ResponseBody> findAccount(@Field("id") @NotNull String id, @Field("name") @NotNull String name);

    @POST("/change")
    @FormUrlEncoded
    @NotNull
    Call<ResponseBody> changePassword(@Field("id") @NotNull String id, @Field("password") @NotNull String password);

    @GET("/qna")
    @NotNull
    Call<ResponseBody> loadQNA();

}