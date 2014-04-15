package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;
import com.icbat.game.tradesong.gameObjects.craftingStations.Processor;
import com.icbat.game.tradesong.gameObjects.craftingStations.Storage;

import java.util.LinkedList;

public class Workshop {
    LinkedList<BaseCraftingStation> orderedNodes = new LinkedList<BaseCraftingStation>();
    protected LinkedList<String> fallingItems = new LinkedList<String>();

    public Workshop() {

        Storage inputChest = new Storage("Input Chest");
        inputChest.iconX = 8;
        inputChest.iconY = 29;
        inputChest.add("Ore");
        inputChest.add("Sword");
        inputChest.add("Tomato");
        inputChest.add("Sword213123");

        orderedNodes.add(inputChest);

        Processor processor = new Processor("Smelter");
        processor.inputToOutput.put("Ore", "Ingot");
        processor.inputToOutput.put("Tomato", "Tomato Sauce");
        processor.iconX = 10;
        processor.iconY = 10;
        orderedNodes.add(processor);

        Storage outputChest = new Storage("Output Chest");
        outputChest.iconX = 7;
        outputChest.iconY = 29;
        orderedNodes.add(outputChest);
    }

    public LinkedList<BaseCraftingStation> getOrderedNodes() {
        return orderedNodes;
    }

    public void doWork() {
        for (BaseCraftingStation station : orderedNodes) {
            Gdx.app.debug("before Ingest", fallingItems.toString());
            station.ingest(fallingItems);
            Gdx.app.debug("before process", fallingItems.toString());
            station.process();
            Gdx.app.debug("before getOutput", fallingItems.toString());
            String nextOutput = station.getNextOutput();
            if (nextOutput != null && nextOutput != "") {
                fallingItems.add(nextOutput);
            }
            Gdx.app.debug("after getOutput", fallingItems.toString());
        }
    }
}
