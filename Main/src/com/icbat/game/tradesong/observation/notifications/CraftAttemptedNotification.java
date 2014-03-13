package com.icbat.game.tradesong.observation.notifications;

import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.observation.Notification;

/***/
public class CraftAttemptedNotification extends Notification {
    public CraftAttemptedNotification(Item output) {
        super("CRAFT-ATTEMPTED", (output != null));
    }
}
