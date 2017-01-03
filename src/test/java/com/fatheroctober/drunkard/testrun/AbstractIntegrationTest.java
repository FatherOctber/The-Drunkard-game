package com.fatheroctober.drunkard.testrun;

import com.fatheroctober.drunkard.engine.core.controller.Commander;
import com.fatheroctober.drunkard.engine.core.controller.TurnBasedCommander;
import com.fatheroctober.drunkard.engine.core.models.World;
import com.fatheroctober.drunkard.engine.core.models.abstraction.AbstractGameFacade;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import org.junit.BeforeClass;
import com.fatheroctober.drunkard.testsettings.AnotherTestModule;


public abstract class AbstractIntegrationTest {

    protected static IfceLoger logger;
    protected static AbstractGameFacade facade;
    protected static Commander totalCommander;

    @BeforeClass
    public static void initialize() throws Exception {
        InjectBundle.configure(new AnotherTestModule());
        facade = InjectBundle.getInstance(World.class);
        facade.build();
        logger = InjectBundle.getInstance(IfceLoger.class);
        totalCommander = InjectBundle.getInstance(TurnBasedCommander.class);
        totalCommander.init(facade);
    }
}
