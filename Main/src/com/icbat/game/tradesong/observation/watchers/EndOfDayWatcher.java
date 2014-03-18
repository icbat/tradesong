package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.GameState;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.popups.EndOfDayPopup;
import com.icbat.game.tradesong.assetReferences.SoundAssets;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;

/***/
public class EndOfDayWatcher implements Watcher {

    private final Sound endOfDaySound = Tradesong.getSound(SoundAssets.CHURCHBELL);

    @Override
    public void handleNotification(Notification notification) {
        if (shouldAct(notification)) {
            endOfDaySound.play();
            GameState.clock.scheduleNonRepeatingTask(new Timer.Task() {
                @Override
                public void run() {
                    Tradesong.screenManager.getCurrentScreen().addPopup(new EndOfDayPopup());
                    GameState.inventory.addMoney(-200);
                    GameState.clock.startDay();
                }
            }, 3); // delay roughly the time of the soundeffect for some notice.
        }
    }

    private boolean shouldAct(Notification notification) {
        return notification.getAction().equalsIgnoreCase("DAY-ENDED");
    }
}
