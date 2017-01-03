package com.fatheroctober.drunkard.engine.core.tools.iocmodule;

import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.models.DrinkerMovingStrategy;
import com.fatheroctober.drunkard.engine.core.models.PolicemanMovingStrategy;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMovingStrategy;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.AbstractModule;

public class RealGameModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IMovingStrategy.class).annotatedWith(DrinkerInject.class).to(DrinkerMovingStrategy.class);
        bind(IMovingStrategy.class).annotatedWith(PolicemanInject.class).to(PolicemanMovingStrategy.class);
        bind(IfceLoger.class).toProvider(LoggerProvider.class); // like as Singleton
        bind(PropertiesReader.class).toProvider(PropertiesReaderProvider.class); // like as Singleton

    }
}
