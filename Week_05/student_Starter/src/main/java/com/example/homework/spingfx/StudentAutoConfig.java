package com.example.homework.spingfx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@AutoConfigureBefore(Student.class)
@ConditionalOnProperty(name = "student.enable", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(Student.class)
public class StudentAutoConfig {
    @Autowired
    private Student student;

    @Bean
    @ConditionalOnMissingBean
    public Klass getKlass(){
        Klass klass = new Klass();
        List<Student> sutdents = new ArrayList<>();
        sutdents.add(student);
        klass.setStudents(sutdents);
        return klass;
    }
}
