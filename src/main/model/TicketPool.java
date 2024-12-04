package main.model;

public class TicketPool {
    private int tickets;

    public TicketPool(int initialTickets) {
        this.tickets = initialTickets;
    }

    public synchronized void addTickets(int count) {
        tickets += count;
        System.out.println("Added " + count + " tickets. Total: " + tickets);
    }

    public synchronized void removeTickets(int count) {
        if (tickets >= count) {
            tickets -= count;
            System.out.println("Removed " + count + " tickets. Remaining: " + tickets);
        } else {
            System.out.println("Not enough tickets to remove. Current: " + tickets);
        }
    }

    public synchronized int getTickets() {
        return tickets;
    }
}
