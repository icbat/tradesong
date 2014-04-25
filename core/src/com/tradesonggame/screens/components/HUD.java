package com.tradesonggame.screens.components;

import com.badlogic.gdx.scenes.scene2d.Stage;

public class HUD extends Stage {

    public HUD() {
        this.addActor(new InfoBlock());
        this.addActor(new NotificationBlock());
        this.addActor(new HudButtonsBlock());
        this.addActor(new ItemDescriptionBlock());
    }
}
