package com.fatheroctober.drunkard.engine.core.common;

public class GlobalTimer {

    private int frameRate = 10;
    private static GlobalTimer timerInstance;
    private int gameTacts = 0;
    private int delayTime = 100;
    private int spriteDistanceByFrameCounter = frameRate / 2;
    private boolean paused = false;

    public static GlobalTimer getInstance() {
        if (timerInstance == null) timerInstance = new GlobalTimer();
        return timerInstance;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean pause) {
        paused = pause;
    }

    public int getFrameRate() {
        return frameRate;
    }

    public void setFrameRate(int frameRate) {
        if (frameRate < 50 && frameRate > 5) {
            this.frameRate = frameRate;
        }
    }

    public void incTacts() {
        gameTacts++;
    }

    public int getTacts() {
        return gameTacts;
    }

    public int getDelayTime() {
        return delayTime;
    }

    public int getSpriteDistanceByFrameCounter() {
        return spriteDistanceByFrameCounter;
    }

}
