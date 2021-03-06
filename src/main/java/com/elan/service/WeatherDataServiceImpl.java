package com.elan.service;

import com.elan.config.Url;
import com.elan.util.JsonUtil;
import com.elan.util.LogUtil;
import com.elan.vo.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

/**
 * WeatherDataService 实现.
 *
 * @author elan
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {


	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private RedisService redisService;
	
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = Url.BY_ID + cityId;
		System.out.println(uri);
		return this.doGetWeahter(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = Url.BY_KEY + cityName;
		return this.doGetWeahter(uri);
	}
	
	private WeatherResponse doGetWeahter(String uri) {
		ResponseEntity<String> respString;
		WeatherResponse resp;
		String strBody = null;
		//拿缓存
		if (redisService.exists(uri)){
			LogUtil.info("=========>命中redis缓存",this.getClass());
			resp = (WeatherResponse) redisService.get(uri);

		}else{
           //无缓存，外网
			resp = getWeatherResponse(uri, strBody);
			redisService.set(uri, resp);
		}
		return resp;
	}


	//TODO: 优化设置
	@Async("simpleExecutor")
	public void doGetWeahterAsync() {
		for (long j= 101280101; j< 101282104; j+=100){
			//失败计数器
			int countInvalid = 0;
			for (long i=j;i<101282104;i++){
			  String uri = Url.BY_ID+i;
			  ResponseEntity<String> respString=null;
			  WeatherResponse resp;
			  String strBody = null;
			  resp=getWeatherResponse(uri, strBody);
				try {
					//防止访问过度
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			  if(!StringUtils.isEmpty(resp)){
				  if (resp.getStatus()==1000){
					  LogUtil.info("=========>"+uri,this.getClass());
					  redisService.set(uri, resp);
				  }else{
                     countInvalid++;
				  }
				  if (countInvalid>=3){
				  	  //正常无此城市
					  LogUtil.info("=========>faile:"+countInvalid,this.getClass());
					  break;
				  }

			  }else{
			  	  //异常终止同步
				  countInvalid++;
				  if (countInvalid>=3){
					  LogUtil.info("=========>faile:"+countInvalid,this.getClass());
					  break;
				  }
			  }
		  }
		}
	}

	private WeatherResponse getWeatherResponse(String uri, String strBody) {
		ResponseEntity<String> respString;
		WeatherResponse resp;
		respString = restTemplate.getForEntity(uri, String.class);
		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		}
		resp = JsonUtil.convertString2Obj(strBody, WeatherResponse.class);
		return resp;
	}

}
