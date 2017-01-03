package com.fatheroctober.drunkard.engine.core.models.actors;

import com.fatheroctober.drunkard.engine.core.models.abstraction.ActorAdapter;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMap;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IPillar;
import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;

public class Pillar extends ActorAdapter implements IPillar {

    public Pillar() {
        super("Pillar");
    }

    @Override
    public void doAction(IMap map) throws DrinkerGameException {
        activity = Activity.NOT_READY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pillar) {
            Pillar bt = (Pillar) obj;
            if (XY.equals(bt.getXY()) && anchor.equals(bt.getAnchorArea())) return true;
            else return false;
        } else return false;
    }

}
