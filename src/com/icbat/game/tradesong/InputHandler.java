package com.icbat.game.tradesong;

import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
	@Override
	public boolean keyDown(int keycode) {
		game.logger.log( new Integer(keycode).toString() );
		
		
		return false;
		
	}
}
