package main.model;

public class TicketPool {
    private int tickets;
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.tickets = 0; // Start with 0 tickets
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int count) throws InterruptedException {
        while (tickets + count > maxCapacity) {
            System.out.println("Ticket pool full. Vendor waiting...");
            wait(); // Wait until space becomes available
        }
        tickets += count;
        System.out.println("Added " + count + " tickets. Total tickets: " + tickets);
        notifyAll(); // Notify waiting customers
    }

    public synchronized void removeTickets(int count) throws InterruptedException {
        while (tickets < count) {
            System.out.println("Not enough tickets. Customer waiting...");
            wait(); // Wait until tickets are added
        }
        tickets -= count;
        System.out.println("Removed " + count + " tickets. Remaining tickets: " + tickets);
        notifyAll(); // Notify waiting vendors
    }

    public synchronized int getTickets() {
        return tickets;
    }
}
