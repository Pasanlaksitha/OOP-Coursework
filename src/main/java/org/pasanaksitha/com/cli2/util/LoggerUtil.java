package org.pasanaksitha.com.cli2.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Utility class for logging messages with different levels of severity.
 * Provides methods to log informational messages, errors, and warnings.
 * This class uses the Apache Log4j logging framework.
 */
public class LoggerUtil {

    private static final Logger logger = LogManager.getLogger(Logger.class);

    /**
     * Logs an informational message.
     *
     * @param message The message to be logged at the INFO level.
     */
    public static void infoMessage(String message) {
        logger.info(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The message to be logged at the ERROR level.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The message to be logged at the WARN level.
     */
    public static void warning(String message) {
        logger.warn(message);
    }
}