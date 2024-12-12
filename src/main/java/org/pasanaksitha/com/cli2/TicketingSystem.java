package org.pasanaksitha.com.cli2;

import org.pasanaksitha.com.cli2.model.TicketPool;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Entry point for the TicketingSystem application.
 * This Spring Boot application initializes the simulation system and provides configuration through the API endpoint.
 */
@SpringBootApplication
public class TicketingSystem implements CommandLineRunner {

	/**
	 * Flag to indicate whether the simulation is currently running.
	 */
	public static boolean isRunning = true;

	/**
	 * Shared ticket pool object for managing ticket allocation during the simulation.
	 */
	public static TicketPool ticketPool;

	/**
	 * Rate at which tickets are released into the pool.
	 */
	public static int ticketReleaseRate;

	/**
	 * Rate at which customers retrieve tickets from the pool.
	 */
	public static int customerRetrievalRate;

	/**
	 * Number of vendor threads running in the simulation.
	 */
	public static int vendorCount;

	/**
	 * Number of customer threads running in the simulation.
	 */
	public static int customerCount;

	/**
	 * Main method that launches the Spring Boot application.
	 *
	 * @param args Command-line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(TicketingSystem.class, args);
	}

	/**
	 * Method executed after the application starts.
	 * Logs a message indicating that the application is ready for configuration.
	 *
	 * @param args Command-line arguments passed during application start.
	 */
	@Override
	public void run(String... args) {
		LoggerUtil.infoMessage("Spring Boot Application Started. Use the API endpoint '/api/config' to configure the system.");
	}
}