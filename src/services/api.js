import axios from 'axios';

const API_BASE_URL = 'http://localhost:8080/api';

// Create a reusable Axios instance with CORS support
const axiosInstance = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Save the configuration
export const saveConfig = async (config) => {
  return await axiosInstance.post('/config', config);
};

// Load the configuration
export const loadConfig = async () => {
  return await axiosInstance.get('/config');
};

// Start the simulation
export const startSimulation = async () => {
  return await axiosInstance.post('/start-simulation');
};

// Stop the simulation
export const stopSimulation = async () => {
  return await axiosInstance.post('/stop');
};

// Reset the simulation
export const resetSimulation = async () => {
  return await axiosInstance.post('/reset');
};

export const fetchLogs = async () => {
  const response = await axiosInstance.get('/logs');
  return response.data;
};


export class handleRestSimulation {
}