package com.fatheroctober.drunkard.engine.core.common;

/**
 * Observer pattern interface
 */
public interface Observerable {

    void registerObserver(Observer ob);

    void removedObserver(Observer ob);

    void sendSignal(int X, int Y);
}
