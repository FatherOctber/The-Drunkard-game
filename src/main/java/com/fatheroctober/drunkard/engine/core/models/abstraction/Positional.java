package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;

import javax.annotation.Nonnull;

public interface Positional {

    /**
     * @param X - x coordinate
     * @param Y - y coordinate
     */
    void setXY(int X, int Y);

    /**
     * @param XY - pair of coordinates
     */
    void setXY(@Nonnull Pair<Integer, Integer> XY);

    /**
     * @return pair of XY coordinates
     */
    Pair<Integer, Integer> getXY();

    /**
     * @return width
     */
    int getWidth();

    /**
     * @return height
     */
    int getHeight();
}
