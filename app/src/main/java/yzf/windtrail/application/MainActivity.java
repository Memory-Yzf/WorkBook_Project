package yzf.windtrail.application;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.util.List;

import yzf.windtrail.application.adapter.FutureWeatherAdapter;
import yzf.windtrail.application.bean.DayWeatherBean;
import yzf.windtrail.application.bean.WeatherBean;
import yzf.windtrail.application.util.NetUtil;

@SuppressWarnings("all")
public class MainActivity extends AppCompatActivity {

    private AppCompatSpinner Spinner;
    private ArrayAdapter<String> SpAdapter;
    private String[] Cities;


    // 设置变量
    private ImageView iv_weather;
    private TextView tv_tem,tv_weather,tv_date,tv_win,tv_win_speed;
    private RecyclerView rv_future_weather;
    private FutureWeatherAdapter WeatherAdapter;

    private Handler Hnadler = new Handler(Looper.myLooper()){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0){
                String weather = (String)msg.obj;
                //Log.d("yzf","---主线程收到了天气数据---weather---" + weather);

                Gson gson = new Gson();
                WeatherBean weatherBean = gson.fromJson(weather, WeatherBean.class);
                //Log.d("yzf","---解析后的数据---WeatherBean---" + weatherBean.toString());

                updateUiOfWeather(weatherBean);
            }
        }
    };

    // 将获取到的JSON数据替换到UI上
    private void updateUiOfWeather(WeatherBean weatherBean) {
        if (weatherBean == null){
            return;
        }

        List<DayWeatherBean> dayWeathers = weatherBean.getDayWeathers();
        DayWeatherBean dayWeather = dayWeathers.get(0);
        if (dayWeather == null){
            return;
        }

        // 替换数据
        iv_weather.setImageResource(getImageResponseOfWeather(dayWeather.getWeatherImg()));
        tv_tem.setText(dayWeather.getTem() + "°C");
        tv_weather.setText(dayWeather.getWeather());
        tv_date.setText(dayWeather.getDate());
        tv_win.setText(dayWeather.getWin());
        tv_win_speed.setText("风速：" + dayWeather.getWinSpeed());

        dayWeathers.remove(0); // 去掉当天天气数据

        // 未来天气展示
        WeatherAdapter = new FutureWeatherAdapter(this,dayWeathers);
        rv_future_weather.setAdapter(WeatherAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        rv_future_weather.setLayoutManager(layoutManager);
    }

    // 获取的天气数据对应的图片
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initView();

    }

    private void initView(){
        Spinner = findViewById(R.id.sp_city);
        Cities = getResources().getStringArray(R.array.cities);
        SpAdapter = new ArrayAdapter<>(this,R.layout.sp_item_layout,Cities);
        Spinner.setAdapter(SpAdapter);
        Spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedCity = Cities[position];
                getWeatherOfCity(selectedCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        iv_weather = findViewById(R.id.iv_weather);
        tv_tem = findViewById(R.id.tv_tem);
        tv_weather = findViewById(R.id.tv_weather);
        tv_date = findViewById(R.id.tv_date);
        tv_win = findViewById(R.id.tv_win);
        tv_win_speed = findViewById(R.id.tv_win_speed);
        rv_future_weather = findViewById(R.id.rv_future_weather);
    }

    private void getWeatherOfCity(String selectedCity) {
        //开启子线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                //请求网络
                String weatherOfCity = NetUtil.getWeatherOfCity(selectedCity);
                // 使用handler将数据传递给主线程
                Message message = Message.obtain();
                message.what = 0;
                message.obj = weatherOfCity;
                Hnadler.sendMessage(message);
            }
        }).start();
    }
}