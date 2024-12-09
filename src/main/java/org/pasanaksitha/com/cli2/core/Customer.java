package org.pasanaksitha.com.cli2.core;

import org.pasanaksitha.com.cli2.TicketingSystem;

public class Customer implements Runnable {
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                boolean successfullyRetrieved = TicketingSystem.ticketPool.removeTickets(TicketingSystem.customerRetrievalRate);
                if (!successfullyRetrieved) {
                    System.out.println("Customer operations stopped as all tickets are sold.");
                    break;
                }
                Thread.sleep(1000); // Simulate customer retrieval delay
            }
        } catch (InterruptedException e) {
            System.out.println("Customer thread interrupted unexpectedly.");
        }
    }
}
