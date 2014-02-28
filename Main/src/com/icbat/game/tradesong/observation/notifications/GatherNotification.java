package com.icbat.game.tradesong.observation.notifications;

import com.icbat.game.tradesong.gameObjects.Item;
import com.icbat.game.tradesong.observation.Notification;

/***/
public class GatherNotification extends Notification {
    public GatherNotification(Item contents) {
        super("GATHER", contents);
    }
}
