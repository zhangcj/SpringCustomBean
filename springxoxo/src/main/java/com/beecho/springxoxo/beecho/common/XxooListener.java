package com.beecho.springxoxo.beecho.common;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

/**
 * @author 春哥大魔王
 */

public class XxooListener implements ApplicationListener<ApplicationEvent> {
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        System.out.println("onApplicationEvent");
    }
}
