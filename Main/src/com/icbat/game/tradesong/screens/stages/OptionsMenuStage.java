package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.listeners.GoToScreenListener;

/***/
public class OptionsMenuStage extends BaseStage {
    @Override
    public void layout() {
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
            }
        });
        return resumeButton;
    }

    private Actor makeSaveButton() {
        return null;
    }

    private Actor makeOptionsButton() {
        return null;
    }

    private Actor makeExitButton() {
        return null;
    }
}
