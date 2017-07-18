package com.beecho.springxoxo.annotation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;

/**
 * @author 春哥大魔王
 *
 * 加载Spring配置文件时，如果Spring配置文件中所定义的Bean类实现了ApplicationContextAware，
 * 就可以获取ApplicationContext上下文，同时方便我们获取ApplicationContext中的所有bean。
 *
 * ApplicationListener 可以帮助我们做一些初始化的工作，比如数据库初始化，配置文件初始化等
 */

@Component
public class AnnotationContext implements ApplicationContextAware,ApplicationListener<ApplicationEvent> {

    private static ApplicationContext context;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
        System.out.println("ApplicationContext registered");
    }

    public static Object getBean(String beanName) {
        return context.getBean(beanName);
    }

    public static void addStudent(String studentName) {
        // 构造一个学生事件
        StudentAddEvent studentAddEvent = new StudentAddEvent(
                context,
                studentName
        );
        context.publishEvent(studentAddEvent); // 触发增加学生事件
    }

    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (!(applicationEvent instanceof StudentAddEvent))
            return;

        StudentAddEvent studentAddEvent = (StudentAddEvent) applicationEvent;
        System.out.println("增加了学生:::" + studentAddEvent.getStudentName());

        Class<CacheResult> annotationType = CacheResult.class;
        // 从容器中获取指定注解的bean对象
        Map<String,Object> beensWithAnnotation = context.getBeansWithAnnotation(annotationType);
        Set<Map.Entry<String,Object>> entrySet = beensWithAnnotation.entrySet();
        for (Map.Entry<String,Object> entry:entrySet){
            System.out.println(entry.getKey());
            CacheResult cr = AnnotationUtils.findAnnotation(entry.getValue().getClass(),annotationType);
            System.out.println(cr.key());
            System.out.println(cr.cacheName());

            Method[] methods = entry.getKey().getClass().getMethods();
            for (Method method : methods){
                if(method.isAnnotationPresent(annotationType)){
                    CacheResult _cr = method.getAnnotation(annotationType);
                    System.out.println(_cr.key());
                    System.out.println(_cr.cacheName());
                }
            }
        }
    }
}
