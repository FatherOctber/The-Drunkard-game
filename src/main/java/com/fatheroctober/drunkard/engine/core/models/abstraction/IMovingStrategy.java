package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IMovingStrategy {

    int MOVING_DISTANCE = 1; // distance to move

    @Nullable
    IArea getAreaToMove(@Nonnull IActor actor, @Nonnull IMap map) throws DrinkerGameException;

}
