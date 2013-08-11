package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;

public class StoreScreen extends AbstractScreen {



    public StoreScreen(HUDStage hud, InventoryStage inventoryStage) {
        stages.add(hud);
        stages.add(inventoryStage);

    }
}
