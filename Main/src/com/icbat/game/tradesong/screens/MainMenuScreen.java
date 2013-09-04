package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.AbstractStage;

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

    public static class MainMenuStage extends AbstractStage {

        private final Tradesong gameInstance;
        private TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        private Table table = new Table();

        public MainMenuStage(final Tradesong gameInstance) {
            this.gameInstance = gameInstance;

            this.buttonStyle.font = new BitmapFont();
            this.buttonStyle.fontColor = Color.WHITE;
            this.buttonStyle.overFontColor = Color.BLUE;

            this.table.setFillParent(true);
            this.addActor(this.table);

            layout();
        }

        @Override
        public void layout() {

            table.clear();

            table.add(newStartButton());
            table.row();
            table.add(newSettingsButton());
            table.row();
            table.add(newExitButton());
        }

        private TextButton newStartButton() {
            TextButton startButton = new TextButton("Start game", buttonStyle);

            startButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gameInstance.goToScreen(Tradesong.ScreenTypes.TOWN);
                }
            });

            return startButton;
        }

        private TextButton newSettingsButton() {
            TextButton settingsButton = new TextButton("Settings", buttonStyle);

            settingsButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gameInstance.goToScreen(Tradesong.ScreenTypes.SETTINGS);
                }
            });

            return settingsButton;
        }

        private TextButton newExitButton() {
            TextButton exitButton = new TextButton("Exit game", buttonStyle);

            exitButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    Gdx.app.exit();
                }
            });

            return exitButton;
        }


    }
}