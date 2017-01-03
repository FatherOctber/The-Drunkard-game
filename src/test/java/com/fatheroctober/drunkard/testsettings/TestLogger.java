package com.fatheroctober.drunkard.testsettings;

import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.fatheroctober.drunkard.engine.core.tools.utils.Log;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TestLogger implements IfceLoger {

    private Logger logger; // logger instance
    private final String LOG_BOX_PREFIX = "TEST:: ";

    public TestLogger() {
        logger = Logger.getLogger(getClass().getName());
    }

    public void log(String message, Level lv) {
        logger.log(lv, LOG_BOX_PREFIX + message);
    }

    public void log(Log log) {
        Log nLog = new Log(log);
    }

    public Iterable<Log> getLogs() {
        return null;
    }

    public Log getLog() {
        return null;
    }

}
