package by.vshkl.localweather.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import by.vshkl.localweather.R;

public class WeatherFragment extends Fragment {
    private RecyclerView recyclerView;

    public WeatherFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        recyclerView = (RecyclerView) container.findViewById(R.id.recycler_view);
        return inflater.inflate(R.layout.fragment_weather, container, false);
    }

}
