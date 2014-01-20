package com.icbat.game.tradesong;

import com.icbat.game.tradesong.observation.NotificationManager;
import com.icbat.game.tradesong.observation.notifications.ScreenSwapNotification;
import com.icbat.game.tradesong.observation.watchers.ScreenSwapWatcher;
import com.icbat.game.tradesong.screens.AbstractScreen;
import com.icbat.game.tradesong.screens.MainMenuScreen;

/**
 * With this implementation, MapScreen should never call back. This keeps the last map screen seen or the last main menu screen as the back target.
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
    public void goToMainMenu() {
        this.goToScreen(new MainMenuScreen());
    }
}
