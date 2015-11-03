package by.vshkl.localweather.weather;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FetchWeatherHelper {
    private static final String FORECAST_ID = "forecast";
    private static final String FORECAST_ATTRIBUTE = "onmouseover";
    private static final String FORECAST_ICON_ATTRIBUTE = "src";

    public List<WeatherObject> fetchWeather(String URL) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://6.pogoda.by/26850").build();

        String body = "";
        try {
            Response response = client.newCall(request).execute();
            body = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document document = Jsoup.parse(body);
        Element forecast = document.getElementById(FORECAST_ID);
        Elements forecastItems = forecast.getElementsByAttribute(FORECAST_ATTRIBUTE);

        List<WeatherObject> weatherObjects = new ArrayList<>();
        for (Element item : forecastItems) {
            WeatherObject weatherObject = new WeatherObject();
            weatherObject.setDayPart(item.child(0).data());
            weatherObject.setTemperature(item.child(1).data());
            weatherObject.setIconUrl(item.child(2).attr(FORECAST_ICON_ATTRIBUTE));
            weatherObject.setDescription(item.child(3).data());
            weatherObject.setWind(item.child(4).data());
            weatherObject.setPressure(item.child(5).data());
            weatherObject.setHumidity(item.child(6).data());
            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }
}
