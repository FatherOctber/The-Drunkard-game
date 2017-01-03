package com.fatheroctober.drunkard.engine.core.models.actors;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.common.GlobalTimer;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;

import javax.annotation.Nonnull;
import java.util.logging.Level;

public class Tavern extends ActorAdapter implements ITavern {

    public Tavern() {
        modelName = "Tavern";
    }

    @Override
    public void doAction(@Nonnull IMap map) throws DrinkerGameException {
        if (activity == Activity.READY_FOR_ACTION) {
            if (GlobalTimer.getInstance().getTacts() % 20 == 0) {
                try {
                    IArea respa = map.getAreaOnPosition(propertiesReader.drinkerRespawnPoint());
                    respawnDrinker(respa);
                    Thread.currentThread().sleep(GlobalTimer.getInstance().getDelayTime());
                    logger.log("Tavern respawn new drinker", Level.WARNING);
                } catch (DrinkerGameException ex) {
                    logger.log(ex.getMessage(), Level.WARNING);
                } catch (Exception ex) {
                    logger.log("Tavern do action exception", Level.WARNING);
                    throw new DrinkerGameException("Tavern do action exception");
                }
            }
            activity = Activity.NOT_READY;
        }
    }

    private void respawnDrinker(IArea respanwPoint) throws DrinkerGameException {
        if (respanwPoint.getExistedActor() != null)
            throw new DrinkerGameException("Tavern Respawn point busy"); //check collisions
        IDrinker drinker = InjectBundle.getInstance(Drinker.class);
        drinker.setAnchorArea(respanwPoint);
        respanwPoint.setExistedActor(drinker);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Tavern) {
            Tavern bt = (Tavern) obj;
            if (XY.equals(bt.getXY()) && anchor.equals(bt.getAnchorArea())) return true;
            else return false;
        } else return false;
    }


}
