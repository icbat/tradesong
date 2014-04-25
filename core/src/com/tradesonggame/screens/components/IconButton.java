package com.tradesonggame.screens.components;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.tradesonggame.assetReferences.TextureAssets;

/***/
public abstract class IconButton extends Image {
    protected IconButton(int x, int y) {
        super(TextureAssets.ITEMS.getRegion(x, y));
        this.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                onClick();
            }
        });
        this.addListener(new ColorOnHoverListener(this, Color.LIGHT_GRAY));
    }

    /** What happens if you click on this button? Default listener is assigned on construction. */
    abstract void onClick();
}
