package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.stages.AbstractStage;
import com.icbat.game.tradesong.utils.ScreenTypes;

/**
 * Screen shown first, directs user to other screens/functions
 * */
public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen(final Tradesong gameInstance) {
        stages.clear();
		stages.add(new MainMenuStage(gameInstance));
	}

    public static class MainMenuStage extends AbstractStage {

        private final Tradesong gameInstance;
        private Table table = new Table();

        public MainMenuStage(final Tradesong gameInstance) {
            this.gameInstance = gameInstance;

            this.table.setFillParent(true);
            this.addActor(this.table);

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
            TextButton startButton = new TextButton("Start game", Tradesong.uiStyles.getTextButtonStyle());

            startButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gameInstance.changeMap("guild_hall");
                }
            });

            return startButton;
        }

        private TextButton newSettingsButton() {
            TextButton settingsButton = new TextButton("Settings", Tradesong.uiStyles.getTextButtonStyle());

            settingsButton.addListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    gameInstance.goToOverlay(ScreenTypes.SETTINGS);
                }
            });

            return settingsButton;
        }

        private TextButton newExitButton() {
            TextButton exitButton = new TextButton("Exit game", Tradesong.uiStyles.getTextButtonStyle());

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