package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL10;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.HUDStage;
import com.icbat.game.tradesong.stages.InventoryStage;

public class InventoryScreen extends AbstractScreen {

    HUDStage hud;
    InventoryStage inventoryStage;
    private final InputMultiplexer inputMultiplexer;

    public InventoryScreen(Tradesong instance) {
		super(instance);
        hud = new HUDStage(instance);
        inventoryStage = new InventoryStage(instance);


        // Setup an input Multiplexer
        inputMultiplexer = new InputMultiplexer();
        inputMultiplexer.addProcessor(hud);
        inputMultiplexer.addProcessor(inventoryStage);

        Gdx.input.setInputProcessor(inputMultiplexer);
	}

    @Override
    public void render(float delta) {
        super.render(delta);
        Gdx.gl.glClearColor(0.431f, 0.659f, 0.278f, 1); // Greenish BG
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
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

    @Override
    public void show() {
        super.show();
        Gdx.input.setInputProcessor(inputMultiplexer);
    }
}
