package by.vshkl.localweather.services;

import android.app.Activity;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;

import java.util.ArrayList;
import java.util.List;

import by.vshkl.localweather.weather.FetchWeatherHelper;
import by.vshkl.localweather.weather.WeatherObject;

public class FetchWeatherService extends IntentService {
    private static final String ACTION_FETCH_WEATHER = "by.vshkl.localweather.action.ACTION_FETCH_WEATHER";
    private static final String EXTRA_RECEIVER = "receiver";
    private static final String EXTRA_LIST = "weather_data_list";

    public FetchWeatherService() {
        super("FetchWeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_FETCH_WEATHER.equals(action)) {
                final ResultReceiver receiver = intent.getParcelableExtra(EXTRA_RECEIVER);
                FetchWeatherHelper helper = new FetchWeatherHelper();
                List<WeatherObject> list = helper.fetchWeather();
                if (list != null) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelableArrayList(EXTRA_LIST, (ArrayList<WeatherObject>) list);
                    receiver.send(Activity.RESULT_OK, bundle);
                } else {
                    receiver.send(Activity.RESULT_CANCELED, new Bundle());
                }
            }
        }
    }
}
