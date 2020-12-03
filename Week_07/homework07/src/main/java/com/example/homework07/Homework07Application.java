package com.example.homework07;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Homework07Application {

	public static void main(String[] args) {
		SpringApplication.run(Homework07Application.class, args);
	}

}
