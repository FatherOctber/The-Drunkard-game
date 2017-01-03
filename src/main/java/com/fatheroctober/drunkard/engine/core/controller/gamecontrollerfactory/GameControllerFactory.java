package com.fatheroctober.drunkard.engine.core.controller.gamecontrollerfactory;

import com.fatheroctober.drunkard.engine.core.controller.AbstractControllerFactory;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;

/**
 * abstract factory implementation
 */
public class GameControllerFactory implements AbstractControllerFactory {

    @Override
    public GameKeyController getKeyListener()
    {
        return InjectBundle.getInstance(GameKeyController.class);
    }

    @Override
    public GameMouseController getMouseListener()
    {
        return InjectBundle.getInstance(GameMouseController.class);
    }

}
