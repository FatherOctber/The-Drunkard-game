package com.fatheroctober.drunkard.engine.core;

import com.fatheroctober.drunkard.engine.core.common.GlobalTimer;
import com.fatheroctober.drunkard.engine.core.common.Observer;
import com.fatheroctober.drunkard.engine.core.common.Observerable;
import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;
import com.fatheroctober.drunkard.engine.core.views.panels.AbstractGamePanel;
import com.fatheroctober.drunkard.engine.core.views.panels.AbstractPanelFactory;
import com.fatheroctober.drunkard.engine.core.views.panels.DrinkingPanelFactory;
import com.fatheroctober.drunkard.engine.core.views.panels.UpdateablePanel;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.logging.Level;


public class Drunkard implements Observerable {

    @Inject
    IfceLoger logger;

    @Inject
    PropertiesReader propertiesReader;

    private JFrame mainFrame = new JFrame("The Drunkard Game");
    private ArrayList<UpdateablePanel> updPanels = new ArrayList<UpdateablePanel>();
    private int FRAME_X = mainFrame.getX();
    private int FRAME_Y = mainFrame.getY();


    private Pair<Integer, Integer> GAME_PANEL_SIZE;
    private Pair<Integer, Integer> BAR_PANEL_SIZE;

    private void init() throws Exception {
        GAME_PANEL_SIZE = new Pair<Integer, Integer>(propertiesReader.gamePanelWidth(), propertiesReader.gamePanelHeight());
        BAR_PANEL_SIZE = new Pair<Integer, Integer>(propertiesReader.toolBarPanelWidth(), propertiesReader.toolBarPanelHeight());

        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));

        AbstractPanelFactory panelFactory = new DrinkingPanelFactory();

        UpdateablePanel gameP = panelFactory.getGamePanel();
        UpdateablePanel toolP = panelFactory.getToolBarPanel();

        updPanels.add(gameP);
        updPanels.add(toolP);

        updPanels.forEach(pn -> {
            if (pn instanceof AbstractGamePanel) {
                Preconditions.checkState((GAME_PANEL_SIZE.getLeft().equals(GAME_PANEL_SIZE.getRight())), "Game panel sizes are not equals");
                pn.setPreferredSize(new Dimension(GAME_PANEL_SIZE.getLeft(), GAME_PANEL_SIZE.getRight()));
            } else {
                Preconditions.checkState((BAR_PANEL_SIZE.getLeft() > BAR_PANEL_SIZE.getRight()), "Game panel sizes are not equals");
                pn.setPreferredSize(new Dimension(BAR_PANEL_SIZE.getLeft(), BAR_PANEL_SIZE.getRight()));
            }
            container.add(pn);
        });
        mainFrame.add(container);
        mainFrame.addKeyListener(toolP.getKeyListeners()[0]);
        mainFrame.addMouseListener(gameP.getMouseListeners()[0]);
        mainFrame.setFocusable(true);
        mainFrame.setSize(GAME_PANEL_SIZE.getLeft(), GAME_PANEL_SIZE.getRight() + BAR_PANEL_SIZE.getRight() + 33);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);
    }

    public void start() {
        try {
            init();
        } catch (Exception ex) {
            logger.log("Game init exception", Level.WARNING);
            return;
        }
        mainFrame.setVisible(true);
        logger.log("New game started", Level.SEVERE);

        while (true) { // game loop
            try {
                sendSignal(mainFrame.getX(), mainFrame.getY());
                updPanels.forEach(pn -> {
                    pn.update();
                    pn.repaint();
                });
                Thread.sleep(GlobalTimer.getInstance().getFrameRate());
            } catch (Exception ex) {
                System.out.println("Drunkard:: Main loop exception");
                ex.printStackTrace();
                break;
            }
        }
        logger.log("Game over", Level.SEVERE);
    }

    @Override
    public void registerObserver(Observer ob) {
    }

    @Override
    public void removedObserver(Observer ob) {
    }

    @Override
    public void sendSignal(int X, int Y) {
        updPanels.forEach(pn -> {
            pn.receiveSignal(X, Y);
        });
    }
}

