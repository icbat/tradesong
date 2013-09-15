package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.InputAdapter;
import com.icbat.game.tradesong.Tradesong;

public class KeyboardHandler extends InputAdapter {

    Tradesong gameInstance;

    public KeyboardHandler(Tradesong gameInstance) {
        this.gameInstance = gameInstance;
    }

    @Override
    public boolean keyDown(int keycode) {

        ScreenTypes current = Tradesong.getCurrentOverlay();

        if (current == null) {
            // There is no overlay, we're looking at a map.
            gameInstance.goToOverlay(ScreenTypes.SETTINGS);
        } else {
            gameInstance.goBack();
        }


        return true;
    }
}
