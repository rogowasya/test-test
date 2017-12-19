package com.haulmont.testtask;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestTaskApplication {

	public static void main(String[] args) {
		SpringApplication.run(new Class<?>[] {TestTaskApplication.class, JpaConfig.class}, args);
	}


}
