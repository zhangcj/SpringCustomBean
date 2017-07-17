package com.beecho.springxoxo.startup;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 春哥大魔王
 */

public class Startup {
    // 加载spring task
    public static ApplicationContext context = new ClassPathXmlApplicationContext("spring/applicationContext.xml");
    public static int FULL_THREAD_TOTAL = 10;
    public static ExecutorService service = Executors.newFixedThreadPool(FULL_THREAD_TOTAL);
    public static boolean isRunning = false;
    public static final AtomicInteger threadNumber = new AtomicInteger(0);
    public static final AtomicInteger gzThreadNumber = new AtomicInteger(0);
}
