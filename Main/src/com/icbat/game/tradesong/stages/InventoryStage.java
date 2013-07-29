package com.icbat.game.tradesong.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.icbat.game.tradesong.Inventory;
import com.icbat.game.tradesong.Item;
import com.icbat.game.tradesong.StackedItem;
import com.icbat.game.tradesong.Tradesong;

import static com.badlogic.gdx.math.MathUtils.floor;

// TODO make them clickable/draggable
// TODO show stack count

public class InventoryStage extends Stage {

    public static final int SIZE = 34;
    public static final int SLOT_SIZE = 40;
    public static final String SPRITES_FRAME_PNG = "sprites/frame.png";
    private BitmapFont font;

    public InventoryStage(Tradesong gameInstance) {
        Inventory inventory = gameInstance.gameState.getInventory();

        // Initialize assets
        font = new BitmapFont();
        gameInstance.assets.load(SPRITES_FRAME_PNG, Texture.class);
        gameInstance.assets.finishLoading();

        Texture frame = gameInstance.assets.get(SPRITES_FRAME_PNG);

        // Slot frames
        for (int i = 0; i < inventory.capacity(); ++i) {
            addSlotFrame(frame, i);
        }

        // Add items
        for (int i = 0; i < inventory.capacity(); ++i) {
            gameInstance.log.info("## Checking");
            if (i < inventory.size()) {
                gameInstance.log.info("## Adding an item");
                addStackedItemToStage(inventory.getStack(i), i);
                addItemCount(inventory.getStack(i), i);
            }
        }

    }

    private void addStackedItemToStage(StackedItem stack, int i) {

        Item item = stack.getBaseItem(); // Item is an Actor and meant to be representative, just like this

        int[] position = positionToXY(i);
        item.setBounds(position[0],position[1], SIZE, SIZE);
        item.setVisible(true);
        item.setTouchable(Touchable.enabled);
        this.addActor(item);
    }

    private void addItemCount(StackedItem stack, int i) {
        Integer stackSize = stack.getCount();
        int[] position = positionToXY(i);

        TextButton.TextButtonStyle buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = font;

        TextButton text = new TextButton(stackSize.toString(), buttonStyle);
        text.setTouchable(Touchable.disabled);
        text.setVisible(true);
        text.setBounds(position[0], position[1], SIZE, SIZE);
        this.addActor(text);


    }

    private void addSlotFrame(Texture frame, int i) {
        int[] position = positionToXY(i);


        Image frameActor = new Image(new TextureRegion(frame));


        frameActor.setBounds(position[0],position[1], SIZE, SIZE);
        frameActor.setVisible(true);
        frameActor.setTouchable(Touchable.disabled);
        this.addActor(frameActor);




    }

    /** Utility to take a list slot and take it to a 2d coordinate */
    private int[] positionToXY(int position) {
        //boundary math
        int x;
        int y;

        x = position % 5; // 5 columns
        y = floor(position / 5);

        x *= SLOT_SIZE;
        y *= SLOT_SIZE;

        //TODO I'd rather do something based on height/width but this is fine for now
        x += 100;
        y += 100;

        int[] out = new int[2];
        out[0] = x;
        out[1] = y;

        return out;
    }


}
