package com.fatheroctober.drunkard.engine.core.controller;

import java.awt.event.KeyListener;
import java.awt.event.MouseListener;

/**
 * Abstract Factory Pattern interface
 */
public interface AbstractControllerFactory {
    /**
     * create KeyListener instance
     *
     * @return instance
     */
    KeyListener getKeyListener();

    /**
     * create MouseListener instance
     *
     * @return instance
     */
    MouseListener getMouseListener();

}
