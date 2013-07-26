package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;

public class InterfaceOverlay extends Stage {
    String itemSpriteFilename = "sprites/items.png";
    int dimension = 34;
    Texture texture;

    Tradesong gameInstance;
    public InterfaceOverlay(Tradesong gameInstance) {
        gameInstance.assets.load(itemSpriteFilename, Texture.class);
        gameInstance.assets.finishLoading();
        texture = gameInstance.assets.get(itemSpriteFilename);

        addInventoryButton();

    }

    private void addInventoryButton() {
        // Add the default actors for the UI
        int x = 7;
        int y = 29;
        Image inventoryButton = new Image(new TextureRegion(texture, x*dimension, y*dimension, dimension, dimension));

        inventoryButton.setTouchable(Touchable.enabled);
        inventoryButton.setVisible(true);

        inventoryButton.addListener(new InterfaceButtonListener());

        this.addActor(inventoryButton);
    }

    class InterfaceButtonListener extends ClickListener {
        //TODO way to differentiate screens that use this 'generic' one


        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            super.touchDown(event, x, y, pointer, button);
            gameInstance.goToInventory();
            return true;
        }
    }
}
