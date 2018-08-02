package com.fromqtoj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.fromqtoj.mapper")
@SpringBootApplication
//@ServletComponentScan
public class SpringBootLogbackDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootLogbackDemoApplication.class, args);
	}
}
