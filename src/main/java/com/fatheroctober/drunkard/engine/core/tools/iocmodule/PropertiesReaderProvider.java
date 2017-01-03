package com.fatheroctober.drunkard.engine.core.tools.iocmodule;

import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class PropertiesReaderProvider implements Provider<PropertiesReader> {

    static private PropertiesReader propertiesReader;

    @Inject
    public PropertiesReaderProvider() {
        if (propertiesReader == null) propertiesReader = new PropertiesReader();
    }

    @Override
    public PropertiesReader get() {
        return propertiesReader;
    }
}