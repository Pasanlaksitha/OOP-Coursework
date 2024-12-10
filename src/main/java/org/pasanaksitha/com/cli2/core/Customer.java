package org.pasanaksitha.com.cli2.core;

import org.pasanaksitha.com.cli2.TicketingSystem;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

public class Customer implements Runnable {
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                boolean successfullyRetrieved = TicketingSystem.ticketPool.removeTickets(TicketingSystem.customerRetrievalRate);
                if (!successfullyRetrieved) {
                    LoggerUtil.infoMessage("Customer operations stopped as all tickets are sold.");
                    break;
                }
                Thread.sleep(1000); // Simulate customer retrieval delay
            }
        } catch (InterruptedException e) {
            LoggerUtil.error("Customer thread interrupted unexpectedly.");
        }
    }
}
