package com.fatheroctober.drunkard.engine.core.views.render;

import java.awt.*;

public interface Renderer2D {

    /**
     * initialize render
     *
     * @param objToInit - object which is needed to initialize
     */
    void init(Object objToInit) throws Exception;

    /**
     * to draw game screen
     *
     * @param g2d - grapgic contecst
     */
    void draw(Graphics2D g2d);
}
