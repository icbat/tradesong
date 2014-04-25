package com.tradesonggame;

import com.tradesonggame.observation.NotificationManager;
import com.tradesonggame.observation.notifications.ScreenSwapNotification;
import com.tradesonggame.observation.watchers.ScreenSwapWatcher;
import com.tradesonggame.screens.AbstractScreen;
import com.tradesonggame.screens.MainMenuScreen;

/**
 * With this implementation, certain "sticky" screens should never call back. This keeps the last map screen seen or the last main menu screen as the back target.
 *
 * Sticky screens for this impl are:
 * - MapScreen
 * - MainMenuScreen
 *
 * @author icbat
 */
public class ShallowSelectiveScreenStack extends NotificationManager implements ScreenManager {

    private Tradesong gameInstance;
    private AbstractScreen lastStickyScreen;
    private AbstractScreen currentScreen;

    public ShallowSelectiveScreenStack(Tradesong gameInstance) {
        this.gameInstance = gameInstance;
        addWatcher(new ScreenSwapWatcher());
    }

    @Override
    public void goToScreen(AbstractScreen destination) {
        if (currentScreen == null || !currentScreen.getScreenName().equalsIgnoreCase(destination.getScreenName()))  {

            if (currentScreen != null && !currentScreen.getScreenName().equals("mainMenuScreen")) {
                notifyWatchers(new ScreenSwapNotification());
            }

            currentScreen = destination;
            gameInstance.setScreen(currentScreen);
            if (currentScreen.getScreenName().equalsIgnoreCase("mainMenuScreen") || currentScreen.getScreenName().equalsIgnoreCase("mapScreen")) {
                lastStickyScreen = currentScreen;
            }
        } else {
            goBack();
        }


    }

    @Override
    public void goBack() {
        goToScreen(lastStickyScreen);
    }

    @Override
    public void goToMainMenu() { goToScreen(new MainMenuScreen()); }

    @Override
    public AbstractScreen getCurrentScreen() {
        return currentScreen;
    }
}
