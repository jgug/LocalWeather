package by.vshkl.localweather.ui;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import by.vshkl.localweather.R;
import by.vshkl.localweather.services.FetchWeatherResultReceiver;
import by.vshkl.localweather.services.FetchWeatherService;
import by.vshkl.localweather.weather.WeatherObject;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ACTION_FETCH_WEATHER = "by.vshkl.localweather.action.ACTION_FETCH_WEATHER";
    private static final String EXTRA_RECEIVER = "receiver";
    private static final String EXTRA_LIST = "weather_data_list";

    private SwipeRefreshLayout swipeRefreshLayout;
    private FetchWeatherResultReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        setupServiceReceiver();
    }

    @Override
    public void onRefresh() {
        if (isNetworkAvailable()) {
            startService();
        } else {
            Toast.makeText(this, "No network connection", Toast.LENGTH_SHORT).show();
        }
    }

    private void startService() {
        Intent intent = new Intent(this, FetchWeatherService.class);
        intent.setAction(ACTION_FETCH_WEATHER);
        intent.putExtra(EXTRA_RECEIVER, receiver);
        startService(intent);
    }

    private void setupServiceReceiver() {
        receiver = new FetchWeatherResultReceiver(new Handler());
        receiver.setReceiver(new FetchWeatherResultReceiver.Receiver() {
            @Override
            public void onReceiverResult(int resultCode, Bundle resultData) {
                if (resultCode == RESULT_OK) {
                    List<WeatherObject> list = resultData.getParcelableArrayList(EXTRA_LIST);
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }
}
