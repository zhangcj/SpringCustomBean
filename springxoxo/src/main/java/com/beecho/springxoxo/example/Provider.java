package com.beecho.springxoxo.example;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author 春哥大魔王
 */

public class Provider {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"demo-provider.xml"});
        context.start();
        System.out.println("服务提供者启动中...按任意键退出");
        System.in.read();
    }
}
