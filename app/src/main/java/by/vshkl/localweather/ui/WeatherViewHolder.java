package by.vshkl.localweather.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import by.vshkl.localweather.R;

public class WeatherViewHolder extends RecyclerView.ViewHolder {
    TextView weatherDate;
    TextView weatherDescription;
    TextView weatherWind;
    TextView weatherPressure;
    TextView weatherHumidity;
    TextView weatherTemp;
    ImageView weatherIcon;

    public WeatherViewHolder(final View itemView) {
        super(itemView);
        weatherDate = (TextView) itemView.findViewById(R.id.weather_date);
        weatherDescription = (TextView) itemView.findViewById(R.id.weather_description);
        weatherWind = (TextView) itemView.findViewById(R.id.weather_wind);
        weatherPressure = (TextView) itemView.findViewById(R.id.weather_pressure);
        weatherHumidity = (TextView) itemView.findViewById(R.id.weather_humidity);
        weatherTemp = (TextView) itemView.findViewById(R.id.weather_temp);
        weatherIcon = (ImageView) itemView.findViewById(R.id.weather_icon);
    }

}
