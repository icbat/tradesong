package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;

/**
 * Common heads-up display for play.
 * */
public class HUD extends Stage {

    public static final int SPRITE_DIMENSION = 32;

    public HUD() {
        this.addActor(menuButton());
        this.addActor(inventoryButton());

        for (Actor actor : this.getActors()) {
            relativeLayout(actor);
        }
    }

    private Image inventoryButton() {
        Texture items = Tradesong.getTexture(TextureAssets.ITEMS);
        TextureRegion region = new TextureRegion(items, 7 * SPRITE_DIMENSION, 29 * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION);
        Image inventoryButton = new Image(region);
        inventoryButton.setTouchable(Touchable.enabled);
        // inventoryButton.addListener();
        return inventoryButton;
    }

    private void relativeLayout(Actor actor) {
        actor.setPosition(0,0);
    }

    private Image menuButton() {
        Texture items = Tradesong.getTexture(TextureAssets.ITEMS);
        TextureRegion region = new TextureRegion(items, 0, 29 * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION);
        Image menuButton = new Image(region);
        menuButton.setTouchable(Touchable.enabled);
        // inventoryButton.addListener();
        return menuButton;
    }
}
