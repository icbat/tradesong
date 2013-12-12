package com.icbat.game.tradesong;

import com.badlogic.gdx.Gdx;
import com.icbat.game.tradesong.screens.AbstractScreen;
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
    private Deque<AbstractScreen> screens = new LinkedList<AbstractScreen>();

    public ScreenStack(Tradesong gameInstance) {
        this.gameInstance = gameInstance;
    }

    @Override
    public void goToScreen(AbstractScreen destination) {
        if (screens.isEmpty()) {
            screens.add(destination);
            gameInstance.setScreen(destination);
        } else if (screens.peekLast().getScreenName().equals(destination.getScreenName())) {
            goBack();
        } else {    // TODO this should be able to be rewritten
            screens.add(destination);
            gameInstance.setScreen(destination);
        }

    }

    @Override
    public void goBack() {
        Gdx.app.debug("screenStack", "back pressed");
        if (!screens.isEmpty()) {
            screens.removeLast();
            gameInstance.setScreen(screens.peekLast());
        }
    }

    @Override
    public void goToMainMenu() {
        screens.clear();
        this.goToScreen(new MainMenuScreen());
    }
}
