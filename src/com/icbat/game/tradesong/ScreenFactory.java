package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.icbat.game.LJ;
import com.icbat.game.tradesong.screens.LevelScreen;

public final class ScreenFactory {
	
	public static final String prefix = "maps/";
	
	public static final LevelScreen getLevelScreen( String level, Tradesong game ) {
		LJ.log("Loading level", level, LJ.LOG);
		
		String shortPath = prefix + level + ".tmx";
		FileHandle file = Gdx.files.internal( shortPath );
		
		LJ.log( "Loading from Path:  ", file.path(), LJ.DEBUG );
		
		if( !file.exists() )
			LJ.log( "Level not found!  ", LJ.ERROR );
		
		return ( file.exists() == true ) ? new LevelScreen( file, game ) : null;
	}

}
