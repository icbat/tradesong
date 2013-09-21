package com.icbat.game.tradesong.utils;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;

public class ScreenMovingListener extends ClickListener {
    private ScreenTypes type;
    private Tradesong gameInstance;

    public ScreenMovingListener(ScreenTypes type, Tradesong gameInstance) {
        super();
        this.type = type;
        this.gameInstance = gameInstance;
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

        gameInstance.goToOverlay(type);

        return super.touchDown(event, x, y, pointer, button);
    }
}
