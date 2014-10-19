package com.minus7games.tradesong.screens.scene2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/** Does exactly what a ClickListener does, and also logs certain events. Add more as necessary. Logs at debug level to remove clutter. */
public class LoggedClickListener extends ClickListener {
    private final String name;

    public LoggedClickListener(String name) {
        this.name = name;
        Gdx.app.debug(name, "instantiated");
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        Gdx.app.debug(name, "clicked");
        super.clicked(event, x, y);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.debug(name, "touch down");
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
        Gdx.app.debug(name, "touch up");
        super.touchUp(event, x, y, pointer, button);
    }
}
