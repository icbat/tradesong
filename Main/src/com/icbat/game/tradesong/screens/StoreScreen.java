package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.InventoryStage;

public class StoreScreen extends AbstractScreen {


    public StoreScreen() {
        super();
        stages.add(Tradesong.getInventoryStage());
        ((InventoryStage) stages.get(1)).setIsInStore(false);
    }

    @Override
    public void render(float delta) {
        super.render(delta, 0.25f, 0.33f, 1, 1);
    }

    @Override
    public void show() {
        super.show();
        ((InventoryStage) stages.get(1)).setIsInStore(true);
    }

    @Override
    public void hide() {
        super.hide();
        ((InventoryStage) stages.get(1)).setIsInStore(false);
    }
}
