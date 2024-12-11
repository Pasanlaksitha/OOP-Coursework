package org.pasanaksitha.com.cli2.util;

import org.pasanaksitha.com.cli2.config.Config;
import org.pasanaksitha.com.cli2.core.Customer;
import org.pasanaksitha.com.cli2.core.Vendor;
import org.pasanaksitha.com.cli2.core.TicketPool;
import org.pasanaksitha.com.cli2.TicketingSystem;
import org.pasanaksitha.com.cli2.util.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api")
public class ConfigController {

    @Autowired
    private Config config;

    // Class-level thread arrays for vendors and customers
    private Thread[] vendorThreads;
    private Thread[] customerThreads;

    @PostMapping("/config")
    public ResponseEntity<String> configureSystem(@RequestBody Config inputConfig) {
        // Update configuration with inputs
        config.updateConfig(inputConfig);

        // Initialize ticket pool with new config
        TicketingSystem.ticketPool = new TicketPool(
                config.getMaxPoolCapacity(),
                config.getTotalEventTickets()
        );
        TicketingSystem.ticketReleaseRate = config.getTicketReleaseRate();
        TicketingSystem.customerRetrievalRate = config.getCustomerRetrievalRate();
        TicketingSystem.vendorCount = config.getVendorCount();
        TicketingSystem.customerCount = config.getCustomerCount();

        return ResponseEntity.ok("Configuration updated successfully.");
    }

    @PostMapping("/start-simulation")
    public ResponseEntity<String> startSimulation() {
        if (TicketingSystem.ticketPool == null) {
            return ResponseEntity.badRequest().body("Simulation cannot start. Configuration is missing.");
        }

        TicketingSystem.isRunning = true;

        vendorThreads = new Thread[TicketingSystem.vendorCount];
        customerThreads = new Thread[TicketingSystem.customerCount];

        // Start vendor threads
        for (int i = 0; i < TicketingSystem.vendorCount; i++) {
            vendorThreads[i] = new Thread(new Vendor());
            vendorThreads[i].start();
        }

        // Start customer threads
        for (int i = 0; i < TicketingSystem.customerCount; i++) {
            customerThreads[i] = new Thread(new Customer());
            customerThreads[i].start();
        }

        // Run a monitoring thread to wait for all threads to complete
        new Thread(() -> {
            try {
                for (Thread thread : vendorThreads) {
                    if (thread != null) thread.join();
                }
                for (Thread thread : customerThreads) {
                    if (thread != null) thread.join();
                }
                TicketingSystem.isRunning = false;
                LoggerUtil.infoMessage("Simulation completed successfully. Total tickets sold: "
                        + TicketingSystem.ticketPool.getTotalTicketsSold());
            } catch (InterruptedException e) {
                LoggerUtil.error("Simulation monitoring thread interrupted.");
            }
        }).start();

        return ResponseEntity.ok("Simulation started successfully.");
    }

    @PostMapping("/stop")
    public ResponseEntity<String> stopSimulation() {
        if (!TicketingSystem.isRunning) {
            return ResponseEntity.badRequest().body("Simulation is not currently running.");
        }

        TicketingSystem.isRunning = false;

        // Interrupt all threads
        if (vendorThreads != null) {
            for (Thread thread : vendorThreads) {
                if (thread != null) thread.interrupt();
            }
        }

        if (customerThreads != null) {
            for (Thread thread : customerThreads) {
                if (thread != null) thread.interrupt();
            }
        }

        return ResponseEntity.ok("Simulation stopped successfully.");
    }

    @PostMapping("/reset")
    public ResponseEntity<String> resetSimulation() {
        // Stop the simulation if running
        stopSimulation();

        // Reset the configuration and ticket pool
        TicketingSystem.ticketPool = null;
        TicketingSystem.ticketReleaseRate = 0;
        TicketingSystem.customerRetrievalRate = 0;
        TicketingSystem.vendorCount = 0;
        TicketingSystem.customerCount = 0;

        config.reset(); // Reset the Config object (add a reset method in Config class)

        return ResponseEntity.ok("Simulation reset successfully. configure again using /config.");
    }

    @RestController
    public class LogController {
        @GetMapping("/logs")
        public List<String> getLogs() {
            // Return a list of logs
            List<String> logs = new ArrayList<>();
            logs.add("[INFO] Example log 1.");
            logs.add("[INFO] Example log 2.");
            return logs;
        }
    }



}
