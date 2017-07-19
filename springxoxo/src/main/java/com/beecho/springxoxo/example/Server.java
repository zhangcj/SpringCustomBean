package com.beecho.springxoxo.example;

import com.beecho.springxoxo.model.Xxoo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * @author 春哥大魔王
 */

public class Server {
    public static void main(String[] args) throws IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("demo-provider.xml");
        context.start();
        System.out.println("服务启动中...按任意键退出");
        System.in.read();
    }
}
