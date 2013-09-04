package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.MainMenuStage;

/**
 * Screen shown first, directs user to other screens/functions
 * */
public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(final Tradesong gameInstance) {
		super();
		batch = new SpriteBatch();
		stages.add(new MainMenuStage(gameInstance));

//		// Configure a TextButtonStyle and name it "default". Skin resources are stored by type, so this doesn't overwrite the font.
//		TextButtonStyle textButtonStyle = new TextButtonStyle();
//		textButtonStyle.over = mainMenuSkin.newDrawable("pixel", Color.LIGHT_GRAY);
//		textButtonStyle.downFontColor = Color.LIGHT_GRAY;
//		textButtonStyle.fontColor = Color.WHITE;
//		textButtonStyle.font = mainMenuSkin.getFont("default");
//		mainMenuSkin.add("default", textButtonStyle);
//
//		// Create a table that fills the screen. Everything else will go inside this table.
//		Table table = new Table();
//		table.setFillParent(true);
//		stage.addActor(table);
//
//		// Create a button with the "default" TextButtonStyle. A 3rd parameter can be used to specify a name other than "default".
//		final TextButton newButton = new TextButton("New Game", mainMenuSkin);
//		final TextButton exitButton = new TextButton("Exit", mainMenuSkin);
//		table.add(newButton);
//		table.row();
//		table.add(exitButton);
//
//		// Add a listener to the button. ChangeListener is fired when the button's checked state changes, eg when clicked,
//		// Button#setChecked() is called, via a key press, etc. If the event.cancel() is called, the checked state will be reverted.
//		// ClickListener could have been used, but would only fire when clicked. Also, canceling a ClickListener event won't
//		// revert the checked state.
//		newButton.addListener(new ChangeListener() {
//				public void changed (ChangeEvent event, Actor actor) {
//                    gameInstance.goToScreen(Tradesong.ScreenTypes.TOWN);
//				}
//			}
//
//		);
//
//		exitButton.addListener(new ChangeListener() {
//				public void changed (ChangeEvent event, Actor actor) {
//					Gdx.app.exit();
//			}
//		});

	}
}