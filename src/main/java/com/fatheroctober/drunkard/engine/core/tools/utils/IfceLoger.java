package com.fatheroctober.drunkard.engine.core.tools.utils;

import java.util.logging.Level;

public interface IfceLoger {

    /**
     * method for log
     *
     * @param log log message
     * @param lv  loging level
     */
    void log(String log, Level lv);

    /**
     * method for log
     *
     * @param log - Log object
     */
    void log(Log log);

    /**
     * get existed logs
     *
     * @return logs
     */
    Iterable<Log> getLogs();

    /**
     * get last log
     *
     * @return last log object
     */
    Log getLog();


}
