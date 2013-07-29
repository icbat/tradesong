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

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);
        inventoryStage.setViewport(width, height, false);
        hud.setViewport(width, height, false); //TODO probably a way to put all of this in to abstractScreen later
    }
}
