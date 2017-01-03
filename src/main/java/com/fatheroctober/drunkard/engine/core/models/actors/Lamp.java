package com.fatheroctober.drunkard.engine.core.models.actors;

import com.fatheroctober.drunkard.engine.core.models.abstraction.ActorAdapter;
import com.fatheroctober.drunkard.engine.core.models.abstraction.ILamp;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMap;
import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class Lamp extends ActorAdapter implements ILamp {

    private int lightingRadius = 2; // in squares

    public Lamp() {
        super("Lamp");
    }

    private void lighten(@Nonnull IMap map) {
        Preconditions.checkState(anchor != null, "No any lamp");
        int basicRow = anchor.getRowCollumn().getLeft();
        int basicColumn = anchor.getRowCollumn().getRight();
        for (int i = 1; i <= lightingRadius; i++) {
            setLight(map, basicRow + i, basicColumn);
            setLight(map, basicRow - i, basicColumn);
            setLight(map, basicRow, basicColumn + i);
            setLight(map, basicRow, basicColumn - i);
            if (i < lightingRadius) {
                setLight(map, basicRow - i, basicColumn - i);
                setLight(map, basicRow + i, basicColumn + i);
                setLight(map, basicRow - i, basicColumn + i);
                setLight(map, basicRow + i, basicColumn - i);
            }
        }
    }

    private void setLight(IMap map, int row, int column) {
        try {
            map.getAreaOnPosition(row, column).illuminatedOn();
        } catch (NullPointerException ex) {
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void doAction(IMap map) throws DrinkerGameException {
        if (activity == Activity.READY_FOR_ACTION)
            try {
                lighten(map);
            } catch (Exception ex) {
                logger.log("Lamp do action exception", Level.WARNING); //GameLog.getInstance().message("Lamp do action exception", Level.WARNING);
                throw new DrinkerGameException("Lamp do action exception");
            }
        activity = Activity.NOT_READY;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Lamp) {
            Lamp lp = (Lamp) obj;
            if (XY.equals(lp.getXY()) && anchor.equals(lp.getAnchorArea())) return true;
            else return false;
        } else return false;
    }

}
