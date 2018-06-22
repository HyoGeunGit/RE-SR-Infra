package com.jinhyeokfang.androidproject.SunRinInfra;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class Client {
    public static API retrofitService;
    public static final Client INSTANCE;

    public final API getRetrofitService() {
        return retrofitService;
    }

    public final void setRetrofitService(API var1) {
        retrofitService = var1;
    }

    static {
        INSTANCE = new Client();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient logger = new OkHttpClient.Builder().addInterceptor(interceptor).readTimeout(20L, TimeUnit.SECONDS).writeTimeout(20L, TimeUnit.SECONDS).build();
        Retrofit retrofit = new retrofit2.Retrofit.Builder().baseUrl("http://58.145.101.15:3030").addConverterFactory((Converter.Factory) GsonConverterFactory.create()).client(logger).build();
        retrofitService = retrofit.create(API.class);
    }
}
