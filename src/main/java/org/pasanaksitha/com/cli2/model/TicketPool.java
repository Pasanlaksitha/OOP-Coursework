package org.pasanaksitha.com.cli2.model;

import org.pasanaksitha.com.cli2.util.LoggerUtil;

import java.util.Vector;

/**
 * The TicketPool class manages the ticket pool. It regulates the addition and removal of tickets while ensuring thread safety using synchronized methods.
 */
public class TicketPool {

    private final Vector<Integer> pool;
    private final int maxCapacity;
    private int ticketsSold;
    private final int eventTicketLimit;

    /**
     * Constructor to initialize the ticket pool with maximum capacity and event ticket limit.
     *
     * @param maxCapacity      The maximum number of tickets the pool can hold.
     * @param eventTicketLimit The total number of tickets available for the event.
     */
    public TicketPool(int maxCapacity, int eventTicketLimit) {
        this.pool = new Vector<>();
        this.maxCapacity = maxCapacity;
        this.ticketsSold = 0;
        this.eventTicketLimit = eventTicketLimit;
    }

    /**
     * Adds tickets to the pool. If the pool is full, it waits until space becomes available.
     *
     * @param count The number of tickets to add to the pool.
     * @return true if tickets were successfully added and the event limit is not reached, false otherwise.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public synchronized boolean addTickets(int count) throws InterruptedException {
        while (pool.size() + count > maxCapacity) {
            LoggerUtil.infoMessage("Ticket pool full. Vendor waiting...");
            wait();
        }

        if (ticketsSold >= eventTicketLimit) {
            LoggerUtil.infoMessage("Event ticket limit reached. No further tickets can be issued.");
            return false;
        }

        // Add tickets to the pool
        for (int i = 0; i < count; i++) {
            pool.add(1); // Each ticket is represented as an integer
            ticketsSold++;
            if (ticketsSold >= eventTicketLimit) break;
        }

        LoggerUtil.infoMessage("Added " + count + " tickets. Pool size: " + pool.size() + ", Total sold: " + ticketsSold);
        notifyAll(); // Notify waiting threads
        return ticketsSold < eventTicketLimit;
    }

    /**
     * Removes tickets from the pool. If insufficient tickets are available, it waits until tickets are added.
     *
     * @param count The number of tickets to remove from the pool.
     * @return true if tickets were successfully removed, false if all tickets are sold.
     * @throws InterruptedException If the thread is interrupted while waiting.
     */
    public synchronized boolean removeTickets(int count) throws InterruptedException {
        while (pool.size() < count) {
            if (ticketsSold >= eventTicketLimit && pool.isEmpty()) {
                LoggerUtil.infoMessage("All tickets sold. Stopping customer operations.");
                return false;
            }

            LoggerUtil.infoMessage("Not enough tickets in pool. Customer waiting...");
            wait();
        }

        // Remove tickets from the pool when bought
        for (int i = 0; i < count; i++) {
            pool.remove(pool.size() - 1);
        }

        LoggerUtil.infoMessage("Purchased " + count + " tickets. Remaining in pool: " + pool.size());
        notifyAll();
        return true;
    }

    /**
     * Retrieves the current number of tickets in the pool.
     *
     * @return The number of tickets currently in the pool.
     */
    public synchronized int getTickets() {
        return pool.size();
    }

    /**
     * Retrieves the total number of tickets sold.
     *
     * @return The total number of tickets sold.
     */
    public synchronized int getTotalTicketsSold() {
        return ticketsSold;
    }

    /**
     * Retrieves the maximum capacity of the ticket pool.
     *
     * @return The maximum number of tickets the pool can hold.
     */
    public int getMaxPoolCapacity() {
        return maxCapacity;
    }

    /**
     * Retrieves the total limit of tickets available for the event.
     *
     * @return The total number of tickets available for the event.
     */
    public int getEventTicketLimit() {
        return eventTicketLimit;
    }
}
