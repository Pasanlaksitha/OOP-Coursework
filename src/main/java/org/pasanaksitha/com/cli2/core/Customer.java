package org.pasanaksitha.com.cli2.core;

import org.pasanaksitha.com.cli2.TicketingSystem;

/**
 * The Customer class simulates customer behavior in the ticketing system.
 * Customers attempt to retrieve tickets from the shared ticket pool
 * while the simulation is running.
 */
public class Customer implements Runnable {

    /**
     * Executes the customer operations in a separate thread.
     * Continuously attempts to retrieve tickets from the ticket pool
     * until the system stops or tickets are sold out.
     */
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                // Attempt to remove tickets from the pool
                boolean successfullyRetrieved = TicketingSystem.ticketPool.removeTickets(TicketingSystem.customerRetrievalRate);

                // Stop operations if all tickets are sold
                if (!successfullyRetrieved) {
                    System.out.println("Customer operations stopped as all tickets are sold.");
                    break;
                }

                // Simulate delay for customer retrieval
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // Handle unexpected thread interruptions
            System.out.println("Customer thread interrupted unexpectedly.");
        }
    }
}
