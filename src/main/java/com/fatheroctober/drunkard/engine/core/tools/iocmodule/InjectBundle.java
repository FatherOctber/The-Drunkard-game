package com.fatheroctober.drunkard.engine.core.tools.iocmodule;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;

public class InjectBundle {

    private static AbstractModule configuration = new RealGameModule();

    /**
     * to create instance of object using Inject (Strongly recommend)
     *
     * @param clazz object class
     * @param <T>   type
     * @return new instance
     */
    static public <T> T getInstance(Class<T> clazz) {
        return Guice.createInjector(configuration).getInstance(clazz);
    }

    static public void configure(AbstractModule conf) {
        configuration = conf;
    }
}
