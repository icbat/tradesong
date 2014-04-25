package com.tradesonggame.observation.notifications;

import com.tradesonggame.gameObjects.collections.Items;
import com.tradesonggame.observation.Notification;

/***/
public class GatherNotification extends Notification {
    public GatherNotification(Items.Item contents) {
        super("GATHER", contents);
    }
}
