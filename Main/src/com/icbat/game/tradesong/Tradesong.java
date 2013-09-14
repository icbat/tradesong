package com.icbat.game.tradesong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.icbat.game.tradesong.screens.*;
import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;
import com.icbat.game.tradesong.stages.WorkshopStage;
import com.icbat.game.tradesong.utils.*;

/**
 * This class:
 *  - sets up the game initially
 *  - tracks/exposes game variables
 *  - loads common/global assets
 * */
public class Tradesong extends Game {

    private static final String PARAM_DELAY_GATHER = "gatherDelay";
    private static final String PARAM_DELAY_CRAFT = "craftDelay";

    public static GameStateManager gameState;
    public static AssetManager assetManager = new AssetManager();

    private static LevelScreen currentMap;
    private static ScreenTypes currentScreenType;

    private HUDStage hud;
    private InventoryStage inventoryStage;
    private WorkshopStage workshopStage;
    private static InputHandler keyHandler = new InputHandler();



    @Override
	public void create() {
        initializeAssets();
        GameStateManager.updateMusic(getMusic(MusicAsset.TITLE_THEME));
        gameState = new GameStateManager();
		goToScreen(ScreenTypes.MAIN_MENU);

        hud = new HUDStage(this);
        inventoryStage = new InventoryStage();
        workshopStage = new WorkshopStage();


	}

    private void initializeAssets() {

        for (TextureAssets texture : TextureAssets.values()) {
            assetManager.load(texture.getPath(), Texture.class);
        }

        for (SoundAssets sound : SoundAssets.values()) {
            assetManager.load(sound.getPath(), Sound.class);
        }

        for (MusicAsset music : MusicAsset.values()) {
            assetManager.load(music.getPath(), Music.class);
        }
        assetManager.finishLoading(); // Blocks until finished
    }

    public static InputHandler getKeyHandler() {
        return keyHandler;
    }

    /* Screen management methods */

    public void goToScreen(ScreenTypes screen) {

        GameStateManager.updateMusic();

        if (screen.equals(currentScreenType)) {

            if (screen.equals(ScreenTypes.LEVEL)) {
                goToOverlap(new MainMenuScreen(this));
                currentScreenType = ScreenTypes.MAIN_MENU;
            } else {
                leaveOverlap();
                currentScreenType = ScreenTypes.LEVEL;
            }

        } else {

            currentScreenType = screen;

            switch(screen) {
                case MAIN_MENU:
                    goToOverlap(new MainMenuScreen(this));
                    break;
                case TOWN:
                    goToMap("town_hub");
                    break;
                case WORKSHOP:
                    goToOverlap(new WorkshopScreen(hud, inventoryStage, workshopStage));
                    break;
                case INVENTORY:
                    goToOverlap(new InventoryScreen(hud, inventoryStage));
                    break;
                case STORE:
                    goToOverlap(new StoreScreen(hud, inventoryStage));
                    break;
                case SETTINGS:
                    goToOverlap(new SettingsScreen(this));
                    break;
            }
        }
    }

    public void goToMap(String mapName) {

        currentMap = new LevelScreen(mapName, hud, this);
        setScreen(currentMap);
    }

    public void goToOverlap(AbstractScreen newScreen) {
        setScreen(newScreen);
    }

    public void leaveOverlap() {
        if (currentMap != null) {
            setScreen(currentMap);
        }
        else {
            goToScreen(ScreenTypes.MAIN_MENU);
        }

    }

    public static String getParamDelayGather() {
        return PARAM_DELAY_GATHER;
    }

    public static String getParamDelayCraft() {
        return PARAM_DELAY_CRAFT;
    }


    /** Convenience method to prevent having to call assetManager.get(longConstantName)
     *
     * @throws Error if the file could not be found
     * @return the object in assetManager by that name
     * */
    public static Texture getTexture(TextureAssets toFind) {
        return assetManager.get(toFind.getPath());
    }
    /** Convenience method to prevent having to call assetManager.get(longConstantName)
     *
     * @throws Error if the file could not be found
     * @return the object in assetManager by that name
     * */
    public static Sound getSound(SoundAssets toFind) {
        return assetManager.get(toFind.getPath());
    }
    /** Convenience method to prevent having to call assetManager.get(longConstantName)
     *
     * @throws Error if the file could not be found
     * @return the object in assetManager by that name
     * */
    public static Music getMusic(MusicAsset toFind) {
        return assetManager.get(toFind.getPath());
    }
}
