package com.tradesonggame.observation.notifications;

import com.tradesonggame.observation.Notification;

/***/
public class PortalTakenNotification extends Notification {
    public PortalTakenNotification(String destinationName) {
        super("PORTAL-TAKEN", destinationName);
    }
}
