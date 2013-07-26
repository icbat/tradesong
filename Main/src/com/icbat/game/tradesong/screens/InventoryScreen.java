package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;

public class InventoryScreen extends AbstractScreen {

    HUDStage hud;
    InventoryStage inventoryStage;

	public InventoryScreen(Tradesong instance) {
		super(instance);
        hud = new HUDStage(instance);
        inventoryStage = new InventoryStage(instance);
	}

    @Override
    public void render(float delta) {
        super.render(delta);
        hud.act(delta);
        inventoryStage.act(delta);
        hud.draw();
        inventoryStage.draw();
    }
}
