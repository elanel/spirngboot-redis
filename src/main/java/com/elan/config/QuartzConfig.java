package com.elan.config;

import com.elan.job.WeatherDataSyncJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: create by elan
 * @version: v1.0
 * @description: com.elan.config
 * @date:2019/10/19
 */
@Configuration
public class QuartzConfig {

    public static int INTERVAL_SEC=30;

    //jobDetail
    @Bean
    public JobDetail weatherDataSyncJobDetail(){
        JobDetail jobDetail = JobBuilder.newJob(WeatherDataSyncJob.class)
                .withIdentity("WeatherSyncJob").storeDurably().build();
        return jobDetail;
    }

    @Bean
    public Trigger weatherDataSyncTrigger(){

       SimpleScheduleBuilder schedBuilder = SimpleScheduleBuilder.simpleSchedule()
               .withIntervalInSeconds(INTERVAL_SEC)
               .repeatForever();

        SimpleTrigger weatherSyncTrigger = TriggerBuilder.newTrigger().forJob(weatherDataSyncJobDetail())
                .withIdentity("WeatherSyncTrigger").withSchedule(schedBuilder)
                .build();
        return weatherSyncTrigger;
    }


}
