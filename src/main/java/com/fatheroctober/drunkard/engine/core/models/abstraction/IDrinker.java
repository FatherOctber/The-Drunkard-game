package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.models.Conditions;
import com.fatheroctober.drunkard.engine.core.models.MovingOrientation;

/**
 * access interface for @Drinker
 */
public interface IDrinker extends IActor {

    /**
     * @return alcohol | sleep | getting sleep condition
     */
    Conditions.DrinkerCondition getCondition();

    /**
     * @return orientation to move
     */
    MovingOrientation getOrientation();
}
