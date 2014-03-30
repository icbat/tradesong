package com.icbat.game.tradesong.screens;

public class CraftingScreen extends AbstractScreen {

    @Override
    public void render(float delta) {
        drawBackground(0,0,0.2f,1);
        renderStages(delta);
    }

    @Override
    public String getScreenName() {
        return "craftingScreen";
    }
}
