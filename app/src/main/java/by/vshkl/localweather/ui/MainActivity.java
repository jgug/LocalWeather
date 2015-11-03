package by.vshkl.localweather.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;

import by.vshkl.localweather.R;
import by.vshkl.localweather.services.FetchWeatherResultReceiver;
import by.vshkl.localweather.services.FetchWeatherService;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ACTION_FETCH_WEATHER = "by.vshkl.localweather.action.ACTION_FETCH_WEATHER";
    private static final String EXTRA_RECEIVER = "receiver";

    private SwipeRefreshLayout swipeRefreshLayout;
    private FetchWeatherResultReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {

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
                    // Do something
                }
            }
        });
    }
}
