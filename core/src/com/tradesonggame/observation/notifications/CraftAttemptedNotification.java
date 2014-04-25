package com.tradesonggame.observation.notifications;

import com.tradesonggame.gameObjects.collections.Items;
import com.tradesonggame.observation.Notification;

/***/
public class CraftAttemptedNotification extends Notification {
    public CraftAttemptedNotification(Items.Item output) {
        super("CRAFT-ATTEMPTED", (output != null));
    }
}
