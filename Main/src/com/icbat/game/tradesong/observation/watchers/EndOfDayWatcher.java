package com.icbat.game.tradesong.observation.watchers;

import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.actors.EndOfDayPopup;
import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.observation.Watcher;

/***/
public class EndOfDayWatcher implements Watcher {
    @Override
    public void handleNotification(Notification notification) {
        if (shouldAct(notification)) {
            // TODO play sound
            Tradesong.screenManager.getCurrentScreen().addActor(new EndOfDayPopup());
            Tradesong.inventory.addMoney(-200);
        }
    }

    private boolean shouldAct(Notification notification) {
        return notification.getAction().equalsIgnoreCase("DAY-ENDED");
    }
}
