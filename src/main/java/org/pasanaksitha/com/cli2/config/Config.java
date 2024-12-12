package org.pasanaksitha.com.cli2.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.pasanaksitha.com.cli2.core.TicketPool;
import org.pasanaksitha.com.cli2.util.LoggerUtil;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Config {
    private static final String CONFIG_FILE = "config.json";
    private int totalEventTickets;
    private int maxPoolCapacity;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int vendorCount;
    private int customerCount;

//    private static final String DEFAULT_CONFIG_FILE = "config/system_config.json";
//    private static final String CONFIG_FILE = System.getProperty("com.pasanlaksitha.com.config.file.path", DEFAULT_CONFIG_FILE);

    public boolean loadConfig() {
    Scanner scanner = new Scanner(System.in);
    while (true) {
        System.out.print("Do you want to load the saved configuration? (yes/no): ");
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("yes")) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                Gson gson = new Gson();
                Config loadedConfig = gson.fromJson(reader, Config.class);

                // Load configuration into this instance
                this.totalEventTickets = loadedConfig.totalEventTickets;
                this.maxPoolCapacity = loadedConfig.maxPoolCapacity;
                this.ticketReleaseRate = loadedConfig.ticketReleaseRate;
                this.customerRetrievalRate = loadedConfig.customerRetrievalRate;
                this.vendorCount = loadedConfig.vendorCount;
                this.customerCount = loadedConfig.customerCount;

                LoggerUtil.infoMessage("Configuration loaded successfully!");
                return true;
            } catch (IOException e) {
                LoggerUtil.error("No saved configuration found or error reading file.");
            }
            return false;
        } else if (choice.equals("no")) {
            LoggerUtil.infoMessage("Configuration loading skipped.");
            return false;
        } else {
            LoggerUtil.warning("Invalid input. Please enter 'yes' or 'no'.");
        }
    }
}


    public void saveConfigPrompt() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Do you want to save this configuration? (yes/no): ");
            String choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals("yes")) {
                saveConfig();
                break;
            } else if (choice.equals("no")) {
                LoggerUtil.infoMessage("Configuration not saved.");
                break;
            } else {
                LoggerUtil.warning("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    public void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(this, writer);
            LoggerUtil.infoMessage("Configuration saved successfully!");
        } catch (IOException e) {
            LoggerUtil.error("Error saving configuration: " + e.getMessage());
        }
    }

    public void configureSystem() {
        Scanner scanner = new Scanner(System.in);

        this.totalEventTickets = getPositiveIntegerInput(scanner, "Enter total event tickets: ");
        this.maxPoolCapacity = getPositiveIntegerInput(scanner, "Enter maximum ticket pool capacity: ");
        this.ticketReleaseRate = getPositiveIntegerInput(scanner, "Enter ticket release rate (tickets/second): ");
        this.customerRetrievalRate = getPositiveIntegerInput(scanner, "Enter customer retrieval rate (tickets/second): ");
        this.vendorCount = getPositiveIntegerInput(scanner, "Enter vendor count: ");
        this.customerCount = getPositiveIntegerInput(scanner, "Enter customer count: ");

        LoggerUtil.infoMessage("System configured successfully!");
    }




    private int getPositiveIntegerInput(Scanner scanner, String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = Integer.parseInt(scanner.nextLine().trim());
                if (input > 0) {
                    return input;
                } else {
                    LoggerUtil.warning("Please enter a positive integer.");
                }
            } catch (NumberFormatException e) {
                LoggerUtil.error("Invalid input. Please enter a valid positive integer.");
            }
        }
    }

    // Getters for configuration values
    public int getTotalEventTickets() {
        return totalEventTickets;
    }

    public int getMaxPoolCapacity() {
        return maxPoolCapacity;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getVendorCount() {
        return vendorCount;
    }

    public int getCustomerCount() {
        return customerCount;
    }
}