package com.fatheroctober.drunkard.engine.core.views.render;

import com.fatheroctober.drunkard.engine.core.models.abstraction.AbstractGameFacade;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IActor;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IArea;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMap;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.Inject;

import java.awt.*;
import java.util.logging.Level;

public class GameRender implements Renderer2D {
    private AbstractGameFacade gamingWorld;
    private SpriteProvider spriteProvider;

    @Inject
    private IfceLoger logger;

    @Override
    public void init(Object gameFacade) throws Exception {
        if (gameFacade instanceof AbstractGameFacade) {
            gamingWorld = (AbstractGameFacade) gameFacade;
            spriteProvider = InjectBundle.getInstance(SpriteProvider.class);
            spriteProvider.init();
        } else
            throw new Exception("Incorrect parameters for initialization game render. Expected: " + AbstractGameFacade.class + "; Real: " + gameFacade.getClass());
    }

    @Override
    public void draw(Graphics2D g2d) {
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawElements(g2d);
    }

    private void drawElements(Graphics2D g2d) {
        drawMap(g2d, gamingWorld.getMap());
        drawActors(g2d);
    }

    private void drawMap(Graphics2D g2d, IMap map) {
        map.getAreas().forEach(sq -> {
                    try {
                        g2d.drawImage(spriteProvider.spriteOf(sq), sq.getXY().getLeft(), sq.getXY().getRight(), sq.getWidth(), sq.getHeight(), null);
                        if (sq.isIlluminated()) {
                            Color gColor = g2d.getColor();
                            g2d.setColor(new Color(232, 226, 29, 69));
                            g2d.fillOval(sq.getXY().getLeft(), sq.getXY().getRight(), sq.getWidth(), sq.getHeight());
                            g2d.setColor(gColor);
                        }
                        drawGridSquare(g2d, sq);
                    } catch (Exception ex) {
                        logger.log("Error due draw map", Level.WARNING); //GameLog.getInstance().message("Error due draw map", Level.WARNING);
                        ex.printStackTrace();
                    }
                }
        );
    }

    private void drawActors(Graphics2D g2d) {
        gamingWorld.getAllActors().forEach(actor -> {
            try {
                drawActor(g2d, actor);
            } catch (Exception ex) {
                logger.log("Error due draw actors exception", Level.WARNING); //GameLog.getInstance().message("Error due draw actors exception", Level.WARNING);
                ex.printStackTrace();
            }
        });
    }

    private void drawActor(Graphics2D g2d, IActor actor) throws Exception {
        if (actor != null)
            g2d.drawImage(spriteProvider.spriteOf(actor), actor.getXY().getLeft(), actor.getXY().getRight(), actor.getWidth(), actor.getHeight(), null);
    }


    private void drawGridSquare(Graphics2D g2d, IArea sq) {
        Color gColor = g2d.getColor();
        g2d.setColor(Color.BLUE);
        g2d.drawLine(sq.getXY().getLeft(), sq.getXY().getRight(), sq.getXY().getLeft() + sq.getWidth(), sq.getXY().getRight());
        g2d.drawLine(sq.getXY().getLeft() + sq.getWidth(), sq.getXY().getRight(), sq.getXY().getLeft() + sq.getWidth(), sq.getXY().getRight() + sq.getHeight());
        g2d.drawLine(sq.getXY().getLeft() + sq.getWidth(), sq.getXY().getRight() + sq.getHeight(), sq.getXY().getLeft(), sq.getXY().getRight() + sq.getHeight());
        g2d.drawLine(sq.getXY().getLeft(), sq.getXY().getRight() + sq.getHeight(), sq.getXY().getLeft(), sq.getXY().getRight());
        g2d.setColor(gColor);

    }
}
