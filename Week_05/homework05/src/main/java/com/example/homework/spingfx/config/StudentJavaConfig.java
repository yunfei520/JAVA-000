package com.example.homework.spingfx.config;


import com.example.homework.spingfx.bean.StudentJavaBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentJavaConfig {
    @Bean
    public StudentJavaBean studentJavaBean() {
        return new StudentJavaBean(202011, "张三1");
    }
}
