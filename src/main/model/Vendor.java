package main.model;

public class Vendor implements Runnable {
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                TicketingSystem.ticketPool.addTickets(TicketingSystem.ticketReleaseRate);
                Thread.sleep(1000); // Simulate ticket release rate (1 second)
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor thread interrupted.");
        }
    }
}
