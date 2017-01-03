package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;
import com.fatheroctober.drunkard.engine.core.tools.utils.IfceLoger;
import com.google.inject.Inject;

public abstract class AbstractGameFacade {

    @Inject
    protected IfceLoger logger;

    public abstract IMap getMap();

    public abstract ITavern getTavern();

    public abstract IPoliceStation getPoliceStation();

    public abstract IPillar getPillar();

    public abstract ILamp getLamp();

    public abstract void build() throws DrinkerGameException;

    public abstract Iterable<IActor> getAllActors();
     
}
