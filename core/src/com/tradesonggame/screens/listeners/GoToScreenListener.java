package com.tradesonggame.screens.listeners;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Listener to move between screens. Must be child, and child must implement the actual call to goToScreen. This class gives a common interface to call more easily.
 */
public abstract class GoToScreenListener extends ClickListener{

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
        goToTargetScreen();
    }

    protected abstract void goToTargetScreen();
}
