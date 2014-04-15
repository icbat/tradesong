package com.icbat.game.tradesong;

import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.Inventory;
import com.icbat.game.tradesong.gameObjects.collections.WorkshopManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstraction of a bunch of game variables in an easy save/load-able place. Also easy enough to know where to look/reference.
 * */
public class SaveableState {
    private Inventory inventory;
    private ArrayList<Contract> contractList;
    private float gatherTimeMultiplier;
    private Random seededRNG;
    private WorkshopManager workshopManager;

    public SaveableState() {
        gatherTimeMultiplier = 1;
        seededRNG = new Random(System.currentTimeMillis());
        inventory = new Inventory();
        contractList = new ArrayList<Contract>();
        workshopManager = new WorkshopManager();
    }

    public float getGatherTimeMultiplier() {
        return gatherTimeMultiplier;
    }

    public Random getSeededRNG() {
        return seededRNG;
    }


    public Inventory inventory() {
        return this.inventory;
    }


    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(ArrayList<Contract> contractList) {
        this.contractList = contractList;
    }

    public WorkshopManager getWorkshopManager() {
        return workshopManager;
    }
}
