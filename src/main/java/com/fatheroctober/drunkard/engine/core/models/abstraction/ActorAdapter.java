package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ActorAdapter extends AbstractGameModel implements IActor {

    protected boolean choosen = false;
    protected IArea anchor;
    protected Activity activity = Activity.READY_FOR_ACTION;
    protected int moveDistance = 1; // distance in square

    @Override
    public void doAction(@Nullable IMap map) throws DrinkerGameException {
        /**
         *  no action by default
         */
    }

    @Override
    public IArea getAnchorArea() {
        return anchor;
    }

    @Override
    public void setAnchorArea(IArea position) {
        anchor = position;
    }

    public ActorAdapter() {
        modelName = "Abstract actor";
    }

    public ActorAdapter(String actor) {
        modelName = actor;
    }

    public boolean isChoosen() {
        return choosen;
    }

    public Activity actorActivity() {
        return activity;
    }

    protected void walk(@Nonnull IArea toArea) {
        if (toArea.getXY().getLeft() > this.XY.getLeft())
            upX(1);
        if (toArea.getXY().getLeft() < this.XY.getLeft())
            downX(1);
        if (toArea.getXY().getRight() > this.XY.getRight())
            upY(1);
        if (toArea.getXY().getRight() < this.XY.getRight())
            downY(1);
    }

    protected void upX(int inch) {
        XY = new Pair<Integer, Integer>(XY.getLeft() + inch, XY.getRight());
    }

    protected void downX(int inch) {
        XY = new Pair<Integer, Integer>(XY.getLeft() - inch, XY.getRight());
    }

    protected void upY(int inch) {
        XY = new Pair<Integer, Integer>(XY.getLeft(), XY.getRight() + inch);
    }

    protected void downY(int inch) {
        XY = new Pair<Integer, Integer>(XY.getLeft(), XY.getRight() - inch);
    }

    public void realiseForAction() {
        activity = Activity.READY_FOR_ACTION;
    }

    @Override
    public String getActorName() {
        return getModelName();
    }
}
