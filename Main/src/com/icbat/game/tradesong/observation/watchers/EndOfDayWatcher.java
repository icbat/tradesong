package com.icbat.game.tradesong.observation.watchers;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.Timer;
import com.icbat.game.tradesong.Tradesong;
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
            Tradesong.clock.scheduleNonRepeatingTask(new Timer.Task() {
                @Override
                public void run() {
                    Tradesong.state.inventory().addMoney(-200);
                    Tradesong.clock.startDay();
                }
            }, 3); // delay roughly the time of the soundeffect for some notice.
        }
    }

    private boolean shouldAct(Notification notification) {
        return notification.getAction().equalsIgnoreCase("DAY-ENDED");
    }
}
