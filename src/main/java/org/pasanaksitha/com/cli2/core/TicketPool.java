package org.pasanaksitha.com.cli2.core;

import org.pasanaksitha.com.cli2.util.LoggerUtil;

import java.util.Vector;

/**
 * The TicketPool class manages the storage, addition, and retrieval of tickets
 * in the ticketing system. It ensures thread-safe operations for a multi-threaded
 * environment and monitors ticket availability.
 */
public class TicketPool {
    private final Vector<Integer> pool; // tickets
    private final int maxCapacity;
    private int ticketsSold;
    private final int eventTicketLimit;

    /**
     * Constructs a TicketPool with a specified maximum capacity and event ticket limit.
     *
     * @param maxCapacity      the maximum number of tickets the pool can hold.
     * @param eventTicketLimit the total number of tickets available for the event.
     */
    public TicketPool(int maxCapacity, int eventTicketLimit) {
        this.pool = new Vector<>();
        this.maxCapacity = maxCapacity;
        this.ticketsSold = 0;
        this.eventTicketLimit = eventTicketLimit;
    }

    /**
     * Adds tickets to the pool in a thread-safe manner.
     *
     * @param count the number of tickets to add.
     * @return true if tickets were successfully added, false if the event ticket limit was reached.
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public synchronized boolean addTickets(int count) throws InterruptedException {
        // Wait if the pool is full
        while (pool.size() + count > maxCapacity) {
            LoggerUtil.infoMessage("Ticket pool full. Vendor waiting...");
            wait();
        }

        // Check if the event ticket limit has been reached
        if (ticketsSold >= eventTicketLimit) {
            LoggerUtil.infoMessage("Event ticket limit reached. No further tickets can be issued.");
            return false;
        }

        // Add tickets to the pool
        for (int i = 0; i < count; i++) {
            pool.add(1); // Represent each ticket as an integer
            ticketsSold++;
            if (ticketsSold >= eventTicketLimit) break;
        }

        LoggerUtil.infoMessage("Added " + count + " tickets. Pool size: " + pool.size() + ", Total sold: " + ticketsSold);
        notifyAll(); // Notify waiting threads
        return ticketsSold < eventTicketLimit;
    }

    /**
     * Removes tickets from the pool in a thread-safe manner.
     *
     * @param count the number of tickets to remove.
     * @return true if tickets were successfully removed, false if all tickets are sold.
     * @throws InterruptedException if the thread is interrupted while waiting.
     */
    public synchronized boolean removeTickets(int count) throws InterruptedException {
        // Wait if there are not enough tickets in the pool
        while (pool.size() < count) {
            if (ticketsSold >= eventTicketLimit && pool.isEmpty()) {
                LoggerUtil.infoMessage("All tickets sold. Stopping customer operations.");
                return false;
            }

            LoggerUtil.infoMessage("Not enough tickets in pool. Customer waiting...");
            wait();
        }

        // Remove tickets from the pool
        for (int i = 0; i < count; i++) {
            pool.remove(pool.size() - 1);
        }

        LoggerUtil.infoMessage("Purchased " + count + " tickets. Remaining in pool: " + pool.size());
        notifyAll(); // Notify waiting threads
        return true;
    }

    /**
     * Retrieves the current number of tickets in the pool.
     *
     * @return the number of tickets currently in the pool.
     */
    public synchronized int getTickets() {
        return pool.size();
    }

    /**
     * Retrieves the total number of tickets sold.
     *
     * @return the total tickets sold.
     */
    public synchronized int getTotalTicketsSold() {
        return ticketsSold;
    }

    /**
     * Retrieves the maximum pool capacity.
     *
     * @return the maximum number of tickets the pool can hold.
     */
    public int getMaxPoolCapacity() {
        return maxCapacity;
    }

    /**
     * Retrieves the event ticket limit.
     *
     * @return the total number of tickets available for the event.
     */
    public int getEventTicketLimit() {
        return eventTicketLimit;
    }
}
