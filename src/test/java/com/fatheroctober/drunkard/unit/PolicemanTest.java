package com.fatheroctober.drunkard.unit;

import com.fatheroctober.drunkard.engine.core.controller.Command;
import com.fatheroctober.drunkard.engine.core.controller.ICommand;
import com.fatheroctober.drunkard.engine.core.models.Conditions;
import com.fatheroctober.drunkard.engine.core.models.abstraction.*;
import com.fatheroctober.drunkard.engine.core.models.actors.Drinker;
import com.fatheroctober.drunkard.engine.core.models.actors.Policeman;
import com.fatheroctober.drunkard.engine.core.tools.iocmodule.InjectBundle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.fatheroctober.drunkard.testsettings.TestModule;

import java.util.ArrayList;
import java.util.List;

public class PolicemanTest extends AbstractUnitTest {

    IDrinker targetDrinker;
    IPoliceman policeman;
    IPoliceStation police;

    int deadLockCounter = 1000;

    @Before
    public void before() throws Exception {
        initialize(new TestModule());
        targetDrinker = InjectBundle.getInstance(Drinker.class);
        policeman = InjectBundle.getInstance(Policeman.class);
        police = world.getPoliceStation();
    }

    @Test
    public void policemanActionTest() throws Exception {
        policeman.setAnchorArea(world.getMap().getAreaOnPosition(1, 1));
        targetDrinker.setAnchorArea(world.getMap().getAreaOnPosition(5, 1));

        policeman.startJob(targetDrinker, police);

        policemanMovingTestInternal(); // test moving to drinker
        policemanCatchingTestInternal(); //test catch drinker
    }

    public void policemanMovingTestInternal() throws Exception {
        List<IActor> twoActorsList = new ArrayList<>();
        twoActorsList.add(targetDrinker);
        twoActorsList.add(policeman);

        IArea nextPosition = world.getMap().getAreaOnPosition(2, 1);
        twoActorsList.forEach(actor -> {
            actor.realiseForAction();
        });
        twoActorsList.forEach(iActor -> {
            while (iActor.actorActivity() != IActor.Activity.NOT_READY && deadLockCounter != 0) {
                ICommand com = InjectBundle.getInstance(Command.class).configure(iActor, world.getMap());
                com.execute();
                deadLockCounter--;
            }
            iActor.realiseForAction();
        });

        Assert.assertNotEquals(deadLockCounter, 0);
        Assert.assertEquals(policeman.getAnchorArea().getRowCollumn(), nextPosition.getRowCollumn());
    }

    public void policemanCatchingTestInternal() throws Exception {
        // two steps catch
        while (policeman.actorActivity() != IActor.Activity.NOT_READY && deadLockCounter != 0) {
            ICommand com = InjectBundle.getInstance(Command.class).configure(policeman, world.getMap());
            com.execute();
            deadLockCounter--;
        }
        policeman.realiseForAction();
        while (policeman.actorActivity() != IActor.Activity.NOT_READY && deadLockCounter != 0) {
            ICommand com = InjectBundle.getInstance(Command.class).configure(policeman, world.getMap());
            com.execute();
            deadLockCounter--;
        }
        Assert.assertNotEquals(deadLockCounter, 0);
        Assert.assertEquals(world.getMap().getAreaOnPosition(4, 1).getExistedActor(), policeman);
        Assert.assertEquals(policeman.getStatus(), Conditions.PolicemanCondition.GO_HOME);
        Assert.assertEquals(policeman.targerActorToCatch(), police);
    }
}
