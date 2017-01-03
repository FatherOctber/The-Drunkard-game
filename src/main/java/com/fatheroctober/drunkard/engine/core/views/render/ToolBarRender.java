package com.fatheroctober.drunkard.engine.core.views.render;

import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.views.panels.AbstractToolBarPanel;
import com.google.inject.Inject;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class ToolBarRender implements Renderer2D {

    @Inject
    PropertiesReader propertiesReader;

    private AbstractToolBarPanel panel;
    private BufferedImage texture;


    @Override
    public void init(Object toolBarPanel) throws Exception {
        if (toolBarPanel instanceof AbstractToolBarPanel) {
            panel = (AbstractToolBarPanel) toolBarPanel;
            initBackgroundTexture();

        } else
            throw new Exception("Incorrect parameters for initialization tool bar render. Expected: " + AbstractToolBarPanel.class + "; Real: " + toolBarPanel.getClass());
    }

    private void initBackgroundTexture() throws Exception {
        texture = ImageIO.read(this.getClass().getResource(propertiesReader.backgroundTexture()));
    }


    @Override
    public void draw(Graphics2D g2d) {
        g2d.drawImage(texture, 0, 0, panel.getWidth(), panel.getHeight(), null);

    }
}
