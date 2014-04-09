package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.icbat.game.tradesong.gameObjects.craftingStations.BaseCraftingStation;
import com.icbat.game.tradesong.gameObjects.craftingStations.Processor;
import com.icbat.game.tradesong.gameObjects.craftingStations.Scrapper;

import java.util.ArrayList;

public class CraftingStations {
    public static final String NODES_JSON = "nodes.json";
    private final ArrayList<BaseCraftingStation> nodes = new ArrayList<BaseCraftingStation>();
    private CraftingStations() {}

    public static CraftingStations parseFromJson() {

        setupFirst();// TODO REMOVE

        CraftingStations craftingStations;
        try {
            Json json = new Json();
            craftingStations = json.fromJson(CraftingStations.class, Gdx.files.internal(NODES_JSON));
            return craftingStations;
        } catch (SerializationException se) {
            Gdx.app.error("Missing or malformed file: "+NODES_JSON, "Cannot read crafting nodes");
            Gdx.app.debug("", "", se);
            return null;
        }
    }

    private static void setupFirst() {
        CraftingStations nodes = new CraftingStations();

        Processor processor = new Processor("Smelter");
        processor.inputToOutput.put("Ore", "Ingot");
        processor.inputToOutput.put("Tomato", "Tomato Sauce");
        nodes.nodes.add(processor);

        Processor cutter = new Processor("Cutter");
        cutter.inputToOutput.put("Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        nodes.nodes.add(cutter);

        Scrapper scrapper = new Scrapper("Scrapper");
        scrapper.output = "Scrap";
        nodes.nodes.add(scrapper);

        Json json = new Json();
        Gdx.app.log("NODES", json.prettyPrint(nodes));
    }

    public ArrayList<BaseCraftingStation> getNodesCopy() {
        return new ArrayList<BaseCraftingStation>(nodes);
    }
}
