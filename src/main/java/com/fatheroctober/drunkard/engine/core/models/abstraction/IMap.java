package com.fatheroctober.drunkard.engine.core.models.abstraction;

import com.fatheroctober.drunkard.engine.core.tools.utils.Pair;

import javax.annotation.Nonnull;

public interface IMap {

    /**
     * for building map
     */
    void build();

    /**
     * @return map size
     */
    int getSize();

    /**
     * @param row    target row
     * @param column target column
     * @return square object
     * @throws Exception
     */
    IArea getAreaOnPosition(int row, int column) throws Exception;

    /**
     * @param rowColumn target pair of row and column
     * @return square object
     * @throws Exception
     */
    IArea getAreaOnPosition(@Nonnull Pair<Integer, Integer> rowColumn) throws Exception;

    /**
     * @return iterable of map squares
     */
    Iterable<IArea> getAreas();
}
