package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.common.DrinkerGameException;

import javax.annotation.Nullable;

public interface IActor extends Positional {

    enum Activity {
        READY_FOR_ACTION,
        IN_ACTION,
        NOT_READY;
    }

    /**
     * do actor action
     *
     * @param map where action is doing
     */
    void doAction(@Nullable IMap map) throws DrinkerGameException;

    /**
     * @return anchor area where this actor is staying
     */
    IArea getAnchorArea();

    /**
     * set anchor area
     */
    void setAnchorArea(IArea position);

    /**
     * @return current activity of actor
     */
    Activity actorActivity();

    /**
     * realise actor for actions
     */
    void realiseForAction();

    /**
     * @return name of actor
     */
    String getActorName();


}
