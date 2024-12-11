package org.pasanaksitha.com.cli2.util;

import org.pasanaksitha.com.cli2.config.Config;
import org.pasanaksitha.com.cli2.core.Customer;
import org.pasanaksitha.com.cli2.core.Vendor;
import org.pasanaksitha.com.cli2.core.TicketPool;
import org.pasanaksitha.com.cli2.TicketingSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ConfigController {

    @Autowired
    private Config config;

    private Thread[] vendorThreads;
    private Thread[] customerThreads;

    @PostMapping("/config")
    public ResponseEntity<String> configureSystem(@RequestBody Config inputConfig) {
        config.updateConfig(inputConfig);

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

        for (int i = 0; i < TicketingSystem.vendorCount; i++) {
            vendorThreads[i] = new Thread(new Vendor());
            vendorThreads[i].start();
        }

        for (int i = 0; i < TicketingSystem.customerCount; i++) {
            customerThreads[i] = new Thread(new Customer());
            customerThreads[i].start();
        }

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
        stopSimulation();

        TicketingSystem.ticketPool = null;
        TicketingSystem.ticketReleaseRate = 0;
        TicketingSystem.customerRetrievalRate = 0;
        TicketingSystem.vendorCount = 0;
        TicketingSystem.customerCount = 0;

        config.reset();

        return ResponseEntity.ok("Simulation reset successfully. Configure again using /config.");
    }

    @GetMapping("/logs")
    public ResponseEntity<List<String>> getLogs(@RequestParam(defaultValue = "50") int lines) {
        try {
            List<String> allLines = Files.readAllLines(Paths.get("ticketingSystem.log"));
            int start = Math.max(0, allLines.size() - lines);
            return ResponseEntity.ok(allLines.subList(start, allLines.size()));
        } catch (IOException e) {
            return ResponseEntity.status(500).body(List.of("Error reading logs: " + e.getMessage()));
        }
    }



}
