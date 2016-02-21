package com.project.sunshine.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.project.sunshine.R;
import com.project.sunshine.model.APIResponse;
import com.project.sunshine.model.Constant;
import com.project.sunshine.model.Temperature;
import com.project.sunshine.utils.HelperUtil;
import com.project.sunshine.utils.LogConfig;
import com.project.sunshine.utils.OpenWeatherAPI;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Nguyen Dinh Duc on 2/20/2016.
 */
public class ForecastFragment extends Fragment implements Callback<APIResponse> {
    private static final String TAG = "ForecastFragment";
    String[] fakeData = {
            "Today - Sunny - 88/63",
            "Tomorrow - Foggy - 50/24",
            "Weds - Rainy - 11/34",
            "Thurs - Sunny - 93/56",
            "Fri - Cloudy - 60/50",
            "Sat - Cold - 50/14",
            "Sun - Hot - 99/100"
    };
    List<String> forecastWeathers = new ArrayList<>(Arrays.asList(fakeData));
    String cityID;
    private ArrayAdapter<String> arrayAdapter;

    public ForecastFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        arrayAdapter = new ArrayAdapter<>(getActivity(),
                R.layout.list_item_forecast,
                R.id.list_item_forecast_textview,
                forecastWeathers);
        ListView forecastListView = (ListView) rootView.findViewById(R.id.listview_forecast);
        forecastListView.setAdapter(arrayAdapter);
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.forecast_fragment, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_refresh:
                fetchDataFromAPI();
                break;
        }
        return true;
    }

    @Override
    public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
        if (LogConfig.ON) {
            Log.d(TAG, response.body().city.getName());
            Log.d(TAG, response.body().weatherOfDays.get(0).getTemp().getMax() + "");
            Log.d(TAG, response.body().weatherOfDays.get(0).getWeathers().get(0).getMain());
        }
        getWeatherForecastFromResponse(response);
    }

    @Override
    public void onFailure(Call<APIResponse> call, Throwable t) {
        if (LogConfig.ON) {
            t.printStackTrace();
        }
    }

    public void fetchDataFromAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        OpenWeatherAPI openWeatherAPI = retrofit.create(OpenWeatherAPI.class);

        cityID = "1581129";
        Call<APIResponse> call = openWeatherAPI.loadCity(cityID);
        call.enqueue(this);
    }

    public void getWeatherForecastFromResponse(Response<APIResponse> response) {
        String date;
        String main;
        String temperature;
//        forecastWeathers.clear();
        arrayAdapter.clear();
        for (int i = 0; i < 7; i++) {
            Temperature temp = response.body().weatherOfDays.get(i).getTemp();
            date = HelperUtil.getReadableDateString(System.currentTimeMillis() + i * TimeUnit.DAYS.toMillis(1));
            main = response.body().weatherOfDays.get(i).getWeathers().get(0).getMain();
            temperature = HelperUtil.formatMaxMinTemp(temp.getMax(), temp.getMin());
            arrayAdapter.add(date + " - " + main + " - " + temperature);
        }
        arrayAdapter.notifyDataSetChanged();
    }
}
