package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/***/
public class OptionsMenuStage extends BaseStage {
    @Override
    public void layout() {
        super.layout();

        Table layout = new Table();
        layout.add(makeMenuTitleLabel());
        layout.row();
        layout.add(makeResumeButton());
        layout.row();
        layout.add(makeSaveButton());
        layout.row();
        layout.add(makeOptionsButton());
        layout.row();
        layout.add(makeExitButton());

    }

    private Actor makeMenuTitleLabel() {
        return null;
    }

    private Actor makeResumeButton() {
        return null;
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
