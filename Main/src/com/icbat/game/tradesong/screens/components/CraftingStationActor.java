package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.icbat.game.tradesong.gameObjects.CraftingStation;

public class CraftingStationActor extends Actor {
    CraftingStation backingNode;

    private CraftingStationActor() {}
    public CraftingStationActor(CraftingStation backingNode) {
        this.backingNode = backingNode;
    }
}
