package yzf.windtrail.application.bean;

import com.google.gson.annotations.SerializedName;

public class DayWeatherBean {
    @SerializedName("date")
    private String date;

    @SerializedName("wea")
    private String weather;

    @SerializedName("wea_img")
    private String weatherImg;

    @SerializedName("tem_day")
    private String tem;

    @SerializedName("win")
    private String win;

    @SerializedName("win_speed")
    private String winSpeed;

    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }

    public String getWeather() { return weather; }
    public void setWeather(String weather) { this.weather = weather; }

    public String getWeatherImg() { return weatherImg; }
    public void setWeatherImg(String weatherImg) { this.weatherImg = weatherImg; }

    public String getTem() { return tem; }
    public void setTem(String tem) { this.tem = tem; }

    public String getWin() { return win; }
    public void setWin(String win) { this.win = win; }

    public String getWinSpeed() { return winSpeed; }
    public void setWinSpeed(String winSpeed) { this.winSpeed = winSpeed; }

    @Override
    public String toString() {
        return "DayWeatherBean{" +
                "date='" + date + '\'' +
                ", weather='" + weather + '\'' +
                ", weatherImg='" + weatherImg + '\'' +
                ", tem='" + tem + '\'' +
                ", win='" + win + '\'' +
                ", winSpeed='" + winSpeed + '\'' +
                '}';
    }
}
