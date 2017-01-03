package com.fatheroctober.drunkard.engine.core.controller;

import com.fatheroctober.drunkard.engine.core.models.abstraction.IActor;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMap;
import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.Inject;

import java.util.logging.Level;

public class Command implements ICommand {

    private IActor gameActor;
    private IActor previousActor; // todo undo-redo command
    private IMap map;

    @Inject
    private IfceLoger logger;

    public Command configure(IActor actor, IMap map) {
        gameActor = actor;
        this.map = map;
        return this;
    }

    @Override
    public void execute() {
        try {
            gameActor.doAction(map);
        } catch (DrinkerGameException drEx) {
            logger.log("Command execute exception", Level.WARNING);
            drEx.printStackTrace();
        }
    }
}
