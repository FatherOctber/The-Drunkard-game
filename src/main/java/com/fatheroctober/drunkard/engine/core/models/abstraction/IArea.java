package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface IArea extends Positional {

    IActor getExistedActor();

    void setRowCollumn(int row, int column);

    void setRowCollumn(@Nonnull Pair<Integer, Integer> rowCollumn);

    Pair<Integer, Integer> getRowCollumn();

    void setExistedActor(@Nullable IActor actor);

    /**
     * @return true if area are select as active
     */
    boolean isSelected();

    /**
     * @return true if area is illuminated
     */
    boolean isIlluminated();

    /**
     * turn on illumination
     */
    void illuminatedOn();

    /**
     * turn off illumination
     */
    void illuminatedOff();

    /**
     * turn on selection
     */
    void selectOn();

    /**
     * turn off selection
     */
    void selectOff();

}
