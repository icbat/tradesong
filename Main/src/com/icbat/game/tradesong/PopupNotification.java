package com.icbat.game.tradesong;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.icbat.game.tradesong.assetReferences.TextureAssets;

/***/
public abstract class PopupNotification extends Table {
    TextureRegionDrawable background = new TextureRegionDrawable(new TextureRegion(Tradesong.getTexture(TextureAssets.POPUP_BG)));
    public PopupNotification() {
        super(Tradesong.uiStyles);
        this.setBackground(background);
        this.add(makeTitleText()).spaceBottom(10).row();
        this.add(addContentBox());
        this.addListener(new ClickToRemoveListener(this));
    }

    protected abstract String makeTitleText();
    protected abstract Table addContentBox();

    private class ClickToRemoveListener extends ClickListener{
        private final PopupNotification owner;

        public ClickToRemoveListener(PopupNotification popupNotification) {
            owner = popupNotification;
        }

        @Override
        public void clicked(InputEvent event, float x, float y) {
            super.clicked(event, x, y);
            owner.remove();
        }
    }
}
