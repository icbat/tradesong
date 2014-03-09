package com.icbat.game.tradesong.observation.notifications;

import com.icbat.game.tradesong.gameObjects.Contract;
import com.icbat.game.tradesong.observation.Notification;

/***/
public class ContractCompletedNotification extends Notification {
    public ContractCompletedNotification(Contract contents) {
        super("CONTRACT-COMPLETED", contents);
    }
}
