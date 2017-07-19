package com.beecho.springxoxo;

import com.beecho.springxoxo.model.Application;
import com.beecho.springxoxo.model.Xxoo;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/7/17.
 */
public class App {
    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("demo-provider.xml");
//        Xxoo bean = (Xxoo) ac.getBean("userBean");
        Xxoo bean = ac.getBean(Xxoo.class);

//        User bean = (User) AnnotationContext.getBean("userBean");
        System.out.println(bean.getUserName() + " " + bean.getEmail());

//        Application application = (Application) ac.getBean("applicationBean");
//        System.out.println(application.getName() + " " + application.getOwner() + " " + application.getOrganization());

//        AnnotationContext.addStudent("第一个学生");
//        AnnotationContext.addStudent("第二个学生");
//        AnnotationContext.addStudent("第三个学生");
        System.out.println("server finish!");
    }
}
