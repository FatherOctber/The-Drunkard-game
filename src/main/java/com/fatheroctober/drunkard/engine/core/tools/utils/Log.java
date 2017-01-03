package com.fatheroctober.drunkard.engine.core.tools.utils;

import java.util.logging.Level;

public class Log {
    private String logText;
    private Level level;

    public Log(String message, Level lv) {
        logText = message;
        level = lv;
    }

    public Log(Log yetAnotherLog) {
        logText = yetAnotherLog.getLogText();
        level = yetAnotherLog.getLevel();
    }

    public String getLogText() {
        return logText;
    }

    public Level getLevel() {
        return level;
    }
}
