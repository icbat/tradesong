package com.icbat.game;

import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Logging utility class to allow seemless logging to either console, file, or both
 * 
 * To use: Instantiate, then turn on the appropriate flags for logging, then use log()
 * 
 * When adding on: a boolean, edit constructor and the appropriate lines to log(string, string, string) 
 * 
 * @author icbat
 * @see log(String, String, String)
 * */
public class Lumberjack {
	
	private Boolean file = false;
	private Boolean console = false;
	private FileHandle logfile = null;
	
	/**
	 * Instantiate with specific flags (as opposed to instantiating all False and setting later
	 * */
	public Lumberjack(String gameName, boolean fi, boolean cons) {
		this.file = fi;
		this.console = cons;
		
		if (this.file) {
			logfile = new FileHandle( gameName + "." +  new Date().toString() );
		}
	}
	
	/**
	 * 
	 * */
	public void log( String category, String message, String additional ) {
		String toBeLogged = category + ", " + message + ", " + additional;
		if ( console ) {
			Gdx.app.log("", toBeLogged);
		}
		
		if ( logfile != null ) { //TODO this is the important part, but it can wait
			
		}
	}
	
	public void log( String category, String message ) {
		this.log ( category, message, "" );
	}
	
	public void log( String message ) {
		this.log ( "log", message );
	}
}
