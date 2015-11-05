package by.vshkl.localweather.ui;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.List;

import by.vshkl.localweather.R;
import by.vshkl.localweather.service.FetchWeatherResultReceiver;
import by.vshkl.localweather.service.FetchWeatherService;
import by.vshkl.localweather.weather.WeatherObject;
import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ACTION_FETCH_WEATHER = "by.vshkl.localweather.action.ACTION_FETCH_WEATHER";
    private static final String EXTRA_RECEIVER = "receiver";
    private static final String EXTRA_LIST = "weather_data_list";
    private static final String DB_BOOK_NAME = "weather_data";
    private static final String DB_ITEM_NAME = "weather_objects";

    private SwipeRefreshLayout swipeRefreshLayout;
    private CoordinatorLayout coordinator;
    private FetchWeatherResultReceiver receiver;
    private WeatherFragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        coordinator = (CoordinatorLayout) findViewById(R.id.coordinator);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        swipeRefreshLayout.setOnRefreshListener(this);
        setupServiceReceiver();
        Paper.init(this);
        readFromDb();
    }

    @Override
    protected void onStart() {
        super.onStart();
        fragment = (WeatherFragment) getFragmentManager().findFragmentById(R.id.weather_fragment);
    }

    @Override
    public void onRefresh() {
        refresh();
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
                    swipeRefreshLayout.setRefreshing(false);
                    fragment.setRecyclerViewAdapter(list);
                    writeToDb(list);
                } else if (resultCode == RESULT_CANCELED) {
                    swipeRefreshLayout.setRefreshing(false);
                    showSnackbar("Connection timeout");
                }
            }
        });
    }

    private void refresh() {
        if (isNetworkAvailable()) {
            startService();
        } else {
            swipeRefreshLayout.setRefreshing(false);
            showSnackbar("No connection");
        }
    }

    private void showSnackbar(String text) {
        Snackbar snackbar = Snackbar.make(coordinator, text, Snackbar.LENGTH_LONG);
        snackbar.setAction("RETRY", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
        snackbar.show();
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void writeToDb(final List<WeatherObject> weatherObjects) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Paper.book(DB_BOOK_NAME).write(DB_ITEM_NAME, weatherObjects);
            }
        }).start();
    }

    private void readFromDb() {
        final MainActivity activity = this;
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (Paper.book(DB_BOOK_NAME).exist(DB_ITEM_NAME)) {
                    final List<WeatherObject> list = Paper.book(DB_BOOK_NAME).read(DB_ITEM_NAME);
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            fragment.recyclerView.setAdapter(new WeatherAdapter(list));
                        }
                    });
                }
            }
        }).start();
    }
}
