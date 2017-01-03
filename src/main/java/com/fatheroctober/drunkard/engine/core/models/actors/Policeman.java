package com.fatheroctober.drunkard.engine.core.models.actors;

import com.fatheroctober.drunkard.engine.core.models.Conditions;
import com.fatheroctober.drunkard.engine.core.models.MovingOrientation;
import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.PolicemanInject;
import com.google.inject.Inject;

import java.util.logging.Level;

public class Policeman extends ActorAdapter implements IPoliceman {

    private Conditions.PolicemanCondition status;
    private IDrinker targetDrinker;
    private IPoliceStation policemanStation;
    private MovingOrientation orientation = MovingOrientation.NONE;
    private int catchCounter = 0; // counter of catch Drinkers

    @Inject
    @PolicemanInject
    IMovingStrategy movingStrategy;

    public Policeman() {
        super("Policeman");
        targetDrinker = null;
        status = Conditions.PolicemanCondition.AT_HOME;
    }

    @Override
    public void setAnchorArea(IArea position) {
        super.setAnchorArea(position);
        setXY(position.getXY());
    }

    public void startJob(IDrinker targetDrinker, IPoliceStation comeBackHome) {
        this.targetDrinker = targetDrinker;
        policemanStation = comeBackHome;
        status = Conditions.PolicemanCondition.ON_MISSION;
    }

    public Conditions.PolicemanCondition getStatus() {
        return status;
    }

    @Override
    public void doAction(IMap map) throws DrinkerGameException {
        if (status != Conditions.PolicemanCondition.AT_HOME)
            moveToTarget(map);
    }

    private void moveToTarget(IMap map) {
        try {
            if (activity == Activity.READY_FOR_ACTION) {
                IArea nextPosition = movingStrategy.getAreaToMove(this, map);
                nextPosition = analizeArea(nextPosition);
                atomicalMove(map, nextPosition);
            }
            if (XY.equals(anchor.getXY())) {
                finishAction(map);
            } else {
                walk(anchor);
            }
        } catch (Exception ex) {
            logger.log("Policeman move with exception", Level.WARNING); //GameLog.getInstance().message("Policeman move with exception", Level.WARNING);
            ex.printStackTrace();
        }
    }

    @Override
    public IActor targerActorToCatch() {
        if (status == Conditions.PolicemanCondition.ON_MISSION)
            return targetDrinker;
        if (status == Conditions.PolicemanCondition.GO_HOME)
            return policemanStation;
        else return null; // statu == AT_HOME
    }

    private void atomicalMove(IMap map, IArea nextPosition) throws Exception {
        orientDrinker(nextPosition);
        map.getAreaOnPosition(anchor.getRowCollumn()).setExistedActor(null);
        anchor = nextPosition;
        map.getAreaOnPosition(anchor.getRowCollumn()).setExistedActor(this);
        activity = Activity.IN_ACTION;
    }

    private void orientDrinker(IArea nextPosition) {
        if (nextPosition.getRowCollumn().getLeft() > anchor.getRowCollumn().getLeft())
            orientation = MovingOrientation.EAST;
        else {
            if (nextPosition.getRowCollumn().getLeft() < anchor.getRowCollumn().getLeft())
                orientation = MovingOrientation.WEST;
            else {
                if (nextPosition.getRowCollumn().getRight() < anchor.getRowCollumn().getRight())
                    orientation = MovingOrientation.NORTH;
                else {
                    if (nextPosition.getRowCollumn().getRight() > anchor.getRowCollumn().getRight())
                        orientation = MovingOrientation.SOUTH;
                    else orientation = MovingOrientation.NONE;
                }
            }
        }
    }

    private void finishAction(IMap map) throws Exception {
        orientation = MovingOrientation.NONE;
        if (status == Conditions.PolicemanCondition.AT_HOME)
            map.getAreaOnPosition(anchor.getRowCollumn()).setExistedActor(null);
        activity = Activity.NOT_READY;
        System.out.println("Policeman action is finished. Status: " + status);
    }

    private IArea analizeArea(IArea area) {
        if (area.getExistedActor() instanceof IDrinker) {
            if (((IDrinker) area.getExistedActor()).equals(targetDrinker)) {
                logger.log(targetDrinker.getActorName() + " was catched!", Level.WARNING);
                area.setExistedActor(null);
                targetDrinker = null;
                status = Conditions.PolicemanCondition.GO_HOME;
            }
        }
        if (area.getExistedActor() instanceof IPoliceStation) {
            if (((IPoliceStation) area.getExistedActor()).equals(policemanStation)) {
                area = anchor;
                status = Conditions.PolicemanCondition.AT_HOME;
                logger.log("Policeman return to home", Level.WARNING);
            }
        }
        return area;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Policeman) {
            Policeman bt = (Policeman) obj;
            if (XY.equals(bt.getXY()) && anchor.equals(bt.getAnchorArea())) return true;
            else return false;
        } else return false;
    }

    @Override
    public MovingOrientation getOrientation() {
        return orientation;
    }
}
