package com.icbat.game.tradesong.observation.notifications;

import com.icbat.game.tradesong.observation.Notification;
import gameObjects.Item;

/***/
public class GatherNotification extends Notification {
    public GatherNotification(Item contents) {
        super("GATHER", contents);
    }
}
