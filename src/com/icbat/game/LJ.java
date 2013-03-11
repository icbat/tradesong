package com.icbat.game;

import java.util.Date;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Logger;

public class LJ extends Logger {
	
	// Categories
	public static final int ERROR = 0;
	public static final int LOG = 1;
	public static final int DEBUG = 2;
	
	private FileHandle logfile = null;
	
	public LJ() {
		super("");
		logfile = Gdx.files.local(makeFileName(""));
	}
	
	public LJ(String tag) {
		super(tag);
		logfile = Gdx.files.local(makeFileName(""));
	}
	
	public LJ(String tag, String gameName) {
		super(tag);
		logfile = Gdx.files.local(makeFileName(gameName));
	}
	
	@Override
	public void debug (String message) {
		super.debug(message);
		if (logfile != null)
			logToFile("Debug", message);
	} 
	
	@Override
	public void debug (String message, java.lang.Exception exception) {
		super.debug(message, exception);
		if (logfile != null)
			logToFile("Debug", message, exception);
	} 
	
	@Override
	public void error (String message) {
		super.error(message);
		if (logfile != null)
			logToFile("Error", message);
	} 
	
	@Override
	public void error (String message, java.lang.Throwable exception) {
		super.error(message, exception);
		if (logfile != null)
			logToFile("Error", message, exception);
	} 
	
	@Override
	public void info (String message) {
		super.info(message);
		if (logfile != null)
			logToFile("Info", message);
	} 
	
	@Override
	public void info (String message, java.lang.Exception exception) {
		super.info(message, exception);
		if (logfile != null)
			logToFile("Info", message, exception);
	} 
	
	// Util
	private String makeFileName(String gameName) {
		return "logs/" + gameName + new Date().toString().replaceAll("\\W", ".") + ".log";
	}
	
	private void logToFile(String tag, String message) {
		this.logfile.writeString(tag + "," + message + ",\n", true);
	}
	
	private void logToFile(String tag, String message, java.lang.Throwable e) {
		this.logfile.writeString(tag + "," + message + "," + e.toString() + "\n", true);
	}
}
