package com.fatheroctober.drunkard.engine.core.views.panels;

import com.fatheroctober.drunkard.engine.core.common.Observer;
import com.fatheroctober.drunkard.engine.core.controller.AbstractControllerFactory;
import com.fatheroctober.drunkard.engine.core.controller.Commander;
import com.fatheroctober.drunkard.engine.core.controller.gamecontrollerfactory.GameControllerFactory;
import com.fatheroctober.drunkard.engine.core.models.abstraction.AbstractGameFacade;
import com.fatheroctober.drunkard.engine.core.models.World;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.views.render.GameRender;
import com.fatheroctober.drunkard.engine.core.controller.TurnBasedCommander;

import java.awt.*;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class GamePanel extends AbstractGamePanel {

    private AbstractGameFacade gameWorld;
    private AbstractControllerFactory gameControllerFactory;
    private Commander strategyCommander;
    private ArrayList<Observer> observers = new ArrayList<>();

    public GamePanel() {
        super();
        gameWorld = InjectBundle.getInstance(World.class);
        render = InjectBundle.getInstance(GameRender.class);
        strategyCommander = InjectBundle.getInstance(TurnBasedCommander.class);
        try {
            gameWorld.build();
            render.init(gameWorld);
            strategyCommander.init(gameWorld);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        registerObserver(strategyCommander);
        gameControllerFactory = new GameControllerFactory();
        keyController = gameControllerFactory.getKeyListener();
        mouseController = gameControllerFactory.getMouseListener();
        addKeyListener(keyController);
        addMouseListener(mouseController);
    }

    public void update() {
        strategyCommander.update();
    }

    @Override
    public void registerObserver(Observer ob) {
        observers.add(ob);
    }

    @Override
    public void removedObserver(Observer ob) {
        try {
            observers.remove(observers.indexOf(ob));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void sendSignal(int X, int Y) {
        for (Observer observer : observers) {
            observer.receiveSignal(X, Y);
        }
    }

    @Override
    public void receiveSignal(int X, int Y) {
        sendSignal(X, Y); // push to next level
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        render.draw(g2d);
    }


}
