package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.icbat.game.Lumberjack;
import com.icbat.game.tradesong.screens.LevelScreen;

public final class ScreenFactory {
	
	public static final String prefix = "maps/";
	public static final String tag = ScreenFactory.class.getSimpleName();
	
	public static final LevelScreen getLevelScreen( String level, Tradesong game ) {
		game.logger.log("Loading level", level, Lumberjack.LOG);
		
		String shortPath = prefix + level + ".tmx";
		FileHandle file = Gdx.files.internal( shortPath );
		
		game.logger.log( "Loading from Path:  ", file.path(), Lumberjack.DEBUG );
		
		if( !file.exists() )
			game.logger.log( "Level not found!  ", Lumberjack.ERROR );
		
		return ( file.exists() == true ) ? new LevelScreen( file, game ) : null;
	}

}
