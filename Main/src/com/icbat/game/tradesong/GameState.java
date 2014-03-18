package com.icbat.game.tradesong;

import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstraction of a bunch of game variables in an easy save/load-able place. Also easy enough to know where to look/reference.
 * */
public class GameState {
    public static Inventory inventory;
    public static Clock clock;
    public static List<Contract> contractList;
    private float craftTimeMultiplier;
    private float gatherTimeMultiplier;
    private Random seededRNG;

    public GameState() {
        craftTimeMultiplier = 1;
        gatherTimeMultiplier = 1;
        seededRNG = new Random(System.currentTimeMillis());
        inventory = new Inventory();
        clock = new Clock();
        contractList = new ArrayList<Contract>();
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
