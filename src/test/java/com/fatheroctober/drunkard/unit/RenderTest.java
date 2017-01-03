package com.fatheroctober.drunkard.unit;

import com.fatheroctober.drunkard.engine.core.models.World;
import com.fatheroctober.drunkard.engine.core.models.abstraction.AbstractGameFacade;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.RealGameModule;
import com.fatheroctober.drunkard.engine.core.views.panels.AbstractToolBarPanel;
import com.fatheroctober.drunkard.engine.core.views.panels.ToolBarPanel;
import com.fatheroctober.drunkard.engine.core.views.render.GameRender;
import com.fatheroctober.drunkard.engine.core.views.render.Renderer2D;
import com.fatheroctober.drunkard.engine.core.views.render.ToolBarRender;
import org.junit.Before;
import org.junit.Test;

public class RenderTest {

    AbstractGameFacade facade = InjectBundle.getInstance(World.class);
    AbstractToolBarPanel tb = InjectBundle.getInstance(ToolBarPanel.class);

    Renderer2D gr;
    Renderer2D tr;

    @Before
    public void createRenders() throws Exception {
        InjectBundle.configure(new RealGameModule());
        gr = InjectBundle.getInstance(GameRender.class);
        tr = InjectBundle.getInstance(ToolBarRender.class);
        facade.build();
    }

    @Test
    public void renderInitTest() throws Exception {
        gr.init(facade);
        tr.init(tb);
    }

}
