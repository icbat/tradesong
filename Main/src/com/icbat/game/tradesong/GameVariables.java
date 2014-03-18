package com.icbat.game.tradesong;

import java.util.Random;

/**
 * Abstraction of a bunch of game variables in an easy save/load-able place. Also easy enough to know where to look/reference.
 * */
public class GameVariables {
    private float craftTimeMultiplier;
    private float gatherTimeMultiplier;
    private Random seededRNG;

    public GameVariables() {
        craftTimeMultiplier = 1;
        gatherTimeMultiplier = 1;
        seededRNG = new Random(System.currentTimeMillis());
    }

    public float getCraftTimeMultiplier() {
        return craftTimeMultiplier;
    }

    public void setCraftTimeMultiplier(float craftTimeMultiplier) {
        this.craftTimeMultiplier = craftTimeMultiplier;
    }

    public float getGatherTimeMultiplier() {
        return gatherTimeMultiplier;
    }

    public void setGatherTimeMultiplier(float gatherTimeMultiplier) {
        this.gatherTimeMultiplier = gatherTimeMultiplier;
    }

    public Random getSeededRNG() {
        return seededRNG;
    }

    public void setSeededRNG(Random seededRNG) {
        this.seededRNG = seededRNG;
    }
}
