package main.model;

public class Customer implements Runnable {
    @Override
    public void run() {
        try {
            while (TicketingSystem.isRunning) {
                TicketingSystem.ticketPool.removeTickets(TicketingSystem.customerRetrievalRate);
                Thread.sleep(1000); // Simulate ticket retrieval rate (1 second)
            }
        } catch (InterruptedException e) {
            System.out.println("Customer thread interrupted.");
        }
    }
}
