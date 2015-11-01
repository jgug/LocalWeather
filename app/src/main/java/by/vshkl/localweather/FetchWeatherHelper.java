package by.vshkl.localweather;

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
    private static final String FORECAST_ATTRIBUTE = "onmousehover";
    private static final String FORECAST_ICON_ATTRIBUTE = "src";

    public List<WeatherObject> FetchWeather(String URL) {
        return getWeather(getElements(getPageBody(URL)));
    }

    private String getPageBody(String url) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        String body = "";
        try {
            Response response = client.newCall(request).execute();
            body = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return body;
    }

    private Elements getElements(String pageBody) {
        Document document = Jsoup.parse(pageBody);
        Element element = document.getElementById(FORECAST_ID);
        return element.getElementsByAttribute(FORECAST_ATTRIBUTE);
    }

    private List<WeatherObject> getWeather(Elements elements) {
        List<WeatherObject> weatherObjects = new ArrayList<>();
        for (Element item : elements) {
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
