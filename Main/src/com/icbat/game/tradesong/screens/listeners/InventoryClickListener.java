package com.icbat.game.tradesong.screens.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Item;

/**
 * Basic Click Listener that knows about its owner.
 */
public class InventoryClickListener extends ClickListener {
    protected Item owner;

    public InventoryClickListener(Item owner) {
        this.owner = owner;
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
    }
}
