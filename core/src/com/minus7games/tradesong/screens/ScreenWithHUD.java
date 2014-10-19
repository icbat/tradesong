package com.minus7games.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.minus7games.tradesong.GameSkin;

/***/
public abstract class ScreenWithHUD extends BaseScreen {

    private Actor makeHUD() {
        Table table = new Table(GameSkin.get());
        table.setFillParent(true);
        table.align(Align.bottom + Align.left);

        table.add(new GoBackButton()).pad(5);

        return table;
    }

    protected void resetStage() {
        stage.clear();
        stage.addActor(makeHUD());
    }

    @Override
    public void resize(int width, int height) {
        resetStage();
        super.resize(width, height);
    }
}
