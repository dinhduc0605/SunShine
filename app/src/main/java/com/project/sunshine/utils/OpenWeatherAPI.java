package com.project.sunshine.utils;

import com.project.sunshine.model.APIResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Nguyen Dinh Duc on 2/20/2016.
 */
public interface OpenWeatherAPI {
    @GET("/data/2.5/forecast/daily?mode=json&units=metric&cnt=7&appid=5f2dd83b2333c3797bbdce266bb37bde")
    Call<APIResponse> loadCity(@Query("q") String name);
}
