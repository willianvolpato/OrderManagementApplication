package com.example.ordermanagement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OrderManagementApplication {

	private static final Logger logger = LogManager.getLogger(OrderManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OrderManagementApplication.class, args);
		logger.info("Order Management Application started successfully.");
	}

}
