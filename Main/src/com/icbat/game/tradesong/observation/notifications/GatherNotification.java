package com.icbat.game.tradesong.observation.notifications;

import com.icbat.game.tradesong.observation.Notification;
import com.icbat.game.tradesong.gameObjects.Item;

/***/
public class GatherNotification extends Notification {
    public GatherNotification(Item contents) {
        super("GATHER", contents);
    }
}
