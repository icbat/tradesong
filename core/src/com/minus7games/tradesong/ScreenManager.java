package com.minus7games.tradesong;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import java.util.LinkedList;

/***/
public class ScreenManager {
    private static ScreenManager instance = null;
    private final Tradesong gameInstance;
    private final LinkedList<Screen> chain = new LinkedList<Screen>();

    public ScreenManager(Tradesong gameInstance) {
        this.gameInstance = gameInstance;
    }

    public static ScreenManager get() {
        return instance;
    }

    /** Convenience method, adds a screen and adds it to the historical chain */
    public void moveToScreen(Screen screen) {
        moveToScreen(screen, true);
    }

    /** Moves to a screen.
     *
     * @param addToChain if true, will add this screen to the "back trail" */
    public void moveToScreen(Screen screen, boolean addToChain) {
        Gdx.app.log("Screen Manager", "move to screen called with addToChain = " + addToChain);
        if (addToChain) {
            chain.push(screen);
        }
        gameInstance.setScreen(screen);
    }

    /** Sets the screen to the last one moved to and added to the chain, if one exists. */
    public void goBack() {
        Gdx.app.log("Screen Manager", "Go back called");
        if (!chain.isEmpty()) {
            final Screen firstScreen = chain.pop();
            Gdx.app.log("moving to screen", firstScreen.toString());
            gameInstance.setScreen(firstScreen);
        } else {
            Gdx.app.log("no screen found", "doing nothing!");
        }
    }
}
