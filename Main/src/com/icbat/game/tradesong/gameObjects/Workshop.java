package com.icbat.game.tradesong.gameObjects;

import com.badlogic.gdx.Gdx;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;

import java.util.LinkedList;

public class Workshop {
    LinkedList<BaseCraftingStation> orderedNodes = new LinkedList<BaseCraftingStation>();
    protected LinkedList<String> fallingItems = new LinkedList<String>();

    public Workshop() {
        orderedNodes.add(Tradesong.craftingStations.getStation("Input Chest"));
        orderedNodes.add(Tradesong.craftingStations.getStation("Smelter"));
        orderedNodes.add(Tradesong.craftingStations.getStation("Output Chest"));
    }

    public LinkedList<BaseCraftingStation> getOrderedNodes() {
        return orderedNodes;
    }

    public void doWork() {
        for (BaseCraftingStation station : orderedNodes) {
            Gdx.app.debug(station.getStationName(), station.toString());
            station.ingest(fallingItems);
            station.process();
            if (station != orderedNodes.getLast()) {
                String nextOutput = station.getNextOutput();
                if (nextOutput != null && nextOutput != "") {
                    fallingItems.add(nextOutput);
                }
            }
        }
    }
}
