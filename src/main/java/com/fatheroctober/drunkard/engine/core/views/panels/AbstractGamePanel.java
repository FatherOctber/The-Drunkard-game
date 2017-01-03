package com.fatheroctober.drunkard.engine.core.views.panels;

import com.fatheroctober.drunkard.engine.core.views.render.Renderer2D;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

public abstract class AbstractGamePanel extends UpdateablePanel {
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

}
