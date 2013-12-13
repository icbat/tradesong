package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.screens.stages.CraftingStage;import com.icbat.game.tradesong.screens.stages.InventoryStage;

public class CraftingScreen extends AbstractScreen {

    public CraftingScreen() {
        stages.add(new CraftingStage());
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
