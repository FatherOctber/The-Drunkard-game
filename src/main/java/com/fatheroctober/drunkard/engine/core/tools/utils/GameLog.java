package com.fatheroctober.drunkard.engine.core.tools.utils;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameLog implements IfceLoger {

    private Deque<Log> logDeque = new ArrayDeque<>();
    private Logger logger; // logger instance
    private final String LOG_BOX_PREFIX = "LOG_BOX:: ";
    private final int LOG_SIZE = 20;

    public GameLog() {
        logger = Logger.getLogger(getClass().getName());
    }

    public void log(String message, Level lv) {
        logger.log(lv, message);
        if (logDeque.size() <= LOG_SIZE) logDeque.addLast(new Log(LOG_BOX_PREFIX + message, lv));
        else {
            logDeque.removeLast();
            logDeque.addFirst(new Log(LOG_BOX_PREFIX + message, lv));
        }
    }

    public void log(Log log) {
        Log nLog = new Log(log);
        if (logDeque.size() <= LOG_SIZE) logDeque.addLast(nLog);
        else {
            logDeque.removeLast();
            logDeque.addFirst(nLog);
        }
    }

    public Iterable<Log> getLogs() {
        Iterable<Log> iterLogs = new ArrayList<>(logDeque);
        logDeque.clear();
        return iterLogs;
    }

    public Log getLog() {
        Log log = null;
        try {
            log = new Log(logDeque.getLast());
            logDeque.removeLast();
        } catch (NoSuchElementException ex) {
        } catch (Exception ex) {
            System.out.println("Logger:: " + ex.getMessage());
        }
        return log;
    }

}
