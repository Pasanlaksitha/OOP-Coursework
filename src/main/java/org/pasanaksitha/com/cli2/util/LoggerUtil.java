package org.pasanaksitha.com.cli2.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The LoggerUtil class is a utility class that provides logging capabilities
 * for the application using Apache Log4j. It simplifies logging operations by
 * exposing methods for logging informational messages, warnings, and errors.
 */
public class LoggerUtil {

    // Logger instance for logging messages
    private static final Logger logger = LogManager.getLogger(Logger.class);

    /**
     * Logs an informational message.
     *
     * @param message The message to be logged as informational.
     */
    public static void infoMessage(String message) {
        logger.info(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The message to be logged as an error.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The message to be logged as a warning.
     */
    public static void warning(String message) {
        logger.warn(message);
    }
}
