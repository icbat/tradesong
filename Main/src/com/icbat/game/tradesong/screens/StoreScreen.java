package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;

public class StoreScreen extends AbstractScreen {



    public StoreScreen(HUDStage hud, InventoryStage inventoryStage) {
        stages.add(hud);
        stages.add(inventoryStage);

    }

    @Override
    public void render(float delta) {
        super.render(delta, 0.25f, 0.33f, 1, 1);
    }
}
