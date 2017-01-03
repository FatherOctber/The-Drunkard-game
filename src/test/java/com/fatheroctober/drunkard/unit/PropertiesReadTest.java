package com.fatheroctober.drunkard.unit;

import com.fatheroctober.drunkard.engine.core.common.PropertiesReader;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.RealGameModule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PropertiesReadTest {

    PropertiesReader propertiesReader;

    @Before
    public void before() {
        InjectBundle.configure(new RealGameModule());
        propertiesReader = InjectBundle.getInstance(PropertiesReader.class);
    }

    @Test
    public void FullPropertiesReaderTest() {
        propertiesReader.roundFinish();
        Assert.assertNotEquals(propertiesReader.backgroundTexture(), null);
        propertiesReader.gameFieldSize();
        propertiesReader.gamePanelHeight();
        propertiesReader.gamePanelWidth();
        Assert.assertNotEquals(propertiesReader.lampPoint(), null);
        Assert.assertNotEquals(propertiesReader.tavernPoint(), null);
        Assert.assertNotEquals(propertiesReader.pillarPoint(), null);
        Assert.assertNotEquals(propertiesReader.policePoint(), null);
        Assert.assertNotEquals(propertiesReader.drinkerRespawnPoint(), null);
        Assert.assertNotEquals(propertiesReader.policeRespawnPoint(), null);
        Assert.assertNotEquals(propertiesReader.spritesLocation(), null);
    }
}
