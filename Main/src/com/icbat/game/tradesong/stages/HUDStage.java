package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.icbat.game.tradesong.Tradesong;
import com.icbat.game.tradesong.utils.ScreenTypes;
import com.icbat.game.tradesong.utils.TextureAssets;

public class HUDStage extends AbstractStage {
    public static final int SPACER = 6;
    public static final int ICON_SIZE = 34;

    /** As of now, this is pulled from items.png */
    Texture itemsTexture;

    private Tradesong gameInstance;
    private Label capacityCounter;
    private Label.LabelStyle textStyle;

    private Actor dragCatcher = new Actor();

    public HUDStage(Tradesong gameInstance) {
        this.itemsTexture = Tradesong.getTexture(TextureAssets.ITEMS);
        this.gameInstance = gameInstance;
    }

    public void layout() {
        this.clear();

        addBackButton();

        addInventoryButton();
        addCapacityCounter();

        addMoney();

        dragCatcher.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        dragCatcher.setTouchable(Touchable.enabled);
        this.addActor(dragCatcher);
        dragCatcher.setZIndex(0);
    }

    private void addBackButton() {
        Image backButton = new Image(Tradesong.getTexture(TextureAssets.MAP_ARROW));

        layoutHorizontally(backButton);
        backButton.setTouchable(Touchable.enabled);
        backButton.addListener(new BackButtonListener(gameInstance));

        this.addActor(backButton);
    }

    public Actor getDragCatcher() {
        return dragCatcher;
    }

    private void addMoney() {
        Image coin = new Image(Tradesong.getTexture(TextureAssets.COIN));

        layoutHorizontally(coin);
        this.addActor(coin);

        Label.LabelStyle style = new Label.LabelStyle();
        style.font = new BitmapFont();
        Label moneyCounter = new Label("-1", style) {
            @Override
            public void draw(SpriteBatch batch, float parentAlpha) {

                this.setText(Tradesong.gameState.getMoney()+"");

                super.draw(batch, parentAlpha);
            }
        };

        layoutHorizontally(moneyCounter);
        this.addActor(moneyCounter);
    }

    private void addInventoryButton() {
        int x = 7;
        int y = 29;
        Image inventoryButton = new Image(new TextureRegion(itemsTexture, x * ICON_SIZE, y * ICON_SIZE, ICON_SIZE, ICON_SIZE));

        inventoryButton.setTouchable(Touchable.enabled);
        inventoryButton.addListener(new InterfaceButtonListener(ScreenTypes.INVENTORY, gameInstance));
        layoutHorizontally(inventoryButton);
        this.addActor(inventoryButton);
    }

    private void addCapacityCounter() {

        Integer capacity = Tradesong.gameState.getInventory().capacity();
        Integer size = Tradesong.gameState.getInventory().size();

        textStyle = new Label.LabelStyle();

        textStyle.font = new BitmapFont();

        capacityCounter = new Label(size.toString() + " / " + capacity.toString(), textStyle) {
            @Override
            public void draw(SpriteBatch batch, float parentAlpha) {
                Integer capacity = Tradesong.gameState.getInventory().capacity();
                Integer size = Tradesong.gameState.getInventory().size();

                if (size.equals(capacity)) {
                    textStyle.font.setColor(Color.RED);
                }
                else if (size.equals(capacity - 3)) {
                    textStyle.font.setColor(Color.ORANGE);
                }
                else {
                    textStyle.font.setColor(Color.WHITE);
                }

                capacityCounter.setText(size.toString() + " / " + capacity.toString());

                super.draw(batch, parentAlpha);
            }
        };

        capacityCounter.setWidth(36);

        layoutHorizontally(capacityCounter);
        capacityCounter.layout();
        this.addActor(capacityCounter);
    }


    private void layoutHorizontally(Actor actorToLayout) {
        int furthestX = 0;
        for (Actor actor : this.getActors()) {
            if (actor.getRight() > furthestX)
                furthestX = (int)actor.getRight();
        }
        actorToLayout.setPosition(furthestX + SPACER, 0);
    }

    class InterfaceButtonListener extends ClickListener {
        private ScreenTypes type;
        private Tradesong gameInstance;

        InterfaceButtonListener(ScreenTypes type, Tradesong gameInstance) {
            super();
            this.type = type;
            this.gameInstance = gameInstance;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            gameInstance.goToOverlay(type);

            return super.touchDown(event, x, y, pointer, button);
        }
    }

    class BackButtonListener extends ClickListener {
        private Tradesong gameInstance;

        BackButtonListener(Tradesong gameInstance) {
            this.gameInstance = gameInstance;
        }

        @Override
        public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {

            gameInstance.goBack();

            return super.touchDown(event, x, y, pointer, button);
        }
    }
}
