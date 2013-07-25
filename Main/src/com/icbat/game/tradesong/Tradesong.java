package com.icbat.game.tradesong;

import java.util.Date;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.screens.MainMenuScreen;


/**
 * This class:
 *  - sets up the game initially
 *  - tracks/exposes game variables
 * */
public class Tradesong extends Game {
	
    public GameStateManager gameState = new GameStateManager();
	public LJ log = new LJ("", this.getClass().getSimpleName());
    public AssetManager assets = new AssetManager();
	
	@Override
	public void create() {
		log.setLevel( Application.LOG_DEBUG );
		log.info( "Creating game" );
		// Some initial logging (type and version)
		log.info( new Date().toString() );
		log.info( "App Type" + Gdx.app.getType().toString() );
		log.info( "Device Version" + Gdx.app.getVersion() );
		setScreen( new MainMenuScreen(this) );

	}
}
