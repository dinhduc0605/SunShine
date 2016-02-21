package com.project.sunshine.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Dinh Duc on 2/20/2016.
 */
public class APIResponse {
    public City city;

    @SerializedName("list")
    public List<ListWeather> weatherOfDays;
}
