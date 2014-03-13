package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;

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
