package com.icbat.game.tradesong;

/**
 * Abstraction of a bunch of game variables in an easy save/load-able place. Also easy enough to know where to look/reference.
 * */
public class GameVariables {
    float craftTimeMultiplier;
    float gatherTimeMultiplier;
    long randomSeed;

    public GameVariables() {
        craftTimeMultiplier = 1;
        gatherTimeMultiplier = 1;
        randomSeed = System.currentTimeMillis();
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

    public long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(long randomSeed) {
        this.randomSeed = randomSeed;
    }
}
