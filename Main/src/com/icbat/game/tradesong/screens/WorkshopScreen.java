package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;
import com.icbat.game.tradesong.stages.WorkshopStage;

public class WorkshopScreen extends InventoryScreen{

    private WorkshopStage workshopStage;

    public WorkshopScreen(HUDStage hud, InventoryStage inventoryStage, WorkshopStage workshopStage) {
        super(hud, inventoryStage);

        this.workshopStage = workshopStage;
        inventoryStage.setLinkedWorkshop(workshopStage);
        inventoryStage.connectItemsToWorkshop();
        inputMultiplexer.addProcessor(workshopStage);

    }

    @Override
    public void show() {
        super.show();
        inputMultiplexer.addProcessor(workshopStage);
    }

    @Override
    public void render(float delta) {
        super.render(delta, 0.98f, 0.639f, 0.008f, 1);
        workshopStage.act();
        workshopStage.draw();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        workshopStage.setViewport(width, height, false);
    }

    @Override
    public void hide() {
        super.hide();
        workshopStage.clearIngredients(true);
    }
}
