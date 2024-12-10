package org.pasanaksitha.com.cli2.core;

import org.pasanaksitha.com.cli2.util.LoggerUtil;

import java.util.Vector;

public class TicketPool {
    private final Vector<Integer> pool; // Storage for tickets
    private final int maxCapacity;
    private int ticketsSold;
    private final int eventTicketLimit;

    public TicketPool(int maxCapacity, int eventTicketLimit) {
        this.pool = new Vector<>();
        this.maxCapacity = maxCapacity;
        this.ticketsSold = 0;
        this.eventTicketLimit = eventTicketLimit;
    }

    public synchronized boolean addTickets(int count) throws InterruptedException {
        while (pool.size() + count > maxCapacity) {
            LoggerUtil.infoMessage("Ticket pool full. Vendor waiting...");
            wait();
        }

        if (ticketsSold >= eventTicketLimit) {
            LoggerUtil.infoMessage("Event ticket limit reached. No further tickets can be issued.");
            return false;
        }

        for (int i = 0; i < count; i++) {
            pool.add(1); // Each ticket is represented by an integer
            ticketsSold++;
            if (ticketsSold >= eventTicketLimit) break;
        }

        LoggerUtil.infoMessage("Added " + count + " tickets. Pool size: " + pool.size() + ", Total sold: " + ticketsSold);
        notifyAll();
        return ticketsSold < eventTicketLimit;
    }

    public synchronized boolean removeTickets(int count) throws InterruptedException {
        while (pool.size() < count) {
            if (ticketsSold >= eventTicketLimit && pool.isEmpty()) {
                LoggerUtil.infoMessage("All tickets sold. Stopping customer operations.");
                return false;
            }

            LoggerUtil.infoMessage("Not enough tickets in pool. Customer waiting...");
            wait();
        }

        for (int i = 0; i < count; i++) {
            pool.remove(pool.size() - 1);
        }

        LoggerUtil.infoMessage("Purchased " + count + " tickets. Remaining in pool: " + pool.size());
        notifyAll();
        return true;
    }

    public synchronized int getTickets() {
        return pool.size();
    }

    public synchronized int getTotalTicketsSold() {
        return ticketsSold;
    }

    public int getMaxPoolCapacity() {
        return maxCapacity;
    }

    public int getEventTicketLimit() {
        return eventTicketLimit;
    }
}
