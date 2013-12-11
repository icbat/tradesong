package com.icbat.game.tradesong.screens;

/**
 * Base, extendable screen that just shows inventory.
 * */
public class InventoryScreen extends AbstractScreen {
    public InventoryScreen() {

    }

    @Override
    public void render(float delta) {
        drawBackground(0.9f, 0.3f, 0.3f, 1f);
        renderStages(delta);
    }
}
