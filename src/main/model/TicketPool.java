package main.model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.locks.ReentrantLock;

public class TicketPool {
    private int tickets;
    private final int maxCapacity;
    private final ReentrantLock lock = new ReentrantLock(true); // Fair lock

    public TicketPool(int maxCapacity) {
        this.tickets = 0;
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTickets(int count) throws InterruptedException {
        while (tickets + count > maxCapacity) {
            System.out.println("Ticket pool full. Vendor waiting...");
            logToFile("Vendor waiting: Pool full.");
            wait(); // Wait until space becomes available
        }
        tickets += count;
        System.out.println("Added " + count + " tickets. Total tickets: " + tickets);
        logToFile("Added " + count + " tickets. Total: " + tickets);
        notifyAll(); // Notify waiting customers
    }

    public synchronized void removeTickets(int count) throws InterruptedException {
        while (tickets < count) {
            System.out.println("Not enough tickets. Customer waiting...");
            logToFile("Customer waiting: Not enough tickets.");
            wait(); // Wait until tickets are added
        }
        tickets -= count;
        System.out.println("Removed " + count + " tickets. Remaining: " + tickets);
        logToFile("Removed " + count + " tickets. Remaining: " + tickets);
        notifyAll(); // Notify waiting vendors
    }

    private void logToFile(String message) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ticketing_log.txt", true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Failed to log message: " + e.getMessage());
        }
    }

}
