package com.icbat.game.tradesong;

import java.util.Date;
import java.util.Stack;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.screens.*;


/**
 * This class:
 *  - sets up the game initially
 *  - tracks/exposes game variables
 *  - loads common/global assets
 * */
public class Tradesong extends Game {

    private static final String PATH_SPRITE_ITEMS = "sprites/items.png";
    private static final String PATH_SPRITE_FRAME = "sprites/frame.png";
    public GameStateManager gameState;
	public LJ log = new LJ("", this.getClass().getSimpleName());
    public AssetManager assets = new AssetManager();
    private Stack<AbstractScreen> screenStack = new Stack<AbstractScreen>();
	
	@Override
	public void create() {
        loggingSetup();
        initializeAssets();

        gameState = new GameStateManager(this);
		goToMainMenu();


	}

    private void loggingSetup() {
        log.setLevel( Application.LOG_DEBUG );
        log.info( "Creating game" );
        // Some initial logging (type and version)
        log.info( new Date().toString() );
        log.info( "App Type" + Gdx.app.getType().toString() );
        log.info( "Device Version" + Gdx.app.getVersion() );

    }

    private void initializeAssets() {
        log.info("Initializing Assets");

        // Item sheet used by all/most icons, items, buttons, etc.
        this.assets.load(PATH_SPRITE_ITEMS, Texture.class);

        // Frame PNG used in inventory/workshops
        this.assets.load(PATH_SPRITE_FRAME, Texture.class);

        this.assets.finishLoading(); // Blocks until finished
    }

    public void goBack() {
        if (screenStack.size() > 0) {
            log.info("Popping screens");
            screenStack.pop();
            setScreen(screenStack.peek());
        }
    }

    public void goToScreen(AbstractScreen newScreen) {
        log.info("Going to new screen");
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
}
