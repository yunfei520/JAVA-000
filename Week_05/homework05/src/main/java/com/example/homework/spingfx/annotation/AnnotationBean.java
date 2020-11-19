package com.example.homework.spingfx.annotation;

import com.example.homework.spingfx.bean.StudentBean;
import com.example.homework.spingfx.config.StudentConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *自动装配Bean
 */
public class AnnotationBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StudentConfig.class);
        StudentBean student = applicationContext.getBean(StudentBean.class);
        System.out.println("student: "+student.toString());
        student.information();
    }
}

