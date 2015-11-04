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
    private String temperatureMax;
    private String temperatureMin;
    private String iconUrl;
    private String date;

    public WeatherObject() {
    }

    public WeatherObject(Parcel parcel) {
        dayPart = parcel.readString();
        description = parcel.readString();
        wind = parcel.readString();
        pressure = parcel.readString();
        humidity = parcel.readString();
        temperatureMax = parcel.readString();
        temperatureMin = parcel.readString();
        iconUrl = parcel.readString();
        date = parcel.readString();
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

    public String getTemperatureMax() {
        return temperatureMax;
    }

    public String getTemperatureMin() {
        return temperatureMin;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setDayPart(String dayPart) {
        this.dayPart =  (dayPart.substring(0, 1).toUpperCase()).concat(dayPart.substring(1));
    }

    public String getDate() {
        return date;
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

    public void setTemperatureMax(String temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public void setTemperatureMin(String temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = URL + iconUrl;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "WeatherObject{" +
                "dayPart='" + dayPart + '\'' +
                ", description='" + description + '\'' +
                ", wind='" + wind + '\'' +
                ", pressure='" + pressure + '\'' +
                ", humidity='" + humidity + '\'' +
                ", temperatureMax='" + temperatureMax + '\'' +
                ", temperatureMin='" + temperatureMin + '\'' +
                ", iconUrl='" + iconUrl + '\'' +
                ", date='" + date + '\'' +
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
        dest.writeString(temperatureMax);
        dest.writeString(temperatureMin);
        dest.writeString(iconUrl);
        dest.writeString(date);
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
