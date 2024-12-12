
---

# CLI2: Ticket Simulation System

This repository hosts the **Ticket Simulation System**, implemented as a Command-Line Interface (CLI) application. The project is designed to manage ticket issuance, retrieval, and simulation for a multi-threaded environment. The **CLI2** branch serves as the primary branch for the application.

## Overview

The Ticket Simulation System is a multi-threaded Java application that simulates vendors issuing tickets and customers retrieving them. Key features include:
- A **Configurable System** to set parameters such as ticket pool size, vendor count, and ticket retrieval rates.
- Real-time logging and user input validation.
- Separate modules for **Backend**, **Frontend**, and **CLI** to ensure modularity and extensibility.

## Features of CLI2

1. **Configurable Parameters**:
    - Total number of event tickets.
    - Ticket pool capacity.
    - Ticket release rate (by vendors).
    - Ticket retrieval rate (by customers).
    - Vendor and customer counts.

2. **Multi-Threading**:
    - Vendors and customers run in separate threads.
    - The system monitors ticket availability and halts operations once all tickets are sold.

3. **Configuration Management**:
    - Save and load configurations via JSON files.
    - Validate user inputs for robustness.

4. **Logging**:
    - Comprehensive logs using `LoggerUtil` to monitor operations.

## Project Structure

The project follows a well-organized structure to separate concerns effectively:

```
src/
├── main/
│   ├── java/
│   │   ├── config/             # Configuration management (Config.java)
│   │   ├── core/               # Core system modules (Customer, TicketPool, Vendor)
│   │   ├── model/              # Data models (Ticket)
│   │   ├── util/               # Utilities (LoggerUtil)
│   ├── resources/              # Configuration and template files
├── test/                       # Unit tests
```

## How to Use

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/yourusername/repository.git
   cd CLI2
   ```

2. **Build the Project**:
   Use Maven to compile the project:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   ```bash
   java -jar target/CLI2-1.0-SNAPSHOT.jar
   ```

4. **Follow the Prompts**:
    - Configure the system by entering the required parameters.
    - Choose whether to save or load configurations.
    - Start or stop the simulation as required.

## Branch Overview

### CLI2 (Default Branch)
This branch contains the **CLI** implementation of the Ticket Simulation System. It is designed to run in a terminal environment, allowing users to configure and simulate the ticketing process.

### Backend
The **Backend** branch hosts the API implementation of the system. It provides RESTful endpoints for managing configurations, starting simulations, and monitoring logs. Ideal for integration with external systems.

### Frontend
The **Frontend** branch includes a **React.js** implementation for a web-based user interface. It allows users to configure and monitor the simulation via a graphical interface.

### Notes on Branches
Each branch is independent and focuses on a specific aspect of the system:
- **CLI2**: Command-line application.
- **Backend**: REST API.
- **Frontend**: Web-based interface.

## Starting the Simulation

***Run the application using the command provided.***

To start the project on another computer after cloning, follow these steps:

---

## Steps to Start the Project

### Prerequisites

1. **Java Development Kit (JDK)**:
    - Install JDK version 11 or higher.
    - Verify installation:
      ```bash
      java -version
      ```

2. **Maven**:
    - Ensure Maven is installed for project build and dependency management.
    - Verify installation:
      ```bash
      mvn -version
      ```

3. **Git**:
    - Git is required to clone the repository.
    - Verify installation:
      ```bash
      git --version
      ```

4. **IDE (Optional)**:
    - Use an Integrated Development Environment (IDE) like IntelliJ IDEA, Eclipse, or VS Code for ease of use.

---

### Steps to Run the Project

1. **Clone the Repository**:
   Open a terminal and execute:
   ```bash
   git clone https://github.com/yourusername/repository.git
   cd CLI2
   ```

2. **Install Dependencies**:
   Run Maven to download and install dependencies:
   ```bash
   mvn clean install
   ```

3. **Run the Project**:
    run the TicketingSystem.java

---

