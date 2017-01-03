package com.fatheroctober.drunkard.engine.core.models;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.logging.Level;

public class PolicemanMovingStrategy implements IMovingStrategy {

    private IPoliceman policeman;
    private IMap gMap;

    @Inject
    private IfceLoger logger;

    @Nullable
    @Override
    public IArea getAreaToMove(@Nonnull IActor actor, @Nonnull IMap map) throws DrinkerGameException {
        try {
            Preconditions.checkState(actor instanceof IPoliceman, "Actor is not a policeman");
            policeman = (IPoliceman) actor;
            gMap = map;
            if (policeman.actorActivity() == IActor.Activity.READY_FOR_ACTION) {
                return findDirection();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.log("Policeman Moving Strategy on get area to move exception", Level.WARNING); //GameLog.getInstance().message("Policeman Moving Strategy on get area to move exception", Level.WARNING);
            throw new DrinkerGameException("Policeman Moving Strategy on get area to move exception");
        }
        return null;
    }

    private IArea findDirection() throws Exception {
        int targetColumn = policeman.targerActorToCatch().getAnchorArea().getRowCollumn().getRight();
        int targetRow = policeman.targerActorToCatch().getAnchorArea().getRowCollumn().getLeft();
        int myColumn = policeman.getAnchorArea().getRowCollumn().getRight();
        int myRow = policeman.getAnchorArea().getRowCollumn().getLeft();
        IArea NEXT_POSITION = null;
        try {
            if (Math.abs(targetColumn - myColumn) > Math.abs(targetRow - myRow)) {
                int nextRow = myRow;
                int nextColumn = myColumn + MOVING_DISTANCE * ((targetColumn - myColumn) / Math.abs(targetColumn - myColumn));
                NEXT_POSITION = gMap.getAreaOnPosition(nextRow, nextColumn);
                if (!checkCollision(NEXT_POSITION)) {
                    nextColumn = myColumn;
                    if ((targetRow - myRow) != 0)
                        nextRow = myRow + MOVING_DISTANCE * ((targetRow - myRow) / Math.abs(targetRow - myRow));
                    else nextRow = myRow + MOVING_DISTANCE;
                    NEXT_POSITION = gMap.getAreaOnPosition(nextRow, nextColumn);
                }
            } else {
                if (Math.abs(targetRow - myRow) > Math.abs(targetColumn - myColumn)) {
                    int nextColumn = myColumn;
                    int nextRow = myRow + MOVING_DISTANCE * ((targetRow - myRow) / Math.abs(targetRow - myRow));
                    NEXT_POSITION = gMap.getAreaOnPosition(nextRow, nextColumn);
                    if (!checkCollision(NEXT_POSITION)) {
                        nextRow = myRow;
                        if ((targetColumn - myColumn) != 0)
                            nextColumn = myColumn + MOVING_DISTANCE * ((targetColumn - myColumn) / Math.abs(targetColumn - myColumn));
                        else nextColumn = myColumn + MOVING_DISTANCE;
                        NEXT_POSITION = gMap.getAreaOnPosition(nextRow, nextColumn);
                    }
                } else {
                    int nextColumn = myColumn;
                    int nextRow = myRow + MOVING_DISTANCE * ((targetRow - myRow) / Math.abs(targetRow - myRow));
                    NEXT_POSITION = gMap.getAreaOnPosition(nextRow, nextColumn);
                    if (!checkCollision(NEXT_POSITION)) {
                        nextRow = myRow;
                        if ((targetColumn - myColumn) != 0)
                            nextColumn = myColumn + MOVING_DISTANCE * ((targetColumn - myColumn) / Math.abs(targetColumn - myColumn));
                        else nextColumn = myColumn + MOVING_DISTANCE;
                        NEXT_POSITION = gMap.getAreaOnPosition(nextRow, nextColumn);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        if (!checkCollision(NEXT_POSITION)) return policeman.getAnchorArea(); // next position don`t change
        else return NEXT_POSITION;
    }

    private boolean checkCollision(IArea area) {
        if (area.getExistedActor() instanceof IActor) // if any other actor
        {
            if (area.getExistedActor().equals(policeman.targerActorToCatch()))
                return true;
            else
                return false;
        } else return true;
    }
}
