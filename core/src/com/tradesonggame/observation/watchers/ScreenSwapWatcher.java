package com.tradesonggame.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.SoundAssets;
import com.tradesonggame.observation.Notification;
import com.tradesonggame.observation.Watcher;
import com.tradesonggame.observation.notifications.ScreenSwapNotification;

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
