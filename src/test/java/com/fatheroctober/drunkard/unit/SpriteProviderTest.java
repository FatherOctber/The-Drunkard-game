package com.fatheroctober.drunkard.unit;

import com.fatheroctober.drunkard.engine.core.models.World;
import com.fatheroctober.drunkard.engine.core.models.abstraction.AbstractGameFacade;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IActor;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IArea;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.RealGameModule;
import com.fatheroctober.drunkard.engine.core.views.render.SpriteProvider;
import org.junit.Before;
import org.junit.Test;

public class SpriteProviderTest {
    SpriteProvider sp;
    AbstractGameFacade facade;

    @Before
    public void before() throws Exception {
        InjectBundle.configure(new RealGameModule());
        sp = InjectBundle.getInstance(SpriteProvider.class);
        facade = InjectBundle.getInstance(World.class);
        facade.build();
    }

    @Test
    public void initSpriteProviderTest() throws Exception {
        sp.init();
    }

    @Test
    public void spritesTest() throws Exception {
        sp.init();
        for (IArea area : facade.getMap().getAreas()) {
            sp.spriteOf(area);
        }
        for (IActor actor : facade.getAllActors()) {
            sp.spriteOf(actor);
        }
    }


}
