package com.icbat.game.tradesong.screens.listeners;

import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Items;

/**
 * Basic Click Listener that knows about its owner.
 */
public class InventoryClickListener extends ClickListener {
    protected Items.Item owner;

    public InventoryClickListener(Items.Item owner) {
        this.owner = owner;
    }


}
