package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.CraftingStation;

public class CraftingStationActor extends Label {
    CraftingStation backingNode;

//    private CraftingStationActor() {}
    public CraftingStationActor(CraftingStation backingNode) {
        super(backingNode.getNodeName(), Tradesong.uiStyles);
        this.backingNode = backingNode;
    }
}
