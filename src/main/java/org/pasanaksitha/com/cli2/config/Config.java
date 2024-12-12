package org.pasanaksitha.com.cli2.config;

import com.google.gson.Gson;
import org.springframework.stereotype.Component;

/**
 * Configuration class to store and manage simulation parameters.
 * This class is annotated as a Spring Component, allowing it to be injected into other beans.
 */
@Component
public class Config {

    /**
     * Total number of tickets available for the event.
     */
    private int totalEventTickets;

    /**
     * Maximum capacity of the ticket pool.
     */
    private int maxPoolCapacity;

    /**
     * Rate at which tickets are released into the pool.
     */
    private int ticketReleaseRate;

    /**
     * Rate at which customers retrieve tickets from the pool.
     */
    private int customerRetrievalRate;

    /**
     * Number of vendors in the simulation.
     */
    private int vendorCount;

    /**
     * Number of customers in the simulation.
     */
    private int customerCount;

    // Getters and Setters

    /**
     * @return Total number of tickets available for the event.
     */
    public int getTotalEventTickets() {
        return totalEventTickets;
    }

    /**
     * Sets the total number of tickets available for the event.
     * @param totalEventTickets Total tickets for the event.
     */
    public void setTotalEventTickets(int totalEventTickets) {
        this.totalEventTickets = totalEventTickets;
    }

    /**
     * @return Maximum capacity of the ticket pool.
     */
    public int getMaxPoolCapacity() {
        return maxPoolCapacity;
    }

    /**
     * Sets the maximum capacity of the ticket pool.
     * @param maxPoolCapacity Maximum capacity for the ticket pool.
     */
    public void setMaxPoolCapacity(int maxPoolCapacity) {
        this.maxPoolCapacity = maxPoolCapacity;
    }

    /**
     * @return Rate of release of tickets into the pool.
     */
    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    /**
     * Sets the rate at which tickets are released into the pool.
     * @param ticketReleaseRate Tickets release rate.
     */
    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    /**
     * @return Rate at which customers retrieve tickets.
     */
    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    /**
     * Sets the rate at which customers retrieve tickets from the pool.
     * @param customerRetrievalRate Customer retrieval rate.
     */
    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    /**
     * @return Number of vendors in the simulation.
     */
    public int getVendorCount() {
        return vendorCount;
    }

    /**
     * Sets the number of vendors in the simulation.
     * @param vendorCount Number of vendors.
     */
    public void setVendorCount(int vendorCount) {
        this.vendorCount = vendorCount;
    }

    /**
     * @return Number of customers in the simulation.
     */
    public int getCustomerCount() {
        return customerCount;
    }

    /**
     * Sets the number of customers in the simulation.
     * @param customerCount Number of customers.
     */
    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    /**
     * Updates the configuration with new values from the input configuration object.
     * @param inputConfig Configuration object with updated values.
     */
    public void updateConfig(Config inputConfig) {
        this.totalEventTickets = inputConfig.getTotalEventTickets();
        this.maxPoolCapacity = inputConfig.getMaxPoolCapacity();
        this.ticketReleaseRate = inputConfig.getTicketReleaseRate();
        this.customerRetrievalRate = inputConfig.getCustomerRetrievalRate();
        this.vendorCount = inputConfig.getVendorCount();
        this.customerCount = inputConfig.getCustomerCount();
    }

    /**
     * Resets all configuration values to zero.
     */
    public void reset() {
        this.totalEventTickets = 0;
        this.maxPoolCapacity = 0;
        this.ticketReleaseRate = 0;
        this.customerRetrievalRate = 0;
        this.vendorCount = 0;
        this.customerCount = 0;
    }

    /**
     * Converts the configuration object to a JSON string representation.
     * @return JSON string representation of the configuration.
     */
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}