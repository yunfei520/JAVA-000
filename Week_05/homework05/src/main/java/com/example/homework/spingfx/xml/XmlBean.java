package com.example.homework.spingfx.xml;

import com.example.homework.spingfx.bean.Student;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * XML装配
 */
public class XmlBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext appletContext = new ClassPathXmlApplicationContext("applicationContext.xml");
        //加载刷新上下文
        appletContext.refresh();

        Student bean = appletContext.getBean(Student.class);
        System.out.println("获取bean信息"+bean.toString());
        //关闭
        appletContext.close();

    }
}
