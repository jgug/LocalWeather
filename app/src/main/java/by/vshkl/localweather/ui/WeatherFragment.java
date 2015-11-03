package by.vshkl.localweather.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import by.vshkl.localweather.R;
import by.vshkl.localweather.weather.WeatherObject;

public class WeatherFragment extends Fragment {
    private RecyclerView recyclerView;
    private WeatherAdapter adapter;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public void setRecyclerViewAdapter(List<WeatherObject> list) {
        if (adapter == null) {
            adapter = new WeatherAdapter(list);
        } else {
            adapter.updateAdapter(list);
        }
    }
}
