package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;

public class CraftingStationActor extends Label {
    BaseCraftingStation backingNode;

//    private CraftingStationActor() {}
    public CraftingStationActor(BaseCraftingStation backingNode) {
        super(backingNode.getStationName(), Tradesong.uiStyles);
        this.backingNode = backingNode;
    }
}
