package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class InputHandler extends InputAdapter {
    @Override
    public boolean keyTyped(char character) {
        Gdx.app.log("typed", character + "");
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        Gdx.app.log("up", keycode+ "");
        return true;
    }

    @Override
    public boolean keyDown(int keycode) {
        Gdx.app.log("down", keycode+ "");
        return true;
    }
}
