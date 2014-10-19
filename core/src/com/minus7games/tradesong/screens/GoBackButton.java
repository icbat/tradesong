package com.minus7games.tradesong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.minus7games.tradesong.GameSkin;
import com.minus7games.tradesong.ScreenManager;

/***/
public class GoBackButton extends TextButton {

    public GoBackButton() {
        super("Go Back", GameSkin.get());
        if (ScreenManager.get().canGoBack()) {
            this.setDisabled(false);
        } else {
            this.setDisabled(true);
        }
        this.addListener(new GoBackOnClickListener());
    }

    private class GoBackOnClickListener extends ClickListener {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            Gdx.app.log("Go back Button", "clicked");
            ScreenManager.get().goBack();
        }
    }
}
