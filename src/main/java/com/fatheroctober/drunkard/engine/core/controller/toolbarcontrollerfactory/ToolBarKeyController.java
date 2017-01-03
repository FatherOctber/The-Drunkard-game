package com.fatheroctober.drunkard.engine.core.controller.toolbarcontrollerfactory;

import com.fatheroctober.drunkard.engine.core.common.GlobalTimer;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.Inject;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.logging.Level;

public class ToolBarKeyController extends KeyAdapter {

    @Inject
    IfceLoger logger;

    private int defaultFrameRate = GlobalTimer.getInstance().getFrameRate();

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT: {
                int currentFrameRate = GlobalTimer.getInstance().getFrameRate();
                GlobalTimer.getInstance().setFrameRate(currentFrameRate + 5);
                logger.log("Frame rate +5", Level.SEVERE);
            }
            break;
            case KeyEvent.VK_RIGHT: {
                int currentFrameRate = GlobalTimer.getInstance().getFrameRate();
                GlobalTimer.getInstance().setFrameRate(currentFrameRate - 5);
                logger.log("Frame rate -5", Level.SEVERE);
            }
            break;
            case KeyEvent.VK_UP: {
                GlobalTimer.getInstance().setFrameRate(defaultFrameRate);
                logger.log("Frame rate set default", Level.SEVERE);
            }
            break;
            case KeyEvent.VK_DOWN: {
                GlobalTimer.getInstance().setFrameRate(defaultFrameRate);
                logger.log("Frame rate set default", Level.SEVERE);
            }
            break;
            case KeyEvent.VK_SPACE: {
                GlobalTimer.getInstance().setPaused(!GlobalTimer.getInstance().isPaused());
                logger.log("Game pause=" + GlobalTimer.getInstance().isPaused(), Level.SEVERE);
            }
        }

    }


}