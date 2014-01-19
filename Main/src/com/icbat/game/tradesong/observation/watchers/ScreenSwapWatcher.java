package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;
import com.icbat.game.tradesong.observation.notifications.ScreenSwapNotification;

/***/
public class ScreenSwapWatcher implements Watcher {

    private Sound swapSound = Tradesong.getSound(SoundAssets.SCREEN_SWAP);

    @Override
    public void handleNotification(Object sender, Notification notification) {
        Gdx.app.debug("Screen Swap Watcher", "Checking if I care");
        if (!verifyICare(sender, notification)) {
            return;
        }

        Gdx.app.debug("Screen Swap Watcher", "Swapping");

        swapSound.stop();
        swapSound.play();
    }

    @Override
    public boolean verifyICare(Object sender, Notification notification) {
        if (sender ==  null || notification == null) {
            return false;
        }

        return notification instanceof ScreenSwapNotification;
    }
}
