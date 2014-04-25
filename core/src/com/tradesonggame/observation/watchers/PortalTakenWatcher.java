package com.tradesonggame.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.tradesonggame.Tradesong;
import com.tradesonggame.assetReferences.SoundAssets;
import com.tradesonggame.observation.Notification;
import com.tradesonggame.observation.Watcher;

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
