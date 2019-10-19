package com.elan.job;

import com.elan.service.WeatherDataService;
import com.elan.util.LogUtil;
import org.quartz.JobBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author: create by elan
 * @version: v1.0
 * @description: com.elan.job
 * @date:2019/10/19
 */
public class WeatherDataSyncJob extends QuartzJobBean {

    @Autowired
    private WeatherDataService dataService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        dataService.doGetWeahterAsync();

    }


}
