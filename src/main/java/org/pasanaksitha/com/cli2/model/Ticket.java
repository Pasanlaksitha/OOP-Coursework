package org.pasanaksitha.com.cli2.model;

/**
 * Represents a single ticket in the ticketing system.
 * Each ticket has a unique ID and a sold status.
 */
public class Ticket {

    private final int ticketId;

    private boolean isSold;

    /**
     * Constructs a Ticket object with a given ID.
     * Initially, the ticket is unsold.
     *
     * @param ticketId The unique identifier for the ticket.
     */
    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.isSold = false;
    }

    /**
     * Retrieves the unique ID of the ticket.
     *
     * @return The ticket ID.
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Checks if the ticket is sold.
     *
     * @return true if the ticket is sold, false otherwise.
     */
    public boolean isSold() {
        return isSold;
    }

    /**
     * Sets the sold status of the ticket.
     *
     * @param sold The new sold status (true if sold, false otherwise).
     */
    public void setSold(boolean sold) {
        isSold = sold;
    }

    /**
     * Provides a string representation of the ticket, including its ID and sold status.
     *
     * @return A string representing the ticket object.
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", isSold=" + isSold +
                '}';
    }
}