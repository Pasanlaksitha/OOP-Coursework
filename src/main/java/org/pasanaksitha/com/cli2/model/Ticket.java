package org.pasanaksitha.com.cli2.model;

/**
 * The Ticket class represents an individual ticket in the ticketing system.
 * It holds information about the ticket's ID and its sold status.
 */
public class Ticket {

    // Unique identifier for the ticket
    private final int ticketId;

    // Indicates whether the ticket has been sold
    private boolean isSold;

    /**
     * Constructor to initialize a Ticket object with a specific ticket ID.
     * By default, the ticket is not sold.
     *
     * @param ticketId The unique identifier for the ticket.
     */
    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.isSold = false;
    }

    /**
     * Gets the unique identifier of the ticket.
     *
     * @return The ticket ID.
     */
    public int getTicketId() {
        return ticketId;
    }

    /**
     * Checks if the ticket has been sold.
     *
     * @return True if the ticket is sold, otherwise false.
     */
    public boolean isSold() {
        return isSold;
    }

    /**
     * Updates the sold status of the ticket.
     *
     * @param sold True to mark the ticket as sold, otherwise false.
     */
    public void setSold(boolean sold) {
        isSold = sold;
    }

    /**
     * Returns a string representation of the ticket, including its ID and sold status.
     *
     * @return A string describing the ticket.
     */
    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", isSold=" + isSold +
                '}';
    }
}