package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.icbat.game.tradesong.Tradesong;

public class MainMenuScreen extends AbstractScreen {

	

	private Stage stage;
	private Table table;

	public MainMenuScreen(Tradesong game) {
		super(game);
		stage = new Stage();
        table = new Table();

//        Gdx.input.setInputProcessor(stage);

        
        table.setFillParent(true);
        stage.addActor(table);
        table.debug();

        // Add widgets to the table here.
        Skin skin = new Skin();
        
        Label nameLabel = new Label("Name:", null);
        TextField nameText = new TextField("NAME", skin);
        Label addressLabel = new Label("Address:", skin);
        TextField addressText = new TextField("ADDR", skin);

        Table table = new Table();
        table.add(nameLabel);
        table.add(nameText).width(100);
        table.row();
        table.add(addressLabel);
        table.add(addressText).width(100);
        
        log("Number of cells " + table.getCells().size());
	}

	public void resize (int width, int height) {
	        stage.setViewport(width, height, true);
	        log("Stage set to " + width + "w by " + height + "h");
	        log("Table is " + table.getWidth());
	        table.setWidth(width);
	        table.setHeight(height);
	        log("Table is NOW " + table.getWidth());
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
