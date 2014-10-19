package com.minus7games.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.minus7games.tradesong.GameSkin;
import com.minus7games.tradesong.screens.scene2d.GoBackButton;

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
        Gdx.app.debug("screen w/ hud", "resetting stage");
        clearStep();
        afterClearStep();
    }

    protected void afterClearStep() {
        Gdx.app.debug("screen w/ hud", "after clear step");
        stage.setDebugAll(true);
        stage.addActor(makeHUD());
    }

    protected void clearStep() {
        Gdx.app.debug("screen w/ hud", "clearing");
        stage.clear();
    }

    @Override
    public void resize(int width, int height) {
        resetStage();
        super.resize(width, height);
    }

}
