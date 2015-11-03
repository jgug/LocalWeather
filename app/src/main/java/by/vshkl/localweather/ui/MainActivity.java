package by.vshkl.localweather.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import by.vshkl.localweather.services.FetchWeatherService;
import by.vshkl.localweather.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        FetchWeatherService.startActionFetchWeather(this);
    }
}
