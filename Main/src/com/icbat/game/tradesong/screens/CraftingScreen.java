package com.icbat.game.tradesong.screens;

public class CraftingScreen extends AbstractScreen {
    @Override
    public String getScreenName() {
        return "craftingScreen";
    }

    @Override
    public void render(float delta) {
        drawBackground(0.2f,0.9f,0.4f,1);
        renderStages(delta);
    }
}
