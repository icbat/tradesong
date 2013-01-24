package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.icbat.game.tradesong.screens.LevelScreen;

public final class ScreenFactory {
	
	public static final String prefix = "maps/";
	public static final String tag = ScreenFactory.class.getSimpleName();
	
	public static final LevelScreen getLevelScreen( String level, Tradesong game ) {
		Tradesong.debug( tag + ": Loading level: " + level );
		
		String shortPath = prefix + level + ".tmx";
		FileHandle file = Gdx.files.internal( shortPath );
		
		Tradesong.debug( tag + ": " + file.path() );
		
		if( !file.exists() )
			Tradesong.error( tag + ": Level not found!  " + file.exists() );
		
		return ( file.exists() == true ) ? new LevelScreen( file, game ) : null;
	}

}
