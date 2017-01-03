package com.fatheroctober.drunkard.engine.core.models;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Random;
import java.util.logging.Level;

public class DrinkerMovingStrategy implements IMovingStrategy {

    private IDrinker drinker;
    private IMap gMap;

    @Inject
    private IfceLoger logger;

    @Nullable
    @Override
    public IArea getAreaToMove(@Nonnull IActor actor, @Nonnull IMap map) throws DrinkerGameException {
        try {
            Preconditions.checkState(actor instanceof IDrinker, "Actor is not a drinker");
            drinker = (IDrinker) actor;
            gMap = map;
            if (drinker.actorActivity() == IActor.Activity.READY_FOR_ACTION) {
                return findDirection();
            }
        } catch (Exception ex) {
            logger.log("Drinker Moving Strategy on get area to move exception", Level.WARNING);
            throw new DrinkerGameException("Drinker Moving Strategy on get area to move exception");
        }
        return null;
    }

    private IArea findDirection() {
        IArea nextPosition = null;
        for (int i = 0; i < 8 && nextPosition == null; i++) {
            nextPosition = directionPoint(gMap);
            nextPosition = collisionDetect(nextPosition);
        }
        if (nextPosition == null) nextPosition = drinker.getAnchorArea();
        return nextPosition;
    }

    private IArea collisionDetect(IArea positionToNext) {
        if (positionToNext != null) {
            if (!checkCollision(positionToNext))
                positionToNext = drinker.getAnchorArea();
        }
        return positionToNext;
    }

    private boolean checkCollision(IArea area) {
        if (area.getExistedActor() instanceof IBottle) {
            return true;
        }
        if (area.getExistedActor() instanceof IDrinker) {
            if (((IDrinker) area.getExistedActor()).getCondition() == Conditions.DrinkerCondition.SLEEP) {
                return true;
            } else return false;
        }
        if (area.getExistedActor() instanceof IPillar) {
            return true;
        }
        if (area.getExistedActor() instanceof IActor) // if any other actor
        {
            return false;
        }
        return true;
    }


    private IArea directionPoint(IMap map) {
        /**
         * direction
         *    2
         * 1  *  3
         *    4
         */
        int dir = new Random().nextInt(4) + 1;
        switch (dir) {
            case 1: {
                int row = drinker.getAnchorArea().getRowCollumn().getLeft();
                int column = drinker.getAnchorArea().getRowCollumn().getRight() - MOVING_DISTANCE;
                try {
                    IArea direction = map.getAreaOnPosition(row, column);
                    return direction;
                } catch (Exception ex) {
                }

            }
            case 2: {
                int row = drinker.getAnchorArea().getRowCollumn().getLeft() - MOVING_DISTANCE;
                int column = drinker.getAnchorArea().getRowCollumn().getRight();
                try {
                    IArea direction = map.getAreaOnPosition(row, column);
                    return direction;
                } catch (Exception ex) {
                }

            }
            case 3: {
                int row = drinker.getAnchorArea().getRowCollumn().getLeft();
                int column = drinker.getAnchorArea().getRowCollumn().getRight() + MOVING_DISTANCE;
                try {
                    IArea direction = map.getAreaOnPosition(row, column);
                    return direction;
                } catch (Exception ex) {
                }
            }
            case 4: {
                int row = drinker.getAnchorArea().getRowCollumn().getLeft() + MOVING_DISTANCE;
                int column = drinker.getAnchorArea().getRowCollumn().getRight();
                try {
                    IArea direction = map.getAreaOnPosition(row, column);
                    return direction;
                } catch (Exception ex) {
                }
            }
        }
        return null;
    }

}
