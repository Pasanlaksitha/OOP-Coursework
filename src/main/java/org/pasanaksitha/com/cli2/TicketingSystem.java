package org.pasanaksitha.com.cli2;

import org.pasanaksitha.com.cli2.config.Config;
import org.pasanaksitha.com.cli2.core.Customer;
import org.pasanaksitha.com.cli2.core.TicketPool;
import org.pasanaksitha.com.cli2.core.Vendor;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

public class TicketingSystem {
	public static boolean isRunning = true;
	public static TicketPool ticketPool;
	public static int ticketReleaseRate;
	public static int customerRetrievalRate;
	public static int vendorCount;
	public static int customerCount;

	public static void main(String[] args) {
		Config config = new Config();

		// Load configuration or get user inputs
		if (!config.loadConfig()) {
			config.configureSystem();
			config.saveConfigPrompt();
		}

		// Initialize variables with the configuration
		ticketReleaseRate = config.getTicketReleaseRate();
		customerRetrievalRate = config.getCustomerRetrievalRate();
		vendorCount = config.getVendorCount();
		customerCount = config.getCustomerCount();
		ticketPool = new TicketPool(config.getMaxPoolCapacity(), config.getTotalEventTickets());

		Thread[] vendorThreads = new Thread[vendorCount];
		Thread[] customerThreads = new Thread[customerCount];

		// Start vendor threads
		for (int i = 0; i < vendorCount; i++) {
			vendorThreads[i] = new Thread(new Vendor());
			vendorThreads[i].start();
		}

		// Start customer threads
		for (int i = 0; i < customerCount; i++) {
			customerThreads[i] = new Thread(new Customer());
			customerThreads[i].start();
		}

		// Wait for threads to finish
		for (Thread thread : vendorThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				System.out.println("Vendor thread interrupted while waiting.");
			}
		}

		for (Thread thread : customerThreads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				LoggerUtil.infoMessage("Customer thread interrupted while waiting.");
			}
		}

		LoggerUtil.infoMessage("System shutdown. Total tickets sold: " + ticketPool.getTotalTicketsSold());
	}
}