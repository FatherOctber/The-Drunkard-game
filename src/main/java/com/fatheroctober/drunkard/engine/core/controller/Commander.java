package com.fatheroctober.drunkard.engine.core.controller;

import com.fatheroctober.drunkard.engine.core.common.Observer;
import com.fatheroctober.drunkard.engine.core.common.Updateable;
import com.fatheroctober.drunkard.engine.core.models.abstraction.AbstractGameFacade;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.Inject;

public abstract class Commander implements Updateable, Observer {
    protected int stepCounter = 0;
    protected int cycles = 0;

    @Inject
    protected IfceLoger logger;

    protected AbstractGameFacade gameFacade;

    public void init(AbstractGameFacade gameWorld) {
        gameFacade = gameWorld;
    }
}
