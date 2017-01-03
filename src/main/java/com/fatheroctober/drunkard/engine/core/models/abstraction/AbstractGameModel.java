package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;
import com.google.inject.Inject;

import javax.annotation.Nonnull;

public abstract class AbstractGameModel {

    protected String modelName = "Abstract model";
    protected int WIDTH = 1;
    protected int HEIGHT = 1;
    protected Pair<Integer, Integer> XY;

    protected PropertiesReader propertiesReader;

    @Inject
    protected IfceLoger logger;

    public AbstractGameModel() {
        propertiesReader = InjectBundle.getInstance(PropertiesReader.class);
        WIDTH = propertiesReader.gamePanelWidth() / propertiesReader.gameFieldSize();
        HEIGHT = propertiesReader.gamePanelHeight() / propertiesReader.gameFieldSize();
        XY = new Pair<Integer, Integer>(0, 0);
    }

    public String getModelName() {
        return modelName;
    }

    public int getWidth() {
        return WIDTH;
    }

    public int getHeight() {
        return HEIGHT;
    }

    public void setXY(int X, int Y) {
        XY = new Pair<Integer, Integer>(X, Y);
    }

    public void setXY(@Nonnull Pair<Integer, Integer> XY) {
        this.XY = XY;
    }

    public Pair<Integer, Integer> getXY() {
        return XY;
    }


}
