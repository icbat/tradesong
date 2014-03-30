package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.screens.components.HudButtonsBlock;
import com.icbat.game.tradesong.screens.components.InfoBlock;
import com.icbat.game.tradesong.screens.components.ItemDescriptionBlock;
import com.icbat.game.tradesong.screens.components.NotificationBlock;

public class HUD extends Stage {

    public HUD() {
        this.addActor(new InfoBlock());
        this.addActor(new NotificationBlock());
        this.addActor(new HudButtonsBlock());
        this.addActor(new ItemDescriptionBlock());
    }
}
