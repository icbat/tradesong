package com.icbat.game.tradesong;

import com.icbat.game.tradesong.screens.components.PopupNotification;

import java.util.LinkedList;

public class PopupQueue {

    private LinkedList<PopupNotification> messageQueue = new LinkedList<PopupNotification>();

    public LinkedList<PopupNotification> emptyQueue() {
        LinkedList<PopupNotification> copyOfQueue = new LinkedList<PopupNotification>(messageQueue);
        messageQueue.clear();
        return copyOfQueue;
    }

    public void addPopup(PopupNotification popup) {
        messageQueue.add(popup);
    }


}
