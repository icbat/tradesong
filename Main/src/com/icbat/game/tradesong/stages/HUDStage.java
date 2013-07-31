package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.InventoryScreen;

public class HUDStage extends Stage {
    String itemSpriteFilename = "sprites/items.png";
    int dimension = 34;
    Texture texture;
    Tradesong gameInstance;

    public HUDStage(Tradesong gameInstance) {
        this.gameInstance = gameInstance;
        gameInstance.assets.load(itemSpriteFilename, Texture.class);
        gameInstance.assets.finishLoading();
        texture = gameInstance.assets.get(itemSpriteFilename);

        addInventoryButton();
        addWorkshopsButton();

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

    private void addWorkshopsButton() {
        int x = 3;
        int y = 9;


        Image workshopButton = new Image(new TextureRegion(texture, x*dimension, y*dimension, dimension, dimension));

        workshopButton.setTouchable(Touchable.enabled);
        workshopButton.setVisible(true);

        workshopButton.addListener(new InterfaceButtonListener());

        int maxX = (int)this.getWidth();

        workshopButton.setBounds(maxX - dimension, 0, dimension, dimension);

        this.addActor(workshopButton);
    }

    class InterfaceButtonListener extends ClickListener {
        //TODO way to differentiate screens that use this 'generic' one


        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            super.touchDown(event, x, y, pointer, button);
            gameInstance.log.info("I've been clicked!");
            // Following error is a known bug in IDEA, not an actual problem
            if (gameInstance.getCurrentScreen().getClass().equals(InventoryScreen.class)) {
                gameInstance.goBack();
            }
            else {
                gameInstance.goToInventory();
            }
            return true;
        }
    }
}
