package yzf.windtrail.application.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherBean {
    @SerializedName("city")
    private String city;

    @SerializedName("data")
    private List<DayWeatherBean> dayWeathers;


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<DayWeatherBean> getDayWeathers() {
        return dayWeathers;
    }

    public void setDayWeathers(List<DayWeatherBean> dayWeathers) {
        this.dayWeathers = dayWeathers;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "城市='" + city + '\'' +
                ", dayWeathers=" + dayWeathers +
                '}';
    }
}
