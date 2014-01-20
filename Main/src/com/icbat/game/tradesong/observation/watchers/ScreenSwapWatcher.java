package com.icbat.game.tradesong.observation.watchers;

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
    public void handleNotification(Notification notification) {
        if (!verifyICare(notification)) {
            return;
        }

        swapSound.stop();
        swapSound.play();

    }

    private boolean verifyICare(Notification notification) {
        return notification != null && notification instanceof ScreenSwapNotification;
    }
}
