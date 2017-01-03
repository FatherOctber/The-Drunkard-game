package com.fatheroctober.drunkard.engine.core.common;

@FunctionalInterface
public interface Observer {
    /**
     * update observer for change X, Y of frame
     * @param X - x coordinate of frame
     * @param Y - y coordinate of frame
     */
    void receiveSignal(int X, int Y);
}
