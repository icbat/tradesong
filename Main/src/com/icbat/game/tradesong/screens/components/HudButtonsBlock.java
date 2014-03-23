package com.icbat.game.tradesong.screens.components;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.Align;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.MainMenuScreen;
import com.icbat.game.tradesong.screens.listeners.GoToScreenListener;

public class HudButtonsBlock extends Table {
    public HudButtonsBlock() {
        this.setFillParent(true);
        this.align(Align.left + Align.bottom);

        this.add(new OptionsButton());
    }

    private class OptionsButton extends Image {
        private OptionsButton() {
            super(TextureAssets.ITEMS.getRegion(0,0));
            this.addListener(new GoToScreenListener() {
                @Override
                protected void goToTargetScreen() {
                    Tradesong.screenManager.goToScreen(new MainMenuScreen());
                }
            });
        }
    }
}
