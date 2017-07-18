package com.beecho.springxoxo;

import com.beecho.springxoxo.annotation.AnnotationContext;
import com.beecho.springxoxo.model.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/7/17.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("user.xml");
//        User bean = (User) ac.getBean("userBean");

        User bean = (User) AnnotationContext.getBean("userBean");
        System.out.println(bean.getUserName() + " " + bean.getEmail());

        AnnotationContext.addStudent("第一个学生");
        AnnotationContext.addStudent("第二个学生");
        AnnotationContext.addStudent("第三个学生");
    }
}
