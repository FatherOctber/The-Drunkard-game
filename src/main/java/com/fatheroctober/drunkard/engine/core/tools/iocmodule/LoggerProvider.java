package com.fatheroctober.drunkard.engine.core.tools.iocmodule;

import com.fatheroctober.drunkard.engine.core.tools.utils.GameLog;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class LoggerProvider implements Provider<IfceLoger> {

    static private IfceLoger logger;

    @Inject
    public LoggerProvider() {
        if (logger == null) logger = new GameLog();
    }

    @Override
    public IfceLoger get() {
        return logger;
    }
}
