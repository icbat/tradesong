package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.icbat.game.tradesong.Tradesong;

public class MainMenuScreen extends AbstractScreen {

	

	private Stage stage;
	private Table table;

	public MainMenuScreen(Tradesong game) {
		super(game);
		stage = new Stage();
        table = new Table();

        log("create called!");
        Gdx.input.setInputProcessor(stage);

        
        table.setFillParent(true);
        stage.addActor(table);
        table.debug();

        // Add widgets to the table here.
        TextButtonStyle style = new TextButtonStyle();
        style.font = new BitmapFont();
        
        TextButton newGameButton = new TextButton("New Game", style);
        newGameButton.setWidth(100);
        newGameButton.setHeight(100);
        newGameButton.setColor(Color.CYAN);
        table.add(newGameButton);
	}

	public void resize (int width, int height) {
	        stage.setViewport(width, height, true);
	        log("Stage set to " + width + "w by " + height + "h");
	        log("Table is " + table.getWidth());
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
