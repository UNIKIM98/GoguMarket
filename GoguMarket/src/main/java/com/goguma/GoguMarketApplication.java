package com.goguma;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan(basePackages = "com.goguma.**.mapper")
public class GoguMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(GoguMarketApplication.class, args);
	}

}
