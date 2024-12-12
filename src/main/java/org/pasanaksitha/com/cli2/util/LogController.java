package org.pasanaksitha.com.cli2.util;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * The LogController class provides an API endpoint to fetch log entries from the log file.
 * This allows the frontend to dynamically display logs related to the ticketing system.
 */
@RestController
@RequestMapping("/api")
public class LogController {

    /**
     * API endpoint to fetch log entries from the ticketing system log file.
     *
     * @return A list of log entries as strings.
     * @throws RuntimeException If the log file cannot be read.
     */
    @GetMapping("/logs")
    public List<String> getLogs() {
        List<String> logs = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ticketingSystem.log"))) {
            String line;
            // Read the log file line by line and add each line to the logs list
            while ((line = reader.readLine()) != null) {
                logs.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to read logs: " + e.getMessage());
        }
        return logs;
    }
}