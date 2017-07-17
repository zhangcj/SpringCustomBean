package com.beecho.springxoxo.business;

import com.beecho.springxoxo.service.DataService;
import com.beecho.springxoxo.startup.Startup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author 春哥大魔王
 */

public class IncrementBusinessTask implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(IncrementBusinessTask.class);
    public static final int MEMCAHE_EXPIRE_TIME = 24 * 3600;
    public static final int RUN_END_TIME = -1;
    public static final int RUN_INTERVAL = -1;
    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    private DataService dataService;

    private String timeCacheKey = "timeCacheKey";

    public void setTimeCacheKey(String timeCacheKey) {
        this.timeCacheKey = timeCacheKey;
    }

    public void setDataService(DataService dataService) {
        this.dataService = dataService;
    }

    public void run() throws RuntimeException {
        if (Startup.isRunning) {
            return;
        }
        try {
            String lastTime = redisTemplate.opsForValue().get(timeCacheKey);
            if (StringUtils.isEmpty(lastTime)) {
                lastTime = getNewTime(Calendar.SECOND, -2 * RUN_INTERVAL + RUN_END_TIME);
            } else {
                logger.info("lastTime{}", lastTime);
            }
            String endTime = getNewTime(Calendar.SECOND, RUN_END_TIME); //当前时间
            redisTemplate.opsForValue().set(timeCacheKey, endTime);
            List<Long> ids = dataService.getIds(lastTime, endTime);
            dataService.dealDataByIds(ids, null);
            ids = null;
            //更新时间戳
            lastTime = endTime;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 取当前时间
     *
     * @return 当前时间
     */
    private static String getNewTime(Integer timeUnit, int interval) {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
        Calendar date = Calendar.getInstance();
        if (timeUnit != null) {
            date.set(timeUnit, date.get(timeUnit) + interval);
        }
        String newTime = sdf.format(date.getTime());
        return newTime;
    }
}