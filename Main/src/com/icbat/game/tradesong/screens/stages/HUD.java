package com.icbat.game.tradesong.screens.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.assetReferences.TextureAssets;
import com.icbat.game.tradesong.screens.InventoryScreen;
import com.icbat.game.tradesong.screens.listeners.GoToScreenListener;

/**
 * Common heads-up display for play.
 * */
public class HUD extends Stage {

    public static final int SPRITE_DIMENSION = 32;

    public HUD() {
        this.addActor(menuButton());
        this.addActor(inventoryButton());
    }

    private Image inventoryButton() {
        Texture items = Tradesong.getTexture(TextureAssets.ITEMS);
        TextureRegion region = new TextureRegion(items, 7 * SPRITE_DIMENSION, 29 * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION);
        Image inventoryButton = new Image(region);
        commonSetup(inventoryButton);
        inventoryButton.addListener(new GoToScreenListener() {
            @Override
            protected void goToTargetScreen() {
                Tradesong.screenManager.goToScreen(new InventoryScreen());
            }
        });
        return inventoryButton;
    }



    private Image menuButton() {
        Texture items = Tradesong.getTexture(TextureAssets.ITEMS);
        TextureRegion region = new TextureRegion(items, 0, 29 * SPRITE_DIMENSION, SPRITE_DIMENSION, SPRITE_DIMENSION);
        Image menuButton = new Image(region);
        commonSetup(menuButton);
        menuButton.addListener(new GoToScreenListener() {
            @Override
            protected void goToTargetScreen() {
                Tradesong.screenManager.goToMainMenu(); // curious if this is actually what I want.
            }
        });
        return menuButton;
    }

    private void commonSetup(Actor toSetup) {
        relativeLayout(toSetup);
        toSetup.setTouchable(Touchable.enabled);
    }

    private void relativeLayout(Actor actorToLayout) {
        float rightmostX = 0;

        for (Actor actor : this.getActors()) {
            float foundX = actor.getX() + actor.getWidth();
            if (foundX > rightmostX) {
                rightmostX = foundX;
            }
        }

        actorToLayout.setPosition(rightmostX, 0);
    }


}
