package by.vshkl.localweather.service;

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
import java.util.regex.Pattern;

import by.vshkl.localweather.weather.WeatherObject;

public class FetchWeatherHelper {
    private static final String FORECAST_ID = "forecast";
    private static final String FORECAST_ATTRIBUTE = "onmouseover";
    private static final String FORECAST_ICON_ATTRIBUTE = "src";
    private static final String DEGREE_SUMBOL = "°";
    private static final String WIND_TEXT = "Ветер ";
    private static final String WIND_SPEED_UNITS = " м/c";
    private static final String PRESSURE_TEXT = "Давление ";
    private static final String PRESSURE_UNITS_1 = " гПа ";
    private static final String PRESSURE_UNITS_2 = " мм.рт.ст ";
    private static final String HUMIDITY_TEXT = "Влажность воздуха ";
    private static final String HUMIDITY_UNITS = "%";

    public List<WeatherObject> fetchWeather() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://6.pogoda.by/26850").build();

        String body = "";
        try {
            Response response = client.newCall(request).execute();
            if (response != null) {
                body = response.body().string();
                if (body.contains("request was interrupted by the host!")) {
                    return null;
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Document document = Jsoup.parse(body);
        Element forecast = document.getElementById(FORECAST_ID);
        if (forecast == null) {
            return null;
        }
        Elements forecastItems = forecast.getElementsByAttribute(FORECAST_ATTRIBUTE);

        List<WeatherObject> weatherObjects = new ArrayList<>();
        for (Element item : forecastItems) {
            WeatherObject weatherObject = new WeatherObject();
            weatherObject.setDayPart(item.child(0).text());
            String[] temperatures = item.child(1).text().split(Pattern.quote(".."));
            weatherObject.setTemperatureMin(temperatures[0].concat(DEGREE_SUMBOL));
            weatherObject.setTemperatureMax(temperatures[1].concat(DEGREE_SUMBOL));
            weatherObject.setIconUrl(item.child(2).child(0).attr(FORECAST_ICON_ATTRIBUTE));
            weatherObject.setDescription(item.child(3).text());
            weatherObject.setWind(formatWind(item.child(4).text()));
            weatherObject.setPressure(formatPressure(item.child(5).text()));
            weatherObject.setHumidity(formatHumidity(item.child(6).text()));
            weatherObjects.add(weatherObject);
        }

        return weatherObjects;
    }

    private static String formatWind(String s) {
        String[] tempArr = s.split(Pattern.quote(","), 0);
        StringBuilder builder = new StringBuilder(WIND_TEXT);
        builder.append(tempArr[0]).append(WIND_SPEED_UNITS).append(",").append(tempArr[1]);
        return builder.toString();
    }

    private static String formatPressure(String s) {
        String[] tempArr = s.split(Pattern.quote(" "));
        StringBuilder builder = new StringBuilder(PRESSURE_TEXT);
        builder.append(tempArr[0]).append(PRESSURE_UNITS_1).append("(")
                .append(tempArr[1].substring(1, tempArr[1].length() - 1))
                .append(PRESSURE_UNITS_2).append(")");
        return builder.toString();
    }

    private static String formatHumidity(String s) {
        StringBuilder builder = new StringBuilder(HUMIDITY_TEXT);
        builder.append(s).append(HUMIDITY_UNITS);
        return builder.toString();
    }
}
