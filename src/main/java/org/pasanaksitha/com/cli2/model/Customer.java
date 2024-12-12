package org.pasanaksitha.com.cli2.model;

import org.pasanaksitha.com.cli2.TicketingSystem;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

/**
 * Represents a customer in the ticketing simulation.
 * Customers attempt to retrieve tickets from the ticket pool while the system is running.
 * Implements the Runnable interface to allow multithreading.
 */
public class Customer implements Runnable {

    /**
     * The run method contains the logic for customer operations.
     * Customers repeatedly attempt to retrieve tickets until either the ticket pool is empty or the simulation is stopped.
     */
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                boolean successfullyRetrieved = TicketingSystem.ticketPool.removeTickets(TicketingSystem.customerRetrievalRate);

                // If retrieval fails, all tickets are sold, and the customer stops
                if (!successfullyRetrieved) {
                    LoggerUtil.infoMessage("Customer operations stopped as all tickets are sold.");
                    break;
                }

                Thread.sleep(1000); // 1000ms = 1 second
            }
        } catch (InterruptedException e) {
            LoggerUtil.error("Customer thread interrupted unexpectedly.");
        }
    }
}