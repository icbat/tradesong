package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.InputMultiplexer;
import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;

public class InventoryScreen extends AbstractScreen {

    public InventoryScreen(HUDStage hud, InventoryStage inventoryStage) {
		super();
        stages.add(hud);
        stages.add(inventoryStage);

        inventoryStage.setLinkedWorkshop(null); // The equivalent of unsetting this var


        // Setup an input Multiplexer
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud);
        inputMultiplexer.addProcessor(inventoryStage);

	}

    @Override
    public void render(float delta) {
        super.render(delta, 0.431f, 0.659f, 0.278f, 1);
    }
}
