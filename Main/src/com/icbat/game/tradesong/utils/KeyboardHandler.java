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

        ScreenTypes current = Tradesong.getCurrentScreenType();

        switch(current) {
            case LEVEL:
                gameInstance.goToScreen(ScreenTypes.MAIN_MENU);
                break;
            case MAIN_MENU:
                break;

            default:
                gameInstance.leaveOverlap();

        }

        return true;
    }
}
