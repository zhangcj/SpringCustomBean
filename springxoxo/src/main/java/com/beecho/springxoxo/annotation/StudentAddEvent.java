package com.beecho.springxoxo.annotation;

import org.springframework.context.ApplicationEvent;

/**
 * @author 春哥大魔王
 */

public class StudentAddEvent extends ApplicationEvent {

    private static final long serialVersionUID = 5779535599987629361L;

    private String studentName;

    public StudentAddEvent(Object source, String studentName) {
        super(source);
        this.studentName = studentName;
    }

    public String getStudentName() {
        return studentName;
    }
}
