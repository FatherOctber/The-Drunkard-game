package com.fatheroctober.drunkard.engine.core.models;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.fatheroctober.drunkard.engine.core.models.actors.Pillar;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;
import com.fatheroctober.drunkard.engine.core.models.actors.Lamp;
import com.fatheroctober.drunkard.engine.core.models.actors.PoliceStation;
import com.fatheroctober.drunkard.engine.core.models.actors.Tavern;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.logging.Level;

/**
 * Game Facade implementation
 */
public class World extends AbstractGameFacade {

    @Inject
    PropertiesReader propertiesReader;

    private IMap map;
    private ITavern tavern;
    private IPoliceStation policeStation;
    private IPillar pillar;
    private ILamp lamp;

    public World() {
        map = InjectBundle.getInstance(GamingMap.class);  //todo nullable annotation
        tavern = InjectBundle.getInstance(Tavern.class);
        policeStation = InjectBundle.getInstance(PoliceStation.class);
        pillar = InjectBundle.getInstance(Pillar.class);
        lamp = InjectBundle.getInstance(Lamp.class);
    }

    @Override
    public void build() throws DrinkerGameException {
        try {
            map.build();
            buildingWith(propertiesReader.pillarPoint(), pillar);
            buildingWith(propertiesReader.policePoint(), policeStation);
            buildingWith(propertiesReader.tavernPoint(), tavern);
            buildingWith(propertiesReader.lampPoint(), lamp);
        } catch (Exception ex) {
            logger.log("World build exception", Level.WARNING); //GameLog.getInstance().message("World build exception", Level.WARNING);
            throw new DrinkerGameException("World build exception");
        }
    }

    private void buildingWith(int row, int column, IActor model) throws Exception {
        if (map.getAreaOnPosition(row, column).getExistedActor() == null) {
            model.setXY(map.getAreaOnPosition(row, column).getXY());
            map.getAreaOnPosition(row, column).setExistedActor(model);
            model.setAnchorArea(map.getAreaOnPosition(row, column));
        }
    }

    private void buildingWith(Pair<Integer, Integer> rowColumn, IActor model) throws Exception {
        if (map.getAreaOnPosition(rowColumn).getExistedActor() == null) {
            model.setXY(map.getAreaOnPosition(rowColumn).getXY());
            map.getAreaOnPosition(rowColumn).setExistedActor(model);
            model.setAnchorArea(map.getAreaOnPosition(rowColumn));
        }
    }

    @Override
    public IMap getMap() {
        return map;
    }

    @Override
    public ITavern getTavern() {
        return tavern;
    }

    @Override
    public IPoliceStation getPoliceStation() {
        return policeStation;
    }

    @Override
    public IPillar getPillar() {
        return pillar;
    }

    @Override
    public ILamp getLamp() {
        return lamp;
    }

    @Override
    public Iterable<IActor> getAllActors() {

        ArrayList<IActor> actors = new ArrayList<>();
        try {
            map.getAreas().forEach(area -> {
                if (area.getExistedActor() != null)
                    actors.add(area.getExistedActor());
            });
        } catch (Exception ex) {
            logger.log("Get all actors exception", Level.WARNING);
            ex.printStackTrace();
        }
        return actors;
    }

}
