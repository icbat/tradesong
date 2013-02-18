package com.icbat.game;

import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

/**
 * Logging utility class to allow seem-less logging to either console, file, or both
 * 
 * To use: Instantiate with the appropriate flags for logging, then use log()
 * 
 * When extending this: a boolean, edit constructor and the appropriate lines to log(string, string, string) 
 * 
 * @author icbat
 * @see log(String, String, String)
 * */
public class Lumberjack {
	
	// Categories
	public static final int ERROR = 0;
	public static final int LOG = 1;
	public static final int DEBUG = 2;
	
	private Boolean file = false;
	private Boolean console = false;
	private FileHandle logfile = null;
	private String gameName = "";
	
	/**
	 * Instantiate with specific flags for each method of logging
	 * 
	 * @param	gameName	String name of the Game for file-naming
	 * @param 	fi			Boolean: True if you want to log to a file
	 * @param	cons		Boolean: True if you want to log to the console/logcat
	 * */
	public Lumberjack( String gameName, boolean fi, boolean cons ) {
		this.file = fi;
		this.console = cons;
		this.gameName = gameName;
		
		if ( this.file ) {
			logfile = new FileHandle( gameName + "." +  new Date().toString() + ".log" );
		}
		
		// Some initial logging (type and version)
		this.log( Gdx.app.getType().toString(), "Version:  " + Gdx.app.getVersion(), LOG );
	}
	
	/**
	 * Workhorse of the class. This method will handle the actual logging of events.
	 * 
	 * @param	message		The message to be logged
	 * @param	className	Class Name of the caller	
	 * @param	category	int (or constant) specifying ERROR, LOG, or DEBUG mode
	 * */
	public void log( String message, String className, int category ) {
		String toBeLogged = category + ", " + className + ", " + message;
		
		// Console Logging
		if ( console ) {
			switch ( category ) {
			case LOG:
				Gdx.app.log( "", toBeLogged );
				break;
			case ERROR:
				Gdx.app.error( "", toBeLogged);
				Gdx.app.error( "", "Java heap in bytes:  " + Gdx.app.getJavaHeap() );
				Gdx.app.error( "", "Native heap in bytes:  " + Gdx.app.getNativeHeap() );
				break;
			case DEBUG:
				Gdx.app.debug( "", toBeLogged );
				break;
			// If something's wrong with the category, go ahead and log
			default:
				Gdx.app.log( "", toBeLogged );
				break;
			}
		}
		
		// File logging
		if ( logfile != null ) {
			logfile.writeString( toBeLogged, true );
			if ( category == ERROR ) {
				logfile.writeString( "Java heap in bytes:  " + Gdx.app.getJavaHeap(), true );
				logfile.writeString( "Native heap in bytes:  " + Gdx.app.getNativeHeap(), true );
			}
		}
	}
	
	/**
	 * Specify a category but no additional column (like variables, stack types, etc.)
	 * 
	 * @param	message		The message to be logged
	 * @param	category	int (or constant) specifying ERROR, LOG, or DEBUG mode
	 * */
	public void log( String message, int category ) {
		this.log ( message, gameName, category );
	}
	
	/**
	 * By default, this will assume LOG-level of sensitivity
	 * 
	 * @param	message	The message to be logged
	 * */
	public void log( String message ) {
		this.log ( message, LOG );
	}
	
	/** Wrapper so this class can encapsulate all logging
	 * ONLY pertains to console logging; file logging and any others will still log everything always
	 * 
	 * @see	Gdx.app
	 *  */
	public void setSensitivity( int logLevel ) {
		Gdx.app.setLogLevel( logLevel );
	}
}
