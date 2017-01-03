package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.models.Conditions;
import com.fatheroctober.drunkard.engine.core.models.MovingOrientation;

/**
 * access interface for @Policeman
 */
public interface IPoliceman extends IActor {
    /**
     * @return Drinker (when status=ON_MISSION) | IPoliceStation (when status=GO_HOME)
     */
    IActor targerActorToCatch();

    /**
     * @return orientation to move
     */
    MovingOrientation getOrientation();

    /**
     * @return job orient status
     */
    Conditions.PolicemanCondition getStatus();

    /**
     * notify to do job
     *
     * @param targetDrinker - drinker for catch
     * @param home          - police station for return
     */
    void startJob(IDrinker targetDrinker, IPoliceStation home);
}
