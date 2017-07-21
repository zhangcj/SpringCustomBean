package com.beecho.springxoxo.beecho.common;

import org.springframework.context.ApplicationEvent;

/**
 * @author 春哥大魔王
 */

public class XxooEvent extends ApplicationEvent {
    private String message;
    public XxooEvent(Object source) {
        super(source);
        this.message = source.toString();
    }

    public void info(){
        System.out.println("info");
    }
}
