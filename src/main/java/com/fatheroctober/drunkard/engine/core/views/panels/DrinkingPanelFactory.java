package com.fatheroctober.drunkard.engine.core.views.panels;

import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;

public class DrinkingPanelFactory implements AbstractPanelFactory {

    public AbstractGamePanel getGamePanel() {
        return InjectBundle.getInstance(GamePanel.class);
    }

    public AbstractToolBarPanel getToolBarPanel() {
        return InjectBundle.getInstance(ToolBarPanel.class);
    }
}
