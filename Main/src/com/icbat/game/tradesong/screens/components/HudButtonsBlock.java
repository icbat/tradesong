package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.RequestsScreen;
import com.icbat.game.tradesong.screens.CraftingScreen;
import com.icbat.game.tradesong.screens.LinkedChestsScreen;
import com.icbat.game.tradesong.screens.MidGameControlScreen;

public class HudButtonsBlock extends Table {
    public HudButtonsBlock() {
        this.setFillParent(true);
        this.align(Align.left + Align.bottom);

        this.add(new IconButton(13,17) {
            @Override
            void onClick() {
                Tradesong.screenManager.goToScreen(new MidGameControlScreen());
            }
        });
        this.add(new IconButton(2,30) {
            @Override
            void onClick() {
                Tradesong.screenManager.goToScreen(new LinkedChestsScreen());
            }
        });

        this.add(new IconButton(14,3) {
            @Override
            void onClick() {
                Tradesong.screenManager.goToScreen(new CraftingScreen());
            }
        });

        this.add(new IconButton(19,0) {
            @Override
            void onClick() {
                Tradesong.screenManager.goToScreen(new RequestsScreen());
            }
        });
    }
}
