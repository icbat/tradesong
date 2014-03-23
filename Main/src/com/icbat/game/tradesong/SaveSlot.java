package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;

public class SaveSlot {
    private final int slotNumber;

    public SaveSlot(int slotNumber) {
        this.slotNumber = slotNumber;
    }

    public void save() {
        FileHandle gameSaveFile = Gdx.files.external("Tradesong/tradesong_save_" + slotNumber + ".json");
        Json json = new Json();
        gameSaveFile.delete();
        Gdx.app.debug("", json.prettyPrint(Tradesong.state));
        gameSaveFile.writeString(json.prettyPrint(Tradesong.state), true);
    }

    public boolean tryToLoad() {
        String path = "Tradesong/tradesong_save_" + slotNumber + ".json";
        try {
            FileHandle gameSaveFile = Gdx.files.external(path);
            Json json = new Json();
            Tradesong.state = json.fromJson(GameState.class, gameSaveFile.readString());
            return true;
        } catch (GdxRuntimeException rte) {
            Gdx.app.log("Starting new game. No save file found at", path);
            return false;
        }
    }

    public void delete() {
        FileHandle gameSaveFile = Gdx.files.external("Tradesong/tradesong_save_" + slotNumber + ".json");
        gameSaveFile.delete();
    }

    public boolean fileExists() {
        FileHandle gameSaveFile = Gdx.files.external("Tradesong/tradesong_save_" + slotNumber + ".json");

        return gameSaveFile.exists();
    }
}
