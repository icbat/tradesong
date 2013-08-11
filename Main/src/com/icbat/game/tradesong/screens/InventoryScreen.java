package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;

public class InventoryScreen extends AbstractScreen {

    final InputMultiplexer inputMultiplexer;

    public InventoryScreen(HUDStage hud, InventoryStage inventoryStage) {
		super();
        stages.add(hud);
        stages.add(inventoryStage);

        inventoryStage.setLinkedWorkshop(null); // The equivalent of unsetting this var


        // Setup an input Multiplexer
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud);
        inputMultiplexer.addProcessor(inventoryStage);

        Gdx.input.setInputProcessor(inputMultiplexer);
	}

    @Override
    public void render(float delta) {
        super.render(delta, 0.431f, 0.659f, 0.278f, 1);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }


}
