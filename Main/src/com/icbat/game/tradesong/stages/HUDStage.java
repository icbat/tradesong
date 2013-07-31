package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.screens.AbstractScreen;
import com.icbat.game.tradesong.screens.InventoryScreen;
import com.icbat.game.tradesong.screens.WorkshopScreen;

public class HUDStage extends Stage {
    String itemSpriteFilename = "sprites/items.png";
    int dimension = 34;
    Texture texture;
    Tradesong gameInstance;
    private TextButton capacityCounter;

    public HUDStage(Tradesong gameInstance) {
        this.gameInstance = gameInstance;
        gameInstance.assets.load(itemSpriteFilename, Texture.class);
        gameInstance.assets.finishLoading();
        texture = gameInstance.assets.get(itemSpriteFilename);

        addInventoryButton();
        addWorkshopsButton();
        addInventoryStats();

    }

    private void addInventoryButton() {
        // Add the default actors for the UI
        int x = 7;
        int y = 29;
        Image inventoryButton = new Image(new TextureRegion(texture, x*dimension, y*dimension, dimension, dimension));

        inventoryButton.setTouchable(Touchable.enabled);
        inventoryButton.addListener(new InterfaceButtonListener(InventoryScreen.class));
        // No need to set bounds; this is the bottom-left corner

        inventoryButton.setVisible(true);
        this.addActor(inventoryButton);
    }

    private void addWorkshopsButton() {
        int x = 3;
        int y = 9;


        Image workshopButton = new Image(new TextureRegion(texture, x*dimension, y*dimension, dimension, dimension));

        workshopButton.setTouchable(Touchable.enabled);
        workshopButton.addListener(new InterfaceButtonListener(WorkshopScreen.class));

        int maxX = (int)this.getWidth();
        workshopButton.setBounds(maxX - dimension, 0, dimension, dimension);

        workshopButton.setVisible(true);
        this.addActor(workshopButton);
    }

    private void addInventoryStats() {

        Integer capacity = gameInstance.gameState.getInventory().capacity();
        Integer size = gameInstance.gameState.getInventory().size();

        TextButton.TextButtonStyle textStyle = new TextButton.TextButtonStyle();
        BitmapFont font = new BitmapFont();
        if (size.equals(capacity)) {
            font.setColor(Color.RED);
        }
        else if (size.equals(capacity - 3)) {
            font.setColor(Color.ORANGE);
        }
        else {
            font.setColor(Color.WHITE);
        }

        textStyle.font = font;

        capacityCounter = new TextButton(size.toString() + " / " + capacity.toString(), textStyle) {
            @Override
            public void draw(SpriteBatch batch, float parentAlpha) {
                Integer size = gameInstance.gameState.getInventory().size();
                Integer capacity = gameInstance.gameState.getInventory().capacity();
                capacityCounter.setText(size.toString() + " / " + capacity.toString());

                super.draw(batch, parentAlpha);    //To change body of overridden methods use File | Settings | File Templates.
            }
        };


        capacityCounter.setVisible(true);
        capacityCounter.setTouchable(Touchable.disabled);
        capacityCounter.setBounds(dimension, 0, dimension, dimension);



        this.addActor(capacityCounter);
    }

    class InterfaceButtonListener extends ClickListener {
        private Class landingPage;

        InterfaceButtonListener(Class landingPage) {
            super();
            this.landingPage = landingPage;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
            super.touchDown(event, x, y, pointer, button);
            // Following error is a known bug in IDEA, not an actual problem
            if (gameInstance.getCurrentScreen().getClass().equals(landingPage)) {
                // If we're already on that screen
                gameInstance.goBack();
            }
            else {
                // Find the screen to go to based on landing
                if (landingPage.equals(InventoryScreen.class)) {
                    gameInstance.goToInventory();
                }
                else if (landingPage.equals(WorkshopScreen.class)) {
                    gameInstance.goToWorkshop();

                }
            }
            return true;
        }
    }
}
