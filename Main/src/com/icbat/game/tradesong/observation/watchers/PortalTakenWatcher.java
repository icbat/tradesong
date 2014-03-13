package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;

/***/
public class PortalTakenWatcher implements Watcher {
    private final Sound portalSound = Tradesong.getSound(SoundAssets.PORTAL);
    @Override
    public void handleNotification(Notification notification) {
        if (notification.getAction().equalsIgnoreCase("PORTAL-TAKEN")) {
            portalSound.play();
        }
    }
}
