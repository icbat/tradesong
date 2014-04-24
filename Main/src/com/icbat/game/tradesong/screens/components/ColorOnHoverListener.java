package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ColorOnHoverListener extends ClickListener {
    private final Actor item;
    Color originalColor = Color.WHITE;
    protected final Color onHoverColor;

    public ColorOnHoverListener(Actor item, Color onHoverColor) {
        this.item = item;
        this.onHoverColor = onHoverColor;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        originalColor = new Color(item.getColor());
        item.setColor(onHoverColor);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
        item.setColor(originalColor);
    }
}
