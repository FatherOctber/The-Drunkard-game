package com.fatheroctober.drunkard.engine.core.models.actors;

import com.fatheroctober.drunkard.engine.core.models.Conditions;
import com.fatheroctober.drunkard.engine.core.models.MovingOrientation;
import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.DrinkerInject;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.google.common.base.Preconditions;
import com.google.inject.Inject;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.logging.Level;

public class Drinker extends ActorAdapter implements IDrinker {

    private Conditions.DrinkerCondition condition;
    private IBottle bottle; // drinker bottle for a drop
    private IArea nextPosition;
    private MovingOrientation orientation = MovingOrientation.NONE;
    private double bottleDropChance = 10; // chance to drop the bottle
    private static int id = 0;

    @Inject
    @DrinkerInject
    private IMovingStrategy movingStrategy;

    public Drinker() {
        super("Drinker " + id++);
        bottle = InjectBundle.getInstance(Bottle.class);
        condition = Conditions.DrinkerCondition.IN_CONDITION;
    }

    @Override
    public void setAnchorArea(IArea position) {
        super.setAnchorArea(position);
        setXY(position.getXY());
    }

    public Conditions.DrinkerCondition getCondition() {
        return condition;
    }

    @Override
    public void doAction(@Nonnull IMap map) throws DrinkerGameException {
        try {
            if (activity == Activity.READY_FOR_ACTION && condition == Conditions.DrinkerCondition.IN_CONDITION) {
                nextPosition = movingStrategy.getAreaToMove(this, map);
                IArea lastPositionForDrop = anchor;
                analizeArea(nextPosition);
                atomicalMove(map);
                dropBootle(lastPositionForDrop);
            }
            if (XY.equals(anchor.getXY())) {
                finishAction();
            } else {
                if (condition != Conditions.DrinkerCondition.SLEEP && activity != Activity.NOT_READY)
                    walk(nextPosition);
            }
        } catch (Exception ex) {
            logger.log("Drinker do action exception", Level.WARNING);  //GameLog.getInstance().message("Drinker do action exception", Level.WARNING);
            throw new DrinkerGameException("Drinker do action exception");
        }
    }

    private void atomicalMove(IMap map) throws Exception {
        orientDrinker(nextPosition);
        map.getAreaOnPosition(getAnchorArea().getRowCollumn()).setExistedActor(null);
        anchor = nextPosition;
        map.getAreaOnPosition(getAnchorArea().getRowCollumn()).setExistedActor(this);
        activity = Activity.IN_ACTION;
    }

    private void dropBootle(IArea areaToDrop) {
        Preconditions.checkState(bottleDropChance < 100 && bottleDropChance > 0, "The chance to drop bottle is incorrect");
        double total = 100;
        double random = new Random().nextDouble() * total;
        if (random < bottleDropChance) {
            if (bottle != null) {
                if (areaToDrop.getExistedActor() == null) {
                    areaToDrop.setExistedActor(bottle);
                    bottle.setAnchorArea(areaToDrop);
                    bottle = null;
                    logger.log(getActorName() + " drop the bottle", Level.WARNING);
                }
            }
        }
    }


    private void finishAction() throws Exception {
        System.out.println("drinker action is finished");
        if (condition == Conditions.DrinkerCondition.GETTING_SLEEP) {
            condition = Conditions.DrinkerCondition.SLEEP;
            logger.log(getActorName() + " fall down asleep", Level.WARNING);
        }
        orientation = MovingOrientation.NONE;
        activity = Activity.NOT_READY;
    }

    private void analizeArea(IArea area) {
        if (area.getExistedActor() instanceof IBottle) {
            condition = Conditions.DrinkerCondition.GETTING_SLEEP;
        }
        if (area.getExistedActor() instanceof IDrinker) {
            if (((IDrinker) area.getExistedActor()).getCondition() == Conditions.DrinkerCondition.SLEEP) {
                condition = Conditions.DrinkerCondition.GETTING_SLEEP;
            }
        }
        if (area.getExistedActor() instanceof IPillar) // todo check for SLEEP
        {
            condition = Conditions.DrinkerCondition.SLEEP;
            nextPosition = anchor;
        }
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


    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Drinker) {
            Drinker dr = (Drinker) obj;
            if (XY.equals(dr.getXY()) && anchor.equals(dr.getAnchorArea())) return true;
            else return false;
        } else return false;
    }

    @Override
    public MovingOrientation getOrientation() {
        return orientation;
    }
}
