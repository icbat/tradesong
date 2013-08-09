package com.icbat.game.tradesong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.icbat.game.tradesong.screens.*;

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
    private static final String PATH_SPRITE_ARROW = "sprites/arrow.png";
    public static GameStateManager gameState;
    public static AssetManager assets = new AssetManager();
    private static final Stack<AbstractScreen> screenStack = new Stack<AbstractScreen>();



	@Override
	public void create() {
        initializeAssets();

        gameState = new GameStateManager();
		goToMainMenu();


	}

    private void initializeAssets() {
        // Item sheet used by all/most icons, items, buttons, etc.
        assets.load(PATH_SPRITE_ITEMS, Texture.class);

        // Frame PNG used in inventory/workshops
        assets.load(PATH_SPRITE_FRAME, Texture.class);

        assets.load(PATH_SPRITE_ARROW, Texture.class);

        assets.finishLoading(); // Blocks until finished
    }

    public void goBack() {
        if (screenStack.size() > 0) {
            screenStack.pop();
            setScreen(screenStack.peek());
        }
    }

    public void goToScreen(AbstractScreen newScreen) {
        screenStack.push(newScreen);
        setScreen(screenStack.peek());
    }

    public void goToMainMenu() {
        goToScreen(new MainMenuScreen(this));
    }

    public void goToInventory() {
        goToScreen(new InventoryScreen(this));
    }
    public void goToLevel(String levelName) {
        goToScreen(new LevelScreen(levelName, this));
    }
    public AbstractScreen getCurrentScreen() {
        return screenStack.peek();
    }

    public void goToWorkshop() {
        goToScreen(new WorkshopScreen(this));
    }

    public static String getItemsPath() {
        return PATH_SPRITE_ITEMS;
    }

    public static String getFramePath() {
        return PATH_SPRITE_FRAME;
    }

    public static String getPathSpriteArrow() {
        return PATH_SPRITE_ARROW;
    }
}
