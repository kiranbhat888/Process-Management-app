package com.infrrd.processManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ProcessManagement {

	public static void main(String[] args) {
		SpringApplication.run(ProcessManagement.class, args);
	}

}
