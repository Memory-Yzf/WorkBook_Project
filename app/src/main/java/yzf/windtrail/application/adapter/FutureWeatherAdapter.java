package yzf.windtrail.application.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import yzf.windtrail.application.R;
import yzf.windtrail.application.bean.DayWeatherBean;

public class FutureWeatherAdapter extends RecyclerView.Adapter<FutureWeatherAdapter.WeatherViewHolder> {
    private Context context;
    private List<DayWeatherBean> WeatherBeans;

    public FutureWeatherAdapter(Context context,List<DayWeatherBean> weatherBeans) {
        this.context = context;
        this.WeatherBeans = weatherBeans;
    }

    @NonNull
    @Override
    public WeatherViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(context).inflate(R.layout.weather_item_layout,parent,false);
    WeatherViewHolder weatherViewHolder = new WeatherViewHolder(view);
        return weatherViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherViewHolder holder, int position) {
        DayWeatherBean weatherBean = WeatherBeans.get(position);

        // 将对应组件的内容替换为获取到的内容
        holder.iv_weather.setImageResource(getImageResponseOfWeather(weatherBean.getWeatherImg()));
        holder.tv_tem.setText(weatherBean.getTem() + "°C");
        holder.tv_weather.setText(weatherBean.getWeather());
        holder.tv_date.setText(weatherBean.getDate());
        holder.tv_win.setText(weatherBean.getWin());
        holder.tv_win_speed.setText(weatherBean.getWinSpeed());
    }

    @Override
    public int getItemCount() {
        return (WeatherBeans == null) ? 0 : WeatherBeans.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder{

        ImageView iv_weather;
        TextView tv_tem,tv_weather,tv_date,tv_win,tv_win_speed;

        public WeatherViewHolder(@NonNull View itemView){
            super(itemView);
            iv_weather = itemView.findViewById(R.id.iv_weather);
            tv_tem = itemView.findViewById(R.id.tv_tem);
            tv_weather = itemView.findViewById(R.id.tv_weather);
            tv_date = itemView.findViewById(R.id.tv_date);
            tv_win = itemView.findViewById(R.id.tv_win);
            tv_win_speed = itemView.findViewById(R.id.tv_win_speed);
        }
    }

    // 将传入的数据转换成对应的图片返回
    private int getImageResponseOfWeather(String weatherString){
        int result = 0;
        switch (weatherString){
            case "qing":
                result = R.drawable.qing;
                break;
            case "yin":
                result = R.drawable.yin;
                break;
            case "yu":
                result = R.drawable.yu;
                break;
            case "yun":
                result = R.drawable.yun;
                break;
            case "bingbao":
                result = R.drawable.bingbao;
                break;
            case "wu":
                result = R.drawable.wu;
                break;
            case "shachen":
                result = R.drawable.shachen;
                break;
            case "lei":
                result = R.drawable.lei;
                break;
            case "xue":
                result = R.drawable.xue;
                break;
            default:
                result = R.drawable.qing;
                break;
        }
        return result;
    }
}
