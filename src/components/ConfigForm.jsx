import { useState, useRef, useEffect } from 'react';
import { TextField, Button, Container, Typography, Box } from '@mui/material';
import { saveConfig, loadConfig, startSimulation, stopSimulation, resetSimulation, fetchLogs } from '../services/api'; // Assuming all endpoints are defined in `api.js`


const ConfigForm = () => {
    const [config, setConfig] = useState({
        totalEventTickets: '',
        ticketReleaseRate: '',
        customerRetrievalRate: '',
        maxPoolCapacity: '',
        vendorCount: '',
        customerCount: '',
    });

    const [isPolling, setIsPolling] = useState(false);
    const terminalRef = useRef(null);
    const [logs, setLogs] = useState([]);

    const handleChange = (e) => {
        setConfig({ ...config, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await saveConfig(config);
            // alert('Configuration saved successfully!');
        } catch (error) {
            alert('Failed to save configuration.');
            console.error(error);
        }
    };

    const handleLoadConfig = async () => {
        try {
            const data = await loadConfig();
            setConfig(data);
            // alert('Configuration loaded successfully!');
        } catch (error) {
            alert('Failed to load configuration.');
            console.error(error);
        }
    };

    const handleStartSimulation = async () => {
        try {
            await startSimulation();
            alert('Simulation started successfully!');
            startPolling(); // Start polling when the simulation begins
        } catch (error) {
            alert('Failed to start simulation.');
            console.error(error);
        }
    };

    const handleStopSimulation = async () => {
        try {
            await stopSimulation();
            alert('Simulation stopped successfully!');
            stopPolling(); // Stop polling when the simulation ends
        } catch (error) {
            alert('Failed to stop simulation.');
            console.error(error);
        }
    };

    const handleRestSimulation = async () => {
        try {
            await resetSimulation();
            // alert('Simulation Reset successfully!');
        } catch (error) {
            alert('Failed to Reset simulation.');
            console.error(error);
        }
    };

    const startPolling = () => {
        setIsPolling(true);
    };

    const stopPolling = () => {
        setIsPolling(false);
    };

    useEffect(() => {
        let interval;
        if (isPolling) {
            interval = setInterval(async () => {
                try {
                    const newLogs = await fetchLogs();
                    setLogs((prevLogs) => [...prevLogs, ...newLogs]); // Append new logs to the existing ones
                    if (terminalRef.current) {
                        terminalRef.current.scrollTop = terminalRef.current.scrollHeight;
                    }
                } catch (error) {
                    console.error('Error fetching logs:', error);
                }
            }, 2000); // Fetch logs every 2 seconds
        }
        return () => clearInterval(interval);
    }, [isPolling]);

    return (
        <Container>
            <Typography variant="h4" gutterBottom>
                Configure Simulation
            </Typography>
            <form onSubmit={handleSubmit}>
                <TextField
                    label="Total Tickets"
                    name="totalEventTickets"
                    value={config.totalEventTickets}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField
                    label="Ticket Release Rate"
                    name="ticketReleaseRate"
                    value={config.ticketReleaseRate}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField
                    label="Customer Retrieval Rate"
                    name="customerRetrievalRate"
                    value={config.customerRetrievalRate}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField
                    label="Max Pool Capacity"
                    name="maxPoolCapacity"
                    value={config.maxPoolCapacity}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField
                    label="Vendor Count"
                    name="vendorCount"
                    value={config.vendorCount}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <TextField
                    label="Customer Count"
                    name="customerCount"
                    value={config.customerCount}
                    onChange={handleChange}
                    fullWidth
                    margin="normal"
                    required
                />
                <Box sx={{ display: 'flex', gap: '10px', mt: 2 }}>
                    <Button type="submit" variant="contained" color="primary">
                        Save Configuration
                    </Button>
                    <Button variant="contained" color="secondary" onClick={handleLoadConfig}>
                        Load Configuration
                    </Button>
                    <Button variant="contained" color="success" onClick={handleStartSimulation}>
                        Start Simulation
                    </Button>
                    <Button variant="contained" color="error" onClick={handleStopSimulation}>
                        Stop Simulation
                    </Button>
                    <Button variant="contained" color="warning" onClick={handleRestSimulation}>
                        Reset Simulation
                    </Button>
                </Box>
            </form>
            {/* Terminal-like area for logs */}
            <div
                ref={terminalRef}
                style={{
                    backgroundColor: 'black',
                    color: 'lime',
                    fontFamily: 'monospace',
                    padding: '10px',
                    marginTop: '20px',
                    height: '300px',
                    overflowY: 'scroll',
                    borderRadius: '5px',
                    border: '1px solid #333',
                }}
            >
                {logs.map((log, index) => (
                    <div key={index}>{log}</div>
                ))}
            </div>
        </Container>
    );
};

export default ConfigForm;
