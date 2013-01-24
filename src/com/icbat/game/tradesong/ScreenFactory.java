package com.icbat.game.tradesong;

import com.badlogic.gdx.files.FileHandle;
import com.icbat.game.tradesong.screens.LevelScreen;

public final class ScreenFactory {
	
	public static final String tag = ScreenFactory.class.getSimpleName();
	
	public static final LevelScreen getLevelScreen( String level, Tradesong game ) {
		Tradesong.debug( tag + ": Loading level: " + level );
		return new LevelScreen( new FileHandle(level), game );
	}

}
