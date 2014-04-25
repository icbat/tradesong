package com.tradesonggame.screens.components;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.tradesonggame.Tradesong;

public class ItemDescriptionBlock extends Table {

    public ItemDescriptionBlock() {
        this.setFillParent(true);
        this.align(Align.bottom + Align.right);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        Actor focusedItem = Tradesong.focusedItem;
        this.clear();
        if (focusedItem != null) {
            add(focusedItem);
        }
    }

}
