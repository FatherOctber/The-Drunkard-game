package com.fatheroctober.drunkard.engine.core.controller.toolbarcontrollerfactory;

import com.fatheroctober.drunkard.engine.core.controller.AbstractControllerFactory;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public class ToolBarControllerFactory implements AbstractControllerFactory {

    public KeyListener getKeyListener()
    {
        return InjectBundle.getInstance(ToolBarKeyController.class);
    }

    public MouseListener getMouseListener()
    {
        return InjectBundle.getInstance(ToolBarMouseController.class);
    }
}
