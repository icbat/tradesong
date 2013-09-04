package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
		stages.add(new MainMenuStage(gameInstance));
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