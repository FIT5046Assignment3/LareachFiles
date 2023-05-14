package com.fit.quizcrafter.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Query;

public interface RetrofitWeather {
    @GET("current")
    Call<ResponseBody> getWeatherbyLocation(@Query("access_key") String auth_key, @Query("query") String location);

    @GET("current/?access_key=6b013cf4b70f9474c7c7691a49d57323&query=melbourne")
    Call<ResponseBody> getWeather();

}
