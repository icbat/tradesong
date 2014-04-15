package com.icbat.game.tradesong.observation.notifications;

import com.icbat.game.tradesong.gameObjects.collections.Items;
import com.icbat.game.tradesong.observation.Notification;

/***/
public class CraftAttemptedNotification extends Notification {
    public CraftAttemptedNotification(Items.Item output) {
        super("CRAFT-ATTEMPTED", (output != null));
    }
}
