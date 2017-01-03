package com.fatheroctober.drunkard.unit;

import com.fatheroctober.drunkard.engine.core.controller.Command;
import com.fatheroctober.drunkard.engine.core.controller.ICommand;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IActor;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IArea;
import com.fatheroctober.drunkard.engine.core.models.abstraction.IDrinker;
import com.fatheroctober.drunkard.engine.core.models.actors.Drinker;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.fatheroctober.drunkard.testsettings.TestModule;

public class DrinkerTests extends AbstractUnitTest {

    IDrinker dr;

    @Before
    public void before() throws Exception {
        initialize(new TestModule());
        dr = InjectBundle.getInstance(Drinker.class);
    }

    @Test
    public void drinkerActionTest() throws Exception {
        int deadLockCounter = 1000;
        dr.setAnchorArea(world.getMap().getAreaOnPosition(5, 1));
        IArea nextPosition = world.getMap().getAreaOnPosition(4, 1);
        dr.realiseForAction();
        while (dr.actorActivity() != IActor.Activity.NOT_READY && deadLockCounter != 0) {
            ICommand com = InjectBundle.getInstance(Command.class).configure(dr, world.getMap());
            com.execute();
            deadLockCounter--;
        }

        Assert.assertNotEquals(deadLockCounter, 0);
        Assert.assertEquals(dr.getAnchorArea().getRowCollumn(), nextPosition.getRowCollumn());

    }
}
