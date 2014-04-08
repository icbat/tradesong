package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.icbat.game.tradesong.gameObjects.CraftingNode;

public class CraftingNodeActor extends Actor {
    CraftingNode backingNode;

    private CraftingNodeActor() {}
    public CraftingNodeActor(CraftingNode backingNode) {
        this.backingNode = backingNode;
    }
}
