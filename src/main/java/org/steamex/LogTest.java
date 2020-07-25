package org.steamex;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
// TODO remove file, this is only to help Ilse with her log4j assignment
public class LogTest {

    private static final Logger logger = LogManager.getLogger(LogTest.class);

    public static void main(String[] args) {
        logger.trace("Trace Test");
        logger.info("Info Test");
        logger.debug("Debug test");
        logger.warn("Warn test");
        logger.error("Error Test");
        logger.fatal("Fatal test");

    }
}
