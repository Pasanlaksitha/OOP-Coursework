package org.pasanaksitha.com.cli2.model;

public class Ticket {
    private final int ticketId;
    private boolean isSold;

    public Ticket(int ticketId) {
        this.ticketId = ticketId;
        this.isSold = false;
    }

    public int getTicketId() {
        return ticketId;
    }

    public boolean isSold() {
        return isSold;
    }

    public void setSold(boolean sold) {
        isSold = sold;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", isSold=" + isSold +
                '}';
    }
}