package com.tradesonggame.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.SoundAssets;
import com.tradesonggame.observation.Notification;
import com.tradesonggame.observation.Watcher;

/***/
public class CraftAttemptedWatcher implements Watcher {
    private final Sound success = Tradesong.getSound(SoundAssets.SUCCESS);
    private final Sound failure = Tradesong.getSound(SoundAssets.FAIL);
    @Override
    public void handleNotification(Notification notification) {
        if (notification.getAction().equalsIgnoreCase("CRAFT-ATTEMPTED")) {
            if ((Boolean) notification.getContents()) {
                success.play();
            } else {
                failure.play();
            }

        }
    }
}
