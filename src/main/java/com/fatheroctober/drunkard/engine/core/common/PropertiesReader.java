package com.fatheroctober.drunkard.engine.core.common;

import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertiesReader {

    protected InputStream fis;
    protected Properties property = new Properties();
    protected String propFile = "gameconfig.properties";

    public int gamePanelWidth() {
        return Integer.parseInt(loadProp("game.panel.width"));
    }

    public int gamePanelHeight() {
        return Integer.parseInt(loadProp("game.panel.height"));
    }

    public int gameFieldSize() {
        return Integer.parseInt(loadProp("game.size"));
    }

    public int toolBarPanelWidth() {
        return Integer.parseInt(loadProp("toolbar.panel.width"));
    }

    public int toolBarPanelHeight() {
        return Integer.parseInt(loadProp("toolbar.panel.height"));
    }

    public int roundFinish() {
        return Integer.parseInt(loadProp("game.roundfinish"));
    }

    public String spritesLocation() {
        return loadProp("game.spritesheet");
    }

    public String backgroundTexture() {
        return loadProp("toolbar.texture");
    }


    public Pair<Integer, Integer> drinkerRespawnPoint() {
        return complexIntegerProperty("game.tavern.respawn.place");
    }

    public Pair<Integer, Integer> pillarPoint() {
        return complexIntegerProperty("game.pillar.place");
    }

    public Pair<Integer, Integer> lampPoint() {
        return complexIntegerProperty("game.lamp.place");
    }

    public Pair<Integer, Integer> tavernPoint() {
        return complexIntegerProperty("game.tavern.place");
    }

    public Pair<Integer, Integer> policePoint() {
        return complexIntegerProperty("game.policestation.place");
    }

    public Pair<Integer, Integer> policeRespawnPoint() {
        return complexIntegerProperty("game.policestation.respawn.place");
    }

    protected Pair<Integer, Integer> complexIntegerProperty(String propname) {
        String complexProperty = loadProp(propname);
        Pair<Integer, Integer> complexIntProp = null;
        try {
            complexIntProp = new Pair<Integer, Integer>(Integer.parseInt(complecsProp(complexProperty).getLeft()),
                    Integer.parseInt(complecsProp(complexProperty).getRight()));
        } catch (Exception ex) {
            System.out.println("Complex property exception");
            ex.printStackTrace();
        }
        return complexIntProp;
    }


    protected String loadProp(String propName) {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            fis = loader.getResourceAsStream(propFile);
            property.load(fis);
            return property.getProperty(propName);
        } catch (IOException e) {
            System.err.println("load prop error");
            e.printStackTrace();
            return null;
        }
    }

    protected Pair<String, String> complecsProp(String propString) throws Exception {
        String[] vars = propString.split(";");
        return new Pair<String, String>(vars[0], vars[1]);
    }

}
