package com.example.supplychain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class DigitalSupplyChainApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalSupplyChainApplication.class, args);
	}

}
