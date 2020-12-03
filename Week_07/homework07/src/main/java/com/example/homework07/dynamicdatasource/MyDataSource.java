package com.example.homework07.dynamicdatasource;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface  MyDataSource {
    //默认主表
    DataSourceEnum value() default DataSourceEnum.MASTER;
}
