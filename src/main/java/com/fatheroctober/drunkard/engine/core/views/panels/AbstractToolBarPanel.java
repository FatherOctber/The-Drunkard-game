package com.fatheroctober.drunkard.engine.core.views.panels;

import com.fatheroctober.drunkard.engine.core.common.Observer;
import com.fatheroctober.drunkard.engine.core.views.render.Renderer2D;

import java.awt.*;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public abstract class AbstractToolBarPanel extends UpdateablePanel {

    /**
     * render entity
     */
    protected Renderer2D render;

    /**
     * keyboard controller
     */
    protected KeyListener keyController;

    /**
     * mouse controller
     */
    protected MouseListener mouseController;

    public AbstractToolBarPanel() {
        super();
        setBackground(Color.RED); // for example
    }

    @Override
    public void registerObserver(Observer ob) {
    }

    @Override
    public void removedObserver(Observer ob) {
    }

    @Override
    public void sendSignal(int X, int Y) {
    }

    @Override
    public void receiveSignal(int X, int Y) {
    }

}
