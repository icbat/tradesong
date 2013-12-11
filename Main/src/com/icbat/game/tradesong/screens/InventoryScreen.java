package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.screens.stages.InventoryStage;

/**
 * basic screen that just shows inventory
 * */
public class InventoryScreen extends AbstractScreen {
    public InventoryScreen() {
        stages.add(new InventoryStage());
    }

    @Override
    public void render(float delta) {
        drawBackground(0.9f, 0.3f, 0.3f, 1f);
        renderStages(delta);
    }
}
