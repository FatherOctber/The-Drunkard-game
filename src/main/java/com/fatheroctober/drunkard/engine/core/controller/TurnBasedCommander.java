package com.fatheroctober.drunkard.engine.core.controller;

import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IActor;
import com.fatheroctober.drunkard.engine.core.common.GlobalTimer;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.google.inject.Inject;


import java.awt.*;
import java.util.logging.Level;


public class TurnBasedCommander extends Commander {

    @Inject
    PropertiesReader propertiesReader;

    private boolean autoMode = false; // todo switch control mode
    private int WINDOW_DX = 0;
    private int WINDOW_DY = 0;

    @Override
    public void update() {
        mouseTracking();
        playRolegame();
    }

    private void playRolegame() {
        if (!GlobalTimer.getInstance().isPaused() && (GlobalTimer.getInstance().getTacts() != propertiesReader.roundFinish())) {
            boolean roundIsOver = true;
            for (IActor actor : gameFacade.getAllActors()) {
                if (actor.actorActivity() != IActor.Activity.NOT_READY) {
                    ICommand com = InjectBundle.getInstance(Command.class).configure(actor, gameFacade.getMap());
                    com.execute();
                    roundIsOver = false;
                    break;
                }
            }
            if (roundIsOver) {
                gameFacade.getAllActors().forEach(actor -> {
                    actor.realiseForAction();
                });
                GlobalTimer.getInstance().incTacts();
                if (GlobalTimer.getInstance().getTacts() == propertiesReader.roundFinish())
                    logger.log("Game finished. " + GlobalTimer.getInstance().getTacts() + " rounds", Level.WARNING);
                logger.log("Game time +1; Times = " + GlobalTimer.getInstance().getTacts(), Level.INFO); //GameLog.getInstance().message("Game time +1; Times = " + GlobalTimer.getInstance().getTacts(), Level.INFO);
            }
        }
    }

    private void mouseTracking() {
        gameFacade.getMap().getAreas().forEach(area -> {
            double mouseX = MouseInfo.getPointerInfo().getLocation().getX();
            double mouseY = MouseInfo.getPointerInfo().getLocation().getY();
            if ((mouseX > area.getXY().getLeft() + WINDOW_DX && mouseX < (area.getXY().getLeft() + WINDOW_DX + area.getWidth())) &&
                    (mouseY > area.getXY().getRight() + WINDOW_DY && mouseY < (area.getXY().getRight() + WINDOW_DY + area.getHeight())))
                area.selectOn();
            else area.selectOff();
        });
    }

    @Override
    public void receiveSignal(int X, int Y) {
        WINDOW_DX = X;
        WINDOW_DY = Y;
    }
}
