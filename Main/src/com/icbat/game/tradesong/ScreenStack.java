package com.icbat.game.tradesong;

import com.badlogic.gdx.Screen;
import com.icbat.game.tradesong.screens.MainMenuScreen;

import java.util.Deque;
import java.util.LinkedList;

/**
 * An attempt at using Stack logic to keep track of this information.
 *
 * @author icbat
 */
public class ScreenStack implements ScreenManager {

    private Tradesong gameInstance;
    private Deque<Screen> screens = new LinkedList<Screen>();

    public ScreenStack(Tradesong gameInstance) {
        this.gameInstance = gameInstance;
    }

    @Override
    public void goToScreen(Screen destination) {
        screens.add(destination);
        gameInstance.setScreen(destination);
    }

    @Override
    public void goBack() {
        screens.pop();
        gameInstance.setScreen(screens.peek());
    }

    @Override
    public void goToMainMenu() {
        screens.clear();
        this.goToScreen(new MainMenuScreen());
    }
}
