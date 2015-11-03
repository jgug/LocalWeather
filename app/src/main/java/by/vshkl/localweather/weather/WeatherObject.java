package by.vshkl.localweather.weather;

import android.os.Parcel;
import android.os.Parcelable;

public class WeatherObject implements Parcelable {
    private static final String URL = "http://6.pogoda.by/";

    private String dayPart;
    private String description;
    private String wind;
    private String pressure;
    private String humidity;
    private String temperature;
    private String iconUrl;

    public WeatherObject() {
    }

    public WeatherObject(String dayPart, String description, String wind,
                         String pressure, String humidity, String temperature, String iconUrl) {
        this.dayPart = dayPart;
        this.description = description;
        this.wind = wind;
        this.pressure = pressure;
        this.humidity = humidity;
        this.temperature = temperature;
        this.iconUrl = iconUrl;
    }

    public WeatherObject(Parcel parcel) {
        dayPart = parcel.readString();
        description = parcel.readString();
        wind = parcel.readString();
        pressure = parcel.readString();
        humidity = parcel.readString();
        temperature = parcel.readString();
        iconUrl = parcel.readString();
    }

    public String getDayPart() {
        return dayPart;
    }

    public String getDescription() {
        return description;
    }

    public String getWind() {
        return wind;
    }

    public String getPressure() {
        return pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setDayPart(String dayPart) {
        this.dayPart = dayPart;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = URL + iconUrl;
    }

    @Override
    public String toString() {
        return "WeatherObject{" +
                "dayPart='" + dayPart + '\'' +
                ", description='" + description + '\'' +
                ", wind='" + wind + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", temperature='" + temperature + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dayPart);
        dest.writeString(description);
        dest.writeString(wind);
        dest.writeString(pressure);
        dest.writeString(humidity);
        dest.writeString(temperature);
        dest.writeString(iconUrl);
    }

    public static final Parcelable.Creator<WeatherObject> CREATOR =
            new Parcelable.Creator<WeatherObject>() {

        @Override
        public WeatherObject createFromParcel(Parcel source) {
            return new WeatherObject(source);
        }

        @Override
        public WeatherObject[] newArray(int size) {
            return new WeatherObject[size];
        }
    };
}
