package com.icbat.game;

import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Logger;


/**
 * Logging class that extends Gdx.utils.Logger
 * 
 * (tries) to log to file automatically from the same commands
 * 
 * @author	icbat
 * */
public class LJ extends Logger {
	
	private FileHandle logfile = null;
	
	public LJ() {
		super("");
		makeFileWithName("");
	}
	
	public LJ(String tag) {
		super(tag);
		makeFileWithName("");
	}
	
	public LJ(String tag, String gameName) {
		super(tag);
		makeFileWithName(gameName);
	}
	
	@Override
	public void debug (String message) {
		super.debug(message);
		logToFile("Debug", message);
	} 
	
	@Override
	public void debug (String message, java.lang.Exception exception) {
		super.debug(message, exception);
		logToFile("Debug", message, exception);
	} 
	
	@Override
	public void error (String message) {
		super.error(message);
		logToFile("Error", message);
	} 
	
	@Override
	public void error (String message, java.lang.Throwable exception) {
		super.error(message, exception);
		logToFile("Error", message, exception);
	} 
	
	@Override
	public void info (String message) {
		super.info(message);
		logToFile("Info", message);
	} 
	
	@Override
	public void info (String message, java.lang.Exception exception) {
		super.info(message, exception);
		logToFile("Info", message, exception);
	} 
	
	// Util
	/**
	 * TRY's to make a new logfile with relative path/name given as:
	 * 
	 * LOCAL:"logs/" + gameName + ".log"
	 * 
	 * @param	gameName	Name of the program to help identify where the log was generated
	 * */
	private boolean makeFileWithName(String gameName) {
		//logfile = Gdx.files.local(makeFileName(""));
		
		
		
		String filename = "logs/" + gameName + new Date().toString().replaceAll("\\W", ".") + ".log";
		boolean outcome = false;
//		logfile = Gdx.files.local(filename);
//		logfile = Gdx.files.local("logtest.log");
		outcome = true;
		
		return outcome;
	}
	
	/**
	 * TRY's to write a log message to the open logfile.
	 * 
	 * Fails out to the SUPER class (Gdx.utils.Logger)
	 * 
	 * @param	tag		Which method called this? Debug, Error, or Info?
	 * @param	message	The string to be logged
	 * */
	private void logToFile(String tag, String message) {
		if (logfile != null) 
			this.logfile.writeString(tag + "," + message + ",\n", true);
	}
	
	/**
	 * TRY's to write a log message to the open log file, concatenating any thrown exception included
	 * 
	 * Fails out to the SUPER class's error method
	 * 
	 * @param 	tag		Which method called this? Debug, Error, or Info?
	 * @param	message	The string to be logged
	 * @param	e		A java Throwable that has been thrown, the reason we're logging an error
	 * */
	private void logToFile(String tag, String message, java.lang.Throwable e) {
		if (logfile != null) 
			this.logfile.writeString(tag + "," + message + "," + e.toString() + "\n", true);
	}
}
