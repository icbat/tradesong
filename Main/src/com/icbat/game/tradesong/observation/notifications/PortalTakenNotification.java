package com.icbat.game.tradesong.observation.notifications;

import com.icbat.game.tradesong.observation.Notification;

/***/
public class PortalTakenNotification extends Notification {
    public PortalTakenNotification(String destinationName) {
        super("PORTAL-TAKEN", destinationName);
    }
}
