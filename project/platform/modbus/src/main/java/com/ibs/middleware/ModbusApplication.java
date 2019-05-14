package com.ibs.middleware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@EnableScheduling
public class ModbusApplication {

	public static void main(String[] args) {
		SpringApplication.run(ModbusApplication.class, args);
	}

}
