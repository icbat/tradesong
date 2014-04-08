package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;
import com.icbat.game.tradesong.gameObjects.CraftingStation;
import com.icbat.game.tradesong.gameObjects.craftingStations.AnythingToOne;
import com.icbat.game.tradesong.gameObjects.craftingStations.OneToOne;

import java.util.ArrayList;

public class CraftingStations {
    public static final String NODES_JSON = "nodes.json";
    private final ArrayList<CraftingStation> nodes = new ArrayList<CraftingStation>();
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

        OneToOne oneToOne = new OneToOne("Smelter");
        oneToOne.inputToOutput.put("Ore", "Ingot");
        oneToOne.inputToOutput.put("Tomato", "Tomato Sauce");
        nodes.nodes.add(oneToOne);

        OneToOne cutter = new OneToOne("Cutter");
        cutter.inputToOutput.put("Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        cutter.inputToOutput.put("Better Wood", "Sword");
        nodes.nodes.add(cutter);

        AnythingToOne scrapper = new AnythingToOne("Scrapper");
        scrapper.output = "Scrap";
        nodes.nodes.add(scrapper);

        Json json = new Json();
        Gdx.app.log("NODES", json.prettyPrint(nodes));
    }

    public ArrayList<CraftingStation> getNodesCopy() {
        return new ArrayList<CraftingStation>(nodes);
    }
}
