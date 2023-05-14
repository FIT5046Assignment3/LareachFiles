package com.fit.quizcrafter.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static Retrofit retrofit;
    public static final String BASE_URL = "http://api.weatherstack.com/";
    public static final String access_key = "6b013cf4b70f9474c7c7691a49d57323";

    public static RetrofitWeather getRetrofitService(){
        retrofit=new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(RetrofitWeather.class);
    }


}
