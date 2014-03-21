package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.gameObjects.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Abstraction of a bunch of game variables in an easy save/load-able place. Also easy enough to know where to look/reference.
 * */
public class GameState {
    private Inventory inventory;
    private ArrayList<Contract> contractList;
    private float gatherTimeMultiplier;
    private Random seededRNG;

    public GameState() {
        gatherTimeMultiplier = 1;
        seededRNG = new Random(System.currentTimeMillis());
        inventory = new Inventory();
        contractList = new ArrayList<Contract>();
    }

    public void saveGame(int saveSlotNumber) {
        dumpDebug();
        FileHandle gameSaveFile = Gdx.files.external("Tradesong/tradesong_save_" + saveSlotNumber + ".json");
        Json json = new Json();
        gameSaveFile.delete();
        Gdx.app.debug("", json.prettyPrint(this));
        gameSaveFile.writeString(json.prettyPrint(this), true);
    }

    public boolean canLoadSlot(int saveSlotNumber) {
        String path = "Tradesong/tradesong_save_" + saveSlotNumber + ".json";
        try {
            FileHandle gameSaveFile = Gdx.files.external(path);
            Json json = new Json();
            Tradesong.state = json.fromJson(GameState.class, gameSaveFile.readString());
            dumpDebug();
            return true;
        } catch (GdxRuntimeException rte) {
            Gdx.app.log("Starting new game. No save file found at", path);
            return false;
        }
    }

    private void dumpDebug() {
        Gdx.app.debug("## State info ##", "");
        Gdx.app.debug("  # Inventory", inventory.toString());
        Gdx.app.debug("  # Current Contracts", contractList.toString());
        Gdx.app.debug("  # Gather Delay", gatherTimeMultiplier + "");
        Gdx.app.debug("  # Seeded RNG", seededRNG.toString());
    }

    public float getGatherTimeMultiplier() {
        return gatherTimeMultiplier;
    }

    public Random getSeededRNG() {
        return seededRNG;
    }


    public Inventory inventory() {
        return this.inventory;
    }


    public List<Contract> getContractList() {
        return contractList;
    }

    public void setContractList(ArrayList<Contract> contractList) {
        this.contractList = contractList;
    }
}
