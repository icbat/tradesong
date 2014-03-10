package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.actors.EndOfDayPopup;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;

/***/
public class EndOfDayWatcher implements Watcher {

    private final Sound endOfDaySound = Tradesong.getSound(SoundAssets.CHURCHBELL);

    @Override
    public void handleNotification(Notification notification) {
        if (shouldAct(notification)) {
            // TODO play sound
            Tradesong.screenManager.getCurrentScreen().addActor(new EndOfDayPopup());
            Tradesong.inventory.addMoney(-200);
            endOfDaySound.play();
        }
    }

    private boolean shouldAct(Notification notification) {
        return notification.getAction().equalsIgnoreCase("DAY-ENDED");
    }
}
