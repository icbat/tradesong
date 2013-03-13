package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.icbat.game.tradesong.Tradesong;

public class MainMenuScreen extends AbstractScreen {

	private Stage stage;
	public MainMenuScreen(Tradesong game) {
		super(game);
		stage = new Stage();
		// TODO Auto-generated constructor stub
	}
	

	public void create () {
	        Gdx.input.setInputProcessor(stage);

	        Table table = new Table();
	        table.setFillParent(true);
	        stage.addActor(table);

	        // Add widgets to the table here.
	}

	public void resize (int width, int height) {
	        stage.setViewport(width, height, true);
	}

	public void render () {
	        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	        stage.act(Gdx.graphics.getDeltaTime());
	        stage.draw();

	        Table.drawDebug(stage); // This is optional, but enables debug lines for tables.
	}

	public void dispose() {
	        stage.dispose();
	}

}
