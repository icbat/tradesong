package com.icbat.game.tradesong.gameObjects.collections;

import com.icbat.game.tradesong.gameObjects.Workshop;

import java.util.LinkedList;

public class WorkshopManager {
    private LinkedList<Workshop> workshops = new LinkedList<Workshop>();

    public WorkshopManager() {
        workshops.add(new Workshop());
    }

    public void actOnEachWorkshop(float delta) {
        for (Workshop shop : workshops) {
            shop.doWork(delta);
        }
    }
}
