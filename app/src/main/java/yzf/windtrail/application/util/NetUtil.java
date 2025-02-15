package yzf.windtrail.application.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetUtil {
    public static final String URL_WEATHER = "https://v1.yiketianqi.com/free/week?unescape=1";
    public static final String APPID = "42599349";
    public static final String APPSECRET = "FZ7GQ0O1";


    public static String doGet(String urlStr){
        String result = "";
        HttpURLConnection connection = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader bufferedReader = null;
        // 连接网络
        try {
            URL url = new URL(urlStr);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);

            // 从连接中读取数据(二进制)
            InputStream inputStream = connection.getInputStream();
            inputStreamReader = new InputStreamReader(inputStream);

            // 二进制流送入缓冲区
            bufferedReader = new BufferedReader(inputStreamReader);

            // 从缓冲区中逐行读取字符
            StringBuilder stringBuilder = new StringBuilder();
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            result = stringBuilder.toString();

        } catch (Exception e){
            e.printStackTrace();
        }finally {
            if (connection != null){
                connection.disconnect();
            }

            if (inputStreamReader != null){
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bufferedReader != null){
                try {
                    bufferedReader.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        return result;
    }


    public static String getWeatherOfCity(String city){
        // 拼接获取的天气数据的URL
        String weatherUrl = URL_WEATHER + "&appid=" + APPID + "&appsecret=" + APPSECRET + "&city=" + city;
        Log.d("yzf","-------weatherUrl-------" + weatherUrl);
        String weatherResult =  doGet(weatherUrl);
        Log.d("yzf","-------weatherResult-------" + weatherResult);
        return weatherResult;
    }
}
