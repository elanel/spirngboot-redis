package com.elan.util;

import com.elan.job.WeatherDataSyncJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author: create by elan
 * @version: v1.0
 * @description: com.elan.util
 * @date:2019/10/19
 */
public class LogUtil {
    private static Logger logger;

    public static void error(String errMsg,Class clazz){
        logger = LoggerFactory.getLogger(clazz);
        logger.error(errMsg);
    }

    public static void info(String msg,Class clazz){
        logger = LoggerFactory.getLogger(clazz);
        logger.info(msg);
    }

}
