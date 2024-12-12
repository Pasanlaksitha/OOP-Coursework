# Backend: Ticket Simulation System

The **Backend** branch contains the REST API implementation for the Ticket Simulation System, designed to support configuration management, simulation control, and logging functionality for the CLI2 project.

---

## Features

1. **RESTful API**:
    - Provides endpoints for configuring, starting, stopping, and resetting the simulation.
    - Logs are accessible via API for real-time monitoring.

2. **Configuration Management**:
    - Configurable parameters include ticket pool size, ticket release rate, and customer retrieval rate.
    - Supports saving and loading configurations from JSON files.

3. **Logging**:
    - Comprehensive logging implemented using `LoggerUtil`.
    - Logs are stored in a file (`ticketingSystem.log`) and can be accessed via the `/logs` API endpoint.

4. **CORS Support**:
    - Configured to allow cross-origin requests, enabling integration with a frontend application hosted on a different domain.

5. **Error Handling**:
    - Input validation and error handling implemented in API endpoints to ensure robustness.

---

## How It Works

1. **Configuration**:
    - The system is initialized with parameters set via the `/api/config` endpoint.
    - Users can save and load configurations.

2. **Simulation Control**:
    - Start the simulation using `/api/start-simulation`. Vendors and customers operate concurrently in a multi-threaded environment.
    - Stop the simulation using `/api/stop`.
    - Reset the simulation and configuration using `/api/reset`.

3. **Logging**:
    - Logs provide insights into the simulation process, including vendor and customer operations.

---

## Classes

### 1. **TicketingSystem**
- Entry point for the backend service.
- Initializes the Spring Boot application.

### 2. **Config**
- Manages simulation parameters and provides methods for saving/loading configurations.

### 3. **ConfigController**
- Handles API requests for configuration management and simulation control.

### 4. **LogController**
- Provides an endpoint to fetch logs.

### 5. **LoggerUtil**
- Utility class for logging messages at various levels (INFO, ERROR, WARN).

### 6. **WebConfig**
- Configures CORS settings to allow requests from the frontend.

---

## API Endpoints Documentation

### 1. Configure System
**POST** `http://localhost:8080/api/config`  
**Request Body**:
```json
{
  "totalEventTickets": 1000,
  "maxPoolCapacity": 200,
  "ticketReleaseRate": 10,
  "customerRetrievalRate": 5,
  "vendorCount": 3,
  "customerCount": 4
}
```
**Description**: Configures the system with the specified parameters.

---

### 2. Start Simulation
**POST** `http://localhost:8080/api/start-simulation`  
**Description**: Starts the simulation with the current configuration.

---

### 3. Stop Simulation
**POST** `http://localhost:8080/api/stop`  
**Description**: Stops the simulation gracefully.

---

### 4. Reset Simulation
**POST** `http://localhost:8080/api/reset`  
**Description**: Resets the simulation and clears all configurations.

---

### 5. Fetch Logs
**GET** `http://localhost:8080/api/logs`  
**Description**: Retrieves the logs of the simulation in real time.

---

## How to Start the Backend Project

### Prerequisites
1. **Java Development Kit (JDK)**:  
   Install JDK version 21 or higher.  
   Verify installation:
   ```bash
   java -version
   ```

2. **Maven**:  
   Ensure Maven is installed for project build and dependency management.  
   Verify installation:
   ```bash
   mvn -version
   ```

3. **Git**:  
   Git is required to clone the repository.  
   Verify installation:
   ```bash
   git --version
   ```

---

### Steps to Start

1. **Clone the Repository**:
   ```bash
   git clone --branch Backend --single-branch https://github.com/Pasanlaksitha/OOP-Coursework.git

   ```

2. **Install Dependencies**:
   ```bash
   mvn clean install
   ```

3. **Run the Project**:
   ```bash
   mvn spring-boot:run
   ```

4. **Access the API**:
    - Use tools like Postman or cURL to interact with the endpoints.
    - The server will be running on `http://localhost:8080`.

---

## Project Structure

```
Backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── config/             # Configuration management
│   │   │   ├── model/              # Core models (Ticket, Vendor, Customer)
│   │   │   ├── util/               # Utility classes (LoggerUtil, ConfigController)
│   │   │   ├── TicketingSystem.java  # Entry point of the application
│   │   ├── resources/
│   │       ├── log4j2.xml          # Logging configuration
│   │       ├── application.yml     # Spring application configuration
├── test/                           # Unit tests
├── config.json                     # Default configuration file
├── pom.xml                         # Maven dependencies
```

--- 

