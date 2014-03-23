package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.components.ContractRewardPopup;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;

/***/
public class ContractCompletedWatcher implements Watcher {

    private Sound completionSound = Tradesong.getSound(SoundAssets.SUCCESS);

    @Override
    public void handleNotification(Notification notification) {
        if (verifyICare(notification)) {

            Actor popup = new ContractRewardPopup((Contract) notification.getContents());

            completionSound.play();
        }
    }

    private boolean verifyICare(Notification notification) {
        return notification.getAction().equalsIgnoreCase("CONTRACT-COMPLETED");
    }
}
