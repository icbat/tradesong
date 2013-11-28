package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.InventoryStage;

public class InventoryScreen extends AbstractScreen {

    public InventoryScreen() {
        super();
        stages.add(Tradesong.getInventoryStage());

        ((InventoryStage) stages.get(1)).setLinkedWorkshop(null);
    }

    @Override
    public void render(float delta) {
        super.render(delta, 0.431f, 0.659f, 0.278f, 1);
    }
}
