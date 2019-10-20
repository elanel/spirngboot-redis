package com.elan.service;

import com.elan.vo.Weather;
import com.elan.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: create by elan
 * @version: v1.0
 * @description: com.elan.service
 * @date:2019/10/20
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {

    @Autowired
    private WeatherDataService dataService;

    @Override
    public Weather getDataByCityId(String cityID) {

        WeatherResponse response = dataService.getDataByCityId(cityID);
        Weather data = response.getData();

        return data;
    }
}
