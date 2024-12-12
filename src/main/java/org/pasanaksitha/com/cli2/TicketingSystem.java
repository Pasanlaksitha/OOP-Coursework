package org.pasanaksitha.com.cli2;

import org.pasanaksitha.com.cli2.config.Config;
import org.pasanaksitha.com.cli2.core.Customer;
import org.pasanaksitha.com.cli2.core.TicketPool;
import org.pasanaksitha.com.cli2.core.Vendor;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

/**
 * The TicketingSystem class serves as the entry point for the CLI-based ticket simulation system.
 * It orchestrates the configuration of the system, initialization of threads, and the overall simulation lifecycle.
 * This program simulates vendors issuing tickets and customers retrieving tickets in a multi-threaded environment.
 */
public class TicketingSystem {
    // Global flag to indicate whether the simulation is running
    public static boolean isRunning = true;

    // Shared resources and configuration parameters for the simulation
    public static TicketPool ticketPool;
    public static int ticketReleaseRate;
    public static int customerRetrievalRate;
    public static int vendorCount;
    public static int customerCount;

    /**
     * The main method initializes the ticketing system, manages configuration, and starts the simulation.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Initialize the configuration object
        Config config = new Config();

        // Load existing configuration or prompt the user for new inputs
        if (!config.loadConfig()) {
            config.configureSystem(); // Configure the system with user inputs
            config.saveConfigPrompt(); // Prompt the user to save the configuration
        }

        // Initialize simulation parameters from the configuration
        ticketReleaseRate = config.getTicketReleaseRate();
        customerRetrievalRate = config.getCustomerRetrievalRate();
        vendorCount = config.getVendorCount();
        customerCount = config.getCustomerCount();
        ticketPool = new TicketPool(config.getMaxPoolCapacity(), config.getTotalEventTickets());

        // Create arrays to manage threads for vendors and customers
        Thread[] vendorThreads = new Thread[vendorCount];
        Thread[] customerThreads = new Thread[customerCount];

        // Start vendor threads to simulate ticket issuing
        for (int i = 0; i < vendorCount; i++) {
            vendorThreads[i] = new Thread(new Vendor());
            vendorThreads[i].start();
        }

        // Start customer threads to simulate ticket retrieval
        for (int i = 0; i < customerCount; i++) {
            customerThreads[i] = new Thread(new Customer());
            customerThreads[i].start();
        }

        // Wait for all vendor threads to complete their tasks
        for (Thread thread : vendorThreads) {
            try {
                thread.join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                System.out.println("Vendor thread interrupted while waiting.");
            }
        }

        // Wait for all customer threads to complete their tasks
        for (Thread thread : customerThreads) {
            try {
                thread.join(); // Wait for the thread to finish
            } catch (InterruptedException e) {
                LoggerUtil.infoMessage("Customer thread interrupted while waiting.");
            }
        }

        // Log a message indicating system shutdown and the total tickets sold
        LoggerUtil.infoMessage("System shutdown. Total tickets sold: " + ticketPool.getTotalTicketsSold());
    }
}