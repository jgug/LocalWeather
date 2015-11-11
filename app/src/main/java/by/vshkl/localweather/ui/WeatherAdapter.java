package by.vshkl.localweather.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.squareup.picasso.Picasso;

import java.util.List;

import by.vshkl.localweather.R;
import by.vshkl.localweather.weather.BaseObject;
import by.vshkl.localweather.weather.DateObject;
import by.vshkl.localweather.weather.WeatherObject;

public class WeatherAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<BaseObject> weatherList;
    private int itemWidth;
    private static final int DATE = 0;
    private static final int WEATHER = 1;

    public WeatherAdapter(List<BaseObject> weatherList) {
        this.weatherList = weatherList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case DATE:
                View viewDate = inflater.inflate(R.layout.item_date, parent, false);
                viewHolder = new DateViewHolder(viewDate);
                break;
            case WEATHER:
                View viewWeather = inflater.inflate(R.layout.item_weather_new, parent, false);
                viewHolder = new WeatherViewHolder(viewWeather);
                break;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (holder.getItemViewType()) {
            case DATE:
                DateViewHolder dateViewHolder = (DateViewHolder) holder;
                configureDateViewHolder(dateViewHolder, position);
                break;
            case WEATHER:
                WeatherViewHolder weatherViewHolder = (WeatherViewHolder) holder;
                configureWeatherViewHolder(weatherViewHolder, position);
                break;
        }
    }

    @Override
    public final int getItemViewType(int position) {
        if (weatherList.get(position) instanceof DateObject) {
            return DATE;
        } else if (weatherList.get(position) instanceof WeatherObject) {
            return WEATHER;
        }
        return -1;
    }

    @Override
    public int getItemCount() {
        if (weatherList == null) {
            return 0;
        } else {
            return weatherList.size();
        }
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (holder.getItemViewType() == WEATHER) {
            final WeatherViewHolder weatherHolder = (WeatherViewHolder) holder;
            holder.itemView.getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {
                            itemWidth = weatherHolder.weatherIcon.getWidth()
                                    + weatherHolder.weatherWind.getPaddingStart()
                                    + weatherHolder.itemView.getPaddingStart();
                            return true;
                        }
                    });
        }
    }

    public int getItemWidth() {
        return itemWidth;
    }

    private void configureWeatherViewHolder(WeatherViewHolder holder, int position) {
        WeatherObject weatherObject = (WeatherObject) weatherList.get(position);
        if (weatherObject != null) {
            holder.weatherDayPart.setText(weatherObject.getDayPart());
            holder.weatherDescription.setText(weatherObject.getDescription());
            holder.weatherWind.setText(weatherObject.getWind());
            holder.weatherPressure.setText(weatherObject.getPressure());
            holder.weatherHumidity.setText(weatherObject.getHumidity());
            holder.weatherTempMax.setText(weatherObject.getTemperatureMax());
            holder.weatherTempMin.setText(weatherObject.getTemperatureMin());
            Picasso.with(holder.weatherIcon.getContext())
                    .load(weatherObject.getIconUrl())
                    .fit()
                    .centerCrop()
                    .into(holder.weatherIcon);
        }
    }

    private void configureDateViewHolder(DateViewHolder holder, int position) {
        DateObject date = (DateObject) weatherList.get(position);
        if (date != null) {
            holder.date.setText(date.getDate());
        }
    }

    public void updateAdapter(List<BaseObject> weatherList) {
        this.weatherList = weatherList;
    }
}
