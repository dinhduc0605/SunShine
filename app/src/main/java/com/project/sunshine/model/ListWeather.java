package com.project.sunshine.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Nguyen Dinh Duc on 2/20/2016.
 */
public class ListWeather {
    Temperature temp;

    @SerializedName("weather")
    List<Weather> weathers;

    public List<Weather> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<Weather> weathers) {
        this.weathers = weathers;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }
}
