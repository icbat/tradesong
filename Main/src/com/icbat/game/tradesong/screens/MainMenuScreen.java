package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.stages.BaseStage;

/***/
public class MainMenuScreen extends AbstractScreen {

    public MainMenuScreen() {
        this.stages.add(new MainMenuStage());
    }

    @Override
    public String getScreenName() {
        return "MAIN-MENU-SCREEN";
    }

    public static class MainMenuStage extends BaseStage {

        public MainMenuStage() {
            Tradesong.clock.stop();

            Table table = new Table();
            this.addActor(table);
            table.debug();
            table.setFillParent(true);

            table.add(getTitleLabel()).spaceBottom(30f).row();
            table.add(getStartButton()).row();
            table.add(getLoadButton()).row();
            table.add(getExitButton());
        }

        private Actor getTitleLabel() {
            return new Label("Tradesong", Tradesong.uiStyles.getLabelStyle());
        }

        private Actor getStartButton() {
            TextButton start = new TextButton("Start New Game", Tradesong.uiStyles.getTextButtonStyle());
            start.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    Tradesong.setupNewGame();
                }
            });
            return start;
        }

        private Actor getLoadButton() {
            TextButton loadButton = new TextButton("Load Saved Game", Tradesong.uiStyles.getTextButtonStyle());
            loadButton.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    Tradesong.state.loadGame();
                }
            });
            return loadButton;
        }

        private Actor getExitButton() {
            TextButton exit = new TextButton("Exit", Tradesong.uiStyles.getTextButtonStyle());
            exit.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    super.clicked(event, x, y);
                    Gdx.app.exit();
                }
            });

            return exit;
        }
    }
}
