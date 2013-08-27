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

import java.util.Stack;


/**
 * This class:
 *  - sets up the game initially
 *  - tracks/exposes game variables
 *  - loads common/global assets
 * */
public class Tradesong extends Game {

    private static final String PATH_SPRITE_ITEMS = "sprites/items.png";
    private static final String PATH_SPRITE_FRAME = "sprites/frame.png";
    private static final String PATH_SPRITE_ARROW_INVENTORY = "sprites/arrow-inventory.png";
    private static final String PATH_SPRITE_ARROW_MAPS = "sprites/arrow-map.png";
    private static final String PATH_SPRITE_ICON_HAMMER = "sprites/hammer-drop.png";
    private static final String PATH_SPRITE_ICON_WRENCH = "sprites/auto-repair.png";
    private static final String PATH_SPRITE_ICON_BOOK = "sprites/burning-book.png";
    private static final String PATH_SPRITE_CHAR = "sprites/character.png";
    private static final String PATH_SPRITE_COIN = "sprites/goldCoin5.png";

    private static final String PATH_SOUNDS_GATHER = "sounds/hammering.ogg";
    private static final String PATH_MUSIC_GENERAL = "music/Thatched Villagers.mp3";

    private static final String PARAM_DELAY_GATHER = "gatherDelay";
    private static final String PARAM_DELAY_CRAFT = "craftDelay";

    private static Music generalMusic;

    public static GameStateManager gameState;
    public static AssetManager assets = new AssetManager();
    private static final Stack<AbstractScreen> screenStack = new Stack<AbstractScreen>();

    private HUDStage hud;
    private InventoryStage inventoryStage;
    private WorkshopStage workshopStage;


    @Override
	public void create() {
        initializeAssets();

        generalMusic = assets.get(PATH_MUSIC_GENERAL);
        generalMusic.play();
        generalMusic.setLooping(true);

        gameState = new GameStateManager();
		goToMainMenu();

        hud = new HUDStage(this);
        inventoryStage = new InventoryStage();
        workshopStage = new WorkshopStage();


	}

    private void initializeAssets() {

        // Music and sounds
        assets.load(PATH_SOUNDS_GATHER, Sound.class);
        assets.load(PATH_MUSIC_GENERAL, Music.class);

        // Item sheet used by all/most icons, items, buttons, etc.
        assets.load(PATH_SPRITE_ITEMS, Texture.class);
        assets.load(PATH_SPRITE_ARROW_MAPS, Texture.class);

        // Character sprite!
        assets.load(PATH_SPRITE_CHAR, Texture.class);
        assets.load(PATH_SPRITE_COIN, Texture.class);

        // Frame PNG used in inventory/workshops
        assets.load(PATH_SPRITE_FRAME, Texture.class);

        assets.load(PATH_SPRITE_ARROW_INVENTORY, Texture.class);
        assets.load(PATH_SPRITE_ICON_BOOK, Texture.class);
        assets.load(PATH_SPRITE_ICON_HAMMER, Texture.class);
        assets.load(PATH_SPRITE_ICON_WRENCH, Texture.class);

        assets.finishLoading(); // Blocks until finished
    }


    /* Screen management methods */

    /** Things that can be passed to goToScreen */
    public static enum ScreenTypes {
        MAIN_MENU,
        SETTINGS,

        LEVEL,
        TOWN,

        WORKSHOP,
        INVENTORY,
        SHOP,
    }

    public void goBack() {
        if (screenStack.size() > 0) {
            screenStack.pop();
            setScreen(screenStack.peek());
        }
    }

    public void goToScreen(AbstractScreen newScreen) {
        AbstractScreen top = screenStack.peek();

        // These errors are a bug in IDEA and not actually wrong
        if (top.getClass().equals(InventoryScreen.class) || top.getClass().equals(WorkshopScreen.class) || top.getClass().equals(StoreScreen.class))
            screenStack.pop();
        screenStack.push(newScreen);
        setScreen(screenStack.peek());
    }

    public void goToMainMenu() {
        screenStack.clear();
        screenStack.push(new MainMenuScreen(this));
        setScreen(screenStack.peek());
    }

    public void goToInventory() {
        goToScreen(new InventoryScreen(hud, inventoryStage));
    }
    public void goToLevel(String levelName) {
        goToScreen(new LevelScreen(levelName, hud));
    }
    public void goToTown() {
        goToLevel("town_hub");
    }

    public AbstractScreen getCurrentScreen() {
        return screenStack.peek();
    }
    public void goToWorkshop() {
        goToScreen(new WorkshopScreen(hud, inventoryStage, workshopStage));
    }
    public void goToStore() {
        goToScreen(new StoreScreen(hud, inventoryStage));
    }




    /* Block for static asset retrieval methods */

    public static Texture getItemsPath() {
        return assets.get(PATH_SPRITE_ITEMS);
    }

    public static Texture getFramePath() {
        return assets.get(PATH_SPRITE_FRAME);
    }

    public static Texture getPathSpriteArrow() {
        return assets.get(PATH_SPRITE_ARROW_INVENTORY);
    }

    public static Texture getSpriteIconHammer() {
        return assets.get(PATH_SPRITE_ICON_HAMMER);
    }

    public static Texture getSpriteIconWrench() {
        return assets.get(PATH_SPRITE_ICON_WRENCH);
    }

    public static Texture getSpriteIconBook() {
        return assets.get(PATH_SPRITE_ICON_BOOK);
    }

    public static Texture getCharacterTexture() {
        return assets.get(PATH_SPRITE_CHAR);
    }

    public static Texture getCoinTexture() {
        return assets.get(PATH_SPRITE_COIN);
    }

    public static Sound getGatherSound() {
        return assets.get(PATH_SOUNDS_GATHER);
    }

    public static String getParamDelayGather() {
        return PARAM_DELAY_GATHER;
    }

    public static String getParamDelayCraft() {
        return PARAM_DELAY_CRAFT;
    }

    public static Texture getMapArrowTexture() {
        return assets.get(PATH_SPRITE_ARROW_MAPS);
    }
}
