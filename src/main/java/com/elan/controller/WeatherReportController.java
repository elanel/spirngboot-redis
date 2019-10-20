package com.elan.controller;

import com.elan.service.CityDataService;
import com.elan.service.CityDataServiceImpl;
import com.elan.service.WeatherDataService;
import com.elan.service.WeatherReportService;
import com.elan.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


/**
 * Weather Controller.
 */
@RestController
@RequestMapping("/report")
public class WeatherReportController {
	@Autowired
	private WeatherReportService reportService;
	@Autowired
	private CityDataService cityDataService;

	@GetMapping("/cityId/{cityId}")
	public ModelAndView getWeatherByCityId(@PathVariable("cityId") String cityId, Model model) throws Exception {
		model.addAttribute("title","Elan Weather Data Service");
		model.addAttribute("cityId",cityId);
        model.addAttribute("cityList",cityDataService.listCity());
        model.addAttribute("report",reportService.getDataByCityId(cityId));
		ModelAndView modelAndView = new ModelAndView("weather/report", "reportModel", model);
		return modelAndView;
	}
	


}
