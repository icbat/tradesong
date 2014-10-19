package com.minus7games.tradesong.screens.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.minus7games.tradesong.GameSkin;
import com.minus7games.tradesong.ScreenManager;

/***/
public class GoBackButton extends TextButton {

    public GoBackButton() {
        super("Go Back", GameSkin.get());
        if (ScreenManager.get().canGoBack()) {
            Gdx.app.debug("go back button", "enabled");
            this.setDisabled(false);
            this.addListener(new GoBackOnClickListener());
        } else {
            Gdx.app.debug("go back button", "disabled");
            this.setDisabled(true);
        }
    }

    private class GoBackOnClickListener extends LoggedClickListener {
        public GoBackOnClickListener() {
            super("'Go Back' button");
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            ScreenManager.get().goBack();
        }
    }
}
