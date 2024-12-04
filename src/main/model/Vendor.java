package main.model;

public class Vendor implements Runnable {
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                TicketingSystem.ticketPool.addTickets(TicketingSystem.ticketReleaseRate);
                Thread.sleep(1000); // Simulate release rate
            }
        } catch (InterruptedException e) {
            System.out.println("main.model.Vendor interrupted.");
        }
    }
}
