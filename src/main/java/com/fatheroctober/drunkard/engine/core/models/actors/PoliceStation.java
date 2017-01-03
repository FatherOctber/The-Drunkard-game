package com.fatheroctober.drunkard.engine.core.models.actors;

import com.fatheroctober.drunkard.engine.core.models.Conditions;
import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;

import java.util.logging.Level;

public class PoliceStation extends ActorAdapter implements IPoliceStation {

    private IPoliceman officer = InjectBundle.getInstance(Policeman.class);

    public PoliceStation() {
        super("Police");
    }

    public IPoliceman getOfficer() {
        return officer;
    }

    @Override
    public void doAction(IMap map) throws DrinkerGameException {
        if (activity == Activity.READY_FOR_ACTION) {
            map.getAreas().forEach(area -> {
                try {
                    if (area.isIlluminated()) {
                        if (area.getExistedActor() instanceof IDrinker)
                            respawnOfficer(map, (IDrinker) area.getExistedActor());
                    }
                } catch (Exception ex) {
                    logger.log("Police Station do action exception", Level.WARNING);
                    ex.printStackTrace();
                }
            });
        }
        activity = Activity.NOT_READY;
    }

    private void respawnOfficer(IMap map, IDrinker targetDrinker) throws Exception {
        if (officer.getStatus() == Conditions.PolicemanCondition.AT_HOME) {
            IArea respa = map.getAreaOnPosition(propertiesReader.policeRespawnPoint());
            if (respa.getExistedActor() != null) throw new DrinkerGameException("Police respawn point a busy");
            officer.setAnchorArea(respa);
            officer.startJob(targetDrinker, this);
            respa.setExistedActor(officer);
        } else logger.log("Police Station: officer on job", Level.WARNING);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof PoliceStation) {
            PoliceStation bt = (PoliceStation) obj;
            if (XY.equals(bt.getXY()) && anchor.equals(bt.getAnchorArea())) return true;
            else return false;
        } else return false;
    }
}
