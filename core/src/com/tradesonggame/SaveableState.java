package com.tradesonggame;

import com.tradesonggame.gameObjects.Inventory;
import com.tradesonggame.gameObjects.Request;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstraction of a bunch of game variables in an easy save/load-able place. Also easy enough to know where to look/reference.
 * */
public class SaveableState {
    private Inventory inventory;
    private ArrayList<Request> requestList;
    private float gatherTimeMultiplier;
    private Random seededRNG;
//    private WorkshopManager workshopManager;

    public SaveableState() {
        gatherTimeMultiplier = 1;
        seededRNG = new Random(System.currentTimeMillis());
        inventory = new Inventory();
        requestList = new ArrayList<Request>();
//        workshopManager = new WorkshopManager();
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


    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(ArrayList<Request> requestList) {
        this.requestList = requestList;
    }

//    public WorkshopManager getWorkshopManager() {
//        return workshopManager;
//    }
}
