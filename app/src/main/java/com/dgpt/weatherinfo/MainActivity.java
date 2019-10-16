package com.dgpt.weatherinfo;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


public class MainActivity extends AppCompatActivity {
    private TextView tvMainName;
    private TextView tvMianWeather;
    private ImageView ivWeather;
    private Spinner spCityselect;
    private WeatherInfo weatherInfo;
    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what==100){
                weatherInfo=(WeatherInfo) msg.obj;
                Log.i("MainActiivity",weatherInfo.toString());
                showView();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        String cityname=spCityselect.getSelectedItem().toString();
        initData(cityname);

        spCityselect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String cityname1=spCityselect.getSelectedItem().toString();
                initData(cityname1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void showView(){
        tvMainName.setText(weatherInfo.getCity());
        tvMianWeather.setText(weatherInfo.getTemp());
    }

    private void initView(){
        tvMainName = (TextView) findViewById(R.id.tv_main_name);
        tvMianWeather = (TextView) findViewById(R.id.tv_mian_weather);
        ivWeather = (ImageView) findViewById(R.id.iv_weather);
        spCityselect = (Spinner) findViewById(R.id.sp_cityselect);
    }

    private void initData(String cityname){
        String address="https://6e18d5ba-9443-464a-95d7-9c685e536f78.mock.pstmn.io/getWeatherByID1";
        if (cityname.equals("天津")){
            address="https://6e18d5ba-9443-464a-95d7-9c685e536f78.mock.pstmn.io/getWeatherByID1";
        }
        if (cityname.equals("北京")){
            address="https://6e18d5ba-9443-464a-95d7-9c685e536f78.mock.pstmn.io/getWeatherByID";
        }
        HttpUtil.sendOkHttpRequest(address, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //获取解析成功网络请求后的字符串
                String responseString = response.body().string();
                //当访问网络成功后会执行这一个方法
                //下面我们使用gson来接收数据就可以了
                //创建gson对象
                Log.i("Mainactivity", responseString);
                JsonParser jsonParser=new JsonParser();
                JsonObject jsonObject= jsonParser.parse(responseString).getAsJsonObject();
                JsonObject weatherObject=jsonObject.get("weatherinfo").getAsJsonObject();
                Gson gson = new Gson();
                //下面将json数据与实体类相关联
               WeatherInfo weatherInfo=gson.fromJson(weatherObject, WeatherInfo.class);

                Message message=new Message();
                message.what=100;
                message.obj=weatherInfo;
                handler.sendMessage(message);
            }
        });
    }




}
