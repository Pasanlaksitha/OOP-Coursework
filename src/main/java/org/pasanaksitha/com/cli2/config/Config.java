package org.pasanaksitha.com.cli2.config;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

@Component
public class Config {
    private int totalEventTickets;
    private int maxPoolCapacity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int vendorCount;
    private int customerCount;

    // Getters and Setters

    public int getTotalEventTickets() {
        return totalEventTickets;
    }

    public void setTotalEventTickets(int totalEventTickets) {
        this.totalEventTickets = totalEventTickets;
    }

    public int getMaxPoolCapacity() {
        return maxPoolCapacity;
    }

    public void setMaxPoolCapacity(int maxPoolCapacity) {
        this.maxPoolCapacity = maxPoolCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    // Method to update configuration via API
    public void updateConfig(Config inputConfig) {
        this.totalEventTickets = inputConfig.getTotalEventTickets();
        this.maxPoolCapacity = inputConfig.getMaxPoolCapacity();
        this.ticketReleaseRate = inputConfig.getTicketReleaseRate();
        this.customerRetrievalRate = inputConfig.getCustomerRetrievalRate();
        this.vendorCount = inputConfig.getVendorCount();
        this.customerCount = inputConfig.getCustomerCount();
    }

    public void reset() {
        this.totalEventTickets = 0;
        this.maxPoolCapacity = 0;
        this.ticketReleaseRate = 0;
        this.customerRetrievalRate = 0;
        this.vendorCount = 0;
        this.customerCount = 0;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}