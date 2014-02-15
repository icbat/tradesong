package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * An empty actor whose dimensions are used for spacing the table.
 */
public class SpacingActor extends Actor{

    public SpacingActor() {
        this(4, 4);
    }

    public SpacingActor(int width, int height) {
        this.setWidth(width);
        this.setHeight(height);
    }

}