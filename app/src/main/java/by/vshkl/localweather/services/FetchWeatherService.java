package by.vshkl.localweather.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import by.vshkl.localweather.weather.FetchWeatherHelper;

public class FetchWeatherService extends IntentService {
    private static final String ACTION_FETCH_WEATHER = "by.vshkl.localweather.action.ACTION_FETCH_WEATHER";
    private static final String URL = "http://6.pogoda.by/26850";

    public static void startActionFetchWeather(Context context) {
        Intent intent = new Intent(context, FetchWeatherService.class);
        intent.setAction(ACTION_FETCH_WEATHER);
        context.startService(intent);
    }

    public FetchWeatherService() {
        super("FetchWeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_WEATHER.equals(action)) {
                FetchWeatherHelper helper = new FetchWeatherHelper();
//                List<WeatherObject> list = helper.fetchWeather(URL);
                helper.fetchWeather(URL);
            }
        }
    }
}