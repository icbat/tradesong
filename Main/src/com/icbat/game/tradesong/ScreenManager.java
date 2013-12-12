package com.icbat.game.tradesong;

import com.icbat.game.tradesong.screens.AbstractScreen;

/**
 * Provides function for changing screens and keeping track of this. Will likely have to rely on Tradesong as I don't think GDX lets you set screen otherwise.
 *
 * @author icbat
 */
public interface ScreenManager {

    public void goToScreen(AbstractScreen destination);

    public void goBack();

    public void goToMainMenu();
}
