# Frontend: Ticket Simulation Dashboard

This repository hosts the **Frontend** implementation for the Ticket Simulation System. The frontend provides a user-friendly graphical interface to configure, monitor, and control the ticketing simulation. It is built with **React.js** and interacts with the backend via RESTful API endpoints.

---

## Features

1. **Configuration Form**:
    - Users can input and submit simulation parameters such as:
        - Total Tickets
        - Ticket Release Rate
        - Customer Retrieval Rate
        - Max Pool Capacity
        - Vendor Count
        - Customer Count

2. **Simulation Control**:
    - Buttons to control the simulation:
        - Start Simulation
        - Stop Simulation
        - Reset Simulation
        - Load Configuration
        - Save Configuration

3. **Live Logs**:
    - A terminal-style log display to monitor real-time updates and operations from the backend.

4. **Responsive Design**:
    - Fully responsive UI for different screen sizes.

5. **API Integration**:
    - Integrated with the backend using RESTful APIs for real-time operations.

6. **Code Modularity**:
    - Modular structure with reusable components for easy scalability and maintainability.

---

## How It Works

1. **User Configuration**:
    - The user enters ticketing parameters in the form fields.
    - Parameters are validated before submission.

2. **Interaction with Backend**:
    - The application interacts with the backend through RESTful APIs to save/load configurations and control the simulation.

3. **Simulation**:
    - Start, stop, and reset operations are controlled via buttons.
    - Logs provide a live feed of simulation status.

---

## Project Structure

```
frontend/
├── .gitignore
├── eslint.config.js
├── index.html
├── package-lock.json
├── package.json
├── project_structure.txt
├── public/
│   ├── vite.svg                 # Public assets
├── README.md
├── src/
│   ├── App.css                  # Global styles
│   ├── App.jsx                  # Main application component
│   ├── assets/
│   │   ├── react.svg            # React logo for branding
│   ├── components/
│   │   ├── ConfigForm.jsx       # Configuration form for user inputs
│   │   ├── Navbar.jsx           # Navigation bar
│   │   ├── Simulation.jsx       # Simulation-related components
│   ├── index.css                # Global CSS
│   ├── main.jsx                 # Entry point for React app
│   ├── pages/
│   │   ├── Configure.jsx        # Configuration page
│   │   ├── Home.jsx             # Home page
│   │   ├── SimulationResults.jsx # Results display page
│   ├── services/
│       ├── api.js               # API integration functions
├── vite.config.js               # Vite configuration
```

---

## API Endpoints Documentation

The frontend interacts with the following API endpoints:

### 1. **Configure System**
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
**Description**: Configures the simulation parameters.

---

### 2. **Start Simulation**
**POST** `http://localhost:8080/api/start-simulation`  
**Description**: Starts the simulation process with the configured parameters.

---

### 3. **Stop Simulation**
**POST** `http://localhost:8080/api/stop`  
**Description**: Stops the running simulation.

---

### 4. **Reset Simulation**
**POST** `http://localhost:8080/api/reset`  
**Description**: Resets the simulation and clears the configuration.

---

### 5. **Fetch Logs**
**GET** `http://localhost:8080/api/logs`  
**Description**: Retrieves logs from the backend to display in the terminal-like log viewer.

---

## Prerequisites to Run the Frontend

1. **Node.js**:
    - Install Node.js version 14 or higher.
    - Verify installation:
      ```bash
      node -v
      ```

2. **npm**:
    - Comes with Node.js.
    - Verify installation:
      ```bash
      npm -v
      ```

---

## Steps to Run the Frontend

1. **Clone the Repository**:
   ```bash
   git clone --branch Frontend --single-branch https://github.com/Pasanlaksitha/OOP-Coursework.git

   ```

2. **Install Dependencies**:
   ```bash
   npm install
   ```

3. **Run the Development Server**:
   ```bash
   npm run dev
   ```

4. **Access the Application**:
    - Open your browser and navigate to `http://localhost:5173`.(enter the port display in the terminal)

---

## Notes

1. Ensure the **backend** is running before starting the frontend to avoid connection errors.
2. Use the terminal to monitor real-time logs and debugging information.
3. For deployment, configure `vite.config.js` and backend CORS settings properly.
