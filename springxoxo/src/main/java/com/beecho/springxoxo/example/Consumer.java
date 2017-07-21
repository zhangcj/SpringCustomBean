package com.beecho.springxoxo.example;

import com.beecho.springxoxo.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author 春哥大魔王
 */

public class Consumer {

    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"demo-consumer.xml"});
        context.start();

        UserService userService = (UserService) context.getBean("userService"); // 获取远程服务代理
        User user = new User(1L, "xxoo");
        Long id = userService.registerUser(user);// 执行远程方法

        System.out.println(id); // 显示调用结果
    }
}
