package com.example.homework.spingfx.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Data
@Component()
public class StudentBean {

        @Value("202011")
        private Long id;

        @Value("张三")
        private String name;

        public void information() {
            System.out.println("学生信息—:"+id +" "+name);
        }
}
