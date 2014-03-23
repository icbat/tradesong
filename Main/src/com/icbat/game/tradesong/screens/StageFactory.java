package com.icbat.game.tradesong.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.icbat.game.tradesong.screens.components.HudButtonsBlock;
import com.icbat.game.tradesong.screens.components.InfoBlock;
import com.icbat.game.tradesong.screens.components.NotificationBlock;

public class StageFactory {

    public static Stage makeHUD() {
        Stage hud = new Stage();

        hud.addActor(new InfoBlock());
        hud.addActor(new HudButtonsBlock());
        hud.addActor( new NotificationBlock());

        return hud;
    }
}
