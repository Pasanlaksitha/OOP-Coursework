package org.pasanaksitha.com.cli2.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggerUtil {
    private static final Logger logger = LogManager.getLogger(Logger.class);

    public static void infoMessage(String message) {
        logger.info(message);
    }

    public static void error(String message) {
        logger.error(message);
    }

    public static void warning(String message) {
        logger.warn(message);
    }
}