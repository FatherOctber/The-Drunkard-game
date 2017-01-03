package com.fatheroctober.drunkard.unit;

import com.fatheroctober.drunkard.engine.core.models.World;
import com.fatheroctober.drunkard.engine.core.models.abstraction.AbstractGameFacade;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.AbstractModule;

public abstract class AbstractUnitTest {

    protected IfceLoger logger;
    protected AbstractGameFacade world;


    public void initialize(AbstractModule testModule) throws Exception {
        InjectBundle.configure(testModule);
        world = InjectBundle.getInstance(World.class);
        world.build();
        logger = InjectBundle.getInstance(IfceLoger.class);

    }
}
