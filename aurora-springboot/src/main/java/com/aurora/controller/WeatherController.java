package com.aurora.controller;


import com.aurora.model.vo.ResultVO;
import com.aurora.util.WeatherForecast;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class WeatherController {

    @GetMapping("/api/weather")
    public ResultVO getWeather(@RequestParam String userCity){
        return ResultVO.ok(WeatherForecast.getTjWether(userCity));
    }



}
