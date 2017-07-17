package com.beecho.springxoxo.util;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author 春哥大魔王
 */

public class TimerUtil {
    private static final long PERIOD_DAY = 24 * 60 * 60 * 1000;
    private static final Timer timer = new Timer();

    /**
     * 每天执行一次定时器
     * @param task 定时任务
     * @param hour 几点执行
     * @param minute 几分执行
     * @param second 几秒执行
     */
    public static void runEveryday(TimerTask task,int hour,int minute,int second) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        Date date = calendar.getTime(); // 第一次执行定时任务时间
        if (date.before(new Date())) {
            date = addDays(date, 1);
        }
        timer.schedule(task, date, PERIOD_DAY);
    }

    /**
     * 增加日期天数
     * @param date
     * @param num
     * @return
     */
    private static Date addDays(Date date,int num) {
        Calendar startDT = Calendar.getInstance();
        startDT.setTime(date);
        startDT.add(Calendar.DAY_OF_MONTH, num);

        return startDT.getTime();
    }
}
