package com.aurora.util;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.text.DecimalFormat;

/**
 * @Author feng
 * @Version 1.0.0
 * @Description xxx
 * Created at 2023/6/9
 */

public class WeatherForecast {


    //和风天气key
    private static String key= "";


    public static   JSONObject getTjWether(String userCity)  {
        String url = "https://api.qweather.com/v7/weather/now";
        try {
            url = HttpUtil.getUrl("https://geoapi.qweather.com/v2/city/lookup?location=" + userCity + "&key="+key);
            JSONObject jsonObject1 = JSON.parseObject(url);
             //System.out.println(jsonObject1);
            JSONObject location = (JSONObject)jsonObject1.getJSONArray("location").get(0);

            //纬度
            String lat = location.getString("lat");
            double dlat = Double.parseDouble(lat);
            DecimalFormat df = new DecimalFormat("#.##");
            lat = df.format(dlat);
            //System.out.println(lat);
            //经度
            String lon = location.getString("lon");
            double dlon = Double.parseDouble(lon);
            lon = df.format(dlon);
            //System.out.println(lon);

            String weather = HttpUtil.getUrl("https://devapi.qweather.com/v7/weather/now?location=" + lon+","+lat + "&key=" + key);
            // System.out.println(weather);
            JSONObject jsonObject = JSON.parseObject(weather);

            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;


    }





}

