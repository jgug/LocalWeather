package by.vshkl.localweather.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import by.vshkl.localweather.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    TextView weatherDayPart;
    TextView weatherDate;
    TextView weatherDescription;
    TextView weatherWind;
    TextView weatherPressure;
    TextView weatherHumidity;
    TextView weatherTempMax;
    TextView weatherTempMin;
    ImageView weatherIcon;

    public WeatherViewHolder(final View itemView) {
        super(itemView);
        weatherDayPart = (TextView) itemView.findViewById(R.id.weather_day_part);
        weatherDescription = (TextView) itemView.findViewById(R.id.weather_description);
        weatherWind = (TextView) itemView.findViewById(R.id.weather_wind);
        weatherPressure = (TextView) itemView.findViewById(R.id.weather_pressure);
        weatherHumidity = (TextView) itemView.findViewById(R.id.weather_humidity);
        weatherTempMax = (TextView) itemView.findViewById(R.id.weather_temp_max);
        weatherTempMin = (TextView) itemView.findViewById(R.id.weather_temp_min);
        weatherIcon = (ImageView) itemView.findViewById(R.id.weather_icon);
    }
}
