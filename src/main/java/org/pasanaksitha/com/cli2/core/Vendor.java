package org.pasanaksitha.com.cli2.core;

import org.pasanaksitha.com.cli2.TicketingSystem;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

/**
 * The Vendor class represents a ticket vendor in the ticketing system.
 * It implements the Runnable interface to allow multi-threaded ticket issuing operations.
 */
public class Vendor implements Runnable {

    /**
     * Executes the vendor's ticket-issuing operations in a separate thread.
     * Continuously adds tickets to the ticket pool at the configured rate until:
     * - The ticket pool reaches its maximum capacity, or
     * - The total event ticket limit is reached.
     */
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                // Attempt to add tickets to the pool
                boolean canContinue = TicketingSystem.ticketPool.addTickets(TicketingSystem.ticketReleaseRate);

                // Stop operations if the ticket limit is reached
                if (!canContinue) {
                    LoggerUtil.infoMessage("Vendor operations halted as all tickets are issued.");
                    break;
                }

                // Simulate a delay for ticket issuance
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            // Log an error if the vendor thread is interrupted
            LoggerUtil.error("Vendor thread interrupted unexpectedly.");
        }
    }
}
