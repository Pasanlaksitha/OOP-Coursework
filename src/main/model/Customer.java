package main.model;

public class Customer implements Runnable {
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                TicketingSystem.ticketPool.removeTickets(TicketingSystem.customerRetrievalRate);
                Thread.sleep(1000); // Simulate retrieval rate
            }
        } catch (InterruptedException e) {
            System.out.println("main.model.Customer interrupted.");
        }
    }
}
