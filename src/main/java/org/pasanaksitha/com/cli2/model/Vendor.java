package org.pasanaksitha.com.cli2.model;

import org.pasanaksitha.com.cli2.TicketingSystem;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

/**
 * The Vendor class represents a vendor responsible for adding tickets to the ticket pool.
 * Implements the Runnable interface to enable multithreading.
 */
public class Vendor implements Runnable {

    /**
     * The run method contains the logic for the vendor's operations.
     * It continuously adds tickets to the pool at a specified rate until the event ticket limit is reached or the simulation stops.
     */
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                boolean canContinue = TicketingSystem.ticketPool.addTickets(TicketingSystem.ticketReleaseRate);

                if (!canContinue) {
                    LoggerUtil.infoMessage("Vendor operations halted as all tickets are issued.");
                    break;
                }

                Thread.sleep(1000); // Simulate ticket addition delay
            }
        } catch (InterruptedException e) {
            LoggerUtil.error("Vendor thread interrupted unexpectedly.");
        }
    }
}