package org.pasanaksitha.com.cli2.core;

import org.pasanaksitha.com.cli2.TicketingSystem;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

public class Vendor implements Runnable {
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
