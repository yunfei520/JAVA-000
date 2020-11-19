package com.example.homework.spingfx.java;

import com.example.homework.spingfx.bean.StudentJavaBean;
import com.example.homework.spingfx.config.StudentJavaConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class JavaCodeBean {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(StudentJavaConfig.class);
        StudentJavaBean studentJavaBean = applicationContext.getBean(StudentJavaBean.class);
        System.out.println("studentJavaBean: "+studentJavaBean.toString());
        studentJavaBean.add();

    }
}
