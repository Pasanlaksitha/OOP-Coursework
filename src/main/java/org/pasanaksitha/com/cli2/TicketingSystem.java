package org.pasanaksitha.com.cli2;

import org.pasanaksitha.com.cli2.core.TicketPool;
import org.pasanaksitha.com.cli2.util.LoggerUtil;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketingSystem implements CommandLineRunner {

	public static boolean isRunning = true;
	public static TicketPool ticketPool;
	public static int ticketReleaseRate;
	public static int customerRetrievalRate;
	public static int vendorCount;
	public static int customerCount;

	public static void main(String[] args) {
		SpringApplication.run(TicketingSystem.class, args);
	}

	@Override
	public void run(String... args) {
		LoggerUtil.infoMessage("Spring Boot Application Started. Use the API endpoint '/api/config' to configure the system.");
	}
}
