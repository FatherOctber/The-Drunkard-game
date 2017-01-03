package com.fatheroctober.drunkard.testsettings;

import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.Inject;
import com.google.inject.Provider;


public class TestLoggerProvider implements Provider<IfceLoger> {

    static private IfceLoger logger;

    @Inject
    public TestLoggerProvider() {
        if (logger == null) logger = new TestLogger();
    }

    @Override
    public IfceLoger get() {
        return logger;
    }
}