package com.example.homework.spingfx.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentJavaBean {
    private int id;
    private String name;

    public void add(){
       System.out.println("java装配bean");
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
