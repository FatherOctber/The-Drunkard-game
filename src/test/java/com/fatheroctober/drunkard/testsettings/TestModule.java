package com.fatheroctober.drunkard.testsettings;

import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.models.PolicemanMovingStrategy;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMovingStrategy;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.DrinkerInject;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.PolicemanInject;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.AbstractModule;

public class TestModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(IMovingStrategy.class).annotatedWith(DrinkerInject.class).to(DrinkerTestMovingStrategy.class);
        bind(IMovingStrategy.class).annotatedWith(PolicemanInject.class).to(PolicemanMovingStrategy.class);
        bind(IfceLoger.class).toProvider(TestLoggerProvider.class); // like as Singleton
        bind(PropertiesReader.class).toProvider(PropProviderForTest.class); // like as Singleto

    }
}
