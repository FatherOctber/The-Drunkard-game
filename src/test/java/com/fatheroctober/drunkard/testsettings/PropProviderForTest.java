package com.fatheroctober.drunkard.testsettings;

import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class PropProviderForTest implements Provider<PropertiesReader> {

    static private PropertiesReader propertiesReader;

    @Inject
    public PropProviderForTest() {
        if (propertiesReader == null) propertiesReader = new TestPropertiesReader();
    }

    @Override
    public PropertiesReader get() {
        return propertiesReader;
    }
}