package by.vshkl.localweather.weather;

public class WeatherObject {
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
}
