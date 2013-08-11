package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;
import com.icbat.game.tradesong.stages.WorkshopStage;

public class WorkshopScreen extends InventoryScreen{

    public WorkshopScreen(HUDStage hud, InventoryStage inventoryStage, WorkshopStage workshopStage) {
        super(hud, inventoryStage);

        stages.add(workshopStage);
        inventoryStage.setLinkedWorkshop(workshopStage);
        inventoryStage.connectItemsToWorkshop();
        inputMultiplexer.addProcessor(workshopStage);

    }

    @Override
    public void render(float delta) {
        super.render(delta, 0.98f, 0.639f, 0.008f, 1);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
    }

    @Override
    public void hide() {
        super.hide();
        ((WorkshopStage)stages.get(2)).clearIngredients(true);
        ((InventoryStage)stages.get(1)).setLinkedWorkshop(null);
    }
}
