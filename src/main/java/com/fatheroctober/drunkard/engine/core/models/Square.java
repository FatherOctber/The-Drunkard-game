package com.fatheroctober.drunkard.engine.core.models;

import com.fatheroctober.drunkard.engine.core.models.abstraction.AbstractGameModel;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IActor;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IArea;
import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;

import javax.annotation.Nullable;

public class Square extends AbstractGameModel implements IArea {

    private boolean select = false;
    private IActor existedActor;
    private boolean illuminated = false;

    public Square() {
        super();
        this.modelName = "Square area";
    }

    public Square(int row, int column) {
        setRowCollumn(row, column);
    }

    public IActor getExistedActor() {
        return existedActor;
    }

    public void setRowCollumn(int row, int column) {
        this.XY = new Pair<Integer, Integer>((row * HEIGHT), (column * WIDTH));
    }

    public void setRowCollumn(Pair<Integer, Integer> rowCollumn) {
        this.XY = new Pair<Integer, Integer>((rowCollumn.getLeft() * HEIGHT), (rowCollumn.getRight() * WIDTH));
    }

    public Pair<Integer, Integer> getRowCollumn() {
        return new Pair<Integer, Integer>(this.XY.getLeft() / HEIGHT, this.XY.getRight() / WIDTH);
    }

    public void setExistedActor(@Nullable IActor model) {
        existedActor = model;
    }

    public boolean isSelected() {
        return select;
    }

    public boolean isIlluminated() {
        return illuminated;
    }

    public void illuminatedOn() {
        illuminated = true;
    }

    public void illuminatedOff() {
        illuminated = true;
    }

    public void selectOn() {
        select = true;
    }

    public void selectOff() {
        select = false;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Square) {
            return (((Square) obj).XY.equals(this.XY)) ? true : false;
        } else return false;
    }


}
