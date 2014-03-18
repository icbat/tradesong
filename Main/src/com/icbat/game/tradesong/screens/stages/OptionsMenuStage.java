package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.GameState;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.listeners.GoToScreenListener;

/***/
public class OptionsMenuStage extends BaseStage {
    @Override
    public void layout() {
        GameState.clock.pause();
        super.layout();

        Table layout = new Table();
        layout.setFillParent(true);
        layout.add(makeMenuTitleLabel());
        layout.row();
        layout.add(makeResumeButton());
        layout.row();
        layout.add(makeSaveButton());
        layout.row();
        layout.add(makeOptionsButton());
        layout.row();
        layout.add(makeExitButton());
        layout.row();
        layout.add(makeQuitGameButton());

        this.addActor(layout);

    }

    private Actor makeMenuTitleLabel() {
        return new Label("Options", Tradesong.uiStyles.getLabelStyle());
    }

    private Actor makeResumeButton() {
        TextButton resumeButton = new TextButton("Resume game", Tradesong.uiStyles.getTextButtonStyle());
        resumeButton.addListener(new GoToScreenListener() {
            @Override
            protected void goToTargetScreen() {
                Tradesong.screenManager.goBack();
                GameState.clock.resume();
            }
        });
        return resumeButton;
    }

    private Actor makeSaveButton() {
        TextButton saveButton = new TextButton("Save game", Tradesong.uiStyles.getTextButtonStyle());
        saveButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                Tradesong.saveGame();
            }
        });
        return saveButton;
    }

    private Actor makeOptionsButton() {
        TextButton optionsButton = new TextButton("Settings", Tradesong.uiStyles.getDisabledButtonStyle());
        return optionsButton;
    }

    private Actor makeExitButton() {
        TextButton exitButton = new TextButton("Exit to main menu", Tradesong.uiStyles.getTextButtonStyle());
        exitButton.addListener(new GoToScreenListener() {
            @Override
            protected void goToTargetScreen() {
                Tradesong.screenManager.goToMainMenu();
            }
        });
        return exitButton;
    }

    private Actor makeQuitGameButton() {
        TextButton quitButton = new TextButton("Quit to desktop", Tradesong.uiStyles.getTextButtonStyle());
        quitButton.addListener(new GoToScreenListener() {
            @Override
            protected void goToTargetScreen() {
                Gdx.app.exit();
            }
        });
        return quitButton;
    }
}
