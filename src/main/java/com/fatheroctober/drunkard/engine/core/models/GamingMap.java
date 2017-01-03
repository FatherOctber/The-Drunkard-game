package com.fatheroctober.drunkard.engine.core.models;


import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IArea;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IMap;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;
import com.google.inject.Inject;

import javax.annotation.CheckForNull;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GamingMap implements IMap {

    @Inject
    PropertiesReader propertiesReader;

    /**
     * gaming map
     * key - Pair with left value as ROW and right value as COLUMN
     * value - Square
     */
    private Map<Pair<Integer, Integer>, IArea> gameMap = new HashMap<>();

    /**
     * gaming map size
     * width=height=MAP_SIZE
     */
    private int MAP_SIZE = 15; // by default

    @Override
    public void build() {
        try {
            MAP_SIZE = propertiesReader.gameFieldSize();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                IArea area = InjectBundle.getInstance(Square.class);
                area.setRowCollumn(i, j);
                gameMap.put(new Pair<Integer, Integer>(i, j), area);
            }
        }
    }

    @Override
    public int getSize() {
        return MAP_SIZE;
    }

    @Override
    public IArea getAreaOnPosition(int row, int column) throws Exception {
        return gameMap.get(new Pair<Integer, Integer>(row, column));
    }

    @Override
    public IArea getAreaOnPosition(@CheckForNull Pair<Integer, Integer> rowColumn) throws Exception {
        return gameMap.get(rowColumn);
    }

    @Override
    public Iterable<IArea> getAreas() {
        return new ArrayList<IArea>(gameMap.values());
    }


}
