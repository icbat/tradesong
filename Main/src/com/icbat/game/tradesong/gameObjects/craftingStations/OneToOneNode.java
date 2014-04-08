package com.icbat.game.tradesong.gameObjects.craftingStations;

import com.icbat.game.tradesong.gameObjects.CraftingNode;

import java.util.HashMap;

public class OneToOneNode implements CraftingNode {
    public String craftingNodeName;
    public final HashMap<String, String> inputToOutput;       // TODO tighter scope

    public OneToOneNode() {
        inputToOutput = new HashMap<String, String>();
    }

    public OneToOneNode(String craftingNodeName) {
        this();
        this.craftingNodeName = craftingNodeName;
    }

    @Override
    public String getNodeName() {
        return craftingNodeName;
    }

    @Override
    public boolean isValidInput(String inputItemName) {
        return inputToOutput.containsKey(inputItemName);
    }
}
