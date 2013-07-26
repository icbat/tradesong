package com.icbat.game.tradesong.screens;

import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.InterfaceOverlay;

public class InventoryScreen extends AbstractScreen {

    InterfaceOverlay hud;

	public InventoryScreen(Tradesong instance) {
		super(instance);
        hud = new InterfaceOverlay(instance);


	}

    @Override
    public void render(float delta) {
        super.render(delta);

        hud.draw();
    }
}
