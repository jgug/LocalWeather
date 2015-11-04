package by.vshkl.localweather.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import by.vshkl.localweather.R;
import by.vshkl.localweather.weather.WeatherObject;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private List<WeatherObject> weatherList;

    public WeatherAdapter(List<WeatherObject> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_weather, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        WeatherObject weatherObject = weatherList.get(position);
        holder.weatherDate.setText(weatherObject.getDayPart());
        holder.weatherDescription.setText(weatherObject.getDescription());
        holder.weatherWind.setText(weatherObject.getWind());
        holder.weatherPressure.setText(weatherObject.getPressure());
        holder.weatherHumidity.setText(weatherObject.getHumidity());
        holder.weatherTempMax.setText(weatherObject.getTemperatureMax());
        holder.weatherTempMin.setText(weatherObject.getTemperatureMin());
        Picasso.with(holder.weatherIcon.getContext()).setIndicatorsEnabled(true); /*For debug*/
        Picasso.with(holder.weatherIcon.getContext())
                .load(weatherObject.getIconUrl())
                .fit()
                .centerCrop()
                .into(holder.weatherIcon);
    }

    @Override
    public int getItemCount() {
        if (weatherList == null) {
            return 0;
        } else {
            return weatherList.size();
        }
    }

    public void updateAdapter(List<WeatherObject> weatherList) {
        this.weatherList = weatherList;
    }
}
