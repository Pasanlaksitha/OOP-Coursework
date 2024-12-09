package org.pasanaksitha.com.cli2.core;

import org.pasanaksitha.com.cli2.TicketingSystem;

public class Vendor implements Runnable {
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                boolean canContinue = TicketingSystem.ticketPool.addTickets(TicketingSystem.ticketReleaseRate);
                if (!canContinue) {
                    System.out.println("Vendor operations halted as all tickets are issued.");
                    break;
                }
                Thread.sleep(1000); // Simulate ticket addition delay
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor thread interrupted unexpectedly.");
        }
    }
}
