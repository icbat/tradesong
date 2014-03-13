package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.screens.stages.CraftingStage;

public class CraftingScreen extends AbstractScreen {

    public CraftingScreen() {
        super(new CraftingStage());
    }

    @Override
    public String getScreenName() {
        return "craftingScreen";
    }

    @Override
    public void render(float delta) {
        drawBackground(0.1f,0.5f,0.2f,1);
        renderStages(delta);
    }


}
