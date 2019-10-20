package com.elan.service;

import com.elan.vo.Weather;

/**
 * @author: create by elan
 * @version: v1.0
 * @description: com.elan.service
 * @date:2019/10/20
 */
public interface WeatherReportService {

   Weather getDataByCityId(String cityID);


}
