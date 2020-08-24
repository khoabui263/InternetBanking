package com.khoa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.khoa")
public class KhoaBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(KhoaBankingApplication.class, args);
	}

}
