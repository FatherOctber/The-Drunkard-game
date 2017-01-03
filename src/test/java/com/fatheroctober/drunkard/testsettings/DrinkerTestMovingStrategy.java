package com.fatheroctober.drunkard.testsettings;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IActor;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IArea;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMap;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMovingStrategy;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class DrinkerTestMovingStrategy implements IMovingStrategy {

    @Nullable
    @Override
    public IArea getAreaToMove(@Nonnull IActor actor, @Nonnull IMap map) throws DrinkerGameException {
        try {
            int nextRow = actor.getAnchorArea().getRowCollumn().getLeft() - 1;
            int nextColumn = actor.getAnchorArea().getRowCollumn().getRight();
            if (map.getAreaOnPosition(nextRow, nextColumn).getExistedActor() == null)
                return map.getAreaOnPosition(nextRow, nextColumn);
            else return actor.getAnchorArea();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
