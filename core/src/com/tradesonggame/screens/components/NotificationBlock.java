package com.tradesonggame.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.tradesonggame.Tradesong;

public class NotificationBlock extends Table {
    public NotificationBlock() {
        this.setFillParent(true);
        this.align(Align.right + Align.top);
    }

    private void addNotification(PopupNotification notification) {
        this.add(notification).row();
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        for (PopupNotification popup : Tradesong.popupQueue.emptyQueue()) {
            addNotification(popup);
        }
    }
}
