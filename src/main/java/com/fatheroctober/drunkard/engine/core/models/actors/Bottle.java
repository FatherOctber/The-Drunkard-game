package com.fatheroctober.drunkard.engine.core.models.actors;

import com.fatheroctober.drunkard.engine.core.models.abstraction.ActorAdapter;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IArea;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IBottle;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMap;
import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;

public class Bottle extends ActorAdapter implements IBottle {

    public Bottle() {
        super("Bottle");
    }

    @Override
    public void doAction(IMap map) throws DrinkerGameException {
        activity = Activity.NOT_READY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Bottle) {
            Bottle bt = (Bottle) obj;
            if (XY.equals(bt.getXY()) && anchor.equals(bt.getAnchorArea())) return true;
            else return false;
        } else return false;
    }

    @Override
    public void setAnchorArea(IArea position) {
        super.setAnchorArea(position);
        setXY(position.getXY());
    }

}
