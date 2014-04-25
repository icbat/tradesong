package com.icbat.game.tradesong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.icbat.game.tradesong.assetReferences.MusicAssets;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.gameObjects.Clock;
import com.icbat.game.tradesong.gameObjects.RequestGenerator;
import com.icbat.game.tradesong.gameObjects.collections.CraftingStations;
import com.icbat.game.tradesong.gameObjects.collections.Items;
import com.icbat.game.tradesong.screens.CraftingScreen;
import com.icbat.game.tradesong.screens.MapScreen;
import com.icbat.game.tradesong.utility.Constants;
import com.icbat.game.tradesong.utility.GameSkin;

import java.util.ArrayList;

public class Tradesong extends Game {

    public static ScreenManager screenManager;
    public static AssetManager assetManager = new AssetManager();
    public static GameSkin uiStyles;
    public static Items items;
    public static RequestGenerator requestGenerator;
    public static SaveableState state;
    public static Clock clock;
    public static PopupQueue popupQueue;
    private static ArrayList<SaveSlot> saveSlots = new ArrayList<SaveSlot>(Constants.NUMBER_OF_SAVE_SLOTS.value());
    public static SaveSlot saveSlot;
    public static Actor focusedItem;
    public static CraftingStations craftingStations;

    @Override
    public void create() {
        Gdx.app.setLogLevel(3);

        initializeSaveSlots();
        initializeStaticData();
        playLoopingMusic(MusicAssets.MYSTERIOUS);
        screenManager.goToMainMenu();
        this.debugMode();
    }

    private void initializeSaveSlots() {
        for (int i=1; i < Constants.NUMBER_OF_SAVE_SLOTS.value(); ++i) {
            saveSlots.add(new SaveSlot(i));
        }
    }

    private void debugMode() {
        for (Items.Item item : items.getAll()) {
            state.inventory().addItem(item.getName());
        }
        startGame();
        screenManager.goToScreen(new CraftingScreen());
    }

    private void initializeStaticData() {
        Tradesong.assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
        initializeAssets();
        craftingStations = CraftingStations.parseFromJson();
        uiStyles = GameSkin.makeDefaultUIStyles();
        screenManager = new ShallowSelectiveScreenStack(this);
        items = Items.parsePrototypes();

        popupQueue = new PopupQueue();
        clock = new Clock();
        state = new SaveableState();
        requestGenerator = new RequestGenerator(items.getAll());
    }

    private void initializeAssets() {
        for (TextureAssets texture : TextureAssets.values()) {
            assetManager.load(texture.getPath(), Texture.class);
        }

        for (SoundAssets sound : SoundAssets.values()) {
            assetManager.load(sound.getPath(), Sound.class);
        }

        for (MusicAssets music : MusicAssets.values()) {
            assetManager.load(music.getPath(), Music.class);
        }
        assetManager.finishLoading();
    }

    /**
     * Convenience method to prevent having to call assetManager.get(longConstantName)
     *
     * @return the object in assetManager by that name
     * @throws Error if the file could not be found
     */
    public static Texture getTexture(TextureAssets toFind) {
        return assetManager.get(toFind.getPath());
    }

    /**
     * Convenience method to prevent having to call assetManager.get(longConstantName)
     *
     * @return the object in assetManager by that name
     * @throws Error if the file could not be found
     */
    public static Sound getSound(SoundAssets toFind) {
        return assetManager.get(toFind.getPath());
    }

    /**
     * Plays the music on loop given the asset name.
     *
     * @throws Error if the file could not be found
     */
    public static void playLoopingMusic(MusicAssets toFind) {
        Music currentTrack = assetManager.get(toFind.getPath());
        currentTrack.setLooping(true);
        if (!currentTrack.isPlaying()) {
            currentTrack.play();
        }

        currentTrack.setVolume(0.9f);
    }

    static void startGame() {
        Gdx.app.debug("main", "going to the screen");
        screenManager.goToScreen(new MapScreen("fairy_fountain"));
        clock.startDay();
    }

    public static void setupNewGame() {
        Gdx.app.debug("main", "new game started");
        state = new SaveableState();
        startGame();
    }

    /**
     * Tries to load from this slot. If no save file is found here, starts a new game in that slot.
     * */
    public static void tryLoadingSlot(int slotNumber) {
        saveSlot = saveSlots.get(slotNumber - 1);
        if (saveSlot.tryToLoad()) {
            Gdx.app.debug("main", "loaded a game");
            startGame();
        } else {
            setupNewGame();
        }
    }

    public static void deleteSlot(int slotNumber) {
        saveSlots.get(slotNumber - 1).delete();
    }

    public static boolean slotExists(int slowNumber) {
        return saveSlots.get(slowNumber - 1).fileExists();
    }
}
