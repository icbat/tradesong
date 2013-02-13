package com.icbat.game.tradesong;

import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
	@Override
	public boolean keyDown(int keycode) {
		Tradesong.log( new Integer(keycode).toString() );
		
		
		return false;
		
	}
}
