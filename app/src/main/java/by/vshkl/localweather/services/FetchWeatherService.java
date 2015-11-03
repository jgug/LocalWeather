package by.vshkl.localweather.services;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;

import java.util.List;

import by.vshkl.localweather.weather.FetchWeatherHelper;
import by.vshkl.localweather.weather.WeatherObject;

public class FetchWeatherService extends IntentService {
    private static final String ACTION_FETCH_WEATHER = "by.vshkl.localweather.action.ACTION_FETCH_WEATHER";
    private static final String URL = "http://6.pogoda.by/26850";
    private static final String EXTRA_RECEIVER = "receiver";

    private FetchWeatherResultReceiver receiver;

    public FetchWeatherService() {
        super("FetchWeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_WEATHER.equals(action)) {
                FetchWeatherHelper helper = new FetchWeatherHelper();
                List<WeatherObject> list = helper.fetchWeather(URL);
                FetchWeatherResultReceiver receiver = intent.getParcelableExtra(EXTRA_RECEIVER);
                Bundle bundle = new Bundle();
            }
        }
    }
}
