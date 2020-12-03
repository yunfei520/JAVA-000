package com.example.homework07.dynamicdatasource;

import lombok.Getter;
import lombok.Setter;

public enum DataSourceEnum {
    // 主表
    MASTER("master"),
    // 从表1
    SLAVE1("slave1"),
    // 从表2
    SLAVE2("slave2");

    @Setter
    @Getter
    private String name;

    DataSourceEnum(String name) {
        this.name = name;
    }

}
