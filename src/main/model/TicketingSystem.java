package main.model;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TicketingSystem {
    public static boolean isRunning = false;
    public static TicketPool ticketPool;
    public static int totalTickets, maxTicketCapacity, ticketReleaseRate, customerRetrievalRate;



    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        configureSystem();
        ticketPool = new TicketPool(totalTickets);
        commandLoop();
    }

    private static void configureSystem() {
    Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter maximum ticket capacity: ");
                maxTicketCapacity = Integer.parseInt(scanner.nextLine());
                if (maxTicketCapacity > 0) {
                    break;
                } else {
                    throw new IllegalArgumentException("Maximum ticket capacity must be greater than zero.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }

        while (true) {
            try {
                System.out.print("Enter total tickets to start with: ");
                totalTickets = Integer.parseInt(scanner.nextLine());
                if (totalTickets >= 0 && totalTickets <= maxTicketCapacity) {
                    break;
                } else {
                    throw new IllegalArgumentException("Total tickets must be non-negative and less than or equal to the maximum capacity.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }


        while (true) {
            try {
                System.out.print("Enter ticket release rate (tickets/second): ");
                ticketReleaseRate = Integer.parseInt(scanner.nextLine());
                if (ticketReleaseRate > 0) {
                    break;
                } else {
                    throw new IllegalArgumentException("Ticket release rate must be greater than zero.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }


        while (true) {
            try {
                System.out.print("Enter customer retrieval rate (tickets/second): ");
                customerRetrievalRate = Integer.parseInt(scanner.nextLine());
                if (customerRetrievalRate > 0) {
                    break;
                } else {
                    throw new IllegalArgumentException("Customer retrieval rate must be greater than zero.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. " + e.getMessage());
            }
        }

    }



    private static void commandLoop() {
        Scanner scanner = new Scanner(System.in);
        ExecutorService executor = null;

        while (true) {
            System.out.print("Enter command (start/stop/exit): ");
            String command = scanner.nextLine().trim();

            if (command.isEmpty()) {
                continue;
            }

            switch (command.toLowerCase()) {
                case "start":
                    if (!isRunning) {
                        isRunning = true;
                        executor = Executors.newFixedThreadPool(2);
                        executor.execute(new Vendor());
                        executor.execute(new Customer());
                        System.out.println("System started.");
                    } else {
                        System.out.println("System is already running.");
                    }
                    break;
                case "stop":
                    if (isRunning) {
                        isRunning = false;
                        executor.shutdownNow();
                        System.out.println("System stopped.");
                    } else {
                        System.out.println("System is not running.");
                    }
                    break;
                case "exit":
                    if (isRunning) {
                        isRunning = false;
                        executor.shutdownNow();
                    }
                    System.out.println("Exiting the system.");
                    return;
                default:
                    System.out.println("Invalid command. Try 'start', 'stop', or 'exit'.");
            }
        }
    }
}
